package com.capgemini.dao.impl;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.AddressEnity;
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
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
@Transactional
public class OutpostDaoImplTest {
    @Autowired
    OutpostDaoImpl outpostRepository;
    @Autowired
    EmployeeDao employeeRepository;
    @PersistenceContext
    private EntityManager entityManager;

    private EmployeeEntity employee;
    private OutpostEntity outpost;

    @Before
    public void setup() {
        PositionEntity position = new PositionEntity.Builder().withName("Sprzedawca").build();
        employee = new EmployeeEntity.Builder().withBirthDate(new Date()).withFirstName("Jan").withLastName("Nowak")
                .withPosition(position).build();
        AddressEnity address = new AddressEnity.Builder().withCity("city").withNo("no").withPostalCode("postal code")
                .withStreet("street").build();
        outpost = new OutpostEntity.Builder().withAddress(address).withContactData("contact data").build();

        outpost = outpostRepository.save(outpost);
        employee = employeeRepository.save(employee);
    }

    @Test
    public void shouldAddEmployeeToOutpost() {
        //when
        outpostRepository.addEmployeeToOutpost(outpost.getId(), employee);

        //then
        OutpostEntity resultOutpost = outpostRepository.findOne(outpost.getId());
        assertEquals(1, resultOutpost.getEmployees().size());
        Iterator<EmployeeEntity> it = resultOutpost.getEmployees().iterator();
        assertEquals(employee.getId(), it.next().getId());
    }

    @Test
    public void shouldRemoveEmployeeFromOutpost() {
        //given
        outpostRepository.addEmployeeToOutpost(outpost.getId(), employee);

        //when
        outpostRepository.removeEmployeeFromOutpost(outpost.getId(), employee);

        //then
        OutpostEntity resultOutpost = outpostRepository.findOne(outpost.getId());
        assertEquals(0, resultOutpost.getEmployees().size());
    }
}