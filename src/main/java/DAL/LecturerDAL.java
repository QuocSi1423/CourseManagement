package DAL;

import BUS.LecturerBUS;
import DAL.IDAL.IObjectDAL;
import DAL.IDAL.ILecturerDAL;
import DTO.LecturerDTO;
import DTO.OfficeAssignmentDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ant1006
 */
public class LecturerDAL implements IObjectDAL, ILecturerDAL {

    private DatabaseManager db;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LecturerDAL() {
        this.db = new DatabaseManager();
    }

    @Override
    public <T> int insertObject(T object) {
        LecturerDTO dto = (LecturerDTO) object;

        java.sql.Date date = java.sql.Date.valueOf(dto.gethireDate().toLocalDate());

        String query = String.format(
                "INSERT INTO Person (PersonID, Firstname, Lastname, HireDate, EnrollmentDate) "
                + "VALUES (NULL, '{0}', '{1}', '{2}', NULL)",
                dto.getFirstName(),
                dto.getLastName(),
                date);

        return db.executeNonQuery(query);
    }

    @Override
    public <T> int updateObject(T object) {
        LecturerDTO dto = (LecturerDTO) object;

        java.sql.Date date = java.sql.Date.valueOf(dto.gethireDate().toLocalDate());

        String query = String.format(
                "UPDATE Person SET "
                + "Firstname = '{0}', "
                + "Lastname = '{1}', "
                + "HireDate = '{2}' "
                + "WHERE PersonID = {3}",
                dto.getFirstName(),
                dto.getLastName(),
                date,
                dto.getID());

        return db.executeNonQuery(query);
    }

    @Override
    public int removeObject(int objectID) {
        String query = String.format("DELETE FROM Person WHERE PersonID = {0}", objectID);
        return db.executeNonQuery(query);
    }

    @Override
    public <T> T getAnObjectByID(int objectID) {
        String query = String.format("SELECT * FROM Person join OfficeAssignment on Person.PersonID = OfficeAssignment.InstructorID WHERE Person.PersonID=%d", objectID);

        try {
            ResultSet rsSet = db.executeQuery(query);

            if (!rsSet.next()) {
                return null;
            }

            return (T) new LecturerDTO(
                    rsSet.getInt("PersonID"),
                    rsSet.getString("Lastname"),
                    rsSet.getString("Firstname"),
                    LocalDateTime.parse(rsSet.getString("HireDate"), formatter),new OfficeAssignmentDTO(rsSet.getInt("PersonID"), rsSet.getString("Location"), LocalDateTime.parse(rsSet.getString("Timestamp"),formatter)));

        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public List<LecturerDTO> getAllLecturers() {
        String query = String.format(
                "SELECT PersonID, Lastname, Firstname, HireDate, Location FROM Person join OfficeAssignment on Person.PersonID = OfficeAssignment.InstructorID"
                + "WHERE HireDate IS NOT NULL");

        return getLecturers(query);
    }

    @Override
    public List<LecturerDTO> getLecturersOfACourse(int courseID) {
        String query = String.format(
                "SELECT p.PersonID, Lastname, Firstname, HireDate "
                + "FROM Person p"
                + "INNER JOIN CourseInstructor ON p.PersonID = CourseInstructor.PersonID"
                + "WHERE CourseID = {0} "
                + "GROUP BY p.PersonID",
                courseID);

        return getLecturers(query);
    }

    @Override
    public List<LecturerDTO> getLecturersByName(String name) {
        String query = String.format(
                "SELECT PersonID, Lastname, Firstname, HireDate FROM Person "
                + "WHERE HireDate IS NOT NULL "
                + "AND CONCAT(Firstname,' ',Lastname,' ',Firstname) LIKE '%{0}%'", name);

        return getLecturers(query);
    }

    private List<LecturerDTO> getLecturers(String query) {
        try {
            List<LecturerDTO> rs = new ArrayList<>();
            ResultSet rsSet = db.executeQuery(query);

            while (rsSet.next()) {
                LecturerDTO dto = new LecturerDTO(
                        rsSet.getInt("PersonID"),
                        rsSet.getString("Lastname"),
                        rsSet.getString("Firstname"),
                        LocalDateTime.parse(rsSet.getString("HireDate"), formatter));

                rs.add(dto);
            }

            return rs;

        } catch (SQLException ex) {
            return null;
        }
    }
    
    public static void main(String[] args) {
        LecturerBUS lecturerBUS = new LecturerBUS(new LecturerDAL());
        LecturerDTO lec = lecturerBUS.getALecturerByID(1);
        System.out.println(lec.getFirstName());
    }
}
