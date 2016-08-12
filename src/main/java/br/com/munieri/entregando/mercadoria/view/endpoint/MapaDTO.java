package br.com.munieri.entregando.mercadoria.view.endpoint;

import java.util.List;

public class MapaDTO {

    private static final long serialVersionUID = 1L;

    private String nome;

    private List<RotaDTO> rotas;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<RotaDTO> getRotas() {
        return rotas;
    }

    public void setRotas(List<RotaDTO> rotas) {
        this.rotas = rotas;
    }

    @Override
    public String toString() {
        return nome;
    }
}
