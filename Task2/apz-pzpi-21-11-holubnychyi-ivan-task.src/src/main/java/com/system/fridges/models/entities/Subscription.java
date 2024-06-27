package com.system.fridges.models.entities;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private int subscriptionId;

    @Column(name = "begin_date", nullable = false)
    private Date beginDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "price", nullable = false)
    private int price;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Subscription(Date beginDate, Date endDate, int price, User user) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.price = price;
        this.user = user;
    }

    public Subscription() {
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setDate(Date beginDate, Date endDate) {
        if (beginDate.getTime() < endDate.getTime()) {
            this.beginDate = beginDate;
            this.endDate = endDate;
        }
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
