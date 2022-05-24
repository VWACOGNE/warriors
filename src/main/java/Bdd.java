
import heros.Personnage;
import java.sql.*;
import java.util.*;
import java.util.Arrays;
import java.util.List;


public class Bdd {
    private Connection connexion;
    public List<Personnage> recupererUtilisateurs() {
        List<Personnage> utilisateurs = new ArrayList<Personnage>();
        Statement statement = null;
        ResultSet resultat = null;

    loadDatabase();

        Personnage utilisateur = null;

        try {
            statement = connexion.createStatement();

            // Exécution de la requête
            resultat = statement.executeQuery("SELECT * FROM Heros;");

            // Récupération des données
            while (resultat.next()) {
                String name = resultat.getString("name");
            int vie = resultat.getInt("vie");
            int attaque = resultat.getInt("attaque");
            String type = resultat.getString("type");
            int id = resultat.getInt("idHeros");





            try {
                Class<?> typeClass = Class.forName("heros."+type);
                utilisateur = (Personnage) typeClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                System.out.println(e);
            }

            utilisateur.setNomGuerrier(name);
            utilisateur.setVieGuerrier(vie);
            utilisateur.setForceGuerrier(attaque);
            utilisateur.setType(type);
            utilisateur.setIdHeros(id);



            utilisateurs.add(utilisateur);
        }
    } catch (SQLException e) {
    } finally {
        // Fermeture de la connexion
        try {
            if (resultat != null)
                resultat.close();
            if (statement != null)
                statement.close();
            if (connexion != null)
                connexion.close();
        } catch (SQLException ignore) {
        }
    }

        return utilisateurs;
}

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
            statement = connexion.createStatement();

            PreparedStatement preparedStatement = connexion.prepareStatement("SELECT plateau FROM Heros where idHeros = ?;");
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
                if (connexion != null)
                    connexion.close();
            } catch (SQLException ignore) {
            }
        }
        return plateau;
    }

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
            statement = connexion.createStatement();

            PreparedStatement preparedStatement = connexion.prepareStatement("SELECT position FROM Heros where idHeros = ?;");
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
                if (connexion != null)
                    connexion.close();
            } catch (SQLException ignore) {
            }
        }
        return position;
    }

    /**
     * fonction pour lancer la bdd
     */
    private void loadDatabase() {
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

    /**
     * Fonciton pouir ajouter un joueur dans la bdd
     * @param utilisateur
     * @param plateau
     */
    public void ajouterUtilisateur(Personnage utilisateur, Plateau plateau) {
        loadDatabase();

        try {
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO Heros(name, vie, attaque, type, plateau,position ) VALUES(?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, utilisateur.getNomGuerrier());
            preparedStatement.setInt(2, utilisateur.getVieGuerrier());
            preparedStatement.setInt(3, utilisateur.getForceGuerrier());
            preparedStatement.setString(4, utilisateur.getType());
            preparedStatement.setString(5, Arrays.toString(plateau.getTableau()));
            preparedStatement.setInt(6,plateau.getPosition());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fonction pour mettre a jour l'utilisateur
     * @param utilisateur
     * @param plateau
     * @param name
     */
    public void majUtilisateur(Personnage utilisateur, Plateau plateau, String name) {
        loadDatabase();

        try {
            PreparedStatement preparedStatement = connexion.prepareStatement("Update Heros  SET vie = ?, attaque = ?, plateau = ? ,position = ? where name = ?");
            preparedStatement.setInt(1, utilisateur.getVieGuerrier());
            preparedStatement.setInt(2, utilisateur.getForceGuerrier());
            preparedStatement.setString(3, Arrays.toString(plateau.getTableau()));
            preparedStatement.setInt(4,plateau.getPosition());
            preparedStatement.setString(5, name);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fonciton pour supprimer un utilisateur
     * @param utilisateur
     */
    public void supprimerUtilisateur(Personnage utilisateur) {
        loadDatabase();

        try {
            PreparedStatement preparedStatement = connexion.prepareStatement("DELETE FROM Heros WHERE name = ?;");
            preparedStatement.setString(1, utilisateur.getNomGuerrier());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
