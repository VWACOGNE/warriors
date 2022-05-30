import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Bdd {
    private Connection connexion;

    public Connection getConnexion() {
        return connexion;
    }

    public void setConnexion(Connection connexion) {
        this.connexion = connexion;
    }

    /**
     * fonction pour lancer la bdd
     */
    void loadDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }
        try {
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/warrior", "wacogne", "Victor240295!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
