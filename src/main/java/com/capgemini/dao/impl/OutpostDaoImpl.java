package com.capgemini.dao.impl;

import com.capgemini.dao.OutpostDao;
import com.capgemini.domain.OutpostEntity;
import org.springframework.stereotype.Repository;

@Repository
public class OutpostDaoImpl extends AbstractDao<OutpostEntity, Long> implements OutpostDao {
    @Override
    public void addEmployee(long employeeId) {

    }

    @Override
    public void removeEmployee(long employeeId) {

    }
}
