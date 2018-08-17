package com.capgemini.dao.impl;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.OutpostDao;
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

import static org.junit.Assert.assertEquals;

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

    private EmployeeEntity employee1;
    private EmployeeEntity employee2;
    private OutpostEntity outpost;
    private OutpostEntity outpost2;
    private PositionEntity position;

    @Before
    public void setup() {
        position = new PositionEntity("Sprzedawca");
        AddressEnity address = new AddressEnity("street", "no", "city", "postal code");
        outpost = new OutpostEntity(address, "contactData");
        outpost2 = new OutpostEntity(address, "contactData2");
        employee1 = new EmployeeEntity("Jan", "Kowalski", new Date(), outpost, position);
        employee2 = new EmployeeEntity("Andrzej", "Nowak", new Date(), outpost2, position);

        CarEntity car = new CarEntity.CarEntityBuilder().withBrandName("BMW").withCarType("Coupe")
                .withEmployee(employee1)
                .withEngineCapacity(2000).withMileage(20000).withPower(120).withProductionYear(2015).withColor("blue")
                .withRentals(new HashSet<RentalEntity>()).build();
        employee1.addCar(car);

        employeeDao.save(employee1);
        employeeDao.save(employee2);
    }

    @Test
    public void shouldReturnEmployeeByOutpost() {
        //given
        OutpostEntity outpost = outpostDao.findAll().get(0);

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
        //given
        CarEntity car = carDao.findAll().get(0);
        OutpostEntity outpost = outpostDao.findAll().get(0);

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
    public void shouldaddCar() {
        //given
        CarEntity car = carDao.findAll().get(0);
        EmployeeEntity employee = employeeDao.findAll().get(1);

        //when
        employeeDao.addCar(employee.getId(), car);


        //then
        EmployeeEntity resultEmployee = employeeDao.findOne(employee.getId());
        Iterator<CarEntity> it = resultEmployee.getCars().iterator();
        assertEquals(it.next().getId(), car.getId());
        assertEquals(resultEmployee.getFirstName(), employee2.getFirstName());
        assertEquals(resultEmployee.getLastName(), employee2.getLastName());
    }
}