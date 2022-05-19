package business;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable, Comparable<Order> {
    private static final long serialVersionUID = 1123569214201942226L;
    private int orderId;
    private String clientUsername;
    private Calendar date;

    public Order(int orderId, String clientUsername) {
        this.orderId = orderId;
        this.clientUsername = clientUsername;
        this.date = Calendar.getInstance();
    }

    public int getOrderId() {
        return orderId;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public Date getDate() {
        return date.getTime();
    }

    public int getHour() {
        return date.get(Calendar.HOUR_OF_DAY);
    }

    public int getDayOfMonth() {
        return date.get(Calendar.DAY_OF_MONTH);
    }

    public int getMonth() {
        return date.get(Calendar.MONTH);
    }

    public int getYear() {
        return date.get(Calendar.YEAR);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Order order = (Order) o;

        return Objects.equals(clientUsername, order.clientUsername) && Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientUsername, date);
    }

    @Override
    public String toString() {
        return "Order {" +
                "clientUsername='" + clientUsername + '\'' +
                ", date=" + date.getTime() +
                '}' + "\n";
    }

    @Override
    public int compareTo(@NotNull Order order) {
        return this.getDate().compareTo(order.getDate());
    }
}
