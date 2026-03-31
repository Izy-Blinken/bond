package bond.dao;

import bond.db.DBConnection;
import bond.model.AcademicYear;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AcademicYearDAO {

    public List<AcademicYear> getAllAcademicYears() {
        
        List<AcademicYear> list = new ArrayList<>();
        String sql = "SELECT * FROM academic_year";
        
        try {
            
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                list.add(new AcademicYear(
                    rs.getInt("academic_year_id"),
                    rs.getString("year_label"),
                    rs.getString("start_date"),
                    rs.getString("end_date"),
                    rs.getInt("is_current")
                ));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }

    public boolean createAcademicYear(String year_label, String start_date, String end_date) {
        
        String sql = "INSERT INTO academic_year (year_label, start_date, end_date) VALUES (?, ?, ?)";
        
        try {
            
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, year_label);
            ps.setString(2, start_date);
            ps.setString(3, end_date);
            int rows = ps.executeUpdate();
            
            return rows > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }

    public boolean setCurrentAcademicYear(int academic_year_id) {
        
        String sql1 = "UPDATE academic_year SET is_current = 0";
        String sql2 = "UPDATE academic_year SET is_current = 1 WHERE academic_year_id = ?";
        
        try {
            
            Connection con = DBConnection.getConnection();
            PreparedStatement ps1 = con.prepareStatement(sql1);
            
            ps1.executeUpdate();
            PreparedStatement ps2 = con.prepareStatement(sql2);
            ps2.setInt(1, academic_year_id);
            int rows = ps2.executeUpdate();
            
            return rows > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
}