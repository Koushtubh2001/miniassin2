package com.example.demo.Services;

import com.example.demo.Models.Address;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;


    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    public Address addAddress(Address newAddress) {
        return addressRepository.save(newAddress);
    }

    @Transactional
    public Optional<Address> updateAddress(Long id, Address updatedAddress) {
        return addressRepository.findById(id)
                .map(address -> {
                    address.setStreet(updatedAddress.getStreet());
                    address.setCity(updatedAddress.getCity());
                    address.setState(updatedAddress.getState());
                    address.setZipCode(updatedAddress.getZipCode());
                    address.setCountry(updatedAddress.getCountry());
                    address.setCurrent(updatedAddress.isCurrent());
                    return addressRepository.save(address);
                });
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}