package com.capgemini.service.impl;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.OutpostDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OutpostEntity;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.service.EmployeeService;
import com.capgemini.types.EmployeeSearchCriteriaTO;
import com.capgemini.types.EmployeeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Employee Service Implementation
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDao employeeRepository;
    OutpostDao outpostRepository;
    CarDao carRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeRepository, OutpostDao outpostRepository, CarDao carRepository) {
        this.employeeRepository = employeeRepository;
        this.outpostRepository = outpostRepository;
        this.carRepository = carRepository;
    }

    /**
     *
     * @param outpostId
     * @return employees assigned to outpost
     */
    @Override
    public List<EmployeeTO> findEmployeeByOutpost(long outpostId) {
        List<EmployeeEntity> employees = employeeRepository.findEmployeeByOutpost(outpostId);
        return EmployeeMapper.map2TOs(employees);
    }

    /**
     *
     * @param outpostId
     * @param carId
     * @return employees assigened to outpost whom are assigned to specified car
     */
    @Override
    public List<EmployeeTO> findCaretakerByOutpost(long outpostId, long carId) {
        OutpostEntity outpost = outpostRepository.findOne(outpostId);
        CarEntity car = carRepository.findOne(carId);
        List<EmployeeEntity> employees = employeeRepository.findCaretakerByOutpost(outpost, car);
        return EmployeeMapper.map2TOs(employees);
    }

    /**
     * Assign car to employee.
     * @param caretakerId
     * @param carId
     */
    @Override
    public void addCar(long caretakerId, long carId) {
        CarEntity car = carRepository.findOne(carId);
        employeeRepository.addCar(caretakerId, car);
    }

    /**
     * Returns employees for requested search criteria: outpost, car, position.
     * @param searchCriteria Stores outpost id, car id, position id. Null criteria is not search for. All criteria can be null.
     * @return Employees for requested search criteria.
     */
    @Override
    public List<EmployeeTO> findEmployeeByOutpost(EmployeeSearchCriteriaTO searchCriteria) {
        List<EmployeeTO> result = new ArrayList<>();
        Long carId = searchCriteria.getCarId();
        if(carId != null) {
            result = addCaretakers(result, carId);
        }
        Long outpostId = searchCriteria.getOutpostId();
        if(outpostId != null) {
            result = addOutpostEmployees(result, outpostId);
        }
        Long positionId = searchCriteria.getPositionId();
        if(positionId != null) {
            result = addEmployeesOfPosition(result, positionId);
        }
        return result;
    }

    private List<EmployeeTO> addCaretakers(List<EmployeeTO> employees, Long carId) {
        List<EmployeeTO> selectedEmployees = EmployeeMapper.map2TOs(employeeRepository.findCaretaker(carId));
        List<EmployeeTO> result = new ArrayList<>();
        for (EmployeeTO e : selectedEmployees) {
            for (EmployeeTO e2 : employees) {
                if(e2.getId() == e.getId()) {
                    result.add(e);
                    break;
                }
            }
        }
        return result;
    }

    private List<EmployeeTO> addOutpostEmployees(List<EmployeeTO> employees, Long outpostId) {
        List<EmployeeTO> selectedEmployees = EmployeeMapper.map2TOs(employeeRepository.findEmployeeByOutpost(outpostId));
        List<EmployeeTO> result = new ArrayList<>();
        for (EmployeeTO e : selectedEmployees) {
            for (EmployeeTO e2 : employees) {
                if(e2.getId() == e.getId()) {
                    result.add(e);
                    break;
                }
            }
        }
        return result;
    }

    private List<EmployeeTO> addEmployeesOfPosition(List<EmployeeTO> employees, Long positionId) {
        List<EmployeeTO> selectedEmployees = EmployeeMapper.map2TOs(employeeRepository.findEmployeeByPosition(positionId));
        List<EmployeeTO> result = new ArrayList<>();
        for (EmployeeTO e : selectedEmployees) {
            for (EmployeeTO e2 : employees) {
                if(e2.getId() == e.getId()) {
                    result.add(e);
                    break;
                }
            }
        }
        return result;
    }
}
