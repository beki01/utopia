package com.ss.utopia.dao;

import com.ss.utopia.entity.UserRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDAO extends BaseDAO<UserRole>{
    public UserRoleDAO(Connection conn) {
        super(conn);
    }

    public List<UserRole> readAllRoles() throws SQLException {
        return read("SELECT * FROM user_role", null);
    }

    public List<UserRole> findRole(String r) throws SQLException{
        return read("SELECT * FROM user_role WHERE name = ?", new Object[]{r});
    }

    public List<UserRole> findRoleById(Integer id) throws SQLException{
        return read("SELECT * FROM user_role WHERE id = ?",
                new Object[] {id});
    }

    @Override
    public List<UserRole> extractData(ResultSet rs) throws SQLException {
        List<UserRole> roles = new ArrayList<>();
        while (rs.next()){
            UserRole ur = new UserRole();
            ur.setId(rs.getInt("id"));
            ur.setName(rs.getString("name"));
            roles.add(ur);
        }
        return roles;
    }
}
