package nl.eindopdracht.bootcamp.service;

import nl.eindopdracht.bootcamp.jwt.JwtUtils;
import nl.eindopdracht.bootcamp.model.Address;
import nl.eindopdracht.bootcamp.model.AppUser;
import nl.eindopdracht.bootcamp.model.AppUserBuilder;
import nl.eindopdracht.bootcamp.model.ERole;
import nl.eindopdracht.bootcamp.model.Role;
import nl.eindopdracht.bootcamp.payload.request.LoginRequest;
import nl.eindopdracht.bootcamp.payload.request.SignupRequest;
import nl.eindopdracht.bootcamp.payload.response.JwtResponse;
import nl.eindopdracht.bootcamp.payload.response.MessageResponse;
import nl.eindopdracht.bootcamp.repository.AddressRepository;
import nl.eindopdracht.bootcamp.repository.AppUserRepository;
import nl.eindopdracht.bootcamp.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
public class AuthorizationService {

    private static final String ROLE_NOT_FOUND_ERROR = "Error: Role is not found.";

    private AppUserRepository appUserRepository;
    private PasswordEncoder encoder;
    private RoleRepository roleRepository;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    AddressRepository addressRepository;

    @Autowired
    public void setAddressRepository(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Autowired
    public void setUserRepository(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Autowired
    public void setEncoder(PasswordEncoder passwordEncoder) {
        this.encoder = passwordEncoder;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtUtils(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    public ResponseEntity<MessageResponse> registerUser(@Valid SignupRequest signUpRequest) {

        if (Boolean.TRUE.equals(!signUpRequest.getPassword().equals(signUpRequest.getRepeatedPassword()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Passwords are not the same!"));
        }

        if (Boolean.TRUE.equals(appUserRepository.existsByUsername(signUpRequest.getUsername()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (Boolean.TRUE.equals(appUserRepository.existsByEmail(signUpRequest.getEmail()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

            AppUser appUser = new AppUserBuilder(signUpRequest).buildAppUser();
            Address address = new AppUserBuilder(signUpRequest).buildAddress();

            Address savedAddress = addressRepository.save(address);
            appUser.setAddress(savedAddress);
            appUser.setPassword(encoder.encode(signUpRequest.getPassword()));
            address.setAppuser(appUser);

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_ERROR));
            roles.add(userRole);
        }

        appUser.setRoles(roles);
        appUserRepository.save(appUser);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    public ResponseEntity<JwtResponse> authenticateUser(@Valid LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

}
