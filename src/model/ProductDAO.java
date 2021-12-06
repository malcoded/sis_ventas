package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author developer1
 */
public class ProductDAO implements CRUD {

    PreparedStatement ps;
    ResultSet rs;

    DataSource ds = new DataSource();
    Connection cn;

    public int updateStock(int qunatity, int id) {
        int r = 0;
        String sql = "UPDATE products SET stock =? WHERE id=?";

        try {
            cn = ds.Connect();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, qunatity);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {

        }

        return r;
    }

    public ProductEntity listID(int id) {
        ProductEntity p = new ProductEntity();
        String sql = "SELECT*FROM products WHERE id=? and status=?";
        try {
            cn = ds.Connect();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setBoolean(2, true);
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setPrice(rs.getDouble(3));
                p.setStock(rs.getInt(4));
                p.setStatus(rs.getBoolean(5));
            }
        } catch (Exception e) {

        }

        return p;
    }

    @Override
    public List listRecords() {
        List<ProductEntity> list = new ArrayList<>();
        String sql = "SELECT*FROM products";

        try {
            cn = ds.Connect();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ProductEntity prod = new ProductEntity();
                prod.setId(rs.getInt(1));
                prod.setName(rs.getString(2));
                prod.setPrice(rs.getDouble(3));
                prod.setStock(rs.getInt(4));
                prod.setStatus(rs.getBoolean(5));
                list.add(prod);
            }
        } catch (Exception e) {

        }
        return list;
    }

    @Override
    public int add(Object[] o) {
        int r = 0;
        String sql = "INSERT INTO products(name,price,stock,status) VALUES(?,?,?,?)";
        try {
            cn = ds.Connect();
            ps = cn.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            r = ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    @Override
    public int update(Object[] o) {
        int r = 0;
        String sql = "UPDATE products SET name=?,price=?,stock=?,status=? WHERE id=?";
        try {
            cn = ds.Connect();
            ps = cn.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            ps.setObject(5, o[4]);
            r = ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM products WHERE id=?";
        try {
            cn = ds.Connect();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
