/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bond.dao;

import bond.db.DBConnection;
import bond.model.Announcement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Erica
 */
public class AnnouncementDAO {
    // ADD
    public boolean addAnnouncement(Announcement a) {
        String sql = "INSERT INTO announcements " +
                     "(org_id, title, content, date) " +
                     "VALUES (?, ?, ?, ?)";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, a.getOrgId());
            ps.setString(2, a.getTitle());
            ps.setString(3, a.getContent());
            ps.setString(4, a.getDate());
            boolean result = ps.executeUpdate() > 0;
            conn.close();
            return result;
        } catch (Exception ex) {
            System.out.println("Add ann error: " + ex.getMessage());
            return false;
        }
    }

    // EDIT
    public boolean updateAnnouncement(Announcement a) {
        String sql = "UPDATE announcements SET title=?, " +
                     "content=? WHERE ann_id=?";
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

    // DELETE
    public boolean deleteAnnouncement(int annId) {
        String sql = "DELETE FROM announcements WHERE ann_id=?";
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

    // GET ALL
    public List<Announcement> getAllAnnouncements(int orgId) {
        List<Announcement> list = new ArrayList<>();
        String sql = "SELECT * FROM announcements WHERE org_id=?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orgId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Announcement a = new Announcement();
                a.setAnnId(rs.getInt("ann_id"));
                a.setOrgId(rs.getInt("org_id"));
                a.setTitle(rs.getString("title"));
                a.setContent(rs.getString("content"));
                a.setDate(rs.getString("date"));
                list.add(a);
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println("Get ann error: " + ex.getMessage());
        }
        return list;
    }
}
