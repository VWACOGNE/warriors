import heros.Guerrier;
import heros.Personnage;

import java.sql.*;
import java.util.*;

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
    public void ajouterUtilisateur(Personnage utilisateur) {
        loadDatabase();

        try {
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO Heros(name, vie, attaque, type, plateau) VALUES(?, ?, ?, ?, ?);");
            preparedStatement.setString(1, utilisateur.getNomGuerrier());
            preparedStatement.setInt(2, utilisateur.getVieGuerrier());
            preparedStatement.setInt(3, utilisateur.getForceGuerrier());
            preparedStatement.setString(4, utilisateur.getType());
            preparedStatement.setString(5, null);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void ajouterTableau(Plateau.Cases[] tableau, Personnage player) {
        loadDatabase();
        List<Plateau.Cases> list = Arrays.asList(tableau);
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement("UPDATE Heros set plateau = ? WHERE name = ? ;");
            preparedStatement.setString(1, String.valueOf(list));

            preparedStatement.setString(2, player.getNomGuerrier());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
