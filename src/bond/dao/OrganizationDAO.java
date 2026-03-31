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
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }

    public boolean updateOrgStatus(int org_id, String status) {
        
        String sql = "UPDATE organization SET status = ? WHERE org_id = ?";
        
        try {
            
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, org_id);
            int rows = ps.executeUpdate();
            
            return rows > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
}