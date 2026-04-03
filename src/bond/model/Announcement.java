/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bond.model;

/**
 *
 * @author Erica
 */
public class Announcement {
    private int annId;
    private int orgId;
    private String title;
    private String content;
    private String date;

    public Announcement() {}

    public Announcement(int orgId, String title, 
                        String content, String date) {
        this.orgId = orgId;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public int getAnnId() { return annId; }
    public void setAnnId(int id) { this.annId = id; }

    public int getOrgId() { return orgId; }
    public void setOrgId(int id) { this.orgId = id; }

    public String getTitle() { return title; }
    public void setTitle(String t) { this.title = t; }

    public String getContent() { return content; }
    public void setContent(String c) { this.content = c; }

    public String getDate() { return date; }
    public void setDate(String d) { this.date = d; }

    @Override
    public String toString() {
        return "[" + annId + "] " + title + 
               " | " + date;
    }
}
