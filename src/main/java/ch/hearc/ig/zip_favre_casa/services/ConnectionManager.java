package ch.hearc.ig.zip_favre_casa.services;

import java.net.HttpURLConnection;
import ch.hearc.ged.JSONUtilities;

public class ConnectionManager {

    private String accessToken;

    public String fetchToken(String username, String password) {
        String url = "http://157.26.83.80:2240/token";
        // Utiliser les paramètres username et password dans la requête
        String requestBody = "grant_type=password&username=" + username + "&password=" + password;

        try {
            HttpURLConnection connection = JSONUtilities.write(url, JSONUtilities.RequestMethod.POST, requestBody, null);
            String response = JSONUtilities.read(connection);
            this.accessToken = parseAccessToken(response);
            System.out.println("Access Token: " + accessToken);
            return accessToken;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String parseAccessToken(String jsonResponse) {
        try {
            // Utilisation de Jackson ou d'une autre bibliothèque pour parser le JSON
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            com.fasterxml.jackson.databind.JsonNode rootNode = mapper.readTree(jsonResponse);
            return rootNode.path("access_token").asText();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getAccessToken() {
        return accessToken;
    }

}

