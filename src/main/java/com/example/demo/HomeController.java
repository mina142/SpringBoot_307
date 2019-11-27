package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    DirectorRepository directorRepository;

    @RequestMapping("/")
    public String index(Model model){
        //first creat a director
        Director director = new Director();

        director.setName("Stephen Bullock");
        director.setGenre("Sci Fi");

        //Now let's creat a movie
        Movie movie = new Movie();
        movie.setTitle("Star Movie");
        movie.setYear(2017);
        movie.setDescription("About Starts");

        //add the movie to an empty list
        Set<Movie> movies = new HashSet<Movie>();
        movies.add(movie);

        movie = new Movie();
        movie.setTitle("DeathStar Ewoks");
        movie.setYear(2011);
        movie.setDescription("About Ewoks on the Deathhstar ...");
        movies.add(movie);


        //add the  list of movies to the direcotr's movie list
        director.setMovies(movies);
        //save the director to the database
        directorRepository.save(director);
        //grab all the directors from the database and send them to the template
        model.addAttribute("directors" , directorRepository.findAll());
        return "index";

    }
}
