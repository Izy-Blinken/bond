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
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Login check
    public OrgAdmin login(String username, String password) {
        String sql = "SELECT * FROM org_admin WHERE username = ? AND password_hash = ? AND is_active = 1";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new OrgAdmin(
                    rs.getInt("org_admin_id"),
                    rs.getInt("org_id"),
                    rs.getInt("created_by"),
                    rs.getString("username"),
                    rs.getString("password_hash"),
                    rs.getString("email"),
                    rs.getInt("is_active")
                );
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get by org id
    public OrgAdmin getByOrgId(int orgId) {
        String sql = "SELECT * FROM org_admin WHERE org_id = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orgId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new OrgAdmin(
                    rs.getInt("org_admin_id"),
                    rs.getInt("org_id"),
                    rs.getInt("created_by"),
                    rs.getString("username"),
                    rs.getString("password_hash"),
                    rs.getString("email"),
                    rs.getInt("is_active")
                );
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Create org admin — officer_id must exist first
    public boolean createOrgAdmin(int orgId, int officerId, int createdBy, String username, String password, String email) {
        String sql = "INSERT INTO org_admin (org_id, officer_id, created_by, username, password_hash, email) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orgId);
            ps.setInt(2, officerId);
            ps.setInt(3, createdBy);
            ps.setString(4, username);
            ps.setString(5, password);
            ps.setString(6, email);
            int rows = ps.executeUpdate();
            con.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Update credentials
    public boolean updateOrgAdmin(int orgAdminId, String username, String password, String email) {
        String sql = "UPDATE org_admin SET username = ?, password_hash = ?, email = ? WHERE org_admin_id = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setInt(4, orgAdminId);
            int rows = ps.executeUpdate();
            con.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Toggle active status
    public boolean setActive(int orgAdminId, boolean active) {
        String sql = "UPDATE org_admin SET is_active = ? WHERE org_admin_id = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, active ? 1 : 0);
            ps.setInt(2, orgAdminId);
            int rows = ps.executeUpdate();
            con.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}