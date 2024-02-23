package DAL;

import DAL.IDAL.IObjectDAL;
import DTO.OfficeAssignmentDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ant1006
 */
public class OfficeAssignmentDAL implements IObjectDAL {

    private DatabaseManager db;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public OfficeAssignmentDAL() {
        this.db = new DatabaseManager();
    }

    @Override
    public <T> int insertObject(T object) {
        OfficeAssignmentDTO dto = (OfficeAssignmentDTO) object;

        java.sql.Date date = java.sql.Date.valueOf(dto.getTimestamp().toLocalDate());

        String query = String.format(
                "INSERT INTO OfficeAssignment(`InstructorID`, `Location`, `Timestamp`) "
                + "VALUES ({0}, '{1}', '{2}')",
                dto.getInstrutorID(),
                dto.getLocation(),
                date);

        return db.executeNonQuery(query);
    }

    @Override
    public <T> int updateObject(T object) {
        OfficeAssignmentDTO dto = (OfficeAssignmentDTO) object;

        java.sql.Date date = java.sql.Date.valueOf(dto.getTimestamp().toLocalDate());

        String query = String.format(
                "UPDATE OfficeAssignment SET "
                + "Location = '{0}', "
                + "Timestamp = '{1}' "
                + "WHERE InstructorID = {2}",
                dto.getLocation(),
                date,
                dto.getInstrutorID());

        return db.executeNonQuery(query);
    }

    @Override
    public int removeObject(int objectID) {
        String query = String.format("DELETE FROM OfficeAssignment WHERE InstructorID = {0}", objectID);
        return db.executeNonQuery(query);
    }

    @Override
    public <T> T getAnObjectByID(int objectID) {
        String query = String.format("SELECT * FROM OfficeAssignment WHERE InstructorID = {0}", objectID);

        try {
            ResultSet rsSet = db.executeQuery(query);

            if (!rsSet.next()) {
                return null;
            }

            return (T) new OfficeAssignmentDTO(
                    rsSet.getInt("InstructorID"),
                    rsSet.getString("Location"),
                    LocalDateTime.parse(rsSet.getString("Timestamp"), formatter));

        } catch (SQLException ex) {
            return null;
        }
    }
    
}
