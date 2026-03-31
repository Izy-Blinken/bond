package bond.model;

public class OrgAdmin {
    
    private int org_admin_id;
    private int org_id;
    private int created_by;
    private String username;
    private String password_hash;
    private String email;
    private int is_active;

    public OrgAdmin(int org_admin_id, int org_id, int created_by, String username, String password_hash, String email, int is_active) {
        
        this.org_admin_id = org_admin_id;
        this.org_id = org_id;
        this.created_by = created_by;
        this.username = username;
        this.password_hash = password_hash;
        this.email = email;
        this.is_active = is_active;
    }

    //getters
    public int getOrg_admin_id() {
        return org_admin_id;
    }

    public int getOrg_id() {
        return org_id;
    }

    public int getCreated_by() {
        return created_by;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public String getEmail() {
        return email;
    }

    public int getIs_active() {
        return is_active;
    }
}