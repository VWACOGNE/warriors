import java.sql.*;

public class BddPlateau extends Bdd {

    /**
     * Fonction permettant de récupérer le plateur de jeux
     * @param id
     * @return plateau
     */
    public String recupererTableau(int id) {
        String plateau = null;
        Statement statement = null;
        ResultSet result = null;
        loadDatabase();
        try {
            statement = getConnexion().createStatement();

            PreparedStatement preparedStatement = getConnexion().prepareStatement("SELECT plateau FROM Heros where idHeros = ?;");
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeQuery();
            while (result.next()){
                plateau = result.getString("plateau");
            }
        } catch (SQLException e) {
        } finally {
            // Close the connection
            try {
                if (result != null)
                    result.close();
                if (statement != null)
                    statement.close();
                if (getConnexion() != null)
                    getConnexion().close();
            } catch (SQLException ignore) {
            }
        }
        return plateau;
    }
}
