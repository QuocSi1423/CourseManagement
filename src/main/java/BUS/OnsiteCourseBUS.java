package BUS;

import java.sql.ResultSet;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import DAL.OnsiteCourseDAL;
import DAL.IDAL.*;
import DTO.OnsiteCourseDTO;

public class OnsiteCourseBUS {
    private IObjectDAL onsiteCourseDAL;

    public OnsiteCourseBUS() {
        this.onsiteCourseDAL = new OnsiteCourseDAL();
    }

    public int createAnOnsiteCourse(OnsiteCourseDTO course) {
        int result = ((OnsiteCourseDAL) this.onsiteCourseDAL).insertObject(course);
        return result;
    }

    public int updateAnOnsiteCourse(OnsiteCourseDTO course) {
        int result = ((OnsiteCourseDAL) this.onsiteCourseDAL).updateObject(course);
        return result;
    }

    public int removeAnOnsiteCourse(int courseID) {
        int result = ((OnsiteCourseDAL) this.onsiteCourseDAL).removeObject(courseID);
        return result;
    }

    public OnsiteCourseDTO getAnOnsiteCourse(int courseID) {
        ResultSet resultSet = (ResultSet) (((OnsiteCourseDAL) this.onsiteCourseDAL).getAnObjectByID(courseID));
        OnsiteCourseDTO result = null;
        try {
            while(resultSet.next()) {
                result = new OnsiteCourseDTO(resultSet.getInt("CourseID"), 
                    resultSet.getString("Location"),
                    resultSet.getString("Days"),
                    LocalTime.parse(resultSet.getString("Time"), DateTimeFormatter.ofPattern("HH:mm:ss"))
                );
            }
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
