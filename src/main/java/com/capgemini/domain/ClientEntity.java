package com.capgemini.domain;

import com.capgemini.Exceptions.IncorrectObjectException;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CLIENTS")
public class ClientEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "FIRST_NAME", nullable = false, length = 50)
    private String firstName;
    @Column(name = "LAST_NAME", nullable = false, length = 50)
    private String lastName;
    @Column(name = "BIRTHDATE", nullable = false)
    private Date birthdate;
    @Column(name = "TELEPHONE", nullable = false, length = 50)
    private String telephone;
    @Column(name = "CARD_NO", nullable = false, length = 50)
    private String cardNo;
    @Column(name = "EMAIL", nullable = false, length = 254)
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    private Set<RentalEntity> rentals = new HashSet<>();

    public ClientEntity() {
    }

    public ClientEntity(String firstName, String lastName, Date birthdate, String telephone, String cardNo,
                        String email, Set<RentalEntity> rentals) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.telephone = telephone;
        this.cardNo = cardNo;
        this.email = email;
        this.rentals = rentals;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RentalEntity> getRentals() {
        return rentals;
    }

    public void setRentals(Set<RentalEntity> rentals) {
        this.rentals = rentals;
    }

    public ClientEntity(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.birthdate = builder.birthdate;
        this.telephone = builder.telephone;
        this.cardNo = builder.cardNo;
        this.email = builder.email;
        this.rentals.addAll(builder.rentals);
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private Date birthdate;
        private String telephone;
        private String cardNo;
        private String email;

        private Set<RentalEntity> rentals = new HashSet<>();

        public Builder withId(Long id) {
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

        public Builder withRentals(Set<RentalEntity> rentals) {
            this.rentals.addAll(rentals);
            return this;
        }

        public ClientEntity build() {
            checkBeforeBuild();
            return new ClientEntity(this);
        }

        private void checkBeforeBuild() {
            if (firstName == null || lastName == null || birthdate == null || telephone == null || cardNo == null
                    || email == null) {
                throw new IncorrectObjectException("Incorrect rental to be created");
            }
        }
    }
}
