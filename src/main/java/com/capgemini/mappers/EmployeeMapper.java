package com.capgemini.mappers;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.EmployeeTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeMapper {
    public static EmployeeTO toTO(EmployeeEntity employee) {
        if (employee == null) {
            return null;
        }

        return new EmployeeTO.Builder().withBirthDate(employee.getBirthDate()).withFirstName(employee.getFirstName())
                .withId(employee.getId()).withLastName(employee.getLastName()).build();
    }

    public static EmployeeEntity toEntity(EmployeeTO employee) {
        if (employee == null) {
            return null;
        }

        return new EmployeeEntity.Builder().withBirthDate(employee.getBirthDate()).withFirstName(employee.getFirstName())
                .withId(employee.getId()).withLastName(employee.getLastName()).build();
    }

    public static Set<EmployeeTO> map2TOs (Set<EmployeeEntity> employees) {
        return employees.stream().map(EmployeeMapper::toTO).collect(Collectors.toSet());
    }

    public static Set<EmployeeEntity> map2Entities (Set<EmployeeTO> employees) {
        return employees.stream().map(EmployeeMapper::toEntity).collect(Collectors.toSet());
    }

    public static List<EmployeeTO> map2TOs (List<EmployeeEntity> employees) {
        return employees.stream().map(EmployeeMapper::toTO).collect(Collectors.toList());
    }

    public static List<EmployeeEntity> map2Entities (List<EmployeeTO> employees) {
        return employees.stream().map(EmployeeMapper::toEntity).collect(Collectors.toList());
    }
}
