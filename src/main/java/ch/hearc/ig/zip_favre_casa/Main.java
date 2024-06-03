package ch.hearc.ig.zip_favre_casa;

import ch.hearc.ig.zip_favre_casa.entities.Object;
import ch.hearc.ig.zip_favre_casa.services.ConnectionManager;
import ch.hearc.ig.zip_favre_casa.services.FlowManager;
import ch.hearc.ig.zip_favre_casa.services.SearchManager;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ConnectionManager manager = new ConnectionManager();
        SearchManager searchManager = new SearchManager();
        FlowManager flowManager = new FlowManager();

        String token = manager.fetchToken("BNicoud", "123456789");
        try {
            var data = searchManager.search(token);
            var objectMapper = new ObjectMapper();
            List<Object> objects = objectMapper.readValue(data, new TypeReference<List<Object>>() {});

            for (Object object : objects) {
                System.out.println("--------------------------------------------------");
                System.out.println(object);
                flowManager.validate(object.getObjectID(), token);
            }
        } catch (JsonMappingException e) {
            System.out.println("Error while mapping JSON to object");
            throw new RuntimeException(e);
        } catch (JsonParseException e) {
            System.out.println("Error while parsing JSON");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error while calling the API");
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("Error");
            throw new RuntimeException(e);
        }
        //JSONUtilities.write()..... + passer le token en paramètre

        // TODO
        // Récupérer les quitances depuis la GED -> état validé
        // Avec une recherche avancée, récupérer les quitances en attente de paiement
        // {
        //  "searchPattern": "_etat|l01|Accepter - En attente de paiement|list",
        //  "contentTypeIDs": "137"
        // }

        // Passer les quitances récupérées dans l'état en attente de paiement
        // Passer les quitances en attente de paiement dans l'état payé

    }
}
