package com.capgemini.dao.impl;

import com.capgemini.dao.RentalDao;
import com.capgemini.domain.RentalEntity;
import org.springframework.stereotype.Repository;

/**
 * Rental DAO implementation
 */
@Repository
public class RentalDaoImpl extends AbstractDao<RentalEntity, Long> implements RentalDao {
}
