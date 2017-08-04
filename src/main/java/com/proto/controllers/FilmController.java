package com.proto.controllers;

import com.proto.beans.Film;
import com.proto.beans.Pays;
import com.proto.services.FilmService;
import com.proto.services.PaysService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/films")
public class FilmController {

    private final Logger logger = LoggerFactory.getLogger(FilmController.class);

    @Autowired
    FilmService filmService;

    @Autowired
    PaysService paysService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Map<String, Object> model) {
        List<Film> films = filmService.list();
        model.put("films", films);
        List<Pays> pays = paysService.list();
        model.put("pays", pays);
        model.put("active", "films");
        return "films";
    }

    //https://stackoverflow.com/questions/34048617/spring-boot-how-to-use-hiddenhttpmethodfilter
    @RequestMapping(method = RequestMethod.PUT)
    public ModelAndView add(ModelMap model, String annee, String titre, String codePays) {
        try {
            Pays pays = null;
            if (StringUtils.isNotBlank(codePays)) {
                pays = paysService.getById(codePays);
            }
            if (StringUtils.isNoneBlank(annee, titre)) {
                Film film = new Film();
                film.setTitre(titre);
                film.setAnnee(Integer.valueOf(annee));
                film.setPays(pays);
                filmService.save(film);
                model.put("success", true);
            }
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            model.put("success", false);
            model.put("error", e.getMessage());
        }
        //http://www.baeldung.com/spring-redirect-and-forward
        return new ModelAndView("redirect:/films", model);
    }
}