package com.capgemini.dao;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OutpostEntity;

import java.util.List;

/**
 * Employee DAO
 */
public interface EmployeeDao extends Dao<EmployeeEntity, Long> {
    /**
     *
     * @param outpostId
     * @return Employees assigend to requested outpost.
     */
    public List<EmployeeEntity> findEmployeeByOutpost(long outpostId);

    /**
     *
     * @param outpost
     * @param car
     * @return Employees assigend to requested car and from requested outpost.
     */
    public List<EmployeeEntity> findCaretakerByOutpost(OutpostEntity outpost, CarEntity car);

    /**
     * Assign car to employee.
     * @param caretakerId
     * @param car
     */
    public void addCar(long caretakerId, CarEntity car);
}
