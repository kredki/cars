package com.capgemini.types;

import com.capgemini.Exceptions.IncorrectObjectException;
import lombok.Getter;
import lombok.Setter;

/**
 * TO for position
 */
@Getter
@Setter
public class PositionTO {
    private Long id;
    private String name;

    public PositionTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public static class Builder {
        private Long id;
        private String name;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public PositionTO build() {
            checkBeforeBuild();
            return new PositionTO(this);
        }

        private void checkBeforeBuild() {
            if (name == null) {
                throw new IncorrectObjectException("Incorrect position to be created");
            }
        }
    }
}
