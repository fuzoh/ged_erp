package ch.hearc.ig.zip_favre_casa;

import ch.hearc.ig.zip_favre_casa.entities.Object;
import ch.hearc.ig.zip_favre_casa.services.GEDAPIService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Authenticate to the GED api
            var gedApiService = new GEDAPIService("admin.24gc", "admin.24gc");
            // 1. Get all content types 137 un Accepted status
            // Content type ID must match the required one in the GED
            var data = gedApiService.searchAcceptedStatus(160);
            // Parse the JSON response with Jackson and a TypeReference
            var objectMapper = new ObjectMapper();
            List<Object> objects = objectMapper.readValue(data, new TypeReference<List<Object>>() {
            });

            // Validates the objects to change their flow step in the GED
            for (Object object : objects) {
                System.out.println("Changing flow step for : " + object.getObjectID());
                var newId = gedApiService.validate(object.getObjectID());
                // Update the object id
                object.setObjectID(newId);
                System.out.println("Object updated, new id : " + object.getObjectID());
            }

            // Make the payments (simulate the ERP that validate payments)
            // This will be called only after the payment has been done in the ERP
            for (Object object : objects) {
                System.out.println(
                        "The object : " + object.getObjectID() + " has been paid, validating the step in the GED.");
                var newId = gedApiService.validate(object.getObjectID());
                // Update the object id
                object.setObjectID(newId);
                System.out.println("Object updated, new id for payed object : " + object.getObjectID());
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
        } catch (RuntimeException e) {
            System.out.println(
                    "An error occurred while processing the data or accessing the API, verify endpoints or credentials");
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("Error");
            throw new RuntimeException(e);
        }

    }
}
