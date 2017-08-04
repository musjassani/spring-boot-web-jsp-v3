package com.proto.services.impl;

import com.proto.beans.Artiste;
import com.proto.beans.Film;
import com.proto.beans.Genre;
import com.proto.services.ArtisteService;
import com.proto.services.FilmService;
import com.proto.services.GenreService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan("com.proto")
//@Transactional
public class FilmServiceImplTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    DataSource dataSource;

    @Autowired
    FilmService filmService;

    @Autowired
    ArtisteService artisteService;

    @Autowired
    GenreService genreService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testInsert() throws Exception {
        Film gravity = new Film();
        gravity.setTitre("Gravity");
        gravity.setAnnee(2013);

//        Genre genre = new Genre();
//        genre.setCode("Science-fiction");
//        genreService.save(genre);
//        gravity.setGenre(genre);

        Artiste cuaron = new Artiste();
        cuaron.setPrenom("Alfonso");
        cuaron.setNom("Cuaron");

        gravity.setRealisateur(cuaron);
        //cuaron.addFilm(gravity);// Le réalisateur de Gravity est Alfonso Cuaron

        // Sauvegardons dans la base
        filmService.save(gravity);
        artisteService.save(cuaron);
        List<Film> list = filmService.list();
    }
}