package com.capgemini.service;

import com.capgemini.types.CarTO;

import java.util.List;

public interface CarService {
    List<CarTO> findCarByType(String type);

    List<CarTO> findCarByBrand(String brand);

    List<CarTO> findCarByCaretaker(long caretakerId);
}
