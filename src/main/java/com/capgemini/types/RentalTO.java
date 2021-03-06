package com.capgemini.types;

import com.capgemini.Exceptions.IncorrectObjectException;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * TO for rental
 */
@Getter
@Setter
public class RentalTO {
    private Long id;
    private Date startDate;
    private Date endDate;
    private BigDecimal cost;

    public Date getStartDate() {
        return new GregorianCalendar(startDate.getYear()+1900, startDate.getMonth(), startDate.getDay()).getTime();
    }

    public void setStartDate(Date startDate) {
        this.startDate = new GregorianCalendar(startDate.getYear()+1900, startDate.getMonth(), startDate.getDay())
                .getTime();
    }

    public Date getEndDate() {
        return new GregorianCalendar(endDate.getYear()+1900, endDate.getMonth(), endDate.getDay()).getTime();
    }

    public void setEndDate(Date endDate) {
        this.endDate = new GregorianCalendar(endDate.getYear()+1900, endDate.getMonth(), endDate.getDay()).getTime();
    }

    public RentalTO(Builder builder) {
        this.id = builder.id;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.cost = builder.cost;
    }

    public static class Builder {
        private Long id;
        private Date startDate;
        private Date endDate;
        private BigDecimal cost;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withStartDate(Date startDate) {
            this.startDate = new GregorianCalendar(startDate.getYear()+1900, startDate.getMonth(), startDate.getDay())
                    .getTime();
            return this;
        }

        public Builder withEndDate(Date endDate) {
            this.endDate = new GregorianCalendar(endDate.getYear()+1900, endDate.getMonth(), endDate.getDay())
                    .getTime();
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
            if (startDate == null || cost == null) {
                throw new IncorrectObjectException("Incorrect rental to be created");
            }
        }
    }
}
