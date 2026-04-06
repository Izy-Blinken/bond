package bond.dao;

import bond.db.DBConnection;
import bond.model.OsoAdmin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OsoAdminDAO {

    // Login check (plaintext — no hashing in this version)
    public OsoAdmin login(String username, String password) {
        String sql = "SELECT * FROM oso_admin WHERE username = ? AND password_hash = ?";
        
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, hashPassword(password));
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                
                return new OsoAdmin(
                    rs.getInt("oso_admin_id"),
                    rs.getString("username"),
                    rs.getString("password_hash"),
                    rs.getString("email")
                );
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update password
    public boolean updatePassword(int osoAdminId, String newPassword) {
        String sql = "UPDATE oso_admin SET password_hash = ? WHERE oso_admin_id = ?";
        
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, newPassword);
            ps.setInt(2, osoAdminId);
            int rows = ps.executeUpdate();
            con.close();
            return rows > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Update email
    public boolean updateEmail(int osoAdminId, String newEmail) {
        String sql = "UPDATE oso_admin SET email = ? WHERE oso_admin_id = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, newEmail);
            ps.setInt(2, osoAdminId);
            int rows = ps.executeUpdate();
            con.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    private String hashPassword(String plain) {
        
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(plain.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            return plain;
        }
        
    }

}