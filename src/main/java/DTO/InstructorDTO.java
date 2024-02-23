package DTO;

public class InstructorDTO {
    private CourseDTO course;
    private LecturerDTO lecturer;

    public InstructorDTO(){}

    public InstructorDTO(CourseDTO course, LecturerDTO lecturer){
        this.course = course;
        this.lecturer = lecturer;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    public LecturerDTO getLecturer() {
        return lecturer;
    }

    public void setLecturer(LecturerDTO lecturer) {
        this.lecturer = lecturer;
    }
    
    
}
