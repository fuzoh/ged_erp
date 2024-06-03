package ch.hearc.ig.zip_favre_casa.services;

import ch.hearc.ged.JSONUtilities;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.util.Objects;

import static ch.hearc.ged.JSONUtilities.RequestMethod.POST;

public class GEDAPIService {

    private String accessToken;

    private final String API_LOCATION = "http://157.26.83.80:2240";
    private final String TOKEN_API_ENDPOINT = API_LOCATION + "/token";
    private final String ADVANCED_SEARCH_API_ENDPOINT = API_LOCATION + "/api/search/advanced";
    private final String VALIDATE_API_ENDPOINT = API_LOCATION + "/api/flow/validate/";

    public String fetchToken(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        // Create the request body (form-data)
        String requestBody = "grant_type=password&username=" + username + "&password=" + password;
        try {
            HttpURLConnection connection = JSONUtilities.write(
                    TOKEN_API_ENDPOINT, POST,
                    requestBody, null);
            String response = JSONUtilities.read(connection);
            this.accessToken = parseAccessToken(response);
            return accessToken;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String parseAccessToken(String jsonResponse) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonResponse);
            return rootNode.path("access_token").asText();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String search(String token) {
        String requestBody = "{searchPattern: \"_etat|l01|Accepter|list\", contentTypeIDs: \"139\"}";
        try {
            HttpURLConnection connection = JSONUtilities.write(
                    ADVANCED_SEARCH_API_ENDPOINT,
                    POST,
                    requestBody,
                    token
            );
            return JSONUtilities.read(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Number validate(Number ObjectID, String token) {
        String url = VALIDATE_API_ENDPOINT + ObjectID;
        try {
            HttpURLConnection connection = JSONUtilities.write(
                    url, POST,
                    null, token
            );
            String response = JSONUtilities.read(connection);
            return Integer.parseInt(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
