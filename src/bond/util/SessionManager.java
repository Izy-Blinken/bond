/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bond.util;

/**
 *
 * @author Erica
 */
public class SessionManager {
     private static String orgAcronym = "";
    private static String orgName = "";
    private static String adminName = "";

    // Call this once when the app starts / user logs in
    public static void loadSession() {
        try {
            java.sql.Connection conn = bond.db.DBConnection.getConnection();

            // Load org name + generate acronym
            java.sql.ResultSet rs = conn.prepareStatement(
                "SELECT name FROM organizations WHERE org_id = 1"
            ).executeQuery();
            if (rs.next()) {
                orgName = rs.getString("name");
                StringBuilder acronym = new StringBuilder();
                for (String word : orgName.split(" ")) {
                    if (!word.isEmpty()) acronym.append(word.charAt(0));
                }
                orgAcronym = acronym.toString().toUpperCase();
            }

            // Load admin name
            java.sql.ResultSet rs2 = conn.prepareStatement(
                "SELECT full_name FROM admins WHERE admin_id = 1"
            ).executeQuery();
            if (rs2.next()) {
                adminName = rs2.getString("full_name");
            }

            conn.close();
        } catch (Exception ex) {
            System.out.println("Session load error: " + ex.getMessage());
        }
    }

    public static String getOrgAcronym() { return orgAcronym; }
    public static String getOrgName()    { return orgName; }
    public static String getAdminName()  { return adminName; }
}
