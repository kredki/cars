package com.capgemini.types;

import java.util.HashSet;
import java.util.Set;

public class PositionTO {
    private long id;
    private String name;

    private Set<EmployeeTO> employees = new HashSet<>();

    public PositionTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.employees = builder.employees;
    }

    public class Builder {
        private long id;
        private String name;

        private Set<EmployeeTO> employees = new HashSet<>();

        public void withId(long id) {
            this.id = id;
        }

        public void withName(String name) {
            this.name = name;
        }

        public void withEmployees(Set<EmployeeTO> employees) {
            this.employees.addAll(employees);
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
