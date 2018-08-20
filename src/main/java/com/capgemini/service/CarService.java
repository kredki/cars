package com.capgemini.service;

import com.capgemini.types.CarTO;

import java.util.List;

/**
 * Car Service
 */
public interface CarService {
    /**
     * Find cars by requested type.
     * @param type String containing type of car.
     * @return Cars of requested type.
     */
    List<CarTO> findCarByType(String type);

    /**
     * Find cars by requested brand.
     * @param brand String containing brand of car.
     * @return Cars of requested brand.
     */
    List<CarTO> findCarByBrand(String brand);

    /**
     * Find cars assigned to equested employee.
     * @param caretakerId Employee id.
     * @return Cars assigned to requested caretaker.
     */
    List<CarTO> findCarByCaretaker(long caretakerId);
}
