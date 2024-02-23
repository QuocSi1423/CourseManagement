package DTO;

import java.time.LocalDateTime;

public class OfficeAssignmentDTO {
    private int instrutorID;
    private String location;
    private LocalDateTime timestamp;
    public OfficeAssignmentDTO(){};
    public OfficeAssignmentDTO(int instructorID, String location, LocalDateTime timestamp){
        this.instrutorID = instructorID;
        this.location = location;
        this.timestamp = timestamp;
    }
    public int getInstrutorID() {
        return instrutorID;
    }
    public void setInstrutorID(int instrutorID) {
        this.instrutorID = instrutorID;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
