package BUS;

import DAL.IDAL.ILecturerDAL;
import DAL.IDAL.IObjectDAL;
import DAL.LecturerDAL;
import DTO.LecturerDTO;
import java.util.List;

/**
 *
 * @author ant1006
 */
public class LecturerBUS {

    private ILecturerDAL lecturerDAL;

    public LecturerBUS(ILecturerDAL lecturerDAL) {
        this.lecturerDAL = lecturerDAL;
    }

    public int createALecturer(LecturerDTO lecturer) {
        return ((IObjectDAL) lecturerDAL).insertObject(lecturer);
    }

    public int updateALecturer(LecturerDTO lecturer) {
        return ((IObjectDAL) lecturerDAL).updateObject(lecturer);
    }

    public int removeALecturer(int lecturerID) {
        return ((IObjectDAL) lecturerDAL).removeObject(lecturerID);
    }

    public LecturerDTO getALecturerByID(int lecturerID) {
        return ((IObjectDAL) lecturerDAL).getAnObjectByID(lecturerID);
    }

    public List<LecturerDTO> getAllLecturers() {
        return lecturerDAL.getAllLecturers();
    }
    public List<LecturerDTO> getLecturersByName(String name){
        return lecturerDAL.getLecturersByName(name);
    }

}
