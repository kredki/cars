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
     * Find employee assigned to requested outpost.
     * @param outpostId Outpost id.
     * @return employees assigned to outpost
     */
    @Override
    public List<EmployeeTO> findEmployeeByOutpost(long outpostId) {
        List<EmployeeEntity> employees = employeeRepository.findEmployeeByOutpost(outpostId);
        return EmployeeMapper.map2TOs(employees);
    }

    /**
     * Find employee assigned to car and outpost.
     * @param outpostId Outpost id.
     * @param carId Car id.
     * @return employees assigned to outpost whom are assigned to specified car
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
    public List<EmployeeTO> findEmployeeByCriteria(EmployeeSearchCriteriaTO searchCriteria) {
        StringBuilder query = new StringBuilder();
        query.append("select e from EmployeeEntity e where ");
        boolean canAddAnd = false;
        Long carId = searchCriteria.getCarId();
        if(carId != null) {
            canAddAnd = true;
            query.append(":car member of e.cars");
        }

        Long outpostId = searchCriteria.getOutpostId();
        if(outpostId != null) {
            if(canAddAnd) {
                query.append(" and ");
            }
            canAddAnd = true;
            query.append(":outpost = outpost");
        }

        Long positionId = searchCriteria.getPositionId();
        if(positionId != null) {
            if(canAddAnd) {
                query.append(" and ");
            }
            canAddAnd = true;
            query.append(":position = e.position");
        }

        if(canAddAnd) {
            return EmployeeMapper.map2TOs(employeeRepository.findEmployeeBySearchCriteria(query.toString(), carId,
                    outpostId, positionId));
        } else {
            return new ArrayList<>();
        }
    }
}
