package com.capgemini.dao.impl;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OutpostEntity;
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
    public List<EmployeeEntity> findCaretakerByOutpost(OutpostEntity outpost, CarEntity car) {
        TypedQuery<EmployeeEntity> query = entityManager.createQuery(
                "select e from EmployeeEntity e where :outpost = e.outpost and :carId member of e.cars", EmployeeEntity.class);
        query.setParameter("outpost", outpost);
        query.setParameter("carId", car.getId());
        return query.getResultList();
    }

    @Override
    public void addCar(long caretakerId, CarEntity car) {
        TypedQuery<EmployeeEntity> query = entityManager.createQuery(
                "select e from EmployeeEntity e where e.id = :caretakerId", EmployeeEntity.class);
        query.setParameter("caretakerId", caretakerId);
        EmployeeEntity caretaker = query.getSingleResult();
        caretaker.addCar(car);
        entityManager.persist(caretaker);
    }
}
