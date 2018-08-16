package com.capgemini.types;

import com.capgemini.Exceptions.IncorrectObjectException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PositionTO {
    private long id;
    private String name;

    public PositionTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public static class Builder {
        private long id;
        private String name;

        public Builder withId(long id) {
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
