package DAL;

import java.sql.ResultSet;

import DAL.IDAL.IObjectDAL;
import DTO.*;

public class OnsiteCourseDAL implements IObjectDAL{
    private DatabaseManager db;
    private static final String url = "jdbc:mysql://localhost:3306/school";
    private static final String user = "root";
    private static final String password = "";

    public OnsiteCourseDAL() {
        this.db = new DatabaseManager(url, user, password);
    }

    public int insertObject(Object obj) {
        OnsiteCourseDTO onsiteCourse = (OnsiteCourseDTO) obj;
        // 1050', 'aaa', 'FS', '15:30')
        String query = "INSERT INTO `onsitecourse` (`CourseID`, `Location`, `Days`, `Time`) VALUES ('" +
            onsiteCourse.getCourseID() + "', '" + 
            onsiteCourse.getLocation() + "', '" +
            onsiteCourse.getDays() +  "', '" +
            onsiteCourse.getTime() +
            "');";
        int result = this.db.executeNonQuery(query);
        return result;
    }

    public int updateObject(Object obj) {
        OnsiteCourseDTO onsiteCourse = (OnsiteCourseDTO) obj;
        String query = "UPDATE `onsitecourse` SET `Location` = '" + 
            onsiteCourse.getLocation() + "', `Days` = '" +
            onsiteCourse.getDays() + "', `Time` = '" +
            onsiteCourse.getTime() + 
            "' WHERE `onsitecourse`.`CourseID` = " + 
            onsiteCourse.getCourseID();
        // System.out.println(query);
        int result = this.db.executeNonQuery(query);
        return result;
    }

    public int removeObject(int objectID) {
        // Department department = (Department) obj;
        String query = "DELETE FROM `onsitecourse` WHERE `onsitecourse`.`CourseID` = " + objectID;
        int result = this.db.executeNonQuery(query);
        return result;
    }

    public Object getAnObjectByID(int objectID) {
        String query = "SELECT * FROM `onsitecourse` where `CourseID` = " + objectID;
        ResultSet result = this.db.executeQuery(query);
        return result;
    }
}
