package com.capgemini.mappers;

import com.capgemini.domain.AddressEnity;
import com.capgemini.types.AddressTO;

public class AddressMapper {
    public static AddressTO toTo(AddressEnity address) {
        if (address == null) {
            return null;
        }

        return new AddressTO.Builder().withCity(address.getCity()).withNo(address.getNo())
                .withPostalCode(address.getPostalCode()).withStreet(address.getStreet()).build();
    }

    public static AddressEnity toEntity(AddressTO address) {
        if (address == null) {
            return null;
        }

        return new AddressEnity.Builder().withCity(address.getCity()).withNo(address.getNo())
                .withPostalCode(address.getPostalCode()).withStreet(address.getStreet()).build();
    }
}
