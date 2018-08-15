package com.capgemini.types;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ClientTO {
    private long id;
    private String firstName;
    private String lastName;
    private Date birthdate;
    private String telephone;
    private String cardNo;
    private String email;

    private Set<RentalTO> rentals = new HashSet<>();

    public ClientTO(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.birthdate = builder.birthdate;
        this.telephone = builder.telephone;
        this.cardNo = builder.cardNo;
        this.email = builder.email;
        this.rentals.addAll(builder.rentals);
    }

    public class Builder {
        private long id;
        private String firstName;
        private String lastName;
        private Date birthdate;
        private String telephone;
        private String cardNo;
        private String email;

        private Set<RentalTO> rentals = new HashSet<>();

        public void withId(long id) {
            this.id = id;
        }

        public void withFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void withLastName(String lastName) {
            this.lastName = lastName;
        }

        public void withBirthdate(Date birthdate) {
            this.birthdate = birthdate;
        }

        public void withTelephone(String telephone) {
            this.telephone = telephone;
        }

        public void withCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public void withEmail(String email) {
            this.email = email;
        }

        public void withRentals(Set<RentalTO> rentals) {
            this.rentals.addAll(rentals);
        }

        public ClientTO build() {
            checkBeforeBuild();
            return new ClientTO(this);
        }

        private void checkBeforeBuild() {
            if (firstName == null || lastName == null || birthdate == null || telephone == null || cardNo == null
                    || email == null) {
                throw new RuntimeException("Incorrect rental to be created");
            }
        }
    }
}
