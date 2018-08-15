package com.capgemini.types;

import com.capgemini.domain.Address;

import java.util.HashSet;
import java.util.Set;

public class OutpostTO {
    private long id;
    private Address address;
    private String contactData;

    private Set<EmployeeTO> employees = new HashSet<EmployeeTO>();
    private Set<RentalTO> startRentals = new HashSet<RentalTO>();
    private Set<RentalTO> endRentals = new HashSet<RentalTO>();

    public OutpostTO(Builder builder) {
        this.id = builder.id;
        this.address = builder.address;
        this.contactData = builder.contactData;
        this.employees = builder.employees;
        this.startRentals.addAll(builder.startRentals);
        this.endRentals.addAll(builder.endRentals);
    }

    public class Builder {
        private long id;
        private Address address;
        private String contactData;

        private Set<EmployeeTO> employees = new HashSet<EmployeeTO>();
        private Set<RentalTO> startRentals = new HashSet<RentalTO>();
        private Set<RentalTO> endRentals = new HashSet<RentalTO>();

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder withContactData(String  contactData) {
            this.contactData = contactData;
            return this;
        }

        public Builder withEmployees(Set<EmployeeTO> employees) {
            this.employees.addAll(employees);
            return this;
        }

        public Builder withStartRentals(Set<RentalTO> startRentals) {
            this.startRentals.addAll(startRentals);
            return this;
        }

        public Builder withEndRentals(Set<RentalTO> endRentals) {
            this.endRentals.addAll(endRentals);
            return this;
        }

        public OutpostTO build() {
            checkBeforeBuild();
            return new OutpostTO(this);
        }

        private void checkBeforeBuild() {
            if (address == null || contactData == null) {
                throw new RuntimeException("Incorrect outpost to be created");
            }
        }
    }
}
