package com.capgemini.dao.impl;

import com.capgemini.dao.PositioDao;
import com.capgemini.domain.PositionEntity;
import org.springframework.stereotype.Repository;

@Repository
public class PositionDaoImpl extends AbstractDao<PositionEntity, Long> implements PositioDao {
}
