package com.capgemini.dao.impl;

import com.capgemini.dao.*;
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
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
@Transactional
public class CarDaoImplTest {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private CarDaoImpl carDao;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private ClientDao clientDAO;
    @Autowired
    private RentalDao rentalDao;
    @Autowired
    private PositionDao positionDao;
    @Autowired
    OutpostDao outpostDao;

    private CarEntity car1;
    private CarEntity car2;
    private CarEntity car3;
    private EmployeeEntity employee;
    private OutpostEntity outpost;
    private PositionEntity position;
    private RentalEntity rental;

    @Before
    public void init() {
        position = new PositionEntity("Sprzedawca");
        positionDao.save(position);
        AddressInTable address = new AddressInTable("street", "no", "city", "postal code");
        outpost = new OutpostEntity(address, "contactData");
        employee = new EmployeeEntity("Jan", "Kowalski", new Date(), outpost,
                position);
        employee = employeeDao.save(employee);
        car1 = new CarEntity.CarEntityBuilder().withBrandName("BMW").withCarType("Coupe")
                .withEmployee(employee)
                .withEngineCapacity(2000).withMileage(20000).withPower(120).withProductionYear(2015).withColor("blue")
                .withRentals(new HashSet<RentalEntity>()).build();
        employee.addCar(car1);
        car2 = new CarEntity.CarEntityBuilder().withBrandName("Opel").withCarType("Sedan")
                .withEmployee(employee)
                .withEngineCapacity(1500).withMileage(30000).withPower(100).withProductionYear(2014).withColor("red")
                .withRentals(new HashSet<RentalEntity>()).build();

        ClientEntity client = new ClientEntity.Builder().withBirthdate(new Date()).withCardNo("no").withEmail("mail")
                .withFirstName("first name").withLastName("last name").withTelephone("tel").build();
        client = clientDAO.save(client);
        car3 = new CarEntity.CarEntityBuilder().withBrandName("VW").withCarType("some type")
                .withEmployee(employee)
                .withEngineCapacity(1500).withMileage(30000).withPower(100).withProductionYear(2014).withColor("red")
                .withRentals(new HashSet<RentalEntity>()).build();
        rental = new RentalEntity.Builder().withCost(new BigDecimal("123.00")).withEndDate(null).withStartDate(new Date())
                .withCar(car3)
                .withClient(client).withEndOutpost(null).withStartOutpost(outpost)
                .build();
        car3.addRental(rental);
        employee.addCar(car2);
        outpost.addStartRental(rental);
        outpostDao.save(outpost);
        car1 = carDao.save(car1);
        car2 = carDao.save(car2);
        car3 = carDao.save(car3);
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
        long employeeId = employee.getId();

        //when
        List<CarEntity> result = carDao.findCarByCaretaker(employeeId);

        //then
        assertEquals(2, result.size());
    }

    @Test
    public void shouldRemoveCarAndRentals() {
        //given
        long carQtyBefore = carDao.count();
        long positionQtyBefore = positionDao.count();
        long employeeQtyBefore = employeeDao.count();
        long rentalQtyBefore = rentalDao.count();
        long clientQtyBefore = clientDAO.count();

        //when
        carDao.delete(car3.getId());

        //then
        assertEquals(carQtyBefore - 1, carDao.count());
        assertEquals(positionQtyBefore, positionDao.count());
        assertEquals(employeeQtyBefore, employeeDao.count());
        assertThat(rentalQtyBefore).isGreaterThan(rentalDao.count());
        assertEquals(clientQtyBefore, employeeDao.count());
    }
}