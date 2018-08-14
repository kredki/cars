package com.capgemini.dao.impl;

import com.capgemini.dao.CarDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImpl extends AbstractDao<CarEntity, Long> implements CarDao {

    @Override
    public List<CarEntity> findCarByType(String type) {
        TypedQuery<CarEntity> query = entityManager.createQuery(
                "select car from CarEntity car where upper(car.carType) like concat(upper(:carType), '%')", CarEntity.class);
        query.setParameter("carType", type);
        return query.getResultList();
    }

    @Override
    public List<CarEntity> findCarByBrand(String brand) {
        TypedQuery<CarEntity> query = entityManager.createQuery(
                "select car from CarEntity car where upper(car.brandName) like concat(upper(:brand), '%')", CarEntity.class);
        query.setParameter("brand", brand);
        return query.getResultList();
    }

    @Override
    public List<CarEntity> findCarByCaretaker(long caretakerId) {
        EmployeeEntity employee = entityManager.getReference(EmployeeEntity.class, caretakerId);
        TypedQuery<CarEntity> query = entityManager.createQuery(
                "select car from CarEntity car where :caretaker member of car.employees", CarEntity.class);
        query.setParameter("caretaker", employee);
        return query.getResultList();
    }

    public void deleteAll() {
        entityManager.createQuery("delete from CarEntity", CarEntity.class).executeUpdate();
    }
}
