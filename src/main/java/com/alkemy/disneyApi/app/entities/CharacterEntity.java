package com.alkemy.disneyApi.app.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`character`")
@Getter @Setter

public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String name;
    private String image;
    private int age;
    private double weight;
    private String history;

    //este Many to Many es el "menor", porque cuando agrego un personaje ya deberían existir las peliculas
    //Muchos personajes participan en varias peliculas
    @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL)
    private List<MovieEntity> movies = new ArrayList<>();

}
