package DTO;

import java.time.LocalDateTime;

public class StudentDTO extends PersonDTO{
    
    private LocalDateTime enrollmentDate;

    public StudentDTO(){}

    public StudentDTO(int studentID, String lastName, String firstName, LocalDateTime enrollmentDate){
        super(studentID,lastName,firstName);
        this.enrollmentDate = enrollmentDate;
    }

    public StudentDTO(int studentID){
        this.setID(studentID);
    }

    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDateTime enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
