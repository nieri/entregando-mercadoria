package br.com.munieri.entregando.mercadoria.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "mapa")
public class Mapa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "mapa")
    private Set<Rota> rotas;

    public Mapa() {
    }

    public Mapa(String nome) {
        this.nome = nome;
    }

    public Mapa(String nome, Set<Rota> rotas) {
        this.nome = nome;
        this.rotas = rotas;
    }

    public Set<Rota> getRotas() {
        if (rotas == null) {
            rotas = new HashSet<Rota>();
        }
        return rotas;
    }

    public void addRota(Rota rota) {
        getRotas().add(rota);
    }

    public void addRotas(Collection<Rota> rotas) {
        getRotas().addAll(rotas);
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
