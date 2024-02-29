package DAL.IDAL;

import java.util.List;
import DTO.LecturerDTO;

public interface ILecturerDAL {
    public List<LecturerDTO> getAllLecturers();
    public List<LecturerDTO> getLecturersOfACourse(int courseID);
    public List<LecturerDTO> getLecturersByName(String name);
    public List<LecturerDTO> getLecturerNotAssignForACourse(int courseID);
}
