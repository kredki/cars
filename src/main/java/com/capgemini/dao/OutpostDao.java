package com.capgemini.dao;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OutpostEntity;

public interface OutpostDao extends Dao<OutpostEntity, Long> {
    public void addEmployee(long outpostId, EmployeeEntity employee);

    public void removeEmployee(long outpostId, EmployeeEntity employee);
}
