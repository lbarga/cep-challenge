package br.com.cep.entity;

public class Cep {
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private String país;
    private String codigoIBGECidade;
    private String codigoIBGEEstado;

    protected Cep() {
    }

    public Cep(String logradouro, String bairro, String cidade, String estado, String país) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.país = país;
        this.codigoIBGECidade = null;
        this.codigoIBGEEstado = null;
    }

    public Cep(String logradouro, String bairro, String cidade, String estado, String país, String codigoIBGECidade, String codigoIBGEEstado) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.país = país;
        this.codigoIBGECidade = codigoIBGECidade;
        this.codigoIBGEEstado = codigoIBGEEstado;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getPaís() {
        return país;
    }

    public String getCodigoIBGECidade() {
        return codigoIBGECidade;
    }

    public String getCodigoIBGEEstado() {
        return codigoIBGEEstado;
    }
}
