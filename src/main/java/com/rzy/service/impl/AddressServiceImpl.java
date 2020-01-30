package com.rzy.service.impl;

import com.rzy.dao.AddressDao;
import com.rzy.model.Address;
import com.rzy.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AddressService")
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressDao addressDao;

    @Override
    public void add(Address address) {
        addressDao.save(address);
    }

    @Override
    public void delete(Integer id) {
        addressDao.deleteById(id);
    }

    @Override
    public void updateAddress(Address address) {
        addressDao.saveAndFlush(address);
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressDao.findAll();
    }

    @Override
    public Address get(Integer id) {
        return addressDao.getOne(id);
    }
}
