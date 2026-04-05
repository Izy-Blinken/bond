package bond.util;

import bond.model.OrgAdmin;
import bond.model.OsoAdmin;

public class SessionManager {

    private static String orgAcronym = "";
    private static String orgName = "";
    private static String adminName = "";
    private static OsoAdmin currentOsoAdmin = null;
    private static OrgAdmin currentOrgAdmin = null;

    public static void loadSession() {
        if (currentOrgAdmin == null) return;
        try {
            java.sql.Connection conn = bond.db.DBConnection.getConnection();
            java.sql.PreparedStatement ps = conn.prepareStatement(
                "SELECT org_name FROM organization WHERE org_id = ?"
            );
            ps.setInt(1, currentOrgAdmin.getOrg_id());
            java.sql.ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                orgName = rs.getString("org_name");
                StringBuilder acronym = new StringBuilder();
                for (String word : orgName.split(" ")) {
                    if (!word.isEmpty()) acronym.append(word.charAt(0));
                }
                orgAcronym = acronym.toString().toUpperCase();
            }
            adminName = currentOrgAdmin.getUsername();
            conn.close();
        } catch (Exception ex) {
            System.out.println("Session load error: " + ex.getMessage());
        }
    }

    public static void loginOso(OsoAdmin admin) {
        currentOsoAdmin = admin;
        currentOrgAdmin = null;
    }

    public static void loginOrg(OrgAdmin admin) {
        currentOrgAdmin = admin;
        currentOsoAdmin = null;
        loadSession();
    }

    public static void logout() {
        currentOsoAdmin = null;
        currentOrgAdmin = null;
        orgAcronym = "";
        orgName = "";
        adminName = "";
    }

    public static OsoAdmin getCurrentOsoAdmin() {
        return currentOsoAdmin;
    }

    public static OrgAdmin getCurrentOrgAdmin() {
        return currentOrgAdmin;
    }

    public static boolean isOsoLoggedIn() {
        return currentOsoAdmin != null;
    }

    public static boolean isOrgAdminLoggedIn() {
        return currentOrgAdmin != null;
    }

    public static int getCurrentOrgId() {
        if (currentOrgAdmin != null) {
            return currentOrgAdmin.getOrg_id();
        }
        return -1;
    }

    public static int getCurrentAdminId() {
        if (currentOrgAdmin != null) {
            return currentOrgAdmin.getOrg_admin_id();
        }
        return -1;
    }

    public static String getOrgAcronym() {
        return orgAcronym;
    }

    public static String getOrgName() {
        return orgName;
    }

    public static String getAdminName() {
        return adminName;
    }

}