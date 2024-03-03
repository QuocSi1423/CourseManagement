package DAL;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import DAL.IDAL.*;
import DTO.DepartmentDTO;

public class DepartmentDAL implements IObjectDAL, IDepartmentDAL{
    private DatabaseManager db;
    private static final String url = "jdbc:mysql://localhost:3306/school";
    private static final String user = "root";
    private static final String password = "";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public DepartmentDAL() {
        this.db = new DatabaseManager();
    }

    public int insertObject(Object obj) {
        DepartmentDTO Department = (DepartmentDTO) obj;
        String query = "INSERT INTO `Department` (`DepartmentID`, `Name`, `Budget`, `StartDate`, `Administrator`) VALUES ('" +
             Department.getDepartmentID() + "', '" + 
             Department.getName() + "', '" + 
             Department.getBudget() + "', '" + 
             Department.getStartDate() + "', '"+ 
             Department.getAdministrator() +"');";
        int result = this.db.executeNonQuery(query);
        return result;
    }

    public int updateObject(Object obj) {
        DepartmentDTO Department = (DepartmentDTO) obj;
        String query = "UPDATE `Department` SET `Name` = '" + 
            Department.getName() + "', `Budget` = " + 
            Department.getBudget() + ", `StartDate` = '" + 
            Department.getStartDate() + "', `Administrator` = '" + 
            Department.getAdministrator() +"' WHERE `Department`.`DepartmentID` = " + 
            Department.getDepartmentID();
        int result = this.db.executeNonQuery(query);
        return result;
    }

    public int removeObject(int objectID) {
        // Department Department = (Department) obj;
        String query = "DELETE FROM `Department` WHERE `Department`.`DepartmentID` = " + objectID;
        int result = this.db.executeNonQuery(query);
        return result;
    }

    public DepartmentDTO getAnObjectByID(int objectID) {
        String query = "SELECT * FROM `Department` WHERE `Department`.`DepartmentID` = " + objectID;
        ResultSet result = this.db.executeQuery(query);
        DepartmentDTO Department = null;
        try {
            while(result.next()) {
                Department = new DepartmentDTO(result.getInt("DepartmentID"), 
                    result.getString("Name"), 
                    result.getDouble("Budget"), 
                    LocalDateTime.parse(result.getString("StartDate"), formatter),  
                    result.getInt("Administrator"));
            }
            return Department;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<DepartmentDTO> getAllDepartments() {
        String query = "SELECT * FROM `Department`";
        ResultSet result = this.db.executeQuery(query);
        
        List<DepartmentDTO> Departments = new ArrayList<>();
        try {
            
            while(result.next()) {
                Departments.add(new DepartmentDTO(result.getInt("DepartmentID"), 
                    result.getString("Name"), 
                    result.getDouble("Budget"), 
                    LocalDateTime.parse(result.getString("StartDate"), formatter),  
                    result.getInt("Administrator")));
            }
            return Departments;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

}
