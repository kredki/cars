package com.capgemini.mappers;

import com.capgemini.domain.AddressInTable;
import com.capgemini.types.AddressTO;

/**
 * Mapper for address
 */
public class AddressMapper {
    /**
     *
     * @param address Object to map.
     * @return Mapped object.
     */
    public static AddressTO toTo(AddressInTable address) {
        if (address == null) {
            return null;
        }

        return new AddressTO.Builder().withCity(address.getCity()).withNo(address.getNo())
                .withPostalCode(address.getPostalCode()).withStreet(address.getStreet()).build();
    }

    /**
     *
     * @param address Object to map.
     * @return Mapped object.
     */
    public static AddressInTable toEntity(AddressTO address) {
        if (address == null) {
            return null;
        }

        return new AddressInTable.Builder().withCity(address.getCity()).withNo(address.getNo())
                .withPostalCode(address.getPostalCode()).withStreet(address.getStreet()).build();
    }
}
