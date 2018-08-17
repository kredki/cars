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
     * @return Employees assigned to requested car and from requested outpost.
     */
    public List<EmployeeEntity> findCaretakerByOutpost(OutpostEntity outpost, CarEntity car);

    /**
     * Assign car to employee.
     * @param caretakerId
     * @param car
     */
    public void addCar(long caretakerId, CarEntity car);

    /**
     *
     * @param carId
     * @return Employees assigned to requested car.
     */
    public List<EmployeeEntity> findCaretaker(Long carId);

    /**
     *
     * @param positionId
     * @return Employee of requested position.
     */
    public List<EmployeeEntity> findEmployeeByPosition(Long positionId);

    /**
     * Find employees by search criteria.
     * @param queryString
     * @param carId
     * @param outpostId
     * @param positionId
     * @return Employees by search criteria.
     */
    public List<EmployeeEntity> findEmployeeBySearchCriteria(String queryString, long carId, long outpostId, long positionId);
}
