package com.capgemini.service.impl;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.OutpostDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.service.OutpostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutpostServiceImpl implements OutpostService {
    @Autowired
    OutpostDao outpostRepository;
    @Autowired
    EmployeeDao employeeRepository;

    @Override
    public void addEmployee(long outpostId, long employeeId) {
        EmployeeEntity employee = employeeRepository.findOne(employeeId);
        outpostRepository.addEmployeeToOutpost(outpostId, employee);
    }

    @Override
    public void removeEmployee(long outpostId, long employeeId) {
        EmployeeEntity employee = employeeRepository.findOne(employeeId);
        outpostRepository.removeEmployeeFromOutpost(outpostId, employee);
    }
}