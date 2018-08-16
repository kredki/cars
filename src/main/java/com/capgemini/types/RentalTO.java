package com.capgemini.types;

import com.capgemini.Exceptions.IncorrectObjectException;
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

    public RentalTO(Builder builder) {
        this.id = builder.id;
        this.brandName = builder.brandName;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.cost = builder.cost;
    }

    public static class Builder {
        private long id;
        private String brandName;
        private Date startDate;
        private Date endDate;
        private BigDecimal cost;

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

        public RentalTO build() {
            checkBeforeBuild();
            return new RentalTO(this);
        }

        private void checkBeforeBuild() {
            if (brandName == null || startDate == null || cost == null) {
                throw new IncorrectObjectException("Incorrect rental to be created");
            }
        }
    }
}
