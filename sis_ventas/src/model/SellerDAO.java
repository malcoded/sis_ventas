/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class SellerDAO implements CRUD {

    PreparedStatement ps;
    ResultSet rs;

    DataSource ds = new DataSource();
    Connection cn;

    public SellerEntity ValidateSeller(String document, String userName) {
        SellerEntity seller = new SellerEntity();
        String sql = "SELECT*FROM sellers WHERE document=? and userName=?;";

        try {
            cn = ds.Connect();
            ps = cn.prepareStatement(sql);
            ps.setString(1, document);
            ps.setString(2, userName);
            rs = ps.executeQuery();

            while (rs.next()) {
                seller.setId(rs.getInt(1));
                seller.setDocument(rs.getString(2));
                seller.setName(rs.getString(3));
                seller.setPhone(rs.getString(4));
                seller.setStatus(rs.getBoolean(5));
                seller.setUserName(rs.getString(6));
            }

        } catch (Exception e) {
        }

        return seller;
    }

    @Override
    public List listRecords() {
        List<SellerEntity> list = new ArrayList<>();
        String sql = "SELECT*FROM sellers";

        try {
            cn = ds.Connect();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                SellerEntity s = new SellerEntity();
                s.setId(rs.getInt(1));
                s.setDocument(rs.getString(2));
                s.setName(rs.getString(3));
                s.setPhone(rs.getString(4));
                s.setStatus(rs.getBoolean(5));
                s.setUserName(rs.getString(6));
                list.add(s);
            }
        } catch (Exception e) {

        }
        return list;
    }

    @Override
    public int add(Object[] o) {
        int r = 0;
        String sql = "INSERT INTO sellers(document,name,phone,status,userName) VALUES(?,?,?,?,?)";
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
    public int update(Object[] o) {
        int r = 0;
        String sql = "UPDATE sellers SET document=?,name=?,phone=?,status=?,userName=? WHERE id=?";
        try {
            cn = ds.Connect();
            ps = cn.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            ps.setObject(5, o[4]);
            ps.setObject(6, o[5]);
            r = ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM sellers WHERE id=?";
        try {
            cn = ds.Connect();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

}
