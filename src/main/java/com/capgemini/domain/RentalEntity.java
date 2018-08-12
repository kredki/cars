package com.capgemini.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "RENTAL")
public class RentalEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "BRAND_NAME", nullable = false, length = 50)
    private String brandName;
    @Column(name = "START_DATE", nullable = false)
    private Date startDate;
    @Column(name = "END_DATE")
    private Date endDate;
    @Column(name = "COST", nullable = false)
    private BigDecimal cost;

//    car_id INT NOT NULL,
//    client_id INT NOT NULL,
//    outpost_id_start INT NOT NULL,
//    outpost_id_end INT ,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_ID", nullable = false)
    private CarEntity car;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private ClientEntity client;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "START_OUTPOST_ID", nullable = false)
    private OutpostEntity startOutpost;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "END_OUTPOST_ID", nullable = false)
    private OutpostEntity endOutpost;

    public RentalEntity() {
    }

    public RentalEntity(String brandName, Date startDate, Date endDate, BigDecimal cost) {
        this.brandName = brandName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
