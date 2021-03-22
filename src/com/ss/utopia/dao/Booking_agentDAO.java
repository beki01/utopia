package com.ss.utopia.dao;

import com.ss.utopia.entity.Booking_agent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Booking_agentDAO extends BaseDAO<Booking_agent> {
    public Booking_agentDAO(Connection conn) {
        super(conn);
    }



    @Override
    public List<Booking_agent> extractData(ResultSet rs) throws SQLException {
        return null;
    }
}
