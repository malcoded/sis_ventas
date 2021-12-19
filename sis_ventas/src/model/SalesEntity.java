package model;

/**
 *
 * @author developer1
 */
public class SalesEntity {

    int id;
    int clientId;
    int sellerId;
    String orderCode;
    String orderDate;
    double total;
    boolean status;

    public SalesEntity() {
    }

    public SalesEntity(int id, int clientId, int sellerId, String orderCode, String orderDate, double total, boolean status) {
        this.id = id;
        this.clientId = clientId;
        this.sellerId = sellerId;
        this.orderCode = orderCode;
        this.orderDate = orderDate;
        this.total = total;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
