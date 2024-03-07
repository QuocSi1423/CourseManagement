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
        this.db = new DatabaseManager();
    }

    public CourseDAL(DatabaseManager db)
    {
        this.db = db;
    }

    public List<CourseDTO> getAllCourses() {
        String query = "SELECT * FROM `Course` join `Department` on Course.DepartmentID = Department.DepartmentID left join OnlineCourse on Course.CourseID = OnlineCourse.CourseID";
        ResultSet result = this.db.executeQuery(query);
        List<CourseDTO> Courses = new ArrayList<>();
        try {
            while(result.next()) { 
                Courses.add(new CourseDTO(result.getInt("CourseID"), 
                    new DepartmentDTO(result.getInt("DepartmentID"), result.getString("Name")),
                    result.getString("Title"), 
                    result.getInt("Credits") ,
                    result.getString("url") != null ? 1 : 0
                    ));
            }
            return Courses;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<CourseDTO> getCoursesByTitle(String title) {
        String query = "SELECT * FROM `Course` where `Course`.`Title` = '" + title + "'";
        ResultSet result = this.db.executeQuery(query);
        List<CourseDTO> Courses = new ArrayList<>();
        try {
            while(result.next()) {
                Courses.add(new CourseDTO(result.getInt("CourseID"), 
                new DepartmentDTO(),
                result.getString("Title"), 
                result.getInt("Credits") 
                ));
            }
            return Courses;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public int insertObject(Object obj) {
        CourseDTO Course = (CourseDTO) obj;
        String query = "INSERT INTO `Course` (`CourseID`, `Title`, `Credits`, `DepartmentID`) VALUES (NULL, " + "'" +
            Course.getTitle() + "', '" + 
            Course.getCredits() + "', '"+ 
            Course.getDepartment().getDepartmentID() +"');";
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
        CourseDTO Course = (CourseDTO) obj;
        String query = "UPDATE `Course` SET `Title` = '" + 
            Course.getTitle() + "', `Credits` = " + 
            Course.getCredits() + ", `DepartmentID` = '" + 
            Course.getDepartment().getDepartmentID() + "' where `CourseID` = '" +
            Course.getCourseID() + "'";
        int result = this.db.executeNonQuery(query);
        return result;
    }

    public int removeObject(int objectID) {
        String query = "DELETE FROM `Course` WHERE `Course`.`CourseID` = " + objectID;
        int result = this.db.executeNonQuery(query);
        return result;
    }

    public Object getAnObjectByID(int objectID) {
        String query = "SELECT * FROM `Course` join Department on Course.DepartmentID = Department.DepartmentID WHERE `Course`.`CourseID` = " + objectID;
        ResultSet result = this.db.executeQuery(query);
        return result;
    }
}
