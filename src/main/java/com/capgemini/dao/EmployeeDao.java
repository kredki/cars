package com.capgemini.dao;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OutpostEntity;

import java.util.List;

public interface EmployeeDao extends Dao<EmployeeEntity, Long> {
    public List<EmployeeEntity> findEmployeeByOutpost(long outpostId);

    public List<EmployeeEntity> findCaretakerByOutpost(OutpostEntity outpost, CarEntity car);

    public void addCar(long caretakerId, CarEntity car);
}
