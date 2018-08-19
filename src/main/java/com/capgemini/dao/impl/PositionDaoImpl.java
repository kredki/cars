package com.capgemini.dao.impl;

import com.capgemini.dao.PositionDao;
import com.capgemini.domain.PositionEntity;
import org.springframework.stereotype.Repository;

/**
 * Position DAO implementation
 */
@Repository
public class PositionDaoImpl extends AbstractDao<PositionEntity, Long> implements PositionDao {
}
