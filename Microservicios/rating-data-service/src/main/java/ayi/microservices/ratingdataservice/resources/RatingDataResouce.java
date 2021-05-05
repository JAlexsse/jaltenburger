package ayi.microservices.ratingdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ayi.microservices.ratingdataservice.resources.model.RatingData;
import ayi.microservices.ratingdataservice.resources.model.UserRating;

@RestController
@RequestMapping("/ratingdata")
public class RatingDataResouce {

    @RequestMapping("/{movieId}")
    public RatingData getRatingData(@PathVariable("movieId") int movieId){
        return new RatingData(movieId, 4);
    }

    @RequestMapping("/user/{userId}")
    public UserRating getUserRatings(@PathVariable("userId") String userId) {
        
        //esta es una respuesta de la API, en este caso van a estar hardcodeadas
        List<RatingData> ratings = Arrays.asList(
            new RatingData(1234, 4),
            new RatingData(5321, 5),
            new RatingData(9892, 3)
        );
        
        UserRating userRating = new UserRating();
        userRating.setUserRatings(ratings);

        /*
        Es mejor devolver un objeto que contenga una lista, en vez de una lista.
        Por que retornar un objeto nos permite agregar funcionalidad sin dejar de ser compatible.
        */
        return userRating;
    }
}
