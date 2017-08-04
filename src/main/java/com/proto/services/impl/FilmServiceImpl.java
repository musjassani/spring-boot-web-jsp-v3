package com.proto.services.impl;

import com.proto.beans.Artiste;
import com.proto.beans.Film;
import com.proto.beans.Genre;
import com.proto.services.ArtisteService;
import com.proto.services.FilmService;
import com.proto.services.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("filmService")
public class FilmServiceImpl extends AbstractServiceImpl<Film> implements FilmService{

    private final Logger logger = LoggerFactory.getLogger(FilmServiceImpl.class);
}
