package bond.dao;

import bond.db.DBConnection;
import bond.model.Announcement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementDAO {

    // Add
    public boolean addAnnouncement(Announcement a) {
        String sql = "INSERT INTO announcement (org_id, academic_year_id, posted_by, title, content, created_at) " +
                     "VALUES (?, 1, 1, ?, ?, NOW())";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, a.getOrgId());
            ps.setString(2, a.getTitle());
            ps.setString(3, a.getContent());
            boolean result = ps.executeUpdate() > 0;
            conn.close();
            return result;
        } catch (Exception ex) {
            System.out.println("Add ann error: " + ex.getMessage());
            return false;
        }
    }

    // Edit
    public boolean updateAnnouncement(Announcement a) {
        String sql = "UPDATE announcement SET title=?, content=? WHERE announcement_id=?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, a.getTitle());
            ps.setString(2, a.getContent());
            ps.setInt(3, a.getAnnId());
            boolean result = ps.executeUpdate() > 0;
            conn.close();
            return result;
        } catch (Exception ex) {
            System.out.println("Update ann error: " + ex.getMessage());
            return false;
        }
    }

    // Delete
    public boolean deleteAnnouncement(int annId) {
        String sql = "DELETE FROM announcement WHERE announcement_id=?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, annId);
            boolean result = ps.executeUpdate() > 0;
            conn.close();
            return result;
        } catch (Exception ex) {
            System.out.println("Delete ann error: " + ex.getMessage());
            return false;
        }
    }

    // Get all for an org
    public List<Announcement> getAllAnnouncements(int orgId) {
        List<Announcement> list = new ArrayList<>();
        String sql = "SELECT * FROM announcement WHERE org_id=? ORDER BY created_at DESC";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orgId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Announcement a = new Announcement();
                a.setAnnId(rs.getInt("announcement_id"));
                a.setOrgId(rs.getInt("org_id"));
                a.setTitle(rs.getString("title"));
                a.setContent(rs.getString("content"));
                a.setDate(rs.getString("created_at"));
                list.add(a);
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println("Get ann error: " + ex.getMessage());
        }
        return list;
    }
}