package com.capgemini.mappers;

import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.RentalEntity;
import com.capgemini.types.ClientTO;
import com.capgemini.types.RentalTO;

import java.util.Set;
import java.util.stream.Collectors;

public class ClientMapper {
    public static ClientTO toTO(ClientEntity client) {
        if (client == null) {
            return null;
        }
        Set<RentalTO> rentalTOs = RentalMapper.map2TOs(client.getRentals());

        return new ClientTO.Builder().withBirthdate(client.getBirthdate()).withCardNo(client.getCardNo())
                .withEmail(client.getEmail()).withFirstName(client.getFirstName()).withId(client.getId())
                .withLastName(client.getLastName()).withRentals(rentalTOs).withTelephone(client.getTelephone())
                .build();
    }

    public static ClientEntity toEntity(ClientTO client) {
        if (client == null) {
            return null;
        }
        Set<RentalEntity> rentalEntities = RentalMapper.map2Entities(client.getRentals());

        return new ClientEntity.Builder().withBirthdate(client.getBirthdate()).withCardNo(client.getCardNo())
                .withEmail(client.getEmail()).withFirstName(client.getFirstName()).withId(client.getId())
                .withLastName(client.getLastName()).withRentals(rentalEntities).withTelephone(client.getTelephone())
                .build();
    }

    public static Set<ClientTO> map2TOs (Set<ClientEntity> clients) {
        return clients.stream().map(ClientMapper::toTO).collect(Collectors.toSet());
    }

    public static Set<ClientEntity> map2Entities (Set<ClientTO> clients) {
        return clients.stream().map(ClientMapper::toEntity).collect(Collectors.toSet());
    }
}
