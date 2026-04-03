package bond.dao;

import bond.db.DBConnection;
import bond.model.OsoAdmin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OsoAdminDAO {

    public OsoAdmin login(String username, String password) {

        String sql = "SELECT * FROM oso_admin WHERE username = ? AND password_hash = ?";

        try {

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new OsoAdmin(
                        rs.getInt("oso_admin_id"),
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        rs.getString("email")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
