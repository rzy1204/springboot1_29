package com.rzy.service;

import com.rzy.model.Address;

import java.util.List;

public interface AddressService {

    public void add(Address address);

    public void delete(Integer id);

    public void updateAddress(Address address);

    public List<Address> getAllAddresses();

    public Address get(Integer id);
}
