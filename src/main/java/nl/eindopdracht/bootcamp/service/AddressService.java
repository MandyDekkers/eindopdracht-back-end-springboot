package nl.eindopdracht.bootcamp.service;

import nl.eindopdracht.bootcamp.model.Address;

import java.util.List;

public interface AddressService {

    List<Address>getAllAddresses();
    Address getAddressById(long id);
    long saveAddress(Address address);
    void updateAddress(long id, Address address);
    void deleteAddress(long id);
}
