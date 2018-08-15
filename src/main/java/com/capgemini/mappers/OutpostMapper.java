package com.capgemini.mappers;

import com.capgemini.domain.AddressEnity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OutpostEntity;
import com.capgemini.domain.RentalEntity;
import com.capgemini.types.AddressTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OutpostTO;
import com.capgemini.types.RentalTO;

import java.util.Set;
import java.util.stream.Collectors;

public class OutpostMapper {
    public static OutpostTO toTO(OutpostEntity outpost) {
        if (outpost == null) {
            return null;
        }
        AddressTO address = AddressMapper.toTo(outpost.getAddress());
        Set<EmployeeTO> employees = EmployeeMapper.map2TOs(outpost.getEmployees());
        Set<RentalTO> startRentals = RentalMapper.map2TOs(outpost.getStartRentals());
        Set<RentalTO> endRentals = RentalMapper.map2TOs(outpost.getEndRentals());

        return new OutpostTO.Builder().withAddress(address).withContactData(outpost.getContactData()).withEmployees(employees)
                .withEndRentals(endRentals).withStartRentals(startRentals).withId(outpost.getId()).build();
    }

    public static OutpostEntity toEntity(OutpostTO outpost) {
        if (outpost == null) {
            return null;
        }
        AddressEnity address = AddressMapper.toEntity(outpost.getAddress());
        Set<EmployeeEntity> employees = EmployeeMapper.map2Entities(outpost.getEmployees());
        Set<RentalEntity> startRentals = RentalMapper.map2Entities(outpost.getStartRentals());
        Set<RentalEntity> endRentals = RentalMapper.map2Entities(outpost.getEndRentals());

        return new OutpostEntity.Builder().withAddress(address).withContactData(outpost.getContactData()).withEmployees(employees)
                .withEndRentals(endRentals).withStartRentals(startRentals).withId(outpost.getId()).build();
    }

    public static Set<OutpostTO> map2TOs (Set<OutpostEntity> outposts) {
        return outposts.stream().map(OutpostMapper::toTO).collect(Collectors.toSet());
    }

    public static Set<OutpostEntity> map2Entities (Set<OutpostTO> outposts) {
        return outposts.stream().map(OutpostMapper::toEntity).collect(Collectors.toSet());
    }
}
