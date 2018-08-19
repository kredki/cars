package com.capgemini.dao.impl;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OutpostEntity;
import com.capgemini.domain.PositionEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Employee DAO Implementation
 */
@Repository
public class EmployeeDaoImpl extends AbstractDao<EmployeeEntity, Long> implements EmployeeDao {
    /**
     *
     * @param outpostId
     * @return Employees assigend to requested outpost.
     */
    @Override
    public List<EmployeeEntity> findEmployeeByOutpost(long outpostId) {
        TypedQuery<EmployeeEntity> query = entityManager.createQuery(
                "select e from EmployeeEntity e where :outpostId = outpost.id", EmployeeEntity.class);
        query.setParameter("outpostId", outpostId);
        return query.getResultList();
    }

    /**
     *
     * @param outpost
     * @param car
     * @return Employees assigend to requested car and from requested outpost.
     */
    @Override
    public List<EmployeeEntity> findCaretakerByOutpost(OutpostEntity outpost, CarEntity car) {
        TypedQuery<EmployeeEntity> query = entityManager.createQuery(
                "select e from EmployeeEntity e where :outpost = e.outpost and :car member of e.cars", EmployeeEntity.class);
        query.setParameter("outpost", outpost);
        query.setParameter("car", car);
        return query.getResultList();
    }

    /**
     * Assign car to employee.
     * @param caretakerId
     * @param car
     */
    @Override
    public void addCar(long caretakerId, CarEntity car) {
        TypedQuery<EmployeeEntity> query = entityManager.createQuery(
                "select e from EmployeeEntity e where e.id = :caretakerId", EmployeeEntity.class);
        query.setParameter("caretakerId", caretakerId);
        EmployeeEntity caretaker = query.getSingleResult();
        caretaker.addCar(car);
        entityManager.persist(caretaker);
    }

    /**
     *
     * @param carId
     * @return Employees assigned to requested car.
     */
    @Override
    public List<EmployeeEntity> findCaretaker(Long carId) {
        CarEntity car = entityManager.getReference(CarEntity.class, carId);
        TypedQuery<EmployeeEntity> query = entityManager.createQuery(
                "select e from EmployeeEntity e where :car member of e.cars", EmployeeEntity.class);
        query.setParameter("car", car);
        return query.getResultList();
    }

    /**
     *
     * @param positionId
     * @return Employee of requested position.
     */
    @Override
    public List<EmployeeEntity> findEmployeeByPosition(Long positionId) {
        PositionEntity position = entityManager.getReference(PositionEntity.class, positionId);
        TypedQuery<EmployeeEntity> query = entityManager.createQuery(
                "select e from EmployeeEntity e where :position = e.position", EmployeeEntity.class);
        query.setParameter("position", position);
        return query.getResultList();
    }

    /**
     * Find employees by search criteria.
     * @param queryString
     * @param carId
     * @param outpostId
     * @param positionId
     * @return Employees by search criteria.
     */
    @Override
    public List<EmployeeEntity> findEmployeeBySearchCriteria(String queryString, Long carId, Long outpostId, Long positionId) {
        PositionEntity position = null;
        if (positionId != null) {
            position = entityManager.getReference(PositionEntity.class, positionId);
        }
        CarEntity car = null;
        if (carId != null) {
            car = entityManager.getReference(CarEntity.class, carId);
        }
        OutpostEntity outpost = null;
        if (outpostId != null) {
            outpost = entityManager.getReference(OutpostEntity.class, outpostId);
        }
        TypedQuery<EmployeeEntity> query = entityManager.createQuery(queryString, EmployeeEntity.class);
        if (position != null) {
            query.setParameter("position", position);
        }
        if (car != null) {
            query.setParameter("car", car);
        }
        if (outpost != null) {
            query.setParameter("outpost", outpost);
        }
        return query.getResultList();
    }
}
