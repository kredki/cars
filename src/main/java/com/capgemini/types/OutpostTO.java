package com.capgemini.types;

import com.capgemini.Exceptions.IncorrectObjectException;
import lombok.Getter;
import lombok.Setter;

/**
 * TO for outpost
 */
@Getter
@Setter
public class OutpostTO {
    private Long id;
    private AddressTO address;
    private String contactData;

    public OutpostTO(Builder builder) {
        this.id = builder.id;
        this.address = builder.address;
        this.contactData = builder.contactData;
    }

    public static class Builder {
        private Long id;
        private AddressTO address;
        private String contactData;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withAddress(AddressTO address) {
            this.address = address;
            return this;
        }

        public Builder withContactData(String  contactData) {
            this.contactData = contactData;
            return this;
        }

        public OutpostTO build() {
            checkBeforeBuild();
            return new OutpostTO(this);
        }

        private void checkBeforeBuild() {
            if (address == null || contactData == null) {
                throw new IncorrectObjectException("Incorrect outpost to be created");
            }
        }
    }
}
