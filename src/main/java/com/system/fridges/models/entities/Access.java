package com.system.fridges.models.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "access")
public class Access {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "access_id")
    private int accessId;

    @ManyToOne
    @JoinColumn(name = "user_access", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "fridge_access", nullable = false)
    private Fridge fridge;

    public Access(User user, Fridge fridge) {
        this.user = user;
        this.fridge = fridge;
    }

    public Access() {
    }

    public int getAccessId() {
        return accessId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Fridge getFridge() {
        return fridge;
    }

    public void setFridge(Fridge fridge) {
        this.fridge = fridge;
    }
}
