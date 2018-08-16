package com.capgemini.service;

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
}
