package com.ss.utopia.dao;

import com.ss.utopia.entity.User;
import com.ss.utopia.entity.UserRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends BaseDAO<User>{

    public UserDao(Connection conn) {
        super(conn);
    }

    public void addUser(User u) throws SQLException{
        save("INSERT INTO user (role_id, given_name, family_name, username, email, password, phone) " +
                        "VALUES (?,?,?,?,?,?,?);",
                new Object[] {u.getRole_id().getId(),u.getGiven_name(), u.getFamily_name(), u.getUsername(),
                        u.getEmail(), u.getPassword(), u.getPhone()});
    }

    public  void updateUser(User u) throws SQLException{
         save("UPDATE user SET given_name = ?, family_name = ?, username = ?, email = ?, password = ?, phone = ?  WHERE id = ?",
                new Object[]{u.getGiven_name(), u.getFamily_name(), u.getUsername(), u.getEmail(), u.getPassword(),
                        u.getPhone(), u.getId()});
    }

    public List<User> findUserById(Integer id) throws SQLException{
        return read("SELECT * FROM user WHERE id= ?", new Object[]{id});
    }

    public List<User> readAllUsers() throws SQLException {
        return read("SELECT * FROM user", null);
    }

    public List<User> readUserByRole(Integer r) throws SQLException {
        return read("SElECT * FROM user WHERE role_id=?", new Object[] {r} );
    }

    public List<User> readUserById(Integer id) throws SQLException {
        return read("SELECT * FROM user WHERE id = ?",
                new Object[] {id});
    }

    public void deleteUser(Integer id) throws SQLException{
        save("DELETE FROM user WHERE id = ? ", new Object[]{id});
    }



    @Override
    public List<User> extractData(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();

        while (rs.next()){
            UserRole r = new UserRole();
            r.setId(rs.getInt("role_id"));

            User u = new User();

            u.setId(rs.getInt("id"));
            u.setRole_id(r);
            u.setGiven_name(rs.getString("given_name"));
            u.setFamily_name(rs.getString("family_name"));
            u.setUsername(rs.getString("username"));
            u.setEmail(rs.getString("email"));
            u.setPassword(rs.getString("password"));
            u.setPhone(rs.getString("phone"));

            users.add(u);
        }


        return users;
    }
}
