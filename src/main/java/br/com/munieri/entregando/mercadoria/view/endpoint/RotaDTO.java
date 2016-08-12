package br.com.munieri.entregando.mercadoria.view.endpoint;

import java.io.Serializable;

public class RotaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String origem;
    private String destino;
    private Integer distancia;

    public RotaDTO() {
    }

    public RotaDTO(String origem, String destino, Integer distancia) {
        this.origem = origem;
        this.destino = destino;
        this.distancia = distancia;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Integer getDistancia() {
        return distancia;
    }

    public void setDistancia(Integer distancia) {
        this.distancia = distancia;
    }
}