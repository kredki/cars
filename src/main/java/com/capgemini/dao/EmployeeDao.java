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
     * Find employees assigned to outpost.
     * @param outpostId Outpost id.
     * @return Employees assigned to requested outpost.
     */
    public List<EmployeeEntity> findEmployeeByOutpost(long outpostId);

    /**
     * Find employees from requested outpost assigned to requested car.
     * @param outpost OutpostEntity
     * @param car CarEntity
     * @return Employees assigend to requested car and from requested outpost.
     */
    public List<EmployeeEntity> findCaretakerByOutpost(OutpostEntity outpost, CarEntity car);

    /**
     * Assign car to employee.
     * @param caretakerId Employee id.
     * @param car Car to be assigned.
     */
    public void addCar(long caretakerId, CarEntity car);

    /**
     * Find employees assigned to requested car.
     * @param carId Car id.
     * @return Employees assigned to requested car.
     */
    public List<EmployeeEntity> findCaretaker(Long carId);

    /**
     * Find employees of requested position.
     * @param positionId
     * @return Employee of requested position.
     */
    public List<EmployeeEntity> findEmployeeByPosition(Long positionId);

    /**
     * Find employees by search criteria.
     * @param queryString String with query.
     * @param carId Car id.
     * @param outpostId Outpost id.
     * @param positionId Position id.
     * @return Employees by search criteria.
     */
    public List<EmployeeEntity> findEmployeeBySearchCriteria(String queryString, Long carId, Long outpostId, Long positionId);
}
