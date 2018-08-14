package com.capgemini.dao;

import com.capgemini.domain.OutpostEntity;

public interface OutpostDao extends Dao<OutpostEntity, Long> {
    public void addEmployee(long outpostId, long employeeId);

    public void removeEmployee(long outpostId, long employeeId);
}
