package br.com.buscar_cep.models;

import java.util.Objects;

public class CepDTO {
    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private String codigoIBGECidade;
    private String codigoIBGEEstado;

    public CepDTO() {
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCodigoIBGECidade() {
        return codigoIBGECidade;
    }

    public void setCodigoIBGECidade(String codigoIBGECidade) {
        this.codigoIBGECidade = codigoIBGECidade;
    }

    public String getCodigoIBGEEstado() {
        return codigoIBGEEstado;
    }

    public void setCodigoIBGEEstado(String codigoIBGEEstado) {
        this.codigoIBGEEstado = codigoIBGEEstado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CepDTO cepDTO = (CepDTO) o;
        return Objects.equals(cep, cepDTO.cep) && Objects.equals(logradouro, cepDTO.logradouro) && Objects.equals(bairro, cepDTO.bairro) && Objects.equals(cidade, cepDTO.cidade) && Objects.equals(estado, cepDTO.estado) && Objects.equals(pais, cepDTO.pais) && Objects.equals(codigoIBGECidade, cepDTO.codigoIBGECidade) && Objects.equals(codigoIBGEEstado, cepDTO.codigoIBGEEstado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cep, logradouro, bairro, cidade, estado, pais, codigoIBGECidade, codigoIBGEEstado);
    }
}
