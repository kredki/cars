package com.capgemini.dao.impl;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.EmployeeEntity;

import java.util.List;

public class EmployeeDaoImpl extends AbstractDao<EmployeeEntity, Long> implements EmployeeDao {
    @Override
    public List<EmployeeEntity> findEmployeeByOutpost(long outpostId) {
        return null;
    }

    @Override
    public List<EmployeeEntity> findCaretakerByOutpost(long outpostId, long carId) {
        return null;
    }

    @Override
    public void addCar(long caretakerId, long carId) {

    }
}
