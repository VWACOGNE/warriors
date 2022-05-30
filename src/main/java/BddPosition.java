import java.sql.*;

public class BddPosition extends Bdd {

    /**
     * Fonction permettant de récupérer la position du joueur
     * @param id
     * @return position
     */
    public int recupererPosition(int id) {
        int position = 0;
        Statement statement = null;
        ResultSet result = null;
        loadDatabase();
        try {
            statement = getConnexion().createStatement();

            PreparedStatement preparedStatement = getConnexion().prepareStatement("SELECT position FROM Heros where idHeros = ?;");
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeQuery();
            while (result.next()){
                position = result.getInt("position");
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
        return position;
    }
}
