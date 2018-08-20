package com.capgemini.service.impl;

import com.capgemini.dao.CarDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.mappers.CarMapper;
import com.capgemini.service.CarService;
import com.capgemini.types.CarTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Car Service Implementation
 */
@Service
public class CarServiceImpl implements CarService {
    CarDao carRepository;

    @Autowired
    public CarServiceImpl(CarDao carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Find cars by requested type.
     * @param type String containing type of car.
     * @return Cars of requested type.
     */
    @Override
    public List<CarTO> findCarByType(String type) {
        List<CarEntity> cars = carRepository.findCarByType(type);
        return CarMapper.map2TOs(cars);
    }

    /**
     * Find cars by requested brand.
     * @param brand String containing brand of car.
     * @return Cars of requested brand.
     */
    @Override
    public List<CarTO> findCarByBrand(String brand) {
        List<CarEntity> cars = carRepository.findCarByBrand(brand);
        return CarMapper.map2TOs(cars);
    }

    /**
     * Find cars assigned to equested employee.
     * @param caretakerId Employee id.
     * @return Cars assigned to requested caretaker.
     */
    @Override
    public List<CarTO> findCarByCaretaker(long caretakerId) {
        List<CarEntity> cars = carRepository.findCarByCaretaker(caretakerId);
        return CarMapper.map2TOs(cars);
    }
}
