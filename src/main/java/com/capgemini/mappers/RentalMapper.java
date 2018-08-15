package com.capgemini.mappers;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.OutpostEntity;
import com.capgemini.domain.RentalEntity;
import com.capgemini.types.CarTO;
import com.capgemini.types.ClientTO;
import com.capgemini.types.OutpostTO;
import com.capgemini.types.RentalTO;

import java.util.Set;
import java.util.stream.Collectors;

public class RentalMapper {
    public static RentalTO toTO(RentalEntity rental) {
        if (rental == null) {
            return null;
        }

        CarTO car = CarMapper.toTO(rental.getCar());
        ClientTO client = ClientMapper.toTO(rental.getClient());
        OutpostTO startOutpost = OutpostMapper.toTO(rental.getStartOutpost());
        OutpostTO endOutpost = OutpostMapper.toTO(rental.getEndOutpost());

        return new RentalTO.Builder().withBrandName(rental.getBrandName()).withCar(car).withClient(client)
                .withCost(rental.getCost()).withEndDate(rental.getEndDate()).withEndOutpost(endOutpost)
                .withId(rental.getId()).withStartDate(rental.getStartDate()).withStartOutpost(startOutpost).build();
    }

    public static RentalEntity toEntity(RentalTO rental) {
        if (rental == null) {
            return null;
        }

        CarEntity car = CarMapper.toEntity(rental.getCar());
        ClientEntity client = ClientMapper.toEntity(rental.getClient());
        OutpostEntity startOutpost = OutpostMapper.toEntity(rental.getStartOutpost());
        OutpostEntity endOutpost = OutpostMapper.toEntity(rental.getEndOutpost());

        return new RentalEntity.Builder().withBrandName(rental.getBrandName()).withCar(car).withClient(client)
                .withCost(rental.getCost()).withEndDate(rental.getEndDate()).withEndOutpost(endOutpost)
                .withId(rental.getId()).withStartDate(rental.getStartDate()).withStartOutpost(startOutpost).build();
    }

    public static Set<RentalTO> map2TOs (Set<RentalEntity> rentals) {
        return rentals.stream().map(RentalMapper::toTO).collect(Collectors.toSet());
    }

    public static Set<RentalEntity> map2Entities (Set<RentalTO> rentals) {
        return rentals.stream().map(RentalMapper::toEntity).collect(Collectors.toSet());
    }
}
