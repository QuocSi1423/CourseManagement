package DAL;

import DAL.IDAL.IInstructorDAL;
import DTO.InstructorDTO;

/**
 *
 * @author ant1006
 */
public class InstructorDAL implements IInstructorDAL {

    private DatabaseManager db;

    public InstructorDAL() {
        this.db = new DatabaseManager();
    }

    @Override
    public int insertInstructor(InstructorDTO instructorDTO) {
        String query = String.format(
                "INSERT INTO CourseInstructor(`CourseID`, `PersonID`) "
                + "VALUES ('{0}','{1}')",
                instructorDTO.getCourseID(),
                instructorDTO.getLecturerID());

        return db.executeNonQuery(query);
    }

    @Override
    public int removeInstructor(InstructorDTO instructorDTO) {
        String query = String.format(
                "DELETE FROM CourseInstructor "
                + "WHERE CourseID = '{0}' AND PersonID = '{1}'",
                instructorDTO.getCourseID(),
                instructorDTO.getLecturerID());

        return db.executeNonQuery(query);
    }
    
}
