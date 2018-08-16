package com.capgemini.domain;

import com.capgemini.Exceptions.IncorrectObjectException;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "RENTALS")
public class RentalEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "BRAND_NAME", nullable = false, length = 50)
    private String brandName;
    @Column(name = "START_DATE", nullable = false)
    private Date startDate;
    @Column(name = "END_DATE")
    private Date endDate;
    @Column(name = "COST", nullable = false)
    private BigDecimal cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_ID", nullable = false)
    private CarEntity car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private ClientEntity client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "START_OUTPOST_ID", nullable = false)
    private OutpostEntity startOutpost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "END_OUTPOST_ID")
    private OutpostEntity endOutpost;

    public RentalEntity() {
    }

    public RentalEntity(String brandName, Date startDate, Date endDate, BigDecimal cost) {
        this.brandName = brandName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
        private Long id;
        private String brandName;
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
            if (brandName == null || startDate == null || cost == null || car == null || client == null || startOutpost == null) {
                throw new IncorrectObjectException("Incorrect rental to be created");
            }
        }
    }
}
