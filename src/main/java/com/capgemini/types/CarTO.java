package com.capgemini.types;

import com.capgemini.Exceptions.IncorrectObjectException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarTO {
    private Long id;
    private String brandName;
    private Integer productionYear;
    private Integer engineCapacity;
    private Integer power;
    private Integer mileage;
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
        private Long id;
        private String brandName;
        private Integer productionYear;
        private Integer engineCapacity;
        private Integer power;
        private Integer mileage;
        private String carType;
        private String color;

        public Builder() {
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withBrandName(String brandName) {
            this.brandName = brandName;
            return this;
        }

        public Builder withProductionYear(Integer productionYear) {
            this.productionYear = productionYear;
            return this;
        }

        public Builder withEngineCapacity(Integer engineCapacity) {
            this.engineCapacity = engineCapacity;
            return this;
        }

        public Builder withPower(Integer power) {
            this.power = power;
            return this;
        }

        public Builder withMileage(Integer mileage) {
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
            if (brandName == null || productionYear == null || engineCapacity == null || power == 0 || mileage == null
                    || carType == null || color == null) {
                throw new IncorrectObjectException("Incorrect car to be created");
            }
        }
    }
}
