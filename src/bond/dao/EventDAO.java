package bond.dao;

import bond.db.DBConnection;
import bond.model.Event;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {

    // Add
    public boolean addEvent(Event e) {
        String sql = "INSERT INTO event (org_id, academic_year_id, posted_by, title, description, event_date, status) " +
                     "VALUES (?, 1, 1, ?, ?, ?, 'Upcoming')";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, e.getOrgId());
            ps.setString(2, e.getName());
            ps.setString(3, e.getDescription());
            ps.setString(4, e.getDate());
            boolean result = ps.executeUpdate() > 0;
            conn.close();
            return result;
        } catch (Exception ex) {
            System.out.println("Add event error: " + ex.getMessage());
            return false;
        }
    }

    // Edit
    public boolean updateEvent(Event e) {
        String sql = "UPDATE event SET title=?, event_date=?, description=? WHERE event_id=?";
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

    // Delete
    public boolean deleteEvent(int eventId) {
        String sql = "DELETE FROM event WHERE event_id=?";
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

    // Get all for an org
    public List<Event> getAllEvents(int orgId) {
        List<Event> list = new ArrayList<>();
        String sql = "SELECT * FROM event WHERE org_id=? ORDER BY event_date DESC";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orgId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Event e = new Event();
                e.setEventId(rs.getInt("event_id"));
                e.setOrgId(rs.getInt("org_id"));
                e.setName(rs.getString("title"));
                e.setDate(rs.getString("event_date"));
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