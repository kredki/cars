package com.capgemini.types;

import com.capgemini.Exceptions.IncorrectObjectException;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ClientTO {
    private long id;
    private String firstName;
    private String lastName;
    private Date birthdate;
    private String telephone;
    private String cardNo;
    private String email;

    public ClientTO(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.birthdate = builder.birthdate;
        this.telephone = builder.telephone;
        this.cardNo = builder.cardNo;
        this.email = builder.email;
    }

    public static class Builder {
        private long id;
        private String firstName;
        private String lastName;
        private Date birthdate;
        private String telephone;
        private String cardNo;
        private String email;

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withBirthdate(Date birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public Builder withTelephone(String telephone) {
            this.telephone = telephone;
            return this;
        }

        public Builder withCardNo(String cardNo) {
            this.cardNo = cardNo;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public ClientTO build() {
            checkBeforeBuild();
            return new ClientTO(this);
        }

        private void checkBeforeBuild() {
            if (firstName == null || lastName == null || birthdate == null || telephone == null || cardNo == null
                    || email == null) {
                throw new IncorrectObjectException("Incorrect rental to be created");
            }
        }
    }
}
