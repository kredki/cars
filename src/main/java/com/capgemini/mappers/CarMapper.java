package com.capgemini.mappers;

import com.capgemini.domain.CarEntity;
import com.capgemini.types.CarTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CarMapper {
    public static CarTO toTO(CarEntity car) {
        if (car == null) {
            return null;
        }
        return new CarTO.Builder().withBrandName(car.getBrandName()).withCarType(car.getCarType())
                .withColor(car.getColor()).withEngineCapacity(car.getEngineCapacity()).withPower(car.getPower())
                .withId(car.getId()).withMileage(car.getMileage()).withProductionYear(car.getProductionYear()).build();
    }

    public static CarEntity toEntity(CarTO car) {
        if (car == null) {
            return null;
        }

        return new CarEntity.CarEntityBuilder().withBrandName(car.getBrandName()).withCarType(car.getCarType())
                .withColor(car.getColor()).withEngineCapacity(car.getEngineCapacity()).withPower(car.getPower())
                .withId(car.getId()).withMileage(car.getMileage()).withProductionYear(car.getProductionYear()).build();
    }

    public static Set<CarTO> map2TOs (Set<CarEntity> cars) {
        return cars.stream().map(CarMapper::toTO).collect(Collectors.toSet());
    }

    public static Set<CarEntity> map2Entities (Set<CarTO> cars) {
        return cars.stream().map(CarMapper::toEntity).collect(Collectors.toSet());
    }

    public static List<CarTO> map2TOs (List<CarEntity> cars) {
        return cars.stream().map(CarMapper::toTO).collect(Collectors.toList());
    }

    public static List<CarEntity> map2Entities (List<CarTO> cars) {
        return cars.stream().map(CarMapper::toEntity).collect(Collectors.toList());
    }
}
