package com.capgemini.dao;

import com.capgemini.domain.CarEntity;

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
}
