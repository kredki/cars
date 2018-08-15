package com.capgemini.types;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class PositionTO {
    private long id;
    private String name;

    private Set<EmployeeTO> employees = new HashSet<>();

    public PositionTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.employees = builder.employees;
    }

    public static class Builder {
        private long id;
        private String name;

        private Set<EmployeeTO> employees = new HashSet<>();

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withEmployees(Set<EmployeeTO> employees) {
            this.employees.addAll(employees);
            return this;
        }

        public PositionTO build() {
            checkBeforeBuild();
            return new PositionTO(this);
        }

        private void checkBeforeBuild() {
            if (name == null) {
                throw new RuntimeException("Incorrect position to be created");
            }
        }
    }
}
