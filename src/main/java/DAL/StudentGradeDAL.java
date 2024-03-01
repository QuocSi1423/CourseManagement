/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DAL.IDAL.ICourseDAL;
import DAL.IDAL.IObjectDAL;
import DAL.IDAL.IStudentDAL;
import DAL.IDAL.IStudentGradeDAL;
import DTO.CourseDTO;
import DTO.StudentDTO;
import DTO.StudentGradeDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class StudentGradeDAL implements IStudentGradeDAL, IObjectDAL {

    private static final String url = "jdbc:mysql://localhost:3306/school";
    private static final String user = "root";
    private static final String password = "";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private Connection connect;
    private DatabaseManager DB;
    private IStudentDAL studentDAL;
    private ICourseDAL courseDAL;

    public StudentGradeDAL(IStudentDAL studentDAL, ICourseDAL courseDAL) {
        this.DB = new DatabaseManager(url, user, password);
        this.connect = this.DB.getConnection();
        this.studentDAL = studentDAL;
        this.courseDAL = courseDAL;
    }

    @Override
    public <T> int insertObject(T object) {
        StudentGradeDTO studentGradeDTO = (StudentGradeDTO) object;
        String insertQuery = "INSERT INTO studentgrade (EnrollmentID, CourseID, StudentID, Grade) VALUES (null, ?, ?, ?)";
        try {
            //Fix bug Insert
            PreparedStatement preparedStatement = connect.prepareStatement(insertQuery);
            preparedStatement.setInt(1, studentGradeDTO.getCourse().getCourseID());
            preparedStatement.setInt(2, studentGradeDTO.getStudent().getID());
            preparedStatement.setDouble(3, studentGradeDTO.getGrade());
            return preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            return 0;
        }
    }

    @Override
    public <T> int updateObject(T object) {
        StudentGradeDTO studentGradeDTO = (StudentGradeDTO) object;
      
        String updateQuery = "update studentgrade set Grade = ?, CourseID = ?, StudentID = ? WHERE EnrollmentID = ?";
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(updateQuery);
            preparedStatement.setDouble(1, studentGradeDTO.getGrade());
            preparedStatement.setDouble(2, studentGradeDTO.getCourse().getCourseID());
            preparedStatement.setInt(3, studentGradeDTO.getStudent().getID());
            preparedStatement.setInt(4, studentGradeDTO.getEnrollmentID());
            return preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            return 0;
        }

    }

    @Override
    public int removeObject(int objectID) {
        String deleteQuery = "Delete from studentgrade WHERE EnrollmentID = ?";
        try {
            PreparedStatement preparedStatement = connect.prepareCall(deleteQuery);
            preparedStatement.setInt(1, objectID);
            return preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            return 0;
        }
    }

    @Override
    public <T> T getAnObjectByID(int objectID) {
        String selectQuery = "SELECT * FROM studentgrade WHERE EnrollmentID = ?";
        StudentGradeDTO studentGradeDTO = null;
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(selectQuery);
            preparedStatement.setInt(1, objectID);
            ResultSet rsSet = preparedStatement.executeQuery();
            while (rsSet.next()) {
                //fix bug
                int studentID = rsSet.getInt("StudentID");
                StudentDTO student = ((IObjectDAL) studentDAL)
                        .getAnObjectByID(studentID);

                int courseID = rsSet.getInt("CourseID");
                CourseDTO course = new CourseDTO();

                studentGradeDTO = new StudentGradeDTO(rsSet.getInt("EnrollmentID"),
                        rsSet.getDouble("Grade"), student, course);
            }
            return (T) studentGradeDTO;
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public List<StudentGradeDTO> getStudentGradesOfCCourse(int courseID) {
        String selectQuery = "SELECT * FROM StudentGrade join Person on StudentGrade.StudentID = Person.PersonID WHERE CourseID = ?";
        List<StudentGradeDTO> studentGradeList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(selectQuery);
            preparedStatement.setInt(1, courseID);
            System.out.println(courseID);
            System.out.println(courseID);
            ResultSet rsSet = preparedStatement.executeQuery();
            //Fix bug 
            CourseDTO course = new CourseDTO();
            while (rsSet.next()) {
                int studentID = rsSet.getInt("StudentID");
                StudentDTO student = ((IObjectDAL) studentDAL)
                        .getAnObjectByID(studentID);

                studentGradeList.add(new StudentGradeDTO(rsSet.getInt("EnrollmentID"),
                        rsSet.getDouble("Grade"), student, course));
            }
            return studentGradeList;
        } catch (SQLException ex) {
            return studentGradeList;
        }
    }
}
