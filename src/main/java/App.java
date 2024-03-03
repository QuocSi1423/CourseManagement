
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import DAL.DatabaseManager;
import GUI.AddStudentsGUI;
import GUI.CreateCourse;
import GUI.Main;
import GUI.QuanLyGiangVien;
import GUI.QuanLyGiangVienPanel;
import GUI.pointmanagement;
import io.github.cdimascio.dotenv.Dotenv;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        FlatMacLightLaf.setup();
        new pointmanagement(1045).setVisible(true);
    }
}
