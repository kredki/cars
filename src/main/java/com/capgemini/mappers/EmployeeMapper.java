package com.capgemini.mappers;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OutpostEntity;
import com.capgemini.domain.PositionEntity;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OutpostTO;
import com.capgemini.types.PositionTO;

import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeMapper {
    public static EmployeeTO toTO(EmployeeEntity employee) {
        if (employee == null) {
            return null;
        }
        OutpostTO outpostTO = OutpostMapper.toTo(employee.getOutpost());
        PositionTO positionTO = PositionMapper.toTo(employee.getPosition());
        Set<CarTO> carTOs = CarMapper.map2TOs(employee.getCars());

        return new EmployeeTO.Builder().withBirthDate(employee.getBirthDate()).withCars(carTOs)
                .withFirstName(employee.getFirstName()).withId(employee.getId()).withLastName(employee.getLastName())
                .withOutpost(outpostTO).withPosition(positionTO).build();
    }

    public static EmployeeEntity toEntity(EmployeeTO employee) {
        if (employee == null) {
            return null;
        }
        OutpostEntity outpostEntity = OutpostMapper.toEntity(employee.getOutpost());
        PositionEntity positionEntity = PositionMapper.toEntity(employee.getPosition());
        Set<CarEntity> carEntities = CarMapper.map2Entities(employee.getCars());

        return new EmployeeEntity.Builder().withBirthDate(employee.getBirthDate()).withCars(carEntities)
                .withFirstName(employee.getFirstName()).withId(employee.getId()).withLastName(employee.getLastName())
                .withOutpost(outpostEntity).withPosition(positionEntity).build();
    }

    public static Set<EmployeeTO> map2TOs (Set<EmployeeEntity> employees) {
        return employees.stream().map(EmployeeMapper::toTO).collect(Collectors.toSet());
    }

    public static Set<EmployeeEntity> map2Entities (Set<EmployeeTO> employees) {
        return employees.stream().map(EmployeeMapper::toEntity).collect(Collectors.toSet());
    }
}
