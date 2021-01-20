package nl.eindopdracht.bootcamp.service;

import nl.eindopdracht.bootcamp.exeption.DatabaseErrorException;
import nl.eindopdracht.bootcamp.exeption.RecordNotFoundException;
import nl.eindopdracht.bootcamp.model.Address;
import nl.eindopdracht.bootcamp.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImp implements AddressService {

    @Autowired
    private AddressRepository addressRepository; //wel of geen private ervoor?

    public void setAddressRepository(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Address getAddressById(long id) {
        if (addressRepository.existsById(id)) {
            return addressRepository.findById(id).orElse(null);
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public long saveAddress(Address address) {
        Address newAddress = addressRepository.save(address);
        return newAddress.getAddressId();
    }

    @Override
    public void updateAddress(long id, Address address) {
        if (addressRepository.existsById(id)) {
            try {
                Address existingAddress = addressRepository.findById(id).orElse(null);
                existingAddress.setStreetName(address.getStreetName());
                existingAddress.setHouseNumber(address.getHouseNumber());
                existingAddress.setPostalCode(address.getPostalCode());
                addressRepository.save(existingAddress);
            }
            catch (Exception ex) {
                throw new DatabaseErrorException();
            }
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void deleteAddress(long id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException();
        }
    }
}

