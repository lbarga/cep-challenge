package br.com.cep.controllers;

import br.com.cep.entity.ApiCep;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/cep")
public class CepController {


    private static CompletableFuture<String> sendGetRequestAsync(String url) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return sendGetRequest(url);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    private static String sendGetRequest(String url) throws IOException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        StringBuilder response = new StringBuilder();

        try {
            URL apiUrl = new URL(url);
            connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }

        return null;
    }

    @GetMapping(value = "/{cep}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCep(@PathVariable(value = "cep") String cep) {
        String url1 = "https://cdn.apicep.com/file/apicep/" + cep.substring(0, 5) + "-" + cep.substring(cep.length() - 3) + ".json";
        String url2 = "https://viacep.com.br/ws/" + cep + "/json/";
        String url3 = "https://brasilapi.com.br/api/cep/v2/" + cep + "";

        CompletableFuture<String> future1 = sendGetRequestAsync(url1);
        CompletableFuture<String> future2 = sendGetRequestAsync(url2);
        CompletableFuture<String> future3 = sendGetRequestAsync(url3);

        CompletableFuture<Void> result = CompletableFuture.allOf(future1, future2, future3);

        AtomicReference<String> answer = new AtomicReference<>("");

        result.thenRun(() -> {
            try {
                String response1 = future1.get();
//                String response2 = future2.get();
//                String response3 = future3.get();

                if (response1 != null) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        ApiCep apiCep = objectMapper.readValue(response1, ApiCep.class);

                        // Now you have the Java object representing the JSON data
                        System.out.println("Code: " + apiCep.getCode());
                        System.out.println("State: " + apiCep.getState());
                        System.out.println("City: " + apiCep.getCity());
                        System.out.println("District: " + apiCep.getDistrict());
                        System.out.println("Address: " + apiCep.getAddress());
                        // Add other fields as needed
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    answer.set(response1);

                    return;
                }

//                if (response2 != null) {
//                    answer.set(response2);
//                    return;
//                }
//
//                if (response3 != null) {
//                    answer.set(response3);
//                    return;
//                }

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        try {
            result.get(); // Wait for all requests to complete
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return String.valueOf(answer);
    }
}


