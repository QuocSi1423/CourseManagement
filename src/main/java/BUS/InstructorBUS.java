package BUS;

import DAL.IDAL.ICourseDAL;
import DAL.IDAL.IInstructorDAL;
import DAL.IDAL.IObjectDAL;
import DAL.InstructorDAL;
import DTO.InstructorDTO;

/**
 *
 * @author ant1006
 */
public class InstructorBUS {

    private IInstructorDAL instructorDAL;
    

    public InstructorBUS(IInstructorDAL instructorDAL) {
        this.instructorDAL = instructorDAL;
    }

    public int createAInstructor(InstructorDTO instructor) {
        // check time conflict course instructor
        // code here
        // ...
        return instructorDAL.insertInstructor(instructor);
    }

    public int removeAInstructor(InstructorDTO instructor) {
        return instructorDAL.removeInstructor(instructor);
    }

}
