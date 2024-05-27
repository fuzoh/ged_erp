package ch.hearc.ig.zip_favre_casa.services;

import ch.hearc.ged.JSONUtilities;

import java.net.HttpURLConnection;

public class FlowManager {
    public Number validate(Number ObjectID, String token) {
        String url = "http://157.26.83.80:2240/api/flow/validate/" + ObjectID;
        try {
            // Ajout de l'en-tête d'autorisation avec le jeton
            HttpURLConnection connection = JSONUtilities.write(url, JSONUtilities.RequestMethod.POST, null, token );
            String response = JSONUtilities.read(connection);
            System.out.println("New ID: " + response);
            return Integer.parseInt(response);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
