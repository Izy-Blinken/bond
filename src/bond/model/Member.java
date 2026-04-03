/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bond.model;

/**
 *
 * @author Erica
 */
public class Member {
    private int memberId;
    private int orgId;
    private String name;
    private String role;
    private String course;
    private String status;

    public Member() {}

    public Member(int orgId, String name, 
                  String role, String course) {
        this.orgId = orgId;
        this.name = name;
        this.role = role;
        this.course = course;
        this.status = "Pending";
    }

    public int getMemberId() { return memberId; }
    public void setMemberId(int id) { this.memberId = id; }

    public int getOrgId() { return orgId; }
    public void setOrgId(int id) { this.orgId = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public String getStatus() { return status; }
    public void setStatus(String s) { this.status = s; }

    @Override
    public String toString() {
        return "[" + memberId + "] " + name + 
               " | " + role + 
               " | " + course + 
               " | " + status;
    }
}
