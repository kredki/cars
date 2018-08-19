package com.capgemini.mappers;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.EmployeeTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper for employee
 */
public class EmployeeMapper {
    /**
     *
     * @param employee Object to map.
     * @return Mapped object.
     */
    public static EmployeeTO toTO(EmployeeEntity employee) {
        if (employee == null) {
            return null;
        }

        return new EmployeeTO.Builder().withBirthDate(employee.getBirthDate()).withFirstName(employee.getFirstName())
                .withId(employee.getId()).withLastName(employee.getLastName()).build();
    }

    /**
     *
     * @param employee Object to map.
     * @return Mapped object.
     */
    public static EmployeeEntity toEntity(EmployeeTO employee) {
        if (employee == null) {
            return null;
        }

        return new EmployeeEntity.Builder().withBirthDate(employee.getBirthDate()).withFirstName(employee.getFirstName())
                .withId(employee.getId()).withLastName(employee.getLastName()).build();
    }

    /**
     *
     * @param employees Objects to map.
     * @return Mapped objects.
     */
    public static Set<EmployeeTO> map2TOs (Set<EmployeeEntity> employees) {
        return employees.stream().map(EmployeeMapper::toTO).collect(Collectors.toSet());
    }

    /**
     *
     * @param employees Objects to map.
     * @return Mapped objects.
     */
    public static Set<EmployeeEntity> map2Entities (Set<EmployeeTO> employees) {
        return employees.stream().map(EmployeeMapper::toEntity).collect(Collectors.toSet());
    }

    /**
     *
     * @param employees Objects to map.
     * @return Mapped objects.
     */
    public static List<EmployeeTO> map2TOs (List<EmployeeEntity> employees) {
        return employees.stream().map(EmployeeMapper::toTO).collect(Collectors.toList());
    }

    /**
     *
     * @param employees Objects to map.
     * @return Mapped objects.
     */
    public static List<EmployeeEntity> map2Entities (List<EmployeeTO> employees) {
        return employees.stream().map(EmployeeMapper::toEntity).collect(Collectors.toList());
    }
}
