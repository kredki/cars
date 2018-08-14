package com.capgemini.dao.impl;

import com.capgemini.dao.OutpostDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OutpostEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

@Repository
public class OutpostDaoImpl extends AbstractDao<OutpostEntity, Long> implements OutpostDao {
    @Override
    public void addEmployee(long outpostId, EmployeeEntity employee) {
        long employeeId = employee.getId();
        TypedQuery<OutpostEntity> query = entityManager.createQuery(
                "select o from OutpostEntity o where o.id = :employeeId", OutpostEntity.class);
        query.setParameter("employeeId", employeeId);
        OutpostEntity outpost = query.getSingleResult();
        outpost.addEmployee(employee);
        entityManager.persist(outpost);
    }

    @Override
    public void removeEmployee(long outpostId, EmployeeEntity employee) {
        long employeeId = employee.getId();
        TypedQuery<OutpostEntity> query = entityManager.createQuery(
                "select o from OutpostEntity o where o.id = :employeeId", OutpostEntity.class);
        query.setParameter("employeeId", employeeId);
        OutpostEntity outpost = query.getSingleResult();
        outpost.removeEmployee(employee);
        entityManager.persist(outpost);
    }
}
