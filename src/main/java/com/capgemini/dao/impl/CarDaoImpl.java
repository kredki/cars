package com.capgemini.dao.impl;

import com.capgemini.dao.CarDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

/**
 * Car DAO Implementation
 */
@Repository
public class CarDaoImpl extends AbstractDao<CarEntity, Long> implements CarDao {

    /**
     *
     * @param type
     * @return Cars of requested type.
     */
    @Override
    public List<CarEntity> findCarByType(String type) {
        TypedQuery<CarEntity> query = entityManager.createQuery(
                "select car from CarEntity car where upper(car.carType) like concat(upper(:carType), '%')", CarEntity.class);
        query.setParameter("carType", type);
        return query.getResultList();
    }

    /**
     *
     * @param brand
     * @return Cars of requested brand.
     */
    @Override
    public List<CarEntity> findCarByBrand(String brand) {
        TypedQuery<CarEntity> query = entityManager.createQuery(
                "select car from CarEntity car where upper(car.brandName) like concat(upper(:brand), '%')", CarEntity.class);
        query.setParameter("brand", brand);
        return query.getResultList();
    }

    /**
     *
     * @param caretakerId
     * @return Cars assigned to requested caretaker.
     */
    @Override
    public List<CarEntity> findCarByCaretaker(long caretakerId) {
        EmployeeEntity employee = entityManager.getReference(EmployeeEntity.class, caretakerId);
        TypedQuery<CarEntity> query = entityManager.createQuery(
                "select car from CarEntity car where :caretaker member of car.employees", CarEntity.class);
        query.setParameter("caretaker", employee);
        return query.getResultList();
    }

    @Override
    public List<CarEntity> findCarRentByMoreThan(long clientQty) {
        TypedQuery<CarEntity> query = entityManager.createQuery(
                "select car from CarEntity car " +
                        "where car.id in (select re.car.id from RentalEntity re " +
                        "group by re.car.id " +
                        "having count(distinct re.client.id) > :clientQty)", CarEntity.class
        );
        query.setParameter("clientQty", clientQty);
        return query.getResultList();
    }

    @Override
    public List<CarEntity> findCarRentedInPeriod(Date from, Date to) {
        TypedQuery<CarEntity> query = entityManager.createQuery(
                "select car from CarEntity car join car.rentals r where r.startDate >= :from and r.endDate <= :to",
                CarEntity.class);
        query.setParameter("from", from);
        query.setParameter("to", to);
        return query.getResultList();
    }

    /**
     * Deleta all cars.
     */
    public void deleteAll() {
        entityManager.createQuery("delete from CarEntity", CarEntity.class).executeUpdate();
    }
}
