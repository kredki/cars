package com.capgemini.mappers;

import com.capgemini.domain.PositionEntity;
import com.capgemini.types.PositionTO;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper for position
 */
public class PositionMapper {
    /**
     *
     * @param position Object to map.
     * @return Mapped object.
     */
    public static PositionTO toTO(PositionEntity position) {
        if (position == null) {
            return null;
        }

        return new PositionTO.Builder().withName(position.getName()).build();
    }

    /**
     *
     * @param position Object to map.
     * @return Mapped object.
     */
    public static PositionEntity toEntity(PositionTO position) {
        if (position == null) {
            return null;
        }

        return new PositionEntity.Builder().withId(position.getId()).withName(position.getName()).build();
    }

    /**
     *
     * @param positions Objects to map.
     * @return Mapped objects.
     */
    public static Set<PositionTO> map2TOs (Set<PositionEntity> positions) {
        return positions.stream().map(PositionMapper::toTO).collect(Collectors.toSet());
    }

    /**
     *
     * @param positions Objects to map.
     * @return Mapped objects.
     */
    public static Set<PositionEntity> map2Entities (Set<PositionTO> positions) {
        return positions.stream().map(PositionMapper::toEntity).collect(Collectors.toSet());
    }
}
