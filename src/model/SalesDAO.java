package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author developer1
 */
public class SalesDAO {

    PreparedStatement ps;
    ResultSet rs;

    DataSource ds = new DataSource();
    Connection cn;

    public String generateOrderCode() {
        String orderCode = "";
        String sql = "SELECT MAX(orderCode) FROM orders";

        try {
            cn = ds.Connect();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                orderCode = rs.getString(1);
            }

        } catch (Exception e) {
        }

        return orderCode;
    }
    
    public String salesId() {
        String id = "";
        String sql = "SELECT MAX(id) FROM orders";

        try {
            cn = ds.Connect();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getString(1);
            }

        } catch (Exception e) {
        }

        return id;
    }

    public int saveSales(SalesEntity s) {
        int r = 0;
        SalesEntity sales = new SalesEntity();
        String sql = "INSERT INTO orders(clientId,sellerId, orderCode,orderDate,total,status) VALUES(?,?,?,?,?,?)";
        try {
            cn = ds.Connect();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, s.getClientId());
            ps.setInt(2, s.getSellerId());
            ps.setString(3, s.getOrderCode());
            ps.setString(4, s.getOrderDate());
            ps.setDouble(5, s.getTotal());
            ps.setBoolean(6, s.isStatus());
            r = ps.executeUpdate();
        } catch (Exception e) {
        }

        return r;
    }

    public int saveSalesDetail(SalesDetailEntity sd) {
        int r = 0;
        String sql = "INSERT INTO orders_details(orderId, productId,quantity,price) VALUES(?,?,?,?)";
        try {
            cn = ds.Connect();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, sd.getOrderId());
            ps.setInt(2, sd.getProductId());
            ps.setInt(3, sd.getQuantity());
            ps.setDouble(4, sd.getPrice());
           r = ps.executeUpdate();
        } catch (Exception e) {
        }

        return r;
    }
}
