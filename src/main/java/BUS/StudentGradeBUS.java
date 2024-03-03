/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAL.IDAL.ICourseDAL;
import DAL.IDAL.IObjectDAL;
import DAL.IDAL.IStudentGradeDAL;
import DTO.CourseDTO;
import DTO.StudentGradeDTO;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class StudentGradeBUS {

    private IStudentGradeDAL studentGradeDAL;
    private ICourseDAL courseDAL;

    public StudentGradeBUS(IStudentGradeDAL studentGradeDAL, ICourseDAL courseDAL) {
        this.studentGradeDAL = studentGradeDAL;
        this.courseDAL = courseDAL;
    }

    public <T> int insertObject(T object) {
        StudentGradeDTO studentGradeObj = (StudentGradeDTO) object;
        StudentGradeDTO studentGradeDTO = ((IObjectDAL) this.studentGradeDAL).
                getAnObjectByID(studentGradeObj.getEnrollmentID());
        if (studentGradeDTO == null) {
            return ((IObjectDAL) studentGradeDAL).insertObject(studentGradeObj);
        }
        return 0;
    }

    public <T> int updateObject(T object) {
      StudentGradeDTO studentGradeObj = (StudentGradeDTO) object;
//        StudentGradeDTO studentGradeDTO = ((IObjectDAL) this.studentGradeDAL).
//                getAnObjectByID(studentGradeObj.getEnrollmentID());
//        if (studentGradeDTO != null) {
            return ((IObjectDAL) studentGradeDAL).updateObject(studentGradeObj);
//        }
//        return 0;
    }

    public int removeObject(int objectID) {
        return ((IObjectDAL) studentGradeDAL).removeObject(objectID);
        // StudentGradeDTO studentGradeDTO = ((IObjectDAL) this.studentGradeDAL).
        //         getAnObjectByID(objectID);
        // if (studentGradeDTO != null) {
        // }
    }
    
    public <T> T getAnObjectByID(int objectID) {
        return ((IObjectDAL) studentGradeDAL).getAnObjectByID(objectID);
    }
//fix bug
    public List<StudentGradeDTO> getStudentGradesOfCCourse(int courseID) {
//        CourseDTO course = ((IObjectDAL) this.courseDAL).getAnObjectByID(courseID);
//        if (course != null)
            return this.studentGradeDAL.getStudentGradesOfCCourse(courseID);
        //return null;
    }
    
}
