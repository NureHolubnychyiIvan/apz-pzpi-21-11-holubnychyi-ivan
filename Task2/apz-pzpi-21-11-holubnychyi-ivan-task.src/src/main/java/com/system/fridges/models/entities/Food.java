package com.system.fridges.models.entities;


import com.system.fridges.models.transferObjects.foodObjects.FoodInFridge;
import com.system.fridges.models.transferObjects.foodObjects.SpoiledFood;
import com.system.fridges.models.transferObjects.userObjects.UserFood;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;


/** These annotations must be here. Annotations NamedNativeQuery, SqlResultSetMapping parse data
 from the custom query and put this data into the DTO object.
 */
@NamedNativeQuery(
        name = "FoodInFridgeQuery",
        query =
                "SELECT f.name as name, f.number_boxes as number_boxes, f.date_validity as date_validity, t.end_date as end_date, us.name as user_name, us.surname as surname, us.patronymic as patronymic, us.email as email " +
                        "FROM food as f LEFT JOIN transaction as t  ON f.transaction_id = t.transaction_id " +
                        "LEFT JOIN access as ac ON ac.access_id = t.access  LEFT JOIN user as us ON us.user_id = ac.user_access " +
                        "WHERE ac.fridge_access = :fridgeId",
        resultSetMapping = "FoodInFridgeMapping"
)
@SqlResultSetMapping(
        name = "FoodInFridgeMapping",
        classes = @ConstructorResult(
                targetClass = FoodInFridge.class,
                columns = {
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "number_boxes", type = Integer.class),
                        @ColumnResult(name = "date_validity", type = Date.class),
                        @ColumnResult(name = "end_date", type = LocalDateTime.class),
                        @ColumnResult(name = "user_name", type = String.class),
                        @ColumnResult(name = "surname", type = String.class),
                        @ColumnResult(name = "patronymic", type = String.class),
                        @ColumnResult(name = "email", type = String.class)
                }
        )
)


@NamedNativeQuery(
        name = "SpoiledFoodQuery",
        query =
                "SELECT f.name, f.number_boxes, f.date_validity, t.end_date, ac.user_access " +
                        "FROM food as f LEFT JOIN transaction as t  ON f.transaction_id = t.transaction_id " +
                        "LEFT JOIN access as ac ON ac.access_id = t.access " +
                        "WHERE ac.fridge_access = :fridgeId AND f.date_validity < current_date()",
        resultSetMapping = "SpoiledFoodMapping"
)
@SqlResultSetMapping(
        name = "SpoiledFoodMapping",
        classes = @ConstructorResult(
                targetClass = SpoiledFood.class,
                columns = {
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "number_boxes", type = Integer.class),
                        @ColumnResult(name = "date_validity", type = Date.class),
                        @ColumnResult(name = "end_date", type = LocalDateTime.class),
                        @ColumnResult(name = "user_access", type = Integer.class)
                }
        )
)

@NamedNativeQuery(
        name = "UserFoodQuery",
        query =
                "SELECT f.name, f.number_boxes, f.date_validity, t.end_date, ac.fridge_access \n" +
                        "            FROM food as f LEFT JOIN transaction as t  ON f.transaction_id = t.transaction_id\n" +
                        "            LEFT JOIN access as ac ON ac.access_id = t.access WHERE ac.user_access = :userId",
        resultSetMapping = "UserFoodMapping"
)
@SqlResultSetMapping(
        name = "UserFoodMapping",
        classes = @ConstructorResult(
                targetClass = UserFood.class,
                columns = {
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "number_boxes", type = Integer.class),
                        @ColumnResult(name = "date_validity", type = Date.class),
                        @ColumnResult(name = "end_date", type = LocalDateTime.class),
                        @ColumnResult(name = "fridge_access", type = Integer.class)
                }
        )
)

@Entity
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private int foodId;

    @Column(name = "date_validity", nullable = false)
    private Date dateValidity;

    @Column(name = "number_boxes", nullable = false)
    private int numberBoxes;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    public Food(Date dateValidity, int numberBoxes, String name, Transaction transaction) {
        this.dateValidity = dateValidity;
        this.numberBoxes = numberBoxes;
        this.name = name;
        this.transaction = transaction;
    }

    public Food() {
    }

    public int getFoodId() {
        return foodId;
    }

    public Date getDateValidity() {
        return dateValidity;
    }

    public void setDateValidity(Date dateValidity) {
        this.dateValidity = dateValidity;
    }

    public int getNumberBoxes() {
        return numberBoxes;
    }

    public void setNumberBoxes(int numberBoxes) {
        this.numberBoxes = numberBoxes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
