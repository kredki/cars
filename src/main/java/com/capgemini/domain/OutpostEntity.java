package com.capgemini.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "OUTPOST")
public class OutpostEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "ADDRESS", nullable = false, length = 200)
    private String addres;
    @Column(name = "CONTACT_DATA", nullable = false, length = 200)
    private String contactData;

    public OutpostEntity() {
    }

    public OutpostEntity(String addres, String contactData) {
        this.addres = addres;
        this.contactData = contactData;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getContactData() {
        return contactData;
    }

    public void setContactData(String contactData) {
        this.contactData = contactData;
    }
}
