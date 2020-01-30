package com.rzy.model;

import javax.persistence.*;

@Entity
@Table(name = "t_address")
public class Address {

    private int id;
    private String addressInfo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "address_info")
    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }
}
