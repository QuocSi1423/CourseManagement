/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAL.IDAL.IObjectDAL;
import DAL.IDAL.IStudentDAL;
import DTO.StudentDTO;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class StudentBUS {
    private IStudentDAL studentDAL;
    
    public StudentBUS(IStudentDAL studentDAL) {
        this.studentDAL = studentDAL;
    }
    
    public int insertObject(StudentDTO object) {
        StudentDTO studentDTO = ((IObjectDAL)this.studentDAL).
                getAnObjectByID(object.getID());
        if (studentDTO == null) 
            return ((IObjectDAL)this.studentDAL).insertObject(studentDTO);
       return 0;
    }
    
    public int updateObject(StudentDTO object) { 
        StudentDTO studentDTO = ((IObjectDAL)this.studentDAL).
                getAnObjectByID(object.getID());
        if (studentDTO != null) 
            return ((IObjectDAL)this.studentDAL).updateObject(studentDTO);
       return 0;
    }
    
    public int removeObject(int objectID) {
         StudentDTO studentDTO = ((IObjectDAL)this.studentDAL).
                getAnObjectByID(objectID);
        if (studentDTO != null) 
            return ((IObjectDAL)this.studentDAL).removeObject(objectID);
       return 0;
    }
    
    public Object getAnObjectByID(int objectID) {
        return ((IObjectDAL)this.studentDAL).getAnObjectByID(objectID);
    }
    
    public List<StudentDTO> getAllStudent() {
        return this.studentDAL.getAllStudent();
    }
    
    public List<StudentDTO> getAllStudentsOfACourse(int courseID) {
        // Chưa xử lý kiểm tra có khóa học không
            //.........
            //.........
        return this.studentDAL.getAllStudentsOfACourse(courseID);
    }
}
