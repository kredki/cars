package com.capgemini.types;

import java.math.BigDecimal;
import java.util.Date;

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

    public class Builder {
        private long id;
        private String brandName;
        private Date startDate;
        private Date endDate;
        private BigDecimal cost;

        private CarTO car;
        private ClientTO client;
        private OutpostTO startOutpost;
        private OutpostTO endOutpost;

        public void withId(long id) {
            this.id = id;
        }

        public void withBrandName(String brandName) {
            this.brandName = brandName;
        }

        public void withStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public void withEndDate(Date endDate) {
            this.endDate = endDate;
        }

        public void withCost(BigDecimal cost) {
            this.cost = cost;
        }

        public void withCar(CarTO car) {
            this.car = car;
        }

        public void withClient(ClientTO client) {
            this.client = client;
        }

        public void withStartOutpost(OutpostTO startOutpost) {
            this.startOutpost = startOutpost;
        }

        public void withEndOutpost(OutpostTO endOutpost) {
            this.endOutpost = endOutpost;
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
