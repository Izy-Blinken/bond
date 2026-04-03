package bond.model;

public class Organization {

    private int org_id;
    private String org_name;
    private String description;
    private String vision;
    private String mission;
    private String classification;
    private String established_date;
    private String logo_path;
    private String status;

    public Organization(int org_id, String org_name, String description, String vision, String mission, String classification, String established_date, String logo_path, String status) {

        this.org_id = org_id;
        this.org_name = org_name;
        this.description = description;
        this.vision = vision;
        this.mission = mission;
        this.classification = classification;
        this.established_date = established_date;
        this.logo_path = logo_path;
        this.status = status;
    }

    //getters
    public int getOrg_id() {
        return org_id;
    }

    public String getOrg_name() {
        return org_name;
    }

    public String getDescription() {
        return description;
    }

    public String getVision() {
        return vision;
    }

    public String getMission() {
        return mission;
    }

    public String getClassification() {
        return classification;
    }

    public String getEstablished_date() {
        return established_date;
    }

    public String getLogo_path() {
        return logo_path;
    }

    public String getStatus() {
        return status;
    }
}
