package com.capgemini.service.impl;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.OutpostDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OutpostEntity;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.service.EmployeeService;
import com.capgemini.types.EmployeeTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeDao employeeRepository;
    @Autowired
    OutpostDao outpostRepository;
    @Autowired
    CarDao carRepository;

    @Override
    public List<EmployeeTO> findEmployeeByOutpost(long outpostId) {
        List<EmployeeEntity> employees = employeeRepository.findEmployeeByOutpost(outpostId);
        return EmployeeMapper.map2TOs(employees);
    }

    @Override
    public List<EmployeeTO> findCaretakerByOutpost(long outpostId, long carId) {
        OutpostEntity outpost = outpostRepository.findOne(outpostId);
        CarEntity car = carRepository.findOne(carId);
        List<EmployeeEntity> employees = employeeRepository.findCaretakerByOutpost(outpost, car);
        return EmployeeMapper.map2TOs(employees);
    }

    @Override
    public void addCar(long caretakerId, long carId) {
        CarEntity car = carRepository.findOne(carId);
        employeeRepository.addCar(caretakerId, car);
    }
}
