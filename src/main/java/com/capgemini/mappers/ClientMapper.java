package com.capgemini.mappers;

import com.capgemini.domain.ClientEntity;
import com.capgemini.types.ClientTO;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper for client
 */
public class ClientMapper {
    /**
     *
     * @param client Object to map.
     * @return Mapped object.
     */
    public static ClientTO toTO(ClientEntity client) {
        if (client == null) {
            return null;
        }

        return new ClientTO.Builder().withBirthdate(client.getBirthdate()).withCardNo(client.getCardNo())
                .withEmail(client.getEmail()).withFirstName(client.getFirstName()).withId(client.getId())
                .withLastName(client.getLastName()).withTelephone(client.getTelephone()).build();
    }

    /**
     *
     * @param client Object to map.
     * @return Mapped object.
     */
    public static ClientEntity toEntity(ClientTO client) {
        if (client == null) {
            return null;
        }

        return new ClientEntity.Builder().withBirthdate(client.getBirthdate()).withCardNo(client.getCardNo())
                .withEmail(client.getEmail()).withFirstName(client.getFirstName()).withId(client.getId())
                .withLastName(client.getLastName()).withTelephone(client.getTelephone()).build();
    }

    /**
     *
     * @param clients Objects to map.
     * @return Mapped objects.
     */
    public static Set<ClientTO> map2TOs (Set<ClientEntity> clients) {
        return clients.stream().map(ClientMapper::toTO).collect(Collectors.toSet());
    }

    /**
     *
     * @param clients Objects to map.
     * @return Mapped objects.
     */
    public static Set<ClientEntity> map2Entities (Set<ClientTO> clients) {
        return clients.stream().map(ClientMapper::toEntity).collect(Collectors.toSet());
    }
}
