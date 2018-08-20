package com.capgemini.dao;

import com.capgemini.domain.CarEntity;

import java.util.Date;
import java.util.List;

/**
 * Car DAO
 */
public interface CarDao extends Dao<CarEntity, Long> {
    /**
     * Find cars of requested type.
     * @param type String containing type of car.
     * @return Cars of requested type.
     */
    List<CarEntity> findCarByType(String type);

    /**
     * Find cars of requested brand.
     * @param brand String containing brand of car.
     * @return Cars of requested brand.
     */
    List<CarEntity> findCarByBrand(String brand);

    /**
     * Find cars assigned to requested employee.
     * @param caretakerId Employee id.
     * @return Cars assigned to requested caretaker.
     */
    List<CarEntity> findCarByCaretaker(long caretakerId);

    /**
     * Find cars that are rented by more than requested number of different clients.
     * @param clientQty Quantity of clients - 1 that should rent car.
     * @return Cars rented by more than requested clients number.
     */
    List<CarEntity> findCarRentByMoreThan(long clientQty);

    /**
     * Find cars that was rented and returned in given period.
     * @param from Date of beginning of requested period.
     * @param to Date of end of requested period.
     * @return Cars rented in given period.
     */
    List<CarEntity> findCarRentedInPeriod(Date from, Date to);
}
