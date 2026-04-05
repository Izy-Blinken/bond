package bond.dao;

import bond.db.DBConnection;
import bond.model.RegistrationForm;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationFormDAO {

    public List<RegistrationForm> getAllForms() {
        List<RegistrationForm> list = new ArrayList<>();
        String sql = "SELECT * FROM registration_form ORDER BY submitted_at DESC";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new RegistrationForm(
                    rs.getInt("form_id"),
                    rs.getString("proposed_org_name"),
                    rs.getString("proposed_classification"),
                    rs.getString("description"),
                    rs.getString("contact_email"),
                    rs.getString("review_status"),
                    rs.getString("submitted_at")
                ));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Submit a new registration form
    public boolean submitForm(String proposedOrgName, String classification, String description, String contactEmail) {
        String sql = "INSERT INTO registration_form (proposed_org_name, proposed_classification, description, contact_email) " +
                     "VALUES (?, ?, ?, ?)";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, proposedOrgName);
            ps.setString(2, classification);
            ps.setString(3, description);
            ps.setString(4, contactEmail);
            int rows = ps.executeUpdate();
            con.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Mark as contacted (OSO admin reached out)
    public boolean markAsContacted(int formId, int reviewedBy) {
        String sql = "UPDATE registration_form SET review_status = 'Contacted', reviewed_by = ?, reviewed_at = NOW() " +
                     "WHERE form_id = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, reviewedBy);
            ps.setInt(2, formId);
            int rows = ps.executeUpdate();
            con.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Mark as approved
    public boolean markAsApproved(int formId, int reviewedBy) {
        String sql = "UPDATE registration_form SET review_status = 'Approved', reviewed_by = ?, reviewed_at = NOW() " +
                     "WHERE form_id = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, reviewedBy);
            ps.setInt(2, formId);
            int rows = ps.executeUpdate();
            con.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Mark as rejected
    public boolean markAsRejected(int formId, int reviewedBy) {
        String sql = "UPDATE registration_form SET review_status = 'Rejected', reviewed_by = ?, reviewed_at = NOW() " +
                     "WHERE form_id = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, reviewedBy);
            ps.setInt(2, formId);
            int rows = ps.executeUpdate();
            con.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}