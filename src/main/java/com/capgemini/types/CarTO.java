package com.capgemini.types;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarTO {
    private long id;
    private String brandName;
    private int productionYear;
    private int engineCapacity;
    private int power;
    private int mileage;
    private String carType;
    private String color;

    public CarTO(Builder builder) {
        this.id = builder.id;
        this.brandName = builder.brandName;
        this.productionYear = builder.productionYear;
        this.engineCapacity = builder.engineCapacity;
        this.power = builder.power;
        this.mileage = builder.mileage;
        this.carType = builder.carType;
        this.color = builder.color;
    }

    public static class Builder {
        private long id;
        private String brandName;
        private int productionYear;
        private int engineCapacity;
        private int power;
        private int mileage;
        private String carType;
        private String color;

        public Builder() {
        }

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withBrandName(String brandName) {
            this.brandName = brandName;
            return this;
        }

        public Builder withProductionYear(int productionYear) {
            this.productionYear = productionYear;
            return this;
        }

        public Builder withEngineCapacity(int engineCapacity) {
            this.engineCapacity = engineCapacity;
            return this;
        }

        public Builder withPower(int power) {
            this.power = power;
            return this;
        }

        public Builder withMileage(int mileage) {
            this.mileage = mileage;
            return this;
        }

        public Builder withCarType(String carType) {
            this.carType = carType;
            return this;
        }

        public Builder withColor(String color) {
            this.color = color;
            return this;
        }

        public CarTO build() {
            checkBeforeBuild();
            return new CarTO(this);
        }

        private void checkBeforeBuild() {
            if (brandName == null || productionYear == 0 || engineCapacity == 0 || power == 0 || mileage == 0
                    || carType == null || color == null) {
                throw new RuntimeException("Incorrect car to be created");
            }
        }
    }
}
