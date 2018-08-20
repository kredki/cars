package com.capgemini.service;

import com.capgemini.types.EmployeeSearchCriteriaTO;
import com.capgemini.types.EmployeeTO;

import java.util.List;

/**
 * Employee Service
 */
public interface EmployeeService {
    /**
     * Find employee assigned to requested outpost.
     * @param outpostId Outpost id.
     * @return employees assigned to outpost
     */
    public List<EmployeeTO> findEmployeeByOutpost(long outpostId);

    /**
     * Find employee assigned to car and outpost.
     * @param outpostId Outpost id.
     * @param carId Car id.
     * @return employees assigned to outpost whom are assigned to specified car
     */
    public List<EmployeeTO> findCaretakerByOutpost(long outpostId, long carId);

    /**
     * Assign car to employee.
     * @param caretakerId
     * @param carId
     */
    public void addCar(long caretakerId, long carId);

    /**
     * Returns employees for requested search criteria: outpost, car, position.
     * @param searchCriteria Stores outpost id, car id, position id. Null criteria is not search for. All criteria can be null.
     * @return Employees for requested search criteria.
     */
    public List<EmployeeTO> findEmployeeByCriteria(EmployeeSearchCriteriaTO searchCriteria);
}
