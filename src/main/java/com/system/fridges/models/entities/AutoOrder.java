package com.system.fridges.models.entities;


import com.system.fridges.models.transferObjects.fridgeObjects.FridgeOrder;
import com.system.fridges.models.transferObjects.userObjects.UserOrder;
import jakarta.persistence.*;

import java.time.LocalDateTime;


/** These annotations must be here. Annotations NamedNativeQuery, SqlResultSetMapping parse data
    from the custom query and put this data into the DTO object.
 */
@NamedNativeQuery(
        name = "FridgeOrderQuery",
        query =
                "SELECT ar.order_id as order_id, ar.date_delivery as date_delivery, us.name as name, us.surname as surname, us.patronymic as patronymic, us.email as email FROM auto_order as ar " +
                        "LEFT JOIN access as ac ON ar.access_order = ac.access_id " +
                        "LEFT JOIN user as us ON ac.user_access = us.user_id where ac.fridge_access = :fridgeId",
        resultSetMapping = "FridgeOrderMapping"
)
@SqlResultSetMapping(
        name = "FridgeOrderMapping",
        classes = @ConstructorResult(
                targetClass = FridgeOrder.class,
                columns = {
                        @ColumnResult(name = "order_id", type = Integer.class),
                        @ColumnResult(name = "date_delivery", type = LocalDateTime.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "surname", type = String.class),
                        @ColumnResult(name = "patronymic", type = String.class),
                        @ColumnResult(name = "email", type = String.class)
                }
        )
)

@NamedNativeQuery(
        name = "UserOrderQuery",
        query =
                "SELECT ar.date_delivery as date_delivery, ar.number as number, ac.fridge_access as fridge_access, p.name as name, p.weight as weight FROM auto_order as ar \n" +
                        "            LEFT JOIN access as ac ON ar.access_order = ac.access_id \n" +
                        "            LEFT JOIN product as p ON ar.product_id = p.product_id WHERE ac.user_access = :userId",
        resultSetMapping = "UserOrderMapping"
)
@SqlResultSetMapping(
        name = "UserOrderMapping",
        classes = @ConstructorResult(
                targetClass = UserOrder.class,
                columns = {
                        @ColumnResult(name = "date_delivery", type = LocalDateTime.class),
                        @ColumnResult(name = "number", type = Integer.class),
                        @ColumnResult(name = "fridge_access", type = Integer.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "weight", type = Double.class)
                }
        )
)


@Entity
@Table(name = "auto_order")
public class AutoOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "date_delivery", nullable = false)
    private LocalDateTime dateDelivery;

    @Column(name = "number", nullable = false)
    private int number;

    @ManyToOne
    @JoinColumn(name = "access_order", nullable = false)
    private Access access;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public AutoOrder(LocalDateTime dateDelivery, int number, Access access, Product product) {
        this.dateDelivery = dateDelivery;
        this.number = number;
        this.access = access;
        this.product = product;
    }

    public AutoOrder() {
    }

    public int getOrderId() {
        return orderId;
    }

    public LocalDateTime getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(LocalDateTime dateDelivery) {
        this.dateDelivery = dateDelivery;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
