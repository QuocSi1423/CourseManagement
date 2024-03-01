package BUS;

import DTO.*;

import java.sql.ResultSet;
import java.util.List;

import DAL.*;
import DAL.IDAL.*;

public class CourseBUS {
    private ICourseDAL courseDAL;
    private ILecturerDAL lecturerDAL;

    public CourseBUS(ICourseDAL courseDAL, ILecturerDAL lectureDAL) {
        this.courseDAL = courseDAL;
        this.lecturerDAL = lectureDAL;
    }

    public int createCourse(CourseDTO course) {
        int result = ((CourseDAL) this.courseDAL).insertObject(course);
        return result;
    }

    public int updateCourse(CourseDTO course) {
        int result = ((CourseDAL) this.courseDAL).updateObject(course);
        return result;
    }

    public int removeCourse(int courseID) {
        int result = ((CourseDAL) this.courseDAL).removeObject(courseID);
        return result;
    }

    public CourseDTO getACourseByID (int courseID) {
        ResultSet resultSet = (ResultSet) (((CourseDAL) this.courseDAL).getAnObjectByID(courseID));
        CourseDTO result = null;
        try {
            while(resultSet.next()) {
                result = new CourseDTO(resultSet.getInt("CourseID"), 
                    new DepartmentDTO(),
                    resultSet.getString("Title"), 
                    resultSet.getInt("Credits") 
                );
            }
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<CourseDTO> getAllCourse() {
        List<CourseDTO> result = this.courseDAL.getAllCourses();
        return result;
    }

    public List<CourseDTO> getCoursesByTitle(String title) {
        List<CourseDTO> result = this.courseDAL.getCoursesByTitle(title);
        return result;
    }
    
    public List<LecturerDTO> getLecturersOfCourse(int courseID) {
        List<LecturerDTO> result = lecturerDAL.getLecturersOfACourse(courseID);
        return result;
    }
    public List<LecturerDTO> getLecturerNotAssignForACourse(int courseID){
        List<LecturerDTO> result = lecturerDAL.getLecturerNotAssignForACourse(courseID);
        return result;
    }
}
