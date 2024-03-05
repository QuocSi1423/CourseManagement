package DAL;

import BUS.LecturerBUS;
import DAL.IDAL.IObjectDAL;
import DAL.IDAL.ILecturerDAL;
import DTO.LecturerDTO;
import DTO.OfficeAssignmentDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
                + "VALUES (NULL, '%s', '%s', '%s', NULL)",
                dto.getFirstName(),
                dto.getLastName(),
                date);

        return db.executeNonQuery(query);
    }
//    public <T> int insertObject(T object) {
//    LecturerDTO dto = (LecturerDTO) object;
//
//    java.sql.Date date = java.sql.Date.valueOf(dto.gethireDate().toLocalDate());
//
//    String query = "INSERT INTO Person (PersonID, Firstname, Lastname, HireDate, EnrollmentDate) " +
//                   "VALUES (NULL, ?, ?, ?, NULL)";
//
//    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_name", "username", "password");
//         PreparedStatement statement = connection.prepareStatement(query)) {
//
//        statement.setString(1, dto.getFirstName());
//        statement.setString(2, dto.getLastName());
//        statement.setDate(3, date);
//
//        return statement.executeUpdate();
//    } catch (SQLException e) {
//        System.out.println("Lỗi khi thực hiện truy vấn: " + e.getMessage());
//        return 0;
//    }
//}
    @Override
   public <T> int updateObject(T object) {
       LecturerDTO dto = (LecturerDTO) object;

       java.sql.Date date = java.sql.Date.valueOf(dto.gethireDate().toLocalDate());

       String query = String.format(
               "UPDATE Person SET "
               + "Firstname = '%s', "
               + "Lastname = '%s', "
               + "HireDate = '%s' "
               + "WHERE PersonID = %d",
               dto.getFirstName(),
               dto.getLastName(),
               date,
               dto.getID());

       return db.executeNonQuery(query);
   }
//-------------------> Lực sửa lại hàm Update <--------------------------
//     public <T> int updateObject(T object) {
//     LecturerDTO dto = (LecturerDTO) object;

//     java.sql.Date date = java.sql.Date.valueOf(dto.gethireDate().toLocalDate());

//     String query = "UPDATE Person SET Firstname = ?, Lastname = ?, HireDate = ? WHERE PersonID = ?";
//     try (Connection connection = db.getConnection();
//          PreparedStatement statement = connection.prepareStatement(query)) {
//         statement.setString(1, dto.getFirstName());
//         statement.setString(2, dto.getLastName());
//         statement.setDate(3, date);
//         statement.setInt(4, dto.getID());

//         return statement.executeUpdate();
//     } catch (SQLException e) {
//         e.printStackTrace();
//         // Xử lý lỗi nếu cần thiết
//         return 0;
//     }
// }
    @Override
    public int removeObject(int objectID) {
        String query = String.format("DELETE FROM Person WHERE PersonID =  %d", objectID);
        return db.executeNonQuery(query);
    }

    @Override
    public <T> T getAnObjectByID(int objectID) {
        String query = String.format("SELECT * FROM Person WHERE Person.PersonID=%d", objectID);

        try {
            ResultSet rsSet = db.executeQuery(query);

            if (!rsSet.next()) {
                return null;
            }

            return (T) new LecturerDTO(
                    rsSet.getInt("PersonID"),
                    rsSet.getString("Lastname"),
                    rsSet.getString("Firstname"),
                    LocalDateTime.parse(rsSet.getString("HireDate"), formatter));

        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public List<LecturerDTO> getAllLecturers() {
        String query = String.format(
                "SELECT PersonID, Lastname, Firstname, HireDate FROM Person "
                + "WHERE HireDate IS NOT NULL");

        return getLecturers(query);
    }

    @Override
    public List<LecturerDTO> getLecturersOfACourse(int courseID) {
        String query = String.format(
                "SELECT * "
                + "FROM Person p "
                + "INNER JOIN CourseInstructor ON p.PersonID = CourseInstructor.PersonID"
                + " WHERE CourseID = %d "
                + "GROUP BY p.PersonID",
                courseID);

        return getLecturers(query);
    }

    @Override
    public List<LecturerDTO> getLecturersByName(String name) {
        String query = String.format(
                "SELECT PersonID, Lastname, Firstname, HireDate FROM Person "
                + " WHERE HireDate IS NOT NULL "
                + "AND CONCAT(Firstname,' ',Lastname,' ',Firstname) LIKE '%{0}%'", name);
        return getLecturers(query);
    }

    private List<LecturerDTO> getLecturers(String query) {
                System.out.println(query);

        try {
            List<LecturerDTO> rs = new ArrayList<>();
            ResultSet rsSet = db.executeQuery(query);

            while (rsSet.next()) {
                LecturerDTO dto = new LecturerDTO(
                        rsSet.getInt("PersonID"),
                        rsSet.getString("Lastname"),
                        rsSet.getString("Firstname"),
                        LocalDateTime.parse(rsSet.getString("HireDate"), formatter));
            //    OfficeAssignmentDTO assigment= new OfficeAssignmentDTO();
            //    assigment.setLocation(rsSet.getString("Location"));
            //     dto.setOfficeAssignment(assigment);

                rs.add(dto);
            }

            return rs;

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public List<LecturerDTO> getLecturerNotAssignForACourse(int courseID) {
        String query = String.format("select * from person"+
                " left join courseinstructor" + 
                " on person.PersonID = courseinstructor.PersonID"+
                " and courseinstructor.CourseID = %d"+
                " where person.HireDate is not null"+
                " and courseinstructor.PersonID is null",courseID);

        return getLecturers(query);
    }
}
