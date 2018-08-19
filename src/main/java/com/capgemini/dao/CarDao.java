package com.capgemini.dao;

import com.capgemini.domain.CarEntity;

import java.util.Date;
import java.util.List;

/**
 * Car DAO
 */
public interface CarDao extends Dao<CarEntity, Long> {
    /**
     *
     * @param type
     * @return Cars of requested type.
     */
    List<CarEntity> findCarByType(String type);

    /**
     *
     * @param brand
     * @return Cars of requested brand.
     */
    List<CarEntity> findCarByBrand(String brand);

    /**
     *
     * @param caretakerId
     * @return Cars assigned to requested caretaker.
     */
    List<CarEntity> findCarByCaretaker(long caretakerId);

    /**
     *
     * @param clientQty Quantity of clients - 1 that should rent car.
     * @return Cars rented by more than requested clients number.
     */
    List<CarEntity> findCarRentByMoreThan(int clientQty);

    /**
     *
     * @param from
     * @param to
     * @return Cars rented in given period.
     */
    List<CarEntity> findCarRentedInPeriod(Date from, Date to);
}
