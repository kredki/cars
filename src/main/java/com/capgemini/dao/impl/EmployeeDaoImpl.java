package com.capgemini.dao.impl;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.EmployeeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class EmployeeDaoImpl extends AbstractDao<EmployeeEntity, Long> implements EmployeeDao {
    @Override
    public List<EmployeeEntity> findEmployeeByOutpost(long outpostId) {
        TypedQuery<EmployeeEntity> query = entityManager.createQuery(
                "select e from EmployeeEntity e where :outpostId member of e.cars", EmployeeEntity.class);
        query.setParameter("outpostId", outpostId);
        return query.getResultList();
    }

    @Override
    public List<EmployeeEntity> findCaretakerByOutpost(long outpostId, long carId) {
        return null;
    }

    @Override
    public void addCar(long caretakerId, long carId) {

    }
}
