package com.capgemini.types;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class RentalTO {
    private long id;
    private String brandName;
    private Date startDate;
    private Date endDate;
    private BigDecimal cost;

    private CarTO car;
    private ClientTO client;
    private OutpostTO startOutpost;
    private OutpostTO endOutpost;

    public RentalTO(Builder builder) {
        this.id = builder.id;
        this.brandName = builder.brandName;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.cost = builder.cost;
        this.car = builder.car;
        this.client = builder.client;
        this.startOutpost = builder.startOutpost;
        this.endOutpost = builder.endOutpost;
    }

    public static class Builder {
        private long id;
        private String brandName;
        private Date startDate;
        private Date endDate;
        private BigDecimal cost;

        private CarTO car;
        private ClientTO client;
        private OutpostTO startOutpost;
        private OutpostTO endOutpost;

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withBrandName(String brandName) {
            this.brandName = brandName;
            return this;
        }

        public Builder withStartDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder withEndDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder withCost(BigDecimal cost) {
            this.cost = cost;
            return this;
        }

        public Builder withCar(CarTO car) {
            this.car = car;
            return this;
        }

        public Builder withClient(ClientTO client) {
            this.client = client;
            return this;
        }

        public Builder withStartOutpost(OutpostTO startOutpost) {
            this.startOutpost = startOutpost;
            return this;
        }

        public Builder withEndOutpost(OutpostTO endOutpost) {
            this.endOutpost = endOutpost;
            return this;
        }

        public RentalTO build() {
            checkBeforeBuild();
            return new RentalTO(this);
        }

        private void checkBeforeBuild() {
            if (brandName == null || startDate == null || cost == null || car == null || client == null || startOutpost == null) {
                throw new RuntimeException("Incorrect rental to be created");
            }
        }
    }
}
