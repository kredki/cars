package com.capgemini.domain;

import com.capgemini.Exceptions.IncorrectObjectException;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CARS")
public class CarEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "BRAND_NAME", nullable = false, length = 50)
    private String brandName;
    @Column(name = "PRODUCTIOIN_YEAR", nullable = false)
    private Integer productionYear;
    @Column(name = "ENGINE_CAPACITY", nullable = false)
    private Integer engineCapacity;
    @Column(name = "POWER", nullable = false)
    private Integer power;
    @Column(name = "MILEAGE", nullable = false)
    private Integer mileage;
    @Column(name = "CAR_TYPE", nullable = false, length = 50)
    private String carType;
    @Column(name = "COLOR", nullable = false, length = 50)
    private String color;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "car")
    private Set<RentalEntity> rentals = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "cars")
    private Set<EmployeeEntity> employees = new HashSet<>();

    public CarEntity() {
    }

    public CarEntity(String brandName, Integer productionYear, Integer engineCapacity, Integer power, Integer mileage,
                     String carType, String color, Set<RentalEntity> rentals, Set<EmployeeEntity> employees) {
        this.brandName = brandName;
        this.productionYear = productionYear;
        this.engineCapacity = engineCapacity;
        this.power = power;
        this.mileage = mileage;
        this.carType = carType;
        this.color = color;
        this.rentals = rentals;
        this.employees = employees;
    }

    public CarEntity(CarEntityBuilder builder) {
        this.id = builder.id;
        this.brandName = builder.brandName;
        this.productionYear = builder.productionYear;
        this.engineCapacity = builder.engineCapacity;
        this.power = builder.power;
        this.mileage = builder.mileage;
        this.carType = builder.carType;
        this.color = builder.color;
        this.rentals = builder.rentals;
        this.employees = builder.employees;
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

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public Integer getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(Integer engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
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

    public Set<RentalEntity> getRentals() {
        return rentals;
    }

    public void setRentals(Set<RentalEntity> rentals) {
        this.rentals = rentals;
    }

    public Set<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeEntity> employees) {
        this.employees = employees;
    }

    public void addRental(RentalEntity rental) {
        this.rentals.add(rental);
        rental.setCar(this);
    }

    public static class CarEntityBuilder {
        private Long id;
        private String brandName;
        private Integer productionYear;
        private Integer engineCapacity;
        private Integer power;
        private Integer mileage;
        private String carType;
        private String color;
        private Set<RentalEntity> rentals = new HashSet<>();
        private Set<EmployeeEntity> employees = new HashSet<>();


        public CarEntityBuilder() {
            super();
        }

        public CarEntityBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public CarEntityBuilder withBrandName(String brandName) {
            this.brandName = brandName;
            return this;
        }

        public CarEntityBuilder withProductionYear(Integer productionYear) {
            this.productionYear = productionYear;
            return this;
        }

        public CarEntityBuilder withEngineCapacity(Integer engineCapacity) {
            this.engineCapacity = engineCapacity;
            return this;
        }

        public CarEntityBuilder withPower(Integer power) {
            this.power = power;
            return this;
        }

        public CarEntityBuilder withMileage(Integer mileage) {
            this.mileage = mileage;
            return this;
        }

        public CarEntityBuilder withCarType(String carType) {
            this.carType = carType;
            return this;
        }

        public CarEntityBuilder withColor(String color) {
            this.color = color;
            return this;
        }

        public CarEntityBuilder withRentals(Set<RentalEntity> rentals) {
            this.rentals.addAll(rentals);
            return this;
        }

        public CarEntityBuilder withRental(RentalEntity rental) {
            this.rentals.add(rental);
            return this;
        }

        public CarEntityBuilder withEmployees(Set<EmployeeEntity> employees) {
            this.employees.addAll(employees);
            return this;
        }

        public CarEntityBuilder withEmployee(EmployeeEntity employee) {
            this.employees.add(employee);
            return this;
        }

        public CarEntity build() {
            checkBeforeBuild();
            return new CarEntity(this);
        }

        private void checkBeforeBuild() {
            if (brandName == null || productionYear == null || engineCapacity == null || power == null || mileage == null
                    || carType == null || color == null) {
                throw new IncorrectObjectException("Incorrect car to be created");
            }

        }
    }
}
