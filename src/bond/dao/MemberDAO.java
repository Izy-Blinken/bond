/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bond.dao;

import bond.db.DBConnection;
import bond.model.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Erica
 */
public class MemberDAO {
    // ADD
    public boolean addMember(Member m) {
        String sql = "INSERT INTO members " +
                     "(org_id, student_id, full_name, role, course, joined_date) " +
                     "VALUES (?, ?, ?, ?, ?, CURDATE())";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, m.getOrgId());
            ps.setString(2, "STU-" + System.currentTimeMillis());
            ps.setString(3, m.getName());
            ps.setString(4, m.getRole());
            ps.setString(5, m.getCourse() != null ? m.getCourse() : "");
            
            boolean result = ps.executeUpdate() > 0;
            conn.close();
            return result;
            
        } catch (Exception ex) {
            System.out.println("Add member error: " + ex.getMessage());
            return false;
        }
    }

    // APPROVE
    public boolean approveMember(int memberId) {
        String sql = "UPDATE members SET status='Approved' " +
                     "WHERE member_id=?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, memberId);
            boolean result = ps.executeUpdate() > 0;
            conn.close();
            return result;
        } catch (Exception ex) {
            System.out.println("Approve error: " + ex.getMessage());
            return false;
        }
    }

    // REJECT
    public boolean rejectMember(int memberId) {
        String sql = "UPDATE members SET status='Rejected' " +
                     "WHERE member_id=?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, memberId);
            boolean result = ps.executeUpdate() > 0;
            conn.close();
            return result;
        } catch (Exception ex) {
            System.out.println("Reject error: " + ex.getMessage());
            return false;
        }
    }

    // DELETE
    public boolean deleteMember(int memberId) {
        String sql = "DELETE FROM members WHERE member_id=?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, memberId);
            boolean result = ps.executeUpdate() > 0;
            conn.close();
            return result;
        } catch (Exception ex) {
            System.out.println("Delete member error: " + ex.getMessage());
            return false;
        }
    }

    // GET ALL
    public List<Member> getAllMembers(int orgId) {
        
        List<Member> list = new ArrayList<>();
        String sql = "SELECT * FROM members WHERE org_id=?";
        
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orgId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Member m = new Member();
                m.setMemberId(rs.getInt("member_id"));
                m.setOrgId(rs.getInt("org_id"));
                m.setName(rs.getString("full_name"));
                m.setRole(rs.getString("role"));
                m.setCourse(rs.getString("course") != null ? rs.getString("course") : "");
                m.setStatus("");
                
                list.add(m);
            }
            conn.close();
            
        } catch (Exception ex) {
            System.out.println("Get members error: " + ex.getMessage());
        }
        return list;
    }
}
