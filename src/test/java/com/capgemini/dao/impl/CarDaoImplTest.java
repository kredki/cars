package com.capgemini.dao.impl;

import com.capgemini.domain.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarDaoImplTest {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private CarDaoImpl carDao;

    private static CarEntity car1;
    private static CarEntity car2;
    private static EmployeeEntity employee;
    private static OutpostEntity outpost;
    private static PositionEntity position;

    @BeforeClass
    public static void init() {
        position = new PositionEntity("Sprzedawca");
        outpost = new OutpostEntity("address", "contactData");
        employee = new EmployeeEntity("Jan", "Kowalski", new Date(), outpost,
                position);
        car1 = new CarEntity.CarEntityBuilder().withBrandName("BMW").withCarType("Coupe").withEmployee(employee)
                .withEngineCapacity(2000).withMileage(20000).withPower(120).withProductionYear(2015)
                .withRentals(new HashSet<RentalEntity>()).build();
        car2 = new CarEntity.CarEntityBuilder().withBrandName("Opel").withCarType("Sedan").withEmployee(null)
                .withEngineCapacity(1500).withMileage(30000).withPower(100).withProductionYear(2014)
                .withRentals(new HashSet<RentalEntity>()).build();
    }

    @Before
    public void setup() {
        //carDao.deleteAll();
        carDao.save(car1);
        carDao.save(car2);
    }

    @Test
    public void shouldReturnCarByType() {
        //given
        String type = "Sedan";

        //when
        List<CarEntity> result = carDao.findCarByType(type);
        assertEquals(1, result.size());
    }
}