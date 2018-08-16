package com.capgemini.service;

import com.capgemini.types.CarTO;

import java.util.List;

/**
 * Car Service
 */
public interface CarService {
    /**
     *
     * @param type
     * @return Cars of requested type.
     */
    List<CarTO> findCarByType(String type);

    /**
     *
     * @param brand
     * @return Cars of requested brand.
     */
    List<CarTO> findCarByBrand(String brand);

    /**
     *
     * @param caretakerId
     * @return Cars assigned to requested caretaker.
     */
    List<CarTO> findCarByCaretaker(long caretakerId);
}
