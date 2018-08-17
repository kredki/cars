package com.capgemini.service;

import com.capgemini.types.EmployeeSearchCriteriaTO;
import com.capgemini.types.EmployeeTO;

import java.util.List;

/**
 * Employee Service
 */
public interface EmployeeService {
    /**
     *
     * @param outpostId
     * @return employees assigned to outpost
     */
    public List<EmployeeTO> findEmployeeByOutpost(long outpostId);

    /**
     *
     * @param outpostId
     * @param carId
     * @return employees assigened to outpost whom are assigned to specified car
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
    public List<EmployeeTO> findEmployeeByOutpost(EmployeeSearchCriteriaTO searchCriteria);
}
