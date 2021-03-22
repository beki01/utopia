package com.ss.utopia.dao;

import com.ss.utopia.entity.Airplane;
import com.ss.utopia.entity.Airplane_type;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirplaneDAO extends BaseDAO<Airplane>{
    public AirplaneDAO(Connection conn) {
        super(conn);
    }

    public List<Airplane> readAllAirplanes() throws SQLException{
        return read("SELECT airplane.id AS airplane_id, airplane_type.id AS type_id, airplane_type.max_capacity FROM airplane LEFT JOIN airplane_type ON airplane.type_id = airplane_type.id", new Object[]{});
    }

    @Override
    public List<Airplane> extractData(ResultSet rs) throws SQLException {
        List<Airplane> airplanes = new ArrayList<>();

        while(rs.next()){
            Airplane a = new Airplane();
            Airplane_type b = new Airplane_type();

            b.setId(rs.getInt("type_id"));
            b.setMax_capacity(rs.getInt("max_capacity"));

            a.setId(rs.getInt("airplane_id"));

            a.setAirplane_type(b);

            airplanes.add(a);
        }
        return airplanes;
    }
}
