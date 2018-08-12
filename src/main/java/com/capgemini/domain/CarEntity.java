package com.capgemini.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CAR")
public class CarEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "BRAND_NAME", nullable = false, length = 50)
    private String brandName;
    @Column(name = "PRODUCTIOIN_YEAR", nullable = false)
    private int productionYear;
    @Column(name = "ENGINE_CAPACITY", nullable = false)
    private int engineCapacity;
    @Column(name = "POWER", nullable = false)
    private int power;
    @Column(name = "MILEAGE", nullable = false)
    private int mileage;
    @Column(name = "CAR_TYPE", nullable = false, length = 50)
    private String carType;
    @Column(name = "COLOR", nullable = false, length = 50)
    private String color;

    public CarEntity() {
    }

    public CarEntity(String brandName, int productionYear, int engineCapacity, int power, int mileage, String carType, String color) {
        this.brandName = brandName;
        this.productionYear = productionYear;
        this.engineCapacity = engineCapacity;
        this.power = power;
        this.mileage = mileage;
        this.carType = carType;
        this.color = color;
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

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
