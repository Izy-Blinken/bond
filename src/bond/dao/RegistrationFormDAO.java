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
        String sql = "SELECT * FROM registration_form";
        
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
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }

    public boolean markAsReviewed(int form_id, int reviewed_by) {
        
        String sql = "UPDATE registration_form SET review_status = 'Reviewed', reviewed_by = ?, reviewed_at = NOW() WHERE form_id = ?";
        try {
            
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, reviewed_by);
            ps.setInt(2, form_id);
            int rows = ps.executeUpdate();
            
            return rows > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
}