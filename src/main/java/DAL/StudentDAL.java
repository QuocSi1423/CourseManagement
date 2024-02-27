/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DAL.IDAL.IObjectDAL;
import DAL.IDAL.IStudentDAL;
import DTO.CourseDTO;
import DTO.StudentDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class StudentDAL implements IObjectDAL, IStudentDAL {

    private Connection connect;
    private DatabaseManager DB;
    private static final String url = "jdbc:mysql://localhost:3306/school";
    private static final String user = "root";
    private static final String password = "";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public StudentDAL() {
        this.DB = new DatabaseManager(url, user, password);
        this.connect = this.DB.getConnection();
    }

    @Override
    public <T> int insertObject(T object) {
        StudentDTO studentDTO = (StudentDTO) object;
    
        String queryInsert = "INSERT INTO person (PersonID, Firstname, "
                + "Lastname, HireDate, EnrollmentDate) "
                + "VALUES (NULL, ?, ?, NULL, ?)";
        java.sql.Date date = java.sql.Date.valueOf(studentDTO.getEnrollmentDate().toLocalDate());

        try {
            PreparedStatement prepareStm = connect.prepareStatement(queryInsert);

            prepareStm.setString(1, studentDTO.getFirstName());
            prepareStm.setString(2, studentDTO.getLastName());
            prepareStm.setDate(3, date);
            return prepareStm.executeUpdate();
        } catch (SQLException ex) {
            return 0;
        }
    }

    @Override
    public <T> int updateObject(T object) {
        StudentDTO studentDTO = (StudentDTO) object;
        String updateQuery = "update Person set Firstname = ?, Lastname = ?, EnrollmentDate = ? WHERE PersonID = ?";
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(updateQuery);
            java.sql.Date date = java.sql.Date.valueOf(studentDTO.getEnrollmentDate().toLocalDate());
            preparedStatement.setString(1, studentDTO.getFirstName());
            preparedStatement.setString(2, studentDTO.getLastName());
            preparedStatement.setDate(3, date);
            preparedStatement.setInt(4, studentDTO.getID());
            return preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAL.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int removeObject(int objectID) {
        String deleteQuery = "Delete from person WHERE PersonID = ?";
        try {
            PreparedStatement prepareStatement = connect.prepareStatement(deleteQuery);
            prepareStatement.setInt(1, objectID);
            return prepareStatement.executeUpdate();
        } catch (SQLException ex) {
            return 0;
        }
    }

    @Override
    public <T> T getAnObjectByID(int objectID) {
        String selectQuery = "SELECT * FROM `person` WHERE PersonID = ?";
        StudentDTO studentRS = null;
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(selectQuery);
            preparedStatement.setInt(1, objectID);
            ResultSet rsSet = preparedStatement.executeQuery();
            while (rsSet.next()) {
                studentRS = new StudentDTO(rsSet.getInt("PersonID"),
                        rsSet.getString("Lastname"), rsSet.getString("Firstname"),
                        LocalDateTime.parse(rsSet.getString("EnrollmentDate"), formatter));

            }
            return (T) studentRS;
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public List<StudentDTO> getAllStudent() {
        List<StudentDTO> studentList = new ArrayList<>();
        String selectQuery = "SELECT * from person WHERE PersonID in "
                + "(SELECT p.PersonID FROM person p JOIN studentgrade "
                + "stg on p.PersonID = stg.StudentID GROUP BY p.PersonID)";
        try {
            PreparedStatement prepareStament = connect.prepareStatement(selectQuery);
            ResultSet rsSet = DB.executeQuery(selectQuery);
            while (rsSet.next()) {
                LocalDateTime date = null;
                String dateStr = rsSet.getString("EnrollmentDate");
                if (dateStr != null) {
                    date = LocalDateTime.parse(dateStr, formatter);
                }
                
                studentList.add(new StudentDTO(rsSet.getInt("PersonID"),
                        rsSet.getString("Lastname"),
                        rsSet.getString("Firstname"),
                        date));
            }
            return studentList;

        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public List<StudentDTO> getAllStudentsOfACourse(int courseID) {
        List<StudentDTO> studentList = new ArrayList<>();
        String selectQuery = "select * FROM person WHERE PersonID in (SELECT stg.StudentID FROM studentgrade stg WHERE stg.CourseID = ? GROUP BY stg.StudentID)";
        try {
            PreparedStatement prepareStament = connect.prepareStatement(selectQuery);
            prepareStament.setInt(1, courseID);
            ResultSet rsSet = prepareStament.executeQuery();
            while (rsSet.next()) {
                LocalDateTime date = null;
                String dateStr = rsSet.getString("EnrollmentDate");
                if (dateStr != null) {
                    date = LocalDateTime.parse(dateStr, formatter);
                }
                
                
                studentList.add(new StudentDTO(rsSet.getInt("PersonID"),
                        rsSet.getString("Lastname"),
                        rsSet.getString("Firstname"),
                        date));
            }
            return studentList;

        } catch (SQLException ex) {
            return null;
        }
    }
}
