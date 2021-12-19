package model;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClientDAO implements CRUD {

    PreparedStatement ps;
    ResultSet rs;

    DataSource ds = new DataSource();
    Connection cn;

    public ClientEntity listID(String document) {
        ClientEntity c = new ClientEntity();
        String sql = "SELECT*FROM clients WHERE document=?";
        try {
            cn = ds.Connect();
            ps = cn.prepareStatement(sql);
            ps.setString(1, document);
            rs = ps.executeQuery();

            while (rs.next()) {
                c.setId(rs.getInt(1));
                c.setDocument(rs.getString(2));
                c.setName(rs.getString(3));
                c.setAddress(rs.getString(4));
                c.setStatus(rs.getBoolean(5));
            }
        } catch (Exception e) {

        }

        return c;
    }

    @Override
    public List listRecords() {
        List<ClientEntity> list = new ArrayList<>();
        String sql = "SELECT*FROM clients";

        try {
            cn = ds.Connect();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ClientEntity c = new ClientEntity();
                c.setId(rs.getInt(1));
                c.setDocument(rs.getString(2));
                c.setName(rs.getString(3));
                c.setAddress(rs.getString(4));
                c.setStatus(rs.getBoolean(5));
                list.add(c);
            }
        } catch (Exception e) {

        }
        return list;
    }

    @Override
    public int add(Object[] o) {
        int r = 0;
        String sql = "INSERT INTO clients(document,name,address,status) VALUES(?,?,?,?)";
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
        String sql = "UPDATE clients SET document=?,name=?,address=?,status=? WHERE id=?";
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
        String sql = "DELETE FROM clients WHERE id=?";
        try {
            cn = ds.Connect();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
