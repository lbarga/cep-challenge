package br.com.buscar_cep.services;

import br.com.buscar_cep.constants.Strings;
import br.com.buscar_cep.models.ApiCepDTO;
import br.com.buscar_cep.models.BrasilApiDTO;
import br.com.buscar_cep.models.CepDTO;
import br.com.buscar_cep.models.ViaCepDTO;
import br.com.buscar_cep.utils.CepUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class CepService {

    @Autowired
    private AsyncService asyncService;
    @Autowired
    private CepUtils cepUtils;
    @Autowired
    private Strings strings;

    public CepDTO getCepInMultiplesApis(String strCep) throws IOException {
        String apiCepUrl = "https://cdn.apicep.com/file/apicep/" + cepUtils.formatCep(strCep) + ".json";
        String viaCepUrl = "https://viacep.com.br/ws/" + strCep + "/json/";
        String brasilApiUrl = "https://brasilapi.com.br/api/cep/v2/" + strCep + "";

        CompletableFuture<String> apiCepFuture = asyncService.sendGetRequestAsync(apiCepUrl);
        CompletableFuture<String> viaCepFuture = asyncService.sendGetRequestAsync(viaCepUrl);
        CompletableFuture<String> brasilApiFuture = asyncService.sendGetRequestAsync(brasilApiUrl);

        CompletableFuture<Void> result = CompletableFuture.allOf(apiCepFuture, viaCepFuture);

        CepDTO cepDTO = new CepDTO();

        result.thenRun(() -> {
            try {
                String apiCepResponse = apiCepFuture.get();
                String viaCepResponse = viaCepFuture.get();
                String brasilApiResponse = brasilApiFuture.get();

                if (apiCepResponse != null) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();

                        ApiCepDTO apiCepDTO = objectMapper.readValue(apiCepResponse, ApiCepDTO.class);

                        cepDTO.setCep(apiCepDTO.getCode());
                        cepDTO.setLogradouro(apiCepDTO.getAddress());
                        cepDTO.setBairro(apiCepDTO.getDistrict());
                        cepDTO.setCidade(apiCepDTO.getCity());
                        cepDTO.setEstado(apiCepDTO.getState());
                        cepDTO.setPais(strings.BRAZIL);

                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (viaCepResponse != null) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();

                        ViaCepDTO viaCepDTO = objectMapper.readValue(viaCepResponse, ViaCepDTO.class);

                        cepDTO.setCep(viaCepDTO.getCep());
                        cepDTO.setLogradouro(viaCepDTO.getLogradouro());
                        cepDTO.setBairro(viaCepDTO.getBairro());
                        cepDTO.setCidade(viaCepDTO.getLocalidade());
                        cepDTO.setEstado(viaCepDTO.getUf());
                        cepDTO.setPais(strings.BRAZIL);
                        cepDTO.setCodigoIBGECidade(viaCepDTO.getIbge());
                        cepDTO.setCodigoIBGEEstado(viaCepDTO.getIbge());

                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (brasilApiResponse != null) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();

                        BrasilApiDTO brasilApiDTO = objectMapper.readValue(brasilApiResponse, BrasilApiDTO.class);

                        cepDTO.setPais(strings.BRAZIL);
                        cepDTO.setCep(cepUtils.formatCep(brasilApiDTO.getCep()));
                        cepDTO.setLogradouro(brasilApiDTO.getStreet());
                        cepDTO.setBairro(brasilApiDTO.getNeighborhood());
                        cepDTO.setCidade(brasilApiDTO.getCity());
                        cepDTO.setEstado(brasilApiDTO.getState());

                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        try {
            result.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return cepDTO;
    }
}
