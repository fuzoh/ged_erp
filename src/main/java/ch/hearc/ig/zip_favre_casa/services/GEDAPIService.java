package ch.hearc.ig.zip_favre_casa.services;

import ch.hearc.ged.JSONUtilities;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.util.Objects;

import static ch.hearc.ged.JSONUtilities.RequestMethod.POST;

public class GEDAPIService {

    private String accessToken;


import ch.hearc.ig.zip_favre_casa.services.ConnectionManager;
import ch.hearc.ig.zip_favre_casa.services.FlowManager;    // GED API endpoints
    private final String API_LOCATION = "http://157.26.83.80:2240";
    private final String TOKEN_API_ENDPOINT = API_LOCATION + "/token";
    private final String ADVANCED_SEARCH_API_ENDPOINT = API_LOCATION + "/api/search/advanced";
    private final String VALIDATE_API_ENDPOINT = API_LOCATION + "/api/flow/validate/";

    /**
     * @param username The username to authenticate with
     * @param password User password
     * @return The raw JSON response from the API containing the token
     */
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

    /**
     * Try to parse the access token from the JSON response
     * @param jsonResponse The JSON response from the token API
     * @return the token from the JSON response
     */
    private String parseAccessToken(String jsonResponse) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonResponse);
            return rootNode.path("access_token").asText();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param contentTypeID The ID of the content type to search
     * @return Raw JSON response from the API
     */
    public String searchAcceptedStatus(int contentTypeID) {
        String requestBody = "{searchPattern: \"_etat|l01|Accepter|list\", contentTypeIDs: \"" + contentTypeID + "\"}";
        try {
            HttpURLConnection connection = JSONUtilities.write(
                    ADVANCED_SEARCH_API_ENDPOINT,
                    POST,
                    requestBody,
                    accessToken
            );
            return JSONUtilities.read(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param ObjectID The ID of the object to validate
     * @return The ID of the new object created after validation
     */
    public int validate(Number ObjectID) {
        String url = VALIDATE_API_ENDPOINT + ObjectID;
        try {
            HttpURLConnection connection = JSONUtilities.write(
                    url, POST,
                    null, accessToken
            );
            String response = JSONUtilities.read(connection);
            return Integer.parseInt(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
