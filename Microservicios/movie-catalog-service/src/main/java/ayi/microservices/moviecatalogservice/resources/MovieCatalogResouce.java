package ayi.microservices.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import ayi.microservices.moviecatalogservice.model.CatalogItem;
import ayi.microservices.moviecatalogservice.model.MovieDetails;
import ayi.microservices.moviecatalogservice.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResouce {
    
    /*
    Pide un bean de tipo restTemplate, como el bean esta creado lo trae.
    Autowired es un consumidor, cuando se lo utiliza se esta pidiendo algo
    por el contrario bean es un productor, spring guarda lo que bean produce 
    para cuando algun servicio lo necesite.
    */

    @Autowired
    private RestTemplate restTemplate;

    @Autowired  
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userID) {     

        //get todas las id de las peliculas que calificaron
        //forma sincronica con restTemplate, pronto va a ser deprecado.
        UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingdata/user/" + userID, UserRating.class);

        //foreach id de pelicula, call movie info service y getdetails
        
        /*
        al utilizar webclient se usa asincronia. utiliza la parte reactiva del ecosistema de spring.
        cuando lo usamos le decimos que hacer, pero no esperamos respuesta
        en cambio, le dmaos un lambda que provee instrucciones de los pasos a seguir 
        cuando obtenga la información que estabamos buscando.
        */

        return ratings.getUserRatings().stream().map(rating -> {
            MovieDetails movie = webClientBuilder.build() //cada vez que se hace una call, se debe hacer una instancia. cliente
                                .get()                    // especificar el metodo
                                .uri("http://localhost:8087/movies/"+ rating.getMovieId()) //proveemos la url donde se quiere acceder
                                .retrieve()                                                //orden, se espera obtener informacion
                                .bodyToMono(MovieDetails.class)                            //convierte la respuesta en una instancia de la clase provista
                                .block();   //como en este metodo retornamos una lista (y no un mono) entonces tenemos que esperar a tener la listas
            
            
            //organizar informacion
            return new CatalogItem(movie.getTitle(), movie.getDescription(), rating.getRating());
        }).collect(Collectors.toList());

        /*
        un mono es una promesa de que vas a obtener un objeto, pero no es este instante.
        va a darte, en el futuro, lo que necesitas
        */
        
        /* list.add(new CatalogItem("Lo que el viento se llevo", "El viento se llevo todo.", 4));
        list.add(new CatalogItem("En el cielo", "hay nubes", 5));
        list.add(new CatalogItem("Chuchu", "Wawa", 3));
        */
    }
}
