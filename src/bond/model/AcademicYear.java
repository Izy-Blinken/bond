package bond.model;

public class AcademicYear {

    private int academic_year_id;
    private String year_label;
    private String start_date;
    private String end_date;
    private int is_current;

    public AcademicYear(int academic_year_id, String year_label, String start_date, String end_date, int is_current) {

        this.academic_year_id = academic_year_id;
        this.year_label = year_label;
        this.start_date = start_date;
        this.end_date = end_date;
        this.is_current = is_current;
    }

    //getters
    public int getAcademic_year_id() {
        return academic_year_id;
    }

    public String getYear_label() {
        return year_label;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public int getIs_current() {
        return is_current;
    }
}
