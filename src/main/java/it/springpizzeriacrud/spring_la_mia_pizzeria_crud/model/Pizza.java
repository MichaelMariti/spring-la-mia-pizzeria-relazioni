package it.springpizzeriacrud.spring_la_mia_pizzeria_crud.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank(message="Name is mandatory")
    @Column(unique=true)
    private String nome;

    @NotNull
    @NotBlank(message="Description is mandatory")
    private String descrizione;

    @NotNull
    @NotBlank(message="Photo is mandatory")
    private String photoUrl;

    @NotNull
    @Min(value = 0)
    private double prezzo;

    // Relazioni
    @OneToMany(mappedBy = "pizza")
    private List<Offer> offers;



    // Costruttori
    // public Pizza(){
    // }

    // public Pizza(String nome, String descrizione, String photoUrl, double prezzo) {
    //     this.nome = nome;
    //     this.descrizione = descrizione;
    //     this.photoUrl = photoUrl;
    //     this.prezzo = prezzo;
    // }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public Integer getId() {
        return id;
    }

    public List<Offer> getOffers() {
        return offers;
    }


    // Setters
    public void setId(Integer id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }



    @Override
    public String toString() {
        return "Pizza [id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + ", photoUrl=" + photoUrl
                + ", prezzo=" + prezzo + ", getNome()=" + getNome() + ", getDescrizione()=" + getDescrizione()
                + ", getPhotoUrl()=" + getPhotoUrl() + ", getPrezzo()=" + getPrezzo() + ", getId()=" + getId()
                + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
                + "]";
    }

    

    

    
    
}
