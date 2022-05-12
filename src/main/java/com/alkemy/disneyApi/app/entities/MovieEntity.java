package com.alkemy.disneyApi.app.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movie")
@Getter @Setter

public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String title;
    private String image;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "release_date") //uso sintaxis correcta para SQL
    private LocalDate releaseDate;

    private int score;

    /*
    muchas peliculas en un solo género
    fetch y cascade para traer la info de antemano, y para q se elimine todo por si hago un delete
    JoinColumn refiere al Id de genero q vamos a usar para instanciar el objeto
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id", insertable = false, updatable = false)
    private GenreEntity genre;

    //uso el id para guardar y actualizar en la DB, si o si tiene un género
    @Column(name = "genre_id", nullable = false)
    private int genreId;

    //Este Many to Many engloba al de Personajes, porque cuando creo una peli, ya puedo agregar los personajes
    //Muchas pelis tienen muchos personajes
    //JoinTable crea la Tabla intermedia entre los N:N, con los datos necesarios para crearla
    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "character_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    private Set<CharacterEntity> characters = new HashSet<>();

}
