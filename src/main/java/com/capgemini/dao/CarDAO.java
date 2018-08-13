package com.capgemini.dao;

import com.capgemini.domain.CarEntity;

import java.util.List;

public interface CarDAO extends Dao<CarEntity, Long> {
    List<CarEntity> findCarByType(String type);

    List<CarEntity> findCarByBrand(String brand);

    List<CarEntity> findCarByCaretaker(long caretakerId);

    public void addCaretaker(long caretakerId);
}
