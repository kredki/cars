package com.capgemini.mappers;

import com.capgemini.domain.CarEntity;
import com.capgemini.types.CarTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper for car
 */
public class CarMapper {
    /**
     * Map entity to TO.
     * @param car Object to map.
     * @return Mapped object.
     */
    public static CarTO toTO(CarEntity car) {
        if (car == null) {
            return null;
        }
        return new CarTO.Builder().withBrandName(car.getBrandName()).withCarType(car.getCarType())
                .withColor(car.getColor()).withEngineCapacity(car.getEngineCapacity()).withPower(car.getPower())
                .withId(car.getId()).withMileage(car.getMileage()).withProductionYear(car.getProductionYear()).build();
    }

    /**
     * Map TO to entity.
     * @param car Object to map.
     * @return Mapped object.
     */
    public static CarEntity toEntity(CarTO car) {
        if (car == null) {
            return null;
        }

        return new CarEntity.CarEntityBuilder().withBrandName(car.getBrandName()).withCarType(car.getCarType())
                .withColor(car.getColor()).withEngineCapacity(car.getEngineCapacity()).withPower(car.getPower())
                .withId(car.getId()).withMileage(car.getMileage()).withProductionYear(car.getProductionYear()).build();
    }

    /**
     * Map set of entities to set of TOs.
     * @param cars Objects to map.
     * @return Mapped objects.
     */
    public static Set<CarTO> map2TOs (Set<CarEntity> cars) {
        return cars.stream().map(CarMapper::toTO).collect(Collectors.toSet());
    }

    /**
     * Map set of TOs to set of entities.
     * @param cars Objects to map.
     * @return Mapped objects.
     */
    public static Set<CarEntity> map2Entities (Set<CarTO> cars) {
        return cars.stream().map(CarMapper::toEntity).collect(Collectors.toSet());
    }

    /**
     * Map list of entities to list of TOs.
     * @param cars Objects to map.
     * @return Mapped objects.
     */
    public static List<CarTO> map2TOs (List<CarEntity> cars) {
        return cars.stream().map(CarMapper::toTO).collect(Collectors.toList());
    }

    /**
     * Map list of TOs to list of entities.
     * @param cars Objects to map.
     * @return Mapped objects.
     */
    public static List<CarEntity> map2Entities (List<CarTO> cars) {
        return cars.stream().map(CarMapper::toEntity).collect(Collectors.toList());
    }
}
