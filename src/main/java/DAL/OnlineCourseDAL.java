package DAL;

import java.sql.ResultSet;

import DAL.IDAL.IObjectDAL;
import DTO.*;

public class OnlineCourseDAL implements IObjectDAL{
    private DatabaseManager db;
    private static final String url = "jdbc:mysql://localhost:3306/school";
    private static final String user = "root";
    private static final String password = "";

    public OnlineCourseDAL() {
        this.db = new DatabaseManager();
    }
    public OnlineCourseDAL(DatabaseManager db){
        this.db = db;
    }
    public int insertObject(Object obj) {
        OnlineCourseDTO onlineCourse = (OnlineCourseDTO) obj;
        String query = "INSERT INTO `OnlineCourse` (`CourseID`, `url`) VALUES (" +
            onlineCourse.getCourseID() + ", '" + 
            onlineCourse.getUrl() + "');";
        int result = this.db.executeNonQuery(query);
        return result;
    }

    public int updateObject(Object obj) {
        OnlineCourseDTO onlineCourse = (OnlineCourseDTO) obj;
        String query = "UPDATE `OnlineCourse` SET `url` = '" + 
            onlineCourse.getUrl() + "' WHERE `OnlineCourse`.`CourseID` = " + 
            onlineCourse.getCourseID();
        int result = this.db.executeNonQuery(query);
        return result;
    }

    public int removeObject(int objectID) {
        // Department department = (Department) obj;
        String query = "DELETE FROM `OnlineCourse` WHERE `OnlineCourse`.`CourseID` = " + objectID;
        int result = this.db.executeNonQuery(query);
        return result;
    }

    public Object getAnObjectByID(int objectID) {
        String query = "SELECT * FROM `OnlineCourse` where `CourseID` = " + objectID;
        ResultSet result = this.db.executeQuery(query);
        return result;
    }
}
