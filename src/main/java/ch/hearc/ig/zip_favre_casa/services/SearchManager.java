package ch.hearc.ig.zip_favre_casa.services;

import ch.hearc.ged.JSONUtilities;

import java.net.HttpURLConnection;
import java.util.Optional;

public class SearchManager {

    public String search(String token) throws Exception {
        String url = "http://157.26.83.80:2240/api/search/advanced";
        String requestBody = "{searchPattern: \"_etat|l01|Accepter|list\", contentTypeIDs: \"139\"}";
        // Ajout de l'en-tête d'autorisation avec le jeton
        HttpURLConnection connection = JSONUtilities.write(
                url,
                JSONUtilities.RequestMethod.POST,
                requestBody,
                token
        );
        return JSONUtilities.read(connection);

    }
}
