package com.capgemini.types;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutpostTO {
    private long id;
    private AddressTO address;
    private String contactData;

    public OutpostTO(Builder builder) {
        this.id = builder.id;
        this.address = builder.address;
        this.contactData = builder.contactData;
    }

    public static class Builder {
        private long id;
        private AddressTO address;
        private String contactData;

        public Builder withId(long id) {
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
                throw new RuntimeException("Incorrect outpost to be created");
            }
        }
    }
}
