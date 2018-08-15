package com.capgemini.mappers;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.RentalEntity;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.RentalTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CarMapper {
    public static CarTO toTO(CarEntity car) {
        if (car == null) {
            return null;
        }
        Set<RentalTO> rentalTOs = RentalMapper.map2TOs(car.getRentals());
        Set<EmployeeTO> employeeTOs = EmployeeMapper.map2TOs(car.getEmployees());
        return new CarTO.Builder().withBrandName(car.getBrandName()).withCarType(car.getCarType())
                .withColor(car.getColor()).withEmployees(employeeTOs).withEngineCapacity(car.getEngineCapacity())
                .withId(car.getId()).withMileage(car.getMileage()).withProductionYear(car.getProductionYear())
                .withRentals(rentalTOs).build();
    }

    public static CarEntity toEntity(CarTO car) {
        if (car == null) {
            return null;
        }
        Set<RentalEntity> rentalEntities = RentalMapper.map2Entities(car.getRentals());
        Set<EmployeeEntity> employeeEntities = EmployeeMapper.map2Entities(car.getEmployees());
        return new CarEntity.CarEntityBuilder().withBrandName(car.getBrandName()).withCarType(car.getCarType())
                .withColor(car.getColor()).withEmployees(employeeEntities).withEngineCapacity(car.getEngineCapacity())
                .withId(car.getId()).withMileage(car.getMileage()).withProductionYear(car.getProductionYear())
                .withRentals(rentalEntities).build();
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
