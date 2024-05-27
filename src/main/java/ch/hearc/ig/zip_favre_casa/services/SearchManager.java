package ch.hearc.ig.zip_favre_casa.services;

import ch.hearc.ged.JSONUtilities;

import java.net.HttpURLConnection;

public class SearchManager {

    public void search(String token) {
        String url = "http://157.26.83.80:2240/api/search/advanced";
        String requestBody = "{searchPattern: \"_etat|l01|Accepter|list\", contentTypeIDs: \"139\"}";

        try {
            // Ajout de l'en-tête d'autorisation avec le jeton
            HttpURLConnection connection = JSONUtilities.write(url, JSONUtilities.RequestMethod.POST, requestBody, token);
            String response = JSONUtilities.read(connection);
            System.out.println("Response: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
