package com.capgemini.service.impl;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.OutpostDao;
import com.capgemini.dao.PositioDao;
import com.capgemini.domain.*;
import com.capgemini.types.EmployeeTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
@Transactional
public class EmployeeServiceImplTest {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private EmployeeServiceImpl employeeService;
    @Autowired
    private OutpostDao outpostDao;
    @Autowired
    private CarDao carDao;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private PositioDao positioDao;

    private OutpostEntity outpost;
    private EmployeeEntity employee1;
    private EmployeeEntity employee2;
    private PositionEntity position;
    private CarEntity car;

    @Before
    public void setup() {
        AddressInTable address = new AddressInTable.Builder().withStreet("a").withPostalCode("b").withNo("c").withCity("d")
                .build();
        outpost = new OutpostEntity.Builder().withAddress(address).withContactData("data").build();
        outpost = outpostDao.save(outpost);
        position = new PositionEntity("Sprzedawca");
        employee1 = new EmployeeEntity("Jan", "Kowalski", new Date(), outpost, position);
        employee2 = new EmployeeEntity("Andrzej", "Nowak", new Date(), null, position);
        car = new CarEntity.CarEntityBuilder().withBrandName("BMW").withCarType("Coupe")
                .withEmployee(employee1)
                .withEngineCapacity(2000).withMileage(20000).withPower(120).withProductionYear(2015).withColor("blue")
                .withRentals(new HashSet<RentalEntity>()).build();
        car = carDao.save(car);
        positioDao.save(position);
        employee1.addCar(car);
        employee1 = employeeDao.save(employee1);
        employee2 = employeeDao.save(employee2);
    }

    @Test
    public void shouldFindEmployeeByOutpost() {
        //when
        List<EmployeeTO> result = employeeService.findEmployeeByOutpost(outpost.getId());

        //then
        assertEquals(1, result.size());
        EmployeeTO employee = result.get(0);
        assertEquals(employee1.getId(), employee.getId());
        assertEquals(employee1.getLastName(), employee.getLastName());
        assertEquals(employee1.getFirstName(), employee.getFirstName());
    }

    @Test
    public void shouldFindCaretakerByOutpost() {
        //when
        List<EmployeeTO> result = employeeService.findCaretakerByOutpost(outpost.getId(), car.getId());

        //then
        assertEquals(1, result.size());
        EmployeeTO employee = result.get(0);
        assertEquals(employee1.getId(), employee.getId());
        assertEquals(employee1.getLastName(), employee.getLastName());
        assertEquals(employee1.getFirstName(), employee.getFirstName());
    }
}