package nl.eindopdracht.bootcamp.service;

import nl.eindopdracht.bootcamp.model.Address;
import nl.eindopdracht.bootcamp.model.Lesson;
import nl.eindopdracht.bootcamp.repository.AddressRepository;
import nl.eindopdracht.bootcamp.repository.LessonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class AddressServiceTest {

    @InjectMocks
    AddressServiceImp addressServiceImp;

    @Mock
    AddressRepository addressRepository;

    @Mock
    Address address;

    @Test
    public void testSaveAddress() {
        Address address = new Address();
        address.setStreetName("street");
        address.setHouseNumber("55");
        address.setPostalCode("1515oo");
        address.setCity("City");
        address.setId(1);

        Mockito
                .when(addressRepository.save(address))
                .thenReturn(address);

        addressServiceImp.saveAddress(address);
    }
}
