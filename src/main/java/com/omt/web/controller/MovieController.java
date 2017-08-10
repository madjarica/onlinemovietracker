package com.omt.web.controller;

import com.omt.domain.Movie;
import com.omt.domain.QueryResultsMovie;
import com.omt.service.MovieService;
import com.omt.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    MovieService movieService;
    VideoService videoService;
    RestOperations restTemplate = new RestTemplate();
    String API_CALL = "https://api.themoviedb.org/3/search/movie?api_key={api_key}&query={search}";
    String API_KEY = "550e1867817e4bf3266023c5274d8858";


    @Autowired
    public MovieController(MovieService movieService, VideoService videoService) {
        this.movieService = movieService;
        this.videoService = videoService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Movie> findAll(){
        return movieService.findAll();
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Movie findOne(@PathVariable("id") Long id){
        return movieService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Movie save(@RequestBody Movie movie)throws Exception{
        if(movie.getId()!= null) {
            if (movieService.findOne(movie.getId()) != null) throw new Exception("You can't do that");
            if (videoService.findOne(movie.getId()) != null) throw new Exception("You can't use that id");
        }
        return movieService.save(movie);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Movie update(@RequestBody Movie movie)throws Exception{
        if(videoService.findOne(movie.getId()).getDtype().equals("TvShow")) throw new Exception("You can't use that id");
        return movieService.save(movie);
    }

    @RequestMapping(path="{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id){
        movieService.delete(id);
    }
    @RequestMapping(path = "search/{query}", method = RequestMethod.GET)
    public List<Movie> searchOnline(@PathVariable("query") String query) {

        QueryResultsMovie queryResults = restTemplate.getForObject(API_CALL,
                QueryResultsMovie.class, API_KEY, query);

        String moviesString = restTemplate.getForObject(API_CALL,
                String.class, API_KEY, query);
        System.out.println(moviesString);
        List<Movie> movies = queryResults.getMovies();
        for(int i = 0; i<movies.size(); i++){
            Long id = movies.get(i).getId();
            String trailers = restTemplate.getForObject("http://api.themoviedb.org/3/movies/{id}/videos?api_key={api_key}", String.class, id, API_KEY);
            System.out.println(trailers);
        }
        System.out.println(queryResults.getTrailers());
        return movies;
    }

}
