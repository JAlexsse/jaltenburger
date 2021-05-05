package ayi.microservices.movieinfoservice.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ayi.microservices.movieinfoservice.model.Movie;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable int movieId){
        return new Movie(1, "Chuchu", "Wawa");
    }
    
}
