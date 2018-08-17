package com.capgemini.mappers;

import com.capgemini.domain.AddressInTable;
import com.capgemini.types.AddressTO;

public class AddressMapper {
    public static AddressTO toTo(AddressInTable address) {
        if (address == null) {
            return null;
        }

        return new AddressTO.Builder().withCity(address.getCity()).withNo(address.getNo())
                .withPostalCode(address.getPostalCode()).withStreet(address.getStreet()).build();
    }

    public static AddressInTable toEntity(AddressTO address) {
        if (address == null) {
            return null;
        }

        return new AddressInTable.Builder().withCity(address.getCity()).withNo(address.getNo())
                .withPostalCode(address.getPostalCode()).withStreet(address.getStreet()).build();
    }
}
