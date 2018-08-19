package com.capgemini.dao.impl;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.OutpostDao;
import com.capgemini.dao.PositionDao;
import com.capgemini.domain.*;
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
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
@Transactional
public class EmployeeDaoImplTest {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private EmployeeDaoImpl employeeDao;
    @Autowired
    private OutpostDao outpostDao;
    @Autowired
    private CarDao carDao;
    @Autowired
    PositionDao positionDao;

    private EmployeeEntity employee1;
    private EmployeeEntity employee2;
    private EmployeeEntity employee3;
    private OutpostEntity outpost;
    private OutpostEntity outpost2;
    private PositionEntity position;
    private PositionEntity position2;
    private CarEntity car;

    @Before
    public void setup() {
        position = new PositionEntity("Sprzedawca");
        position2 = new PositionEntity("Kierownik");
        position = positionDao.save(position);
        position2 = positionDao.save(position2);
        AddressInTable address = new AddressInTable("street", "no", "city", "postal code");
        outpost = new OutpostEntity(address, "contactData");
        outpost2 = new OutpostEntity(address, "contactData2");
        employee1 = new EmployeeEntity("Jan", "Kowalski", new Date(), outpost, position);
        employee2 = new EmployeeEntity("Andrzej", "Nowak", new Date(), outpost2, position);
        employee3 = new EmployeeEntity("Jakub", "Dziwny", new Date(), outpost2, position2);

        car = new CarEntity.CarEntityBuilder().withBrandName("BMW").withCarType("Coupe")
                .withEmployee(employee1)
                .withEngineCapacity(2000).withMileage(20000).withPower(120).withProductionYear(2015).withColor("blue")
                .withRentals(new HashSet<RentalEntity>()).build();
        car = carDao.save(car);
        employee1.addCar(car);

        employee1 = employeeDao.save(employee1);
        employee2 = employeeDao.save(employee2);
        employee3 = employeeDao.save(employee3);
    }

    @Test
    public void shouldReturnEmployeeByOutpost() {
        //when
        List<EmployeeEntity> result = employeeDao.findEmployeeByOutpost(outpost.getId());

        //then
        assertEquals(1, result.size());
        EmployeeEntity employee = result.get(0);
        assertEquals(employee.getOutpost().getId(), outpost.getId());
        assertEquals(employee.getFirstName(), employee1.getFirstName());
        assertEquals(employee.getLastName(), employee1.getLastName());
    }

    @Test
    public void shouldReturnCaretakerByOutpost() {
        //when
        List<EmployeeEntity> result = employeeDao.findCaretakerByOutpost(outpost, car);

        //then
        assertEquals(1, result.size());
        EmployeeEntity employee = result.get(0);
        assertEquals(employee.getOutpost().getId(), outpost.getId());
        Iterator<CarEntity> it = employee.getCars().iterator();
        assertEquals(it.next().getId(), car.getId());
        assertEquals(employee.getFirstName(), employee1.getFirstName());
        assertEquals(employee.getLastName(), employee1.getLastName());
    }

    @Test
    public void shouldAddCar() {
        //when
        employeeDao.addCar(employee2.getId(), car);


        //then
        EmployeeEntity resultEmployee = employeeDao.findOne(employee2.getId());
        Iterator<CarEntity> it = resultEmployee.getCars().iterator();
        assertEquals(it.next().getId(), car.getId());
        assertEquals(resultEmployee.getFirstName(), this.employee2.getFirstName());
        assertEquals(resultEmployee.getLastName(), this.employee2.getLastName());
    }

    @Test
    public void shouldFindCaretaker() {
        //when
        List<EmployeeEntity> result = employeeDao.findCaretaker(car.getId());

        //then
        assertEquals(1, result.size());
        EmployeeEntity resultEmployee = result.get(0);
        assertEquals(resultEmployee.getFirstName(), employee1.getFirstName());
        assertEquals(resultEmployee.getLastName(), employee1.getLastName());
    }

    @Test
    public void shouldFindEmployeeByPosition() {
        //when
        List<EmployeeEntity> result = employeeDao.findEmployeeByPosition(position.getId());

        //then
        assertEquals(2, result.size());
        List<Long> ids = result.stream().map(x -> x.getId()).collect(Collectors.toList());
        assertTrue(ids.contains(employee1.getId()));
        assertTrue(ids.contains(employee2.getId()));
    }
}