package br.com.buscar_cep.utils;

import org.springframework.stereotype.Service;

@Service
public class CepUtils {
    public String formatCep(String cep) {
        return cep.replaceAll("(\\d{5})(\\d{3})", "$1-$2");
    }
}
