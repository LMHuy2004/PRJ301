package dao;

import dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DBUtils;


public class UserDAO {
    public UserDTO checkLogin(String username, String password) {
        UserDTO user = null;
        String sql = "SELECT * FROM tblUsers WHERE username = ? AND password = ?";
        
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new UserDTO(
                    rs.getString("username"),
                    rs.getString("name"),
                    rs.getString("password"),
                    rs.getString("role")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}