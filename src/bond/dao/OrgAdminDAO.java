package bond.dao;

import bond.db.DBConnection;
import bond.model.OrgAdmin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrgAdminDAO {

    public List<OrgAdmin> getAllOrgAdmins() {

        List<OrgAdmin> list = new ArrayList<>();
        String sql = "SELECT * FROM org_admin";

        try {

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new OrgAdmin(
                        rs.getInt("org_admin_id"),
                        rs.getInt("org_id"),
                        rs.getInt("created_by"),
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        rs.getString("email"),
                        rs.getInt("is_active")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean createOrgAdmin(int org_id, int created_by, String username, String password, String email) {

        String sql = "INSERT INTO org_admin (org_id, created_by, username, password_hash, email) VALUES (?, ?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, org_id);
            ps.setInt(2, created_by);
            ps.setString(3, username);
            ps.setString(4, password);
            ps.setString(5, email);
            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateOrgAdmin(int org_admin_id, String username, String password, String email) {

        String sql = "UPDATE org_admin SET username = ?, password_hash = ?, email = ? WHERE org_admin_id = ?";

        try {

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setInt(4, org_admin_id);
            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
