package com.system.fridges.models.entities;


import com.system.fridges.models.transferObjects.fridgeObjects.FridgeTransactionHistory;
import com.system.fridges.models.transferObjects.userObjects.UserTransactionHistory;
import jakarta.persistence.*;

import java.time.LocalDateTime;


/** These annotations must be here. Annotations NamedNativeQuery, SqlResultSetMapping parse data
 from the custom query and put this data into the DTO object.
 */
@NamedNativeQuery(
        name = "FridgeTransactionHistoryQuery",
        query =
                "SELECT end_date, u.name, u.surname, u.patronymic, u.email  " +
                        "FROM transaction as t LEFT JOIN access as ac ON t.access = ac.access_id " +
                        "LEFT JOIN user as u ON ac.user_access = u.user_id WHERE ac.fridge_access = :fridgeId",
        resultSetMapping = "FridgeTransactionHistoryMapping"
)
@SqlResultSetMapping(
        name = "FridgeTransactionHistoryMapping",
        classes = @ConstructorResult(
                targetClass = FridgeTransactionHistory.class,
                columns = {
                        @ColumnResult(name = "end_date", type = LocalDateTime.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "surname", type = String.class),
                        @ColumnResult(name = "patronymic", type = String.class),
                        @ColumnResult(name = "email", type = String.class)
                }
        )
)

@NamedNativeQuery(
        name = "UserTransactionHistoryQuery",
        query =
                "SELECT end_date, fridge_access FROM transaction as t \n" +
                        " LEFT JOIN access as ac ON t.access = ac.access_id WHERE ac.user_access = :userId",
        resultSetMapping = "UserTransactionHistoryMapping"
)
@SqlResultSetMapping(
        name = "UserTransactionHistoryMapping",
        classes = @ConstructorResult(
                targetClass = UserTransactionHistory.class,
                columns = {
                        @ColumnResult(name = "end_date", type = LocalDateTime.class),
                        @ColumnResult(name = "fridge_access", type = Integer.class),
                }
        )
)


@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int transactionId;

    @Column(name = "begin_date", nullable = false)
    private LocalDateTime beginDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "access", nullable = false)
    private Access access;

    public Transaction(LocalDateTime beginDate, LocalDateTime endDate, Access access) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.access = access;
    }

    public Transaction() {
    }

    public int getTransactionId() {
        return transactionId;
    }

    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDateTime beginDate, LocalDateTime endDate) {
        if (beginDate.isBefore(endDate)) {
            this.beginDate = beginDate;
            this.endDate = endDate;
        }
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }
}
