package com.capgemini.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {
    @Column(name = "STREET", nullable = false, length = 50)
    String street;
    @Column(name = "NO", nullable = false, length = 50)
    String no;
    @Column(name = "CITY", nullable = false, length = 50)
    String city;
    @Column(name = "POSTAL_CODE", nullable = false, length = 50)
    String postalCode;
}
