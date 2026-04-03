package bond.model;

public class RegistrationForm {

    private int form_id;
    private String proposed_org_name;
    private String proposed_classification;
    private String description;
    private String contact_email;
    private String review_status;
    private String submitted_at;

    public RegistrationForm(int form_id, String proposed_org_name, String proposed_classification, String description, String contact_email, String review_status, String submitted_at) {

        this.form_id = form_id;
        this.proposed_org_name = proposed_org_name;
        this.proposed_classification = proposed_classification;
        this.description = description;
        this.contact_email = contact_email;
        this.review_status = review_status;
        this.submitted_at = submitted_at;
    }

    //getters
    public int getForm_id() {
        return form_id;
    }

    public String getProposed_org_name() {
        return proposed_org_name;
    }

    public String getProposed_classification() {
        return proposed_classification;
    }

    public String getDescription() {
        return description;
    }

    public String getContact_email() {
        return contact_email;
    }

    public String getReview_status() {
        return review_status;
    }

    public String getSubmitted_at() {
        return submitted_at;
    }
}
