package DTO;

import java.time.LocalDateTime;

public class LecturerDTO extends PersonDTO {
    
    private LocalDateTime hireDate;
    private OfficeAssignmentDTO officeAssignment;

    public LecturerDTO(){}

    public LecturerDTO(int studentID, String lastName, String firstName, LocalDateTime hireDate, OfficeAssignmentDTO officeAssignment){
        super(studentID, lastName, firstName);
        this.hireDate = hireDate;
        this.officeAssignment = officeAssignment;
    }

    public LecturerDTO(int studentID, String lastName, String firstName, LocalDateTime hireDate){
        super(studentID, lastName, firstName);
        this.hireDate = hireDate;
    }

    public LocalDateTime getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDateTime hireDate) {
        this.hireDate = hireDate;
    }

    public OfficeAssignmentDTO getOfficeAssignment() {
        return officeAssignment;
    }

    public void setOfficeAssignment(OfficeAssignmentDTO officeAssignment) {
        this.officeAssignment = officeAssignment;
    }

    public LocalDateTime gethireDate() {
        return hireDate;
    }

    public void sethireDate(LocalDateTime hireDate) {
        this.hireDate = hireDate;
    }
}
