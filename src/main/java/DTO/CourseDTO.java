package DTO;

public class CourseDTO {
    private int courseID;
    private DepartmentDTO department;
    private String title;
    private int credits;
    private int type;

    public CourseDTO(){};
    public CourseDTO(int courseID, DepartmentDTO department, String title, int credits, int type){
        this.courseID = courseID;
        this.department = department;
        this.title = title;
        this.credits = credits;
        this.type = type;
    }
    public CourseDTO(int courseID, DepartmentDTO department, String title, int credits){
        this.courseID = courseID;
        this.department = department;
        this.title = title;
        this.credits = credits;
    }
    public CourseDTO(DepartmentDTO department, String title, int credits){
        this.department = department;
        this.title = title;
        this.credits = credits;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getCourseID() {
        return courseID;
    }
    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
    public DepartmentDTO getDepartment() {
        return department;
    }
    public void setDepartment(DepartmentDTO department) {
        this.department = department;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getCredits() {
        return credits;
    }
    public void setCredits(int credits) {
        this.credits = credits;
    }

    
}
