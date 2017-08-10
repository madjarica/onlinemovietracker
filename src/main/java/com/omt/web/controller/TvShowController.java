package com.omt.web.controller;

import java.util.List;

import com.omt.domain.Person;
import com.omt.domain.QueryResultsTv;
import com.omt.domain.TvShowEpisode;
import com.omt.service.PersonService;
import com.omt.service.TvShowEpisodeService;
import com.omt.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omt.domain.TvShow;
import com.omt.service.TvShowService;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/tvshows")
public class TvShowController {

    TvShowService tvShowService;
    VideoService videoService;
    PersonService personService;
    TvShowEpisodeService tvShowEpisodeService;
    RestTemplate restTemplate = new RestTemplate();


    @Autowired
    public TvShowController(TvShowService tvShowService, VideoService videoService, PersonService personService, TvShowEpisodeService tvShowEpisodeService) {
        this.tvShowService = tvShowService;
        this.videoService = videoService;
        this.personService = personService;
        this.tvShowEpisodeService = tvShowEpisodeService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<TvShow> findAll() {
        return tvShowService.findAll();
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public TvShow findOne(@PathVariable("id") Long id) {
        return tvShowService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public TvShow save(@RequestBody TvShow tvShow) throws Exception{
        if(tvShow.getId()!=null) {
            if (tvShowService.findOne(tvShow.getId()) != null) throw new Exception("You can't do that");
            if (videoService.findOne(tvShow.getId()) != null) throw new Exception("You can't use that id");
        }
        return tvShowService.save(tvShow);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public TvShow update(@RequestBody TvShow tvShow) throws Exception{
        if(videoService.findOne(tvShow.getId()).getDtype().equals("Movie")) throw new Exception("You can't use that id");
        return tvShowService.save(tvShow);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        deletePersons(id);
        deleteEpisodes(id);
        tvShowService.delete(id);
    }

    @RequestMapping(path = "search/{query}", method = RequestMethod.GET)
    public List<TvShow> searchOnline(@PathVariable("query") String query) {

        QueryResultsTv queryResultsTv = restTemplate.getForObject("https://api.themoviedb.org/3/search/tv?api_key={api_key}&query={search}",
                QueryResultsTv.class, "550e1867817e4bf3266023c5274d8858", query);

        String tvShowsString = restTemplate.getForObject("https://api.themoviedb.org/3/search/tv?api_key={api_key}&query={search}",
                String.class, "550e1867817e4bf3266023c5274d8858", query);
        System.out.println(tvShowsString);
        List<TvShow> tvShows = queryResultsTv.getTvShows();
        return tvShows;
    }

    public void deletePersons(Long id){
        TvShow tvShow = tvShowService.findOne(id);
        tvShow.getPersonList().clear();
        tvShowService.save(tvShow);
    }

    public void deleteEpisodes(Long id){
        TvShow tvShow = tvShowService.findOne(id);
        List<TvShowEpisode> episodes = tvShow.getTvShowEpisodes();
        if(episodes != null) {
            for (int i = 0; i < episodes.size(); i++) {
                tvShowEpisodeService.delete(episodes.get(i).getId());
                if(episodes.isEmpty()) break;
            }
        }
        tvShow.getTvShowEpisodes().clear();
        tvShowService.save(tvShow);
    }
}
