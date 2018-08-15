package com.capgemini.mappers;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.PositionEntity;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.PositionTO;

import java.util.Set;
import java.util.stream.Collectors;

public class PositionMapper {
    public static PositionTO toTO(PositionEntity position) {
        if (position == null) {
            return null;
        }

        Set<EmployeeTO> employees = EmployeeMapper.map2TOs(position.getEmployees());

        return new PositionTO.Builder().withEmployees(employees).withId(position.getId()).withName(position.getName())
                .build();
    }

    public static PositionEntity toEntity(PositionTO position) {
        if (position == null) {
            return null;
        }

        Set<EmployeeEntity> employees = EmployeeMapper.map2Entities(position.getEmployees());

        return new PositionEntity.Builder().withEmployees(employees).withId(position.getId()).withName(position.getName())
                .build();
    }

    public static Set<PositionTO> map2TOs (Set<PositionEntity> positions) {
        return positions.stream().map(PositionMapper::toTO).collect(Collectors.toSet());
    }

    public static Set<PositionEntity> map2Entities (Set<PositionTO> positions) {
        return positions.stream().map(PositionMapper::toEntity).collect(Collectors.toSet());
    }
}
