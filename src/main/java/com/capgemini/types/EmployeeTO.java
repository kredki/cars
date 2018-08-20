package com.capgemini.types;

import com.capgemini.Exceptions.IncorrectObjectException;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * TO for employee
 */
@Getter
@Setter
public class EmployeeTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;

    public Date getBirthDate() {
        return new GregorianCalendar(birthDate.getYear()+1900, birthDate.getMonth(), birthDate.getDay()).getTime();
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = new GregorianCalendar(birthDate.getYear()+1900, birthDate.getMonth(), birthDate.getDay())
                .getTime();
    }

    public EmployeeTO(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.birthDate = builder.birthDate;
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private Date birthDate;

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

        public Builder withBirthDate(Date birthDate) {
            this.birthDate = new GregorianCalendar(birthDate.getYear()+1900, birthDate.getMonth(), birthDate.getDay())
                    .getTime();
            return this;
        }

        public EmployeeTO build() {
            checkBeforeBuild();
            return new EmployeeTO(this);
        }

        private void checkBeforeBuild() {
            if (firstName == null || lastName == null || birthDate == null) {
                throw new IncorrectObjectException("Incorrect employee to be created");
            }
        }
    }
}
