package com.progmatic.SpringIoc.model;

import jakarta.persistence.*;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String email;
    private String tel;
    private String misc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name +
                ": \n\temail: " + email +
                ",\n\ttel: " + tel +
                ",\n\tmisc: " + misc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }

    public boolean match(String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }

        String sre = text.toLowerCase();
        if (this.name != null && this.name.toLowerCase().contains(sre)) {
            return true;
        }
        if (this.misc != null && this.misc.toLowerCase().contains(sre)) {
            return true;
        }
        if (this.tel != null && this.tel.toLowerCase().contains(sre)) {
            return true;
        }
        if (this.email != null && this.email.toLowerCase().contains(sre)) {
            return true;
        }
        return false;
    }
}
