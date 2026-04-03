/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bond.model;

/**
 *
 * @author Erica
 */
public class Event {
    private int eventId;
    private int orgId;
    private String name;
    private String date;
    private String description;
    private String status;

    // Constructor
    public Event() {}

    public Event(int orgId, String name, 
                 String date, String description) {
        this.orgId = orgId;
        this.name = name;
        this.date = date;
        this.description = description;
        this.status = "Pending";
    }

    // Getters and Setters
    public int getEventId() { return eventId; }
    public void setEventId(int id) { this.eventId = id; }

    public int getOrgId() { return orgId; }
    public void setOrgId(int id) { this.orgId = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getDescription() { return description; }
    public void setDescription(String d) { this.description = d; }

    public String getStatus() { return status; }
    public void setStatus(String s) { this.status = s; }

    @Override
    public String toString() {
        return "[" + eventId + "] " + name + 
               " | " + date + 
               " | " + description + 
               " | " + status;
    }
}
