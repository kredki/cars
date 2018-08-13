package com.capgemini.dao;

import com.capgemini.domain.OutpostEntity;

public interface OutpostDAO extends Dao<OutpostEntity, Long> {
    public void addEmployee(long employeeId);

    public void removeEmployee(long employeeId);
}
