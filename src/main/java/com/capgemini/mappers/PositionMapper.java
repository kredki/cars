package com.capgemini.mappers;

import com.capgemini.domain.PositionEntity;
import com.capgemini.types.PositionTO;

import java.util.Set;
import java.util.stream.Collectors;

public class PositionMapper {
    public static PositionTO toTO(PositionEntity position) {
        if (position == null) {
            return null;
        }

        return new PositionTO.Builder().withName(position.getName()).build();
    }

    public static PositionEntity toEntity(PositionTO position) {
        if (position == null) {
            return null;
        }

        return new PositionEntity.Builder().withId(position.getId()).withName(position.getName()).build();
    }

    public static Set<PositionTO> map2TOs (Set<PositionEntity> positions) {
        return positions.stream().map(PositionMapper::toTO).collect(Collectors.toSet());
    }

    public static Set<PositionEntity> map2Entities (Set<PositionTO> positions) {
        return positions.stream().map(PositionMapper::toEntity).collect(Collectors.toSet());
    }
}
