package com.capgemini.dao.impl;

import com.capgemini.dao.EmployeeDao;
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
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CarDaoImplTest {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private CarDaoImpl carDao;
    @Autowired
    private EmployeeDao employeeDao;

    private CarEntity car1;
    private CarEntity car2;
    private EmployeeEntity employee;
    private OutpostEntity outpost;
    private PositionEntity position;

    @Before
    public void init() {
        position = new PositionEntity("Sprzedawca");
        AddressEnity address = new AddressEnity("street", "no", "city", "postal code");
        outpost = new OutpostEntity(address, "contactData");
        employee = new EmployeeEntity("Jan", "Kowalski", new Date(), outpost,
                position);
        car1 = new CarEntity.CarEntityBuilder().withBrandName("BMW").withCarType("Coupe")
                .withEmployee(employee)
                .withEngineCapacity(2000).withMileage(20000).withPower(120).withProductionYear(2015).withColor("blue")
                .withRentals(new HashSet<RentalEntity>()).build();
        employee.addCar(car1);
        car2 = new CarEntity.CarEntityBuilder().withBrandName("Opel").withCarType("Sedan")
                .withEmployee(employee)
                .withEngineCapacity(1500).withMileage(30000).withPower(100).withProductionYear(2014).withColor("red")
                .withRentals(new HashSet<RentalEntity>()).build();
        employee.addCar(car2);
        carDao.save(car1);
        carDao.save(car2);
    }

    @Test
    public void shouldReturnCarByType() {
        //given
        String type = "Sedan";

        //when
        List<CarEntity> result = carDao.findCarByType(type);

        //then
        assertEquals(1, result.size());
        CarEntity car = result.get(0);
        assertEquals(car2.getEngineCapacity(), car.getEngineCapacity());
        assertEquals(car2.getBrandName(), car.getBrandName());
        assertEquals(type, car.getCarType());
        assertEquals(car2.getColor(), car.getColor());
        assertEquals(car2.getMileage(), car.getMileage());
        assertEquals(car2.getPower(), car.getPower());
        assertEquals(car2.getProductionYear(), car.getProductionYear());
    }

    @Test
    public void shouldReturnCarByBrand() {
        //given
        String brand = "Opel";

        //when
        List<CarEntity> result = carDao.findCarByBrand(brand);

        //then
        assertEquals(1, result.size());
        CarEntity car = result.get(0);
        assertEquals(car2.getEngineCapacity(), car.getEngineCapacity());
        assertEquals(brand, car.getBrandName());
        assertEquals(car2.getCarType(), car.getCarType());
        assertEquals(car2.getColor(), car.getColor());
        assertEquals(car2.getMileage(), car.getMileage());
        assertEquals(car2.getPower(), car.getPower());
        assertEquals(car2.getProductionYear(), car.getProductionYear());
    }

    @Test
    public void shouldReturnCarByCaretaker() {
        //given
        long employeeId = employeeDao.findAll().get(0).getId();

        //when
        List<CarEntity> result = carDao.findCarByCaretaker(employeeId);

        //then
        assertEquals(2, result.size());
    }
}