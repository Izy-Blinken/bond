package bond.dao;

import bond.db.DBConnection;
import bond.model.Organization;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrganizationDAO {

    // Get all orgs
    public List<Organization> getAllOrganizations() {
        List<Organization> list = new ArrayList<>();
        String sql = "SELECT * FROM organization";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Organization(
                    rs.getInt("org_id"),
                    rs.getString("org_name"),
                    rs.getString("description"),
                    rs.getString("vision"),
                    rs.getString("mission"),
                    rs.getString("classification"),
                    rs.getString("established_date"),
                    rs.getString("logo_path"),
                    rs.getString("status")
                ));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Get active orgs only
    public List<Organization> getActiveOrganizations() {
        List<Organization> list = new ArrayList<>();
        String sql = "SELECT * FROM organization WHERE status='Active'";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Organization(
                    rs.getInt("org_id"),
                    rs.getString("org_name"),
                    rs.getString("description"),
                    rs.getString("vision"),
                    rs.getString("mission"),
                    rs.getString("classification"),
                    rs.getString("established_date"),
                    rs.getString("logo_path"),
                    rs.getString("status")
                ));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Get featured orgs for dashboard (up to 5)
    public List<Organization> getFeaturedOrganizations() {
        List<Organization> list = new ArrayList<>();
        String sql = "SELECT * FROM organization WHERE is_featured=1 AND status='Active' LIMIT 5";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Organization(
                    rs.getInt("org_id"),
                    rs.getString("org_name"),
                    rs.getString("description"),
                    rs.getString("vision"),
                    rs.getString("mission"),
                    rs.getString("classification"),
                    rs.getString("established_date"),
                    rs.getString("logo_path"),
                    rs.getString("status")
                ));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Get single org by id
    public Organization getOrganizationById(int orgId) {
        String sql = "SELECT * FROM organization WHERE org_id=?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orgId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Organization(
                    rs.getInt("org_id"),
                    rs.getString("org_name"),
                    rs.getString("description"),
                    rs.getString("vision"),
                    rs.getString("mission"),
                    rs.getString("classification"),
                    rs.getString("established_date"),
                    rs.getString("logo_path"),
                    rs.getString("status")
                );
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Search orgs by name
    public List<Organization> searchOrganizations(String keyword) {
        List<Organization> list = new ArrayList<>();
        String sql = "SELECT * FROM organization WHERE status='Active' AND org_name LIKE ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Organization(
                    rs.getInt("org_id"),
                    rs.getString("org_name"),
                    rs.getString("description"),
                    rs.getString("vision"),
                    rs.getString("mission"),
                    rs.getString("classification"),
                    rs.getString("established_date"),
                    rs.getString("logo_path"),
                    rs.getString("status")
                ));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Filter orgs by classification
    public List<Organization> getByClassification(String classification) {
        List<Organization> list = new ArrayList<>();
        String sql = "SELECT * FROM organization WHERE status='Active' AND classification=?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, classification);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Organization(
                    rs.getInt("org_id"),
                    rs.getString("org_name"),
                    rs.getString("description"),
                    rs.getString("vision"),
                    rs.getString("mission"),
                    rs.getString("classification"),
                    rs.getString("established_date"),
                    rs.getString("logo_path"),
                    rs.getString("status")
                ));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Update org status (Active/Inactive)
    public boolean updateOrgStatus(int org_id, String status) {
        String sql = "UPDATE organization SET status = ? WHERE org_id = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, org_id);
            int rows = ps.executeUpdate();
            con.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Update org profile fields
    public boolean updateOrgProfile(int orgId, String description, String vision, String mission, String classification) {
        String sql = "UPDATE organization SET description=?, vision=?, mission=?, classification=? WHERE org_id=?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, description);
            ps.setString(2, vision);
            ps.setString(3, mission);
            ps.setString(4, classification);
            ps.setInt(5, orgId);
            int rows = ps.executeUpdate();
            con.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Create new org (used by OSO admin)
    public boolean createOrganization(String orgName, String description, String vision, String mission,
                                       String classification, int createdBy) {
        String sql = "INSERT INTO organization (org_name, description, vision, mission, classification, created_by) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, orgName);
            ps.setString(2, description);
            ps.setString(3, vision);
            ps.setString(4, mission);
            ps.setString(5, classification);
            ps.setInt(6, createdBy);
            int rows = ps.executeUpdate();
            con.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}