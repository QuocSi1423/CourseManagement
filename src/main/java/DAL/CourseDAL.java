package DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DAL.IDAL.*;
import DTO.CourseDTO;
import DTO.DepartmentDTO;
import java.sql.SQLException;

public class CourseDAL implements IObjectDAL, ICourseDAL{
    private DatabaseManager db;
    private static final String url = "jdbc:mysql://localhost:3306/school";
    private static final String user = "root";
    private static final String password = "";

    public CourseDAL() {
        this.db = new DatabaseManager(url, user, password);
    }

    public List<CourseDTO> getAllCourses() {
        String query = "SELECT * FROM `Course` join `Department` on Course.DepartmentID = Department.DepartmentID left join OnlineCourse on Course.CourseID = OnlineCourse.CourseID";
        ResultSet result = this.db.executeQuery(query);
        List<CourseDTO> courses = new ArrayList<>();
        try {
            while(result.next()) { 
                courses.add(new CourseDTO(result.getInt("CourseID"), 
                    new DepartmentDTO(result.getInt("DepartmentID"), result.getString("Name")),
                    result.getString("Title"), 
                    result.getInt("Credits") ,
                    result.getString("url") != null ? 1 : 0
                    ));
            }
            return courses;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<CourseDTO> getCoursesByTitle(String title) {
        String query = "SELECT * FROM `Course` where `course`.`Title` = '" + title + "'";
        ResultSet result = this.db.executeQuery(query);
        List<CourseDTO> courses = new ArrayList<>();
        try {
            while(result.next()) {
                courses.add(new CourseDTO(result.getInt("CourseID"), 
                new DepartmentDTO(),
                result.getString("Title"), 
                result.getInt("Credits") 
                ));
            }
            return courses;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public int insertObject(Object obj) {
        CourseDTO course = (CourseDTO) obj;
        String query = "INSERT INTO `course` (`CourseID`, `Title`, `Credits`, `DepartmentID`) VALUES (NULL, " + "'" +
            course.getTitle() + "', '" + 
            course.getCredits() + "', '"+ 
            course.getDepartment().getDepartmentID() +"');";
        int rowsAffected = this.db.executeNonQuery(query);
        if(rowsAffected == 0){
            return -1;
        }
        ResultSet rs = this.db.executeQuery("select last_insert_id()");
        try {
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            return -1;
        }
    }


    public int updateObject(Object obj) {
        CourseDTO course = (CourseDTO) obj;
        String query = "UPDATE `course` SET `Title` = '" + 
            course.getTitle() + "', `Credits` = " + 
            course.getCredits() + ", `DepartmentID` = '" + 
            course.getDepartment().getDepartmentID() + "' where `CourseID` = '" +
            course.getCourseID() + "'";
        int result = this.db.executeNonQuery(query);
        return result;
    }

    public int removeObject(int objectID) {
        // Department department = (Department) obj;
        String query = "DELETE FROM `course` WHERE `course`.`CourseID` = " + objectID;
        int result = this.db.executeNonQuery(query);
        return result;
    }

    public Object getAnObjectByID(int objectID) {
        String query = "SELECT * FROM `course` join department on course.DepartmentID = department.DepartmentID WHERE `course`.`CourseID` = " + objectID;
        ResultSet result = this.db.executeQuery(query);
        return result;
    }
}
