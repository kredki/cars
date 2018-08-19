package com.capgemini.mappers;

import com.capgemini.domain.AddressInTable;
import com.capgemini.domain.OutpostEntity;
import com.capgemini.types.AddressTO;
import com.capgemini.types.OutpostTO;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper for outpost
 */
public class OutpostMapper {
    /**
     *
     * @param outpost Object to map.
     * @return Mapped object.
     */
    public static OutpostTO toTO(OutpostEntity outpost) {
        if (outpost == null) {
            return null;
        }
        AddressTO address = AddressMapper.toTo(outpost.getAddress());

        return new OutpostTO.Builder().withAddress(address).withContactData(outpost.getContactData())
                .withId(outpost.getId()).build();
    }

    /**
     *
     * @param outpost Object to map.
     * @return Mapped object.
     */
    public static OutpostEntity toEntity(OutpostTO outpost) {
        if (outpost == null) {
            return null;
        }
        AddressInTable address = AddressMapper.toEntity(outpost.getAddress());

        return new OutpostEntity.Builder().withAddress(address).withContactData(outpost.getContactData())
                .withId(outpost.getId()).build();
    }

    /**
     *
     * @param outposts Objects to map.
     * @return Mapped objects.
     */
    public static Set<OutpostTO> map2TOs (Set<OutpostEntity> outposts) {
        return outposts.stream().map(OutpostMapper::toTO).collect(Collectors.toSet());
    }

    /**
     *
     * @param outposts Objects to map.
     * @return Mapped objects.
     */
    public static Set<OutpostEntity> map2Entities (Set<OutpostTO> outposts) {
        return outposts.stream().map(OutpostMapper::toEntity).collect(Collectors.toSet());
    }
}
