package com.capgemini.service.impl;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.OutpostDao;
import com.capgemini.dao.PositioDao;
import com.capgemini.domain.*;
import com.capgemini.types.EmployeeSearchCriteriaTO;
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
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    private OutpostEntity outpost2;
    private EmployeeEntity employee1;
    private EmployeeEntity employee2;
    private EmployeeEntity employee3;
    private EmployeeEntity employee4;
    private EmployeeEntity employee5;
    private PositionEntity position;
    private PositionEntity position2;
    private CarEntity car;
    private CarEntity car2;

    @Before
    public void setup() {
        AddressInTable address = new AddressInTable.Builder().withStreet("a").withPostalCode("b").withNo("c").withCity("d")
                .build();
        outpost = new OutpostEntity.Builder().withAddress(address).withContactData("data").build();
        outpost2 = new OutpostEntity.Builder().withAddress(address).withContactData("data2").build();
        outpost = outpostDao.save(outpost);
        outpost2 = outpostDao.save(outpost2);

        position = new PositionEntity("Sprzedawca");
        position2 = new PositionEntity("Kierownik");

        employee1 = new EmployeeEntity("Jan", "Kowalski", new Date(), outpost, position);
        employee2 = new EmployeeEntity("Andrzej", "Nowak", new Date(), null, position);
        employee3 = new EmployeeEntity("Onufry", "Zagłoba", new Date(), outpost2, position2);
        employee4 = new EmployeeEntity("John", "Rambo", new Date(), outpost2, position2);
        employee5 = new EmployeeEntity("Patrycja", "Czarna", new Date(), outpost2, position);

        car = new CarEntity.CarEntityBuilder().withBrandName("BMW").withCarType("Coupe")
                .withEmployee(employee1)
                .withEngineCapacity(2000).withMileage(20000).withPower(120).withProductionYear(2015).withColor("blue")
                .withRentals(new HashSet<RentalEntity>()).build();
        car2 = new CarEntity.CarEntityBuilder().withBrandName("Ford").withCarType("Coupe")
                .withEngineCapacity(2000).withMileage(20000).withPower(120).withProductionYear(2015).withColor("red")
                .withRentals(new HashSet<RentalEntity>()).build();

        employee1.addCar(car);
        employee4.addCar(car2);
        employee5.addCar(car2);

        car = carDao.save(car);
        car2 = carDao.save(car2);
        positioDao.save(position);
        position2 = positioDao.save(position2);
        position2 = positioDao.save(position2);
        employee1 = employeeDao.save(employee1);
        employee2 = employeeDao.save(employee2);
        employee3 = employeeDao.save(employee3);
        employee4 = employeeDao.save(employee4);
        employee5 = employeeDao.save(employee5);
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

    @Test
    public void shouldFindEmployeeByCriteria() {
        //given
        EmployeeSearchCriteriaTO criteria = new EmployeeSearchCriteriaTO(outpost2.getId(), car2.getId(), position2.getId());

        //when
        List<EmployeeTO> result = employeeService.findEmployeeByCriteria(criteria);

        //then
        assertEquals(1, result.size());
        EmployeeTO employee = result.get(0);
        assertEquals(employee4.getId(), employee.getId());
        assertEquals(employee4.getLastName(), employee.getLastName());
        assertEquals(employee4.getFirstName(), employee.getFirstName());
    }

    @Test
    public void shouldFindEmployeeByOutpostAndCar() {
        //given
        EmployeeSearchCriteriaTO criteria = new EmployeeSearchCriteriaTO(outpost2.getId(), car2.getId(), null);

        //when
        List<EmployeeTO> result = employeeService.findEmployeeByCriteria(criteria);

        //then
        assertEquals(2, result.size());
        List<Long> ids = result.stream().map(x -> x.getId()).collect(Collectors.toList());
        assertTrue(ids.contains(employee4.getId()));
        assertTrue(ids.contains(employee5.getId()));
    }

    @Test
    public void shouldFindEmployeeByOutpost2() {
        //given
        EmployeeSearchCriteriaTO criteria = new EmployeeSearchCriteriaTO(outpost2.getId(), null, null);

        //when
        List<EmployeeTO> result = employeeService.findEmployeeByCriteria(criteria);

        //then
        assertEquals(3, result.size());
        List<Long> ids = result.stream().map(x -> x.getId()).collect(Collectors.toList());
        assertTrue(ids.contains(employee3.getId()));
        assertTrue(ids.contains(employee4.getId()));
        assertTrue(ids.contains(employee5.getId()));
    }

    @Test
    public void shouldFindEmptyList() {
        //given
        EmployeeSearchCriteriaTO criteria = new EmployeeSearchCriteriaTO(null, null, null);

        //when
        List<EmployeeTO> result = employeeService.findEmployeeByCriteria(criteria);

        //then
        assertTrue(result.isEmpty());
    }

    @Test
    public void shouldFindEmployeeByOuytpostAndPosition() {
        //given
        EmployeeSearchCriteriaTO criteria = new EmployeeSearchCriteriaTO(outpost2.getId(), null, position2.getId());

        //when
        List<EmployeeTO> result = employeeService.findEmployeeByCriteria(criteria);

        //then
        assertEquals(2, result.size());
        List<Long> ids = result.stream().map(x -> x.getId()).collect(Collectors.toList());
        assertTrue(ids.contains(employee3.getId()));
        assertTrue(ids.contains(employee4.getId()));
    }

    @Test
    public void shouldFindEmployeeByPosition() {
        //given
        EmployeeSearchCriteriaTO criteria = new EmployeeSearchCriteriaTO(null, null, position2.getId());

        //when
        List<EmployeeTO> result = employeeService.findEmployeeByCriteria(criteria);

        //then
        assertEquals(2, result.size());
        List<Long> ids = result.stream().map(x -> x.getId()).collect(Collectors.toList());
        assertTrue(ids.contains(employee3.getId()));
        assertTrue(ids.contains(employee4.getId()));
    }

    @Test
    public void shouldFindEmployeeByCar() {
        //given
        EmployeeSearchCriteriaTO criteria = new EmployeeSearchCriteriaTO(null, car2.getId(), null);

        //when
        List<EmployeeTO> result = employeeService.findEmployeeByCriteria(criteria);

        //then
        assertEquals(2, result.size());
        List<Long> ids = result.stream().map(x -> x.getId()).collect(Collectors.toList());
        assertTrue(ids.contains(employee4.getId()));
        assertTrue(ids.contains(employee5.getId()));
    }
}