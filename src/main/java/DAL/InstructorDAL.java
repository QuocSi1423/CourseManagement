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
                + "VALUES ('%d','%d')",
                instructorDTO.getCourse().getCourseID(),
                instructorDTO.getLecturer().getID());

        return db.executeNonQuery(query);
    }

    @Override
    public int removeInstructor(InstructorDTO instructorDTO) {
        String query = String.format(
                "DELETE FROM CourseInstructor "
                + "WHERE CourseID = '%d' AND PersonID = '%d'",
                instructorDTO.getCourse().getCourseID(),
                instructorDTO.getLecturer().getID());

        return db.executeNonQuery(query);
    }
    
}
