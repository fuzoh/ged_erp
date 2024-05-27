package ch.hearc.ig.zip_favre_casa;


import ch.hearc.ig.zip_favre_casa.services.ConnectionManager;
import ch.hearc.ig.zip_favre_casa.services.FlowManager;
import ch.hearc.ig.zip_favre_casa.services.SearchManager;

public class Main {
    public static void main(String[] args) {
        ConnectionManager manager = new ConnectionManager();
        SearchManager searchManager = new SearchManager();
        FlowManager flowManager = new FlowManager();

        String token = manager.fetchToken("CFavre", "123456789");
        searchManager.search(token);
        //ID manuel
        flowManager.validate(1237, token);
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
