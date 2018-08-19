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
     *
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
     *
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
     *
     * @param rentals Objects to map.
     * @return Mapped objects.
     */
    public static Set<RentalTO> map2TOs (Set<RentalEntity> rentals) {
        return rentals.stream().map(RentalMapper::toTO).collect(Collectors.toSet());
    }

    /**
     *
     * @param rentals Objects to map.
     * @return Mapped objects.
     */
    public static Set<RentalEntity> map2Entities (Set<RentalTO> rentals) {
        return rentals.stream().map(RentalMapper::toEntity).collect(Collectors.toSet());
    }
}
