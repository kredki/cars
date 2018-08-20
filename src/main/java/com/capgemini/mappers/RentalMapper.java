package com.capgemini.mappers;

import com.capgemini.domain.RentalEntity;
import com.capgemini.types.RentalTO;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper for rental
 */
public class RentalMapper {
    /**
     * Map entity to TO.
     * @param rental Object to map.
     * @return Mapped object.
     */
    public static RentalTO toTO(RentalEntity rental) {
        if (rental == null) {
            return null;
        }

        return new RentalTO.Builder().withCost(rental.getCost()).withEndDate(rental.getEndDate())
                .withId(rental.getId()).withStartDate(rental.getStartDate()).build();
    }

    /**
     * Map TO to entity.
     * @param rental Object to map.
     * @return Mapped object.
     */
    public static RentalEntity toEntity(RentalTO rental) {
        if (rental == null) {
            return null;
        }

        return new RentalEntity.Builder().withCost(rental.getCost()).withEndDate(rental.getEndDate())
                .withId(rental.getId()).withStartDate(rental.getStartDate()).build();
    }

    /**
     * Map set of entities to set of TOs.
     * @param rentals Objects to map.
     * @return Mapped objects.
     */
    public static Set<RentalTO> map2TOs (Set<RentalEntity> rentals) {
        return rentals.stream().map(RentalMapper::toTO).collect(Collectors.toSet());
    }

    /**
     * Map set of TOs to set of entities.
     * @param rentals Objects to map.
     * @return Mapped objects.
     */
    public static Set<RentalEntity> map2Entities (Set<RentalTO> rentals) {
        return rentals.stream().map(RentalMapper::toEntity).collect(Collectors.toSet());
    }
}
