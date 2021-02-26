package nl.eindopdracht.bootcamp.service;

import nl.eindopdracht.bootcamp.model.Address;
import nl.eindopdracht.bootcamp.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImp implements AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public void setAddressRepository(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public long saveAddress(Address address) {
        Address newAddress = addressRepository.save(address);
        return newAddress.getId();
    }

}

