package com.capgemini.domain;

import com.capgemini.Exceptions.IncorrectObjectException;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Entity for rental.
 */
@Entity
@Table(name = "RENTALS")
public class RentalEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "START_DATE", nullable = false)
    private Date startDate;
    @Column(name = "END_DATE")
    private Date endDate;
    @Column(name = "COST", nullable = false)
    private BigDecimal cost;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.MERGE})
    @JoinColumn(name = "CAR_ID", nullable = false)
    private CarEntity car;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.MERGE})
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private ClientEntity client;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.MERGE})
    @JoinColumn(name = "START_OUTPOST_ID", nullable = false)
    private OutpostEntity startOutpost;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.MERGE})
    @JoinColumn(name = "END_OUTPOST_ID")
    private OutpostEntity endOutpost;

    public RentalEntity() {
    }

    public RentalEntity(Date startDate, Date endDate, BigDecimal cost) {
        this.startDate = new GregorianCalendar(startDate.getYear(), startDate.getMonth(), startDate.getDay()).getTime();
        this.endDate = new GregorianCalendar(endDate.getYear(), endDate.getMonth(), endDate.getDay()).getTime();
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return new GregorianCalendar(startDate.getYear()+1900, startDate.getMonth(), startDate.getDay()).getTime();
    }

    public void setStartDate(Date startDate) {
        this.startDate = new GregorianCalendar(startDate.getYear()+1900, startDate.getMonth(), startDate.getDay()).getTime();
    }

    public Date getEndDate() {
        return new GregorianCalendar(endDate.getYear()+1900, endDate.getMonth(), endDate.getDay()).getTime();
    }

    public void setEndDate(Date endDate) {
        this.endDate = new GregorianCalendar(endDate.getYear()+1900, endDate.getMonth(), endDate.getDay()).getTime();
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public OutpostEntity getStartOutpost() {
        return startOutpost;
    }

    public void setStartOutpost(OutpostEntity startOutpost) {
        this.startOutpost = startOutpost;
    }

    public OutpostEntity getEndOutpost() {
        return endOutpost;
    }

    public void setEndOutpost(OutpostEntity endOutpost) {
        this.endOutpost = endOutpost;
    }

    public RentalEntity(Builder builder) {
        this.id = builder.id;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.cost = builder.cost;
        this.car = builder.car;
        this.client = builder.client;
        this.startOutpost = builder.startOutpost;
        this.endOutpost = builder.endOutpost;
    }

    public static class Builder {
        private Long id;
        private Date startDate;
        private Date endDate;
        private BigDecimal cost;

        private CarEntity car;
        private ClientEntity client;
        private OutpostEntity startOutpost;
        private OutpostEntity endOutpost;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withStartDate(Date startDate) {
            this.startDate = new GregorianCalendar(startDate.getYear()+1900, startDate.getMonth(), startDate.getDay()).getTime();
            return this;
        }

        public Builder withEndDate(Date endDate) {
            this.endDate = new GregorianCalendar(endDate.getYear()+1900, endDate.getMonth(), endDate.getDay()).getTime();
            return this;
        }

        public Builder withCost(BigDecimal cost) {
            this.cost = cost;
            return this;
        }

        public Builder withCar(CarEntity car) {
            this.car = car;
            return this;
        }

        public Builder withClient(ClientEntity client) {
            this.client = client;
            return this;
        }

        public Builder withStartOutpost(OutpostEntity startOutpost) {
            this.startOutpost = startOutpost;
            return this;
        }

        public Builder withEndOutpost(OutpostEntity endOutpost) {
            this.endOutpost = endOutpost;
            return this;
        }

        public RentalEntity build() {
            checkBeforeBuild();
            return new RentalEntity(this);
        }

        private void checkBeforeBuild() {
            if (startDate == null || cost == null || car == null || client == null || startOutpost == null) {
                throw new IncorrectObjectException("Incorrect rental to be created");
            }
        }
    }
}
