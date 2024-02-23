
import DAL.DatabaseManager;
import io.github.cdimascio.dotenv.Dotenv;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Dotenv dotenv = Dotenv.configure().directory("./.env").load();
        String user = dotenv.get("USER_NAME");
        String password = dotenv.get("PASSWORD");
        String url = dotenv.get("URL");
        DatabaseManager db = new DatabaseManager(url, user, password);
    }
}
