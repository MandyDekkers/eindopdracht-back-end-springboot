package nl.eindopdracht.bootcamp.service;

import nl.eindopdracht.bootcamp.model.AppUser;
import nl.eindopdracht.bootcamp.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserService appUserService;

    //    @Autowired
//    private AuthorityService authorityService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<AppUser> appUser = appUserService.getAppUser(username);
        if (appUser == null) {
            throw new UsernameNotFoundException(username);
        }

        String password = appUser.get().getPassword();

        Set<Authority> authorities = appUser.get().getAuthorities();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority: authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }

        return new org.springframework.security.core.userdetails.User(username, password, grantedAuthorities);
    }

}
