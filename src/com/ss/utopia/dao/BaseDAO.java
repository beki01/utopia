package com.ss.utopia.dao;

import java.sql.*;
import java.util.List;

public abstract class BaseDAO <T>{
    protected Connection conn;

    public BaseDAO(Connection conn ){ this.conn = conn; }

    public void save(String sql, Object[] vals)  throws  SQLException {
        PreparedStatement pstmt =
                conn.prepareStatement(sql);
        int count = 1;
        for(Object o: vals) {
            pstmt.setObject(count, o);
            count++;
        }
        pstmt.executeUpdate();
//        System.out.println("rows added: " + rowCount);
    }

    public List<T> read(String sql, Object[] vals) throws SQLException {
        PreparedStatement pstmt =
                conn.prepareStatement(sql);
        int count = 1;

//        System.out.println("Read from BaseDAO: SQL Executed = "+ sql);

        if(vals!= null){
            for(Object o: vals) {
                pstmt.setObject(count, o);
                count++;
            }
        }

        ResultSet rs = pstmt.executeQuery();
//        System.out.println("RS is:");
//        System.out.println(rs);
        return extractData(rs);
    }

    abstract public List<T> extractData(ResultSet rs) throws SQLException;
}
