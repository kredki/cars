package com.capgemini.service.impl;

import com.capgemini.dao.CarDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.mappers.CarMapper;
import com.capgemini.service.CarService;
import com.capgemini.types.CarTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarDao carRepository;

    @Override
    public List<CarTO> findCarByType(String type) {
        List<CarEntity> cars = carRepository.findCarByType(type);
        return CarMapper.map2TOs(cars);
    }

    @Override
    public List<CarTO> findCarByBrand(String brand) {
        List<CarEntity> cars = carRepository.findCarByBrand(brand);
        return CarMapper.map2TOs(cars);
    }

    @Override
    public List<CarTO> findCarByCaretaker(long caretakerId) {
        List<CarEntity> cars = carRepository.findCarByCaretaker(caretakerId);
        return CarMapper.map2TOs(cars);
    }
}
