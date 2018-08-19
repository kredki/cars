package com.capgemini.service.impl;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.OutpostDao;
import com.capgemini.dao.PositionDao;
import com.capgemini.domain.AddressInTable;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OutpostEntity;
import com.capgemini.domain.PositionEntity;
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
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
@Transactional
public class OutpostServiceImplTest {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    OutpostServiceImpl outpostService;
    @Autowired
    OutpostDao outpostDao;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private PositionDao positionDao;

    private OutpostEntity outpost;
    private EmployeeEntity employee;
    private PositionEntity position;

    @Before
    public void setup() {
        AddressInTable address = new AddressInTable.Builder().withStreet("a").withPostalCode("b").withNo("c").withCity("d")
                .build();
        outpost = new OutpostEntity.Builder().withAddress(address).withContactData("data").build();
        outpost = outpostDao.save(outpost);
        position = new PositionEntity("Sprzedawca");
        employee = new EmployeeEntity("Jan", "Kowalski", new Date(), null, position);
        position = positionDao.save(position);
        employee = employeeDao.save(employee);
    }

    @Test
    public void shoudAddEmployee() {
        //given
        Long outpostId = outpost.getId();
        long employeeQtyBefore = employeeDao.findEmployeeByOutpost(outpostId).size();
        Long employeeId = employee.getId();

        //when
        outpostService.addEmployee(outpostId, employeeId);

        //then
        List<EmployeeEntity> employees = employeeDao.findEmployeeByOutpost(outpostId);
        assertEquals(employeeQtyBefore + 1, employees.size());
        List<Long> ids = employees.stream().map(x -> x.getId()).collect(Collectors.toList());
        assertTrue(ids.contains(employeeId));
    }

    @Test
    public void shoudRemoveEmployee() {
        //given
        Long outpostId = outpost.getId();
        Long employeeId = employee.getId();
        outpostService.addEmployee(outpostId, employeeId);
        long employeeQtyBefore = employeeDao.findEmployeeByOutpost(outpostId).size();

        //when
        outpostService.removeEmployee(outpostId, employeeId);

        //then
        List<EmployeeEntity> employees = employeeDao.findEmployeeByOutpost(outpostId);
        assertEquals(employeeQtyBefore - 1, employees.size());
        List<Long> ids = employees.stream().map(x -> x.getId()).collect(Collectors.toList());
        assertFalse(ids.contains(employeeId));
    }
}