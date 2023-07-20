package br.com.cep.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {
    @Autowired
    private HttpService httpService;

    public CompletableFuture<String> sendGetRequestAsync(String url) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return httpService.sendGetRequest(url);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        });
    }
}
