package com.capgemini.dao;

import com.capgemini.domain.EmployeeEntity;

import java.util.List;

public interface EmployeeDao extends Dao<EmployeeEntity, Long> {
    public List<EmployeeEntity> findEmployeeByOutpost(long outpostId);

    public List<EmployeeEntity> findCaretakerByOutpost(long outpostId, long carId);

    public void addCar(long caretakerId, long carId);
}
