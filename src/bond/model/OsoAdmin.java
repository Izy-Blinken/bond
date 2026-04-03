package bond.model;

public class OsoAdmin {

    private int oso_admin_id;
    private String username;
    private String password_hash;
    private String email;

    public OsoAdmin(int oso_admin_id, String username, String password_hash, String email) {

        this.oso_admin_id = oso_admin_id;
        this.username = username;
        this.password_hash = password_hash;
        this.email = email;
    }

    //getters
    public int getOso_admin_id() {
        return oso_admin_id;
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
}
