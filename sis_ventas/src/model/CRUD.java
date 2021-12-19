package model;

import java.util.List;

public interface CRUD {

    public List listRecords();

    public int add(Object[] o);

    public int update(Object[] o);

    public void delete(int id);
}
