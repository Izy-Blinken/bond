/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bond.dao;

import bond.db.DBConnection;
import bond.model.Event;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Erica
 */
public class EventDAO {
    // ADD
    public boolean addEvent(Event e) {
        String sql = "INSERT INTO events " +
                     "(org_id, name, date, description, status) " +
                     "VALUES (?, ?, ?, ?, 'Pending')";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, e.getOrgId());
            ps.setString(2, e.getName());
            ps.setString(3, e.getDate());
            ps.setString(4, e.getDescription());
            boolean result = ps.executeUpdate() > 0;
            conn.close();
            return result;
        } catch (Exception ex) {
            System.out.println("Add event error: " + ex.getMessage());
            return false;
        }
    }

    // EDIT
    public boolean updateEvent(Event e) {
        String sql = "UPDATE events SET name=?, " +
                     "date=?, description=? " +
                     "WHERE event_id=?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, e.getName());
            ps.setString(2, e.getDate());
            ps.setString(3, e.getDescription());
            ps.setInt(4, e.getEventId());
            boolean result = ps.executeUpdate() > 0;
            conn.close();
            return result;
        } catch (Exception ex) {
            System.out.println("Update event error: " + ex.getMessage());
            return false;
        }
    }

    // DELETE
    public boolean deleteEvent(int eventId) {
        String sql = "DELETE FROM events WHERE event_id=?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, eventId);
            boolean result = ps.executeUpdate() > 0;
            conn.close();
            return result;
        } catch (Exception ex) {
            System.out.println("Delete event error: " + ex.getMessage());
            return false;
        }
    }

    // GET ALL
    public List<Event> getAllEvents(int orgId) {
        List<Event> list = new ArrayList<>();
        String sql = "SELECT * FROM events WHERE org_id=?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orgId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Event e = new Event();
                e.setEventId(rs.getInt("event_id"));
                e.setOrgId(rs.getInt("org_id"));
                e.setName(rs.getString("name"));
                e.setDate(rs.getString("date"));
                e.setDescription(rs.getString("description"));
                e.setStatus(rs.getString("status"));
                list.add(e);
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println("Get events error: " + ex.getMessage());
        }
        return list;
    }
}
