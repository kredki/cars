package com.capgemini.dao;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OutpostEntity;

/**
 * Outpost DAO
 */
public interface OutpostDao extends Dao<OutpostEntity, Long> {
    /**
     * Add employee to outpost.
     * @param outpostId
     * @param employee
     */
    public void addEmployeeToOutpost(long outpostId, EmployeeEntity employee);

    /**
     * Remove employee from outpost.
     * @param outpostId
     * @param employee
     */
    public void removeEmployeeFromOutpost(long outpostId, EmployeeEntity employee);
}
