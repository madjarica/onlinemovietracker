package com.omt.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.omt.JsonResults.*;
import com.omt.domain.*;
import com.omt.domain.Character;
import com.omt.repository.GenreRepository;
import com.omt.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import static com.omt.web.controller.MovieController.*;

@RestController
@RequestMapping("/tvshows")
public class TvShowController {

    TvShowService tvShowService;
    TvShowEpisodeService tvShowEpisodeService;
    VideoService videoService;
    PersonService personService;
    CharacterService characterService;
    KeywordService keywordService;
    GenreRepository genreRepository;
    RestOperations restTemplate = new RestTemplate();

    String POSTER_PATH = "src/main/resources/static/img/posters/tvshows/poster";
    String BACKDROP_PATH = "src/main/resources/static/img/backdrops/tvshows/backdrop";

    String API_SEARCH = "https://api.themoviedb.org/3/search/movie?api_key={api_key}&query={search}";
    String API_GET_MOVIE = "https://api.themoviedb.org/3/tv/{id}?api_key={api_key}&language=en-US";
    String API_GET_CREDITS = "https://api.themoviedb.org/3/tv/{id}/credits?api_key={api_key}";
    String API_GET_PERSON = "https://api.themoviedb.org/3/person/{person_id}?api_key={api_key}&language=en-US";
    String API_GET_VIDEO = "https://api.themoviedb.org/3/tv/{id}/videos?api_key={api_key}";
    String API_GET_EXTERNAL = "https://api.themoviedb.org/3/tv/{tv_id}/external_ids?api_key={api_key}";
    String API_GET_EPISODES = "https://api.themoviedb.org/3/tv/{tv_id}/season/{season_number}?api_key={api_key}";
    String API_GET_ALL_TV_SHOW_BACKDROPS = "https://api.themoviedb.org/3/tv/{tv_id}/images?api_key={api_key}";
    String API_GET_TV_SHOW_KEYWORDS = "https://api.themoviedb.org/3/tv/{tv_id}/keywords?api_key={api_key}";
    //    String API_GET_EPISODE_STILL = "https://api.themoviedb.org/3/tv/{tv_id}/season/{season}/episode/{episode}/images?api_key=550e1867817e4bf3266023c5274d8858";
    String API_KEY = "550e1867817e4bf3266023c5274d8858";


    @Autowired
    public TvShowController(TvShowService tvShowService, TvShowEpisodeService tvShowEpisodeService, VideoService videoService, PersonService personService,
                            CharacterService characterService, GenreRepository genreRepository, KeywordService keywordService) {
        this.tvShowService = tvShowService;
        this.tvShowEpisodeService = tvShowEpisodeService;
        this.videoService = videoService;
        this.personService = personService;
        this.characterService = characterService;
        this.genreRepository = genreRepository;
        this.keywordService = keywordService;
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
    public TvShow save(@RequestBody TvShow tvShow) throws Exception {
        if (tvShow.getId() != null) {
            if (tvShowService.findOne(tvShow.getId()) != null) throw new Exception("You can't do that");
            if (videoService.findOne(tvShow.getId()) != null) throw new Exception("You can't use that id");
        }
        List<Genre> genresToBeAdded = new ArrayList<>();
        if(tvShow.getGenres() == null) tvShow.setGenres(genresToBeAdded);
        if (!tvShow.getGenres().isEmpty()) {
            for (Genre genre : tvShow.getGenres()) {
                genresToBeAdded.add(getGenres(genre.getName()));
                System.out.println(genre.getName());
            }
            tvShow.getGenres().clear();
            tvShow.setGenres(genresToBeAdded);
        }

        List<Keyword> keywordsToBeAdded = new ArrayList<>();
        if(tvShow.getKeywords() == null) tvShow.setKeywords(keywordsToBeAdded);
        if (!tvShow.getKeywords().isEmpty()) {
            for (Keyword keyword : tvShow.getKeywords()) {
                keywordsToBeAdded.add(getKeywords(keyword.getName()));
                System.out.println(keyword.getName());
            }
            tvShow.getKeywords().clear();
            tvShow.setKeywords(keywordsToBeAdded);
        }

        return tvShowService.save(tvShow);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public TvShow update(@RequestBody TvShow tvShow) throws Exception {
        if (videoService.findOne(tvShow.getId()).getDtype().equals("Movie"))
            throw new Exception("You can't use that id");
        List<Genre> genresToBeAdded = new ArrayList<>();
        for (Genre genre : tvShow.getGenres()) {
            genresToBeAdded.add(getGenres(genre.getName()));
            System.out.println(genre.getName());
        }
        tvShow.getGenres().clear();
        tvShow.setGenres(genresToBeAdded);

        List<Keyword> keywordsToBeAdded = new ArrayList<>();
        for (Keyword keyword : tvShow.getKeywords()) {
            keywordsToBeAdded.add(getKeywords(keyword.getName()));
            System.out.println(keyword.getName());
        }
        tvShow.getKeywords().clear();
        tvShow.setKeywords(keywordsToBeAdded);

        System.out.print(tvShow.getName());

        return tvShowService.save(tvShow);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
//        deletePersons(id);
        deleteEpisodes(id);
        tvShowService.delete(id);
    }

    @RequestMapping(path = "search/title/{search}", method = RequestMethod.GET)
    public List<TvShow> findByTitleContaining(@PathVariable("search") String search) {
        return tvShowService.findByTitleContaining(search);
    }

    @RequestMapping(path = "getTvShow/{id}", method = RequestMethod.GET)
    public TvShow saveFromTMDB(@PathVariable("id") Long id) {

        TvShow checkIfAlreadyExists = tvShowService.findByTmdbTvShowId(id);
        if (checkIfAlreadyExists != null) {
            return checkIfAlreadyExists;
        }

        TvShow tvShow = restTemplate.getForObject(API_GET_MOVIE, TvShow.class, id, API_KEY);
        getCharacters(id);

        Locale[] locales = Locale.getAvailableLocales();
        for (Locale locale : locales) {
            if (locale.getLanguage().equals(tvShow.getOriginalLanguage())) {
                tvShow.setOriginalLanguage(locale.getDisplayLanguage());
                break;
            }
        }

        tvShow.setTmdbTvShowId(tvShow.getId());
        tvShow.setId(null);

        QueryResultsTv externalLinks = restTemplate.getForObject(API_GET_EXTERNAL, QueryResultsTv.class, id, API_KEY);
        tvShow.setImdbPage("http://www.imdb.com/title/" + externalLinks.getImdb_id());

//        tvShow.setPosterPath("https://image.tmdb.org/t/p/w640" + tvShow.getPosterPath());
//        tvShow.setBackdropPath( "http://image.tmdb.org/t/p/w640" + tvShow.getBackdropPath());

        List<Character> characterList = characterService.findByTmdbMediaId(tvShow.getTmdbTvShowId());
        tvShow.setCharacterList(characterList);



        TrailerResults trailerResults = restTemplate.getForObject(API_GET_VIDEO, TrailerResults.class, id, API_KEY);
        if (!trailerResults.getTrailers().isEmpty()) {
            String youtube = trailerResults.getTrailers().get(0).getTrailerLink();
            tvShow.setTrailerLink("https://www.youtube.com/embed/" + youtube);
        } else {
            tvShow.setTrailerLink(null);
        }

        List<Genre> genresToBeAdded = new ArrayList<>();
        for (Genre genre : tvShow.getGenres()) {
            genresToBeAdded.add(getGenres(genre.getName()));
        }
        tvShow.getGenres().clear();
        tvShow.setGenres(genresToBeAdded);

        KeywordsResults keywordsResults = restTemplate.getForObject(API_GET_TV_SHOW_KEYWORDS, KeywordsResults.class, id, API_KEY);
        List<Keyword> keywords = keywordsResults.getKeywords();

        List<Keyword> keywordsToBeAdded = new ArrayList<>();
        for (Keyword keyword:keywords) {
            keywordsToBeAdded.add(getKeywords(keyword.getName()));
        }
        tvShow.setKeywords(keywordsToBeAdded);

//        String ext = tvShow.getTmdbTvShowId() + ".jpg";

//        try {
//            saveImage("https://image.tmdb.org/t/p/w640" + tvShow.getPosterPath(), POSTER_PATH + ext);
////            tvShow.setPosterPath("/img/posters/tvshows/poster" + ext);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            saveImage("https://image.tmdb.org/t/p/w640" + tvShow.getBackdropPath(), BACKDROP_PATH + ext);
//            tvShow.setBackdropPath("/img/backdrops/tvshows/backdrop" + ext);
        ApiImageResults imageResults = restTemplate.getForObject(API_GET_ALL_TV_SHOW_BACKDROPS, ApiImageResults.class, id, API_KEY);
        List<String> backdrops = imageResults.returnApiImagePaths(imageResults.getBackdrops());
        tvShow.setAdditionalBackdrops(new ArrayList<>());
        System.out.println(backdrops.size());
        int size;
        if (backdrops.size() < 5) {
            size = backdrops.size();
        } else {
            size = 5;
        }
        for (int i = 0; i < size; i++) {
            System.out.println(backdrops.get(i));
//                ext = tvShow.getTmdbTvShowId() + "_" + i + ".jpg";
////                saveImage("https://image.tmdb.org/t/p/w500" + backdrops.get(i), ADDTIONAL_BACKDROPS_PATH + ext);
////                        movie.getAdditionalBackdrops().add("/img/backdrops/movies/additional_backdrops/backdrop" + ext);
            tvShow.getAdditionalBackdrops().add(backdrops.get(i));
        }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Person person;
        List<Character> creatorsList = new ArrayList<>();
        for (Character creators : tvShow.getCreators()) {
            person = getPerson(creators.getId());
            creators.setActorId(creators.getId());
            creators.setTmdbMediaId(tvShow.getTmdbTvShowId());
            creators.setId(null);
            creators.setPerson(person);
            creators.setJob("director");
            characterService.save(creators);
            creatorsList.add(creators);
        }

        tvShow.getCharacterList().addAll(creatorsList);

        tvShow.setTvShowEpisodes(new ArrayList<>());
        tvShow = tvShowService.save(tvShow);
        for (int i = 1; i <= tvShow.getNumberOfSeasons(); i++) {
            tvShow.getTvShowEpisodes().addAll((getEpisodes(tvShow.getTmdbTvShowId(), tvShow.getId(), i)));
        }


        return tvShowService.save(tvShow);

    }

    public List<TvShowEpisode> getEpisodes(Long tmdbId, Long id, int season) {

        List<TvShowEpisode> tvShowEpisodesToSend = new ArrayList<>();
        try {
            EpisodeResults episodeResults = restTemplate.getForObject(API_GET_EPISODES, EpisodeResults.class, tmdbId, season, API_KEY);
            List<TvShowEpisode> tvShowEpisodes = episodeResults.getEpisodesList();
            if (!tvShowEpisodes.isEmpty()) {
                TvShowEpisode episodeToSend;
                for (TvShowEpisode episode : tvShowEpisodes) {

                    episode.setId(null);
                    episode.setTv_show_id(id);
//            TimeUnit.MILLISECONDS.sleep(10);
//            ApiImageResults episodeStills = restTemplate.getForObject(API_GET_EPISODE_STILL, ApiImageResults.class, tmdbId, episode.getSeason(), episode.getEpisode());
//            if (episodeStills != null) {
//                if (!episodeStills.getStills().isEmpty()) {
//                    String episodeSitll = episodeStills.returnApiImagePaths(episodeStills.getStills()).get(0);
//                    System.out.println(episodeSitll);
//                    episode.setStill(episodeSitll);
//                }
//            }
                    episodeToSend = tvShowEpisodeService.save(episode);
                    tvShowEpisodesToSend.add(episodeToSend);
                }
            }
        } catch (HttpClientErrorException ex) {
            return tvShowEpisodesToSend;
        }
        return tvShowEpisodesToSend;
    }

    public Genre getGenres(String name) {
        Genre genre = genreRepository.findByName(name);
        if (genreRepository.findByName(name) != null) {
            return genre;
        } else {
            genre = new Genre();
            genre.setName(name);
            return genreRepository.save(genre);
        }
    }

    public void getCharacters(Long id) {
        Person person;
        int size;
        CreditsResults creditsResults = restTemplate.getForObject(API_GET_CREDITS, CreditsResults.class, id, API_KEY);
        List<Character> characterList = creditsResults.getCharacters();
        if (characterList.size() < 14) {
            size = characterList.size();
        } else {
            size = 14;
        }

        for (int i = 0; i < size; i++) {
            person = getPerson(characterList.get(i).getId());
            characterList.get(i).setActorId(characterList.get(i).getId());
            characterList.get(i).setTmdbMediaId(creditsResults.getTmdbMediaId());
            characterList.get(i).setId(null);
            characterList.get(i).setJob("actor");
            characterList.get(i).setPerson(person);
            characterService.save(characterList.get(i));
        }
    }

    public Person getPerson(Long id) {
        Person personCheck = personService.findByTmdbPersonId(id);
        if (personCheck != null) {
            return personCheck;
        } else {
            Person person;
            person = restTemplate.getForObject(API_GET_PERSON, Person.class, id, API_KEY);
            person.setTmdbPersonId(person.getId());
            person.setId(null);
            ApiImageResults results = restTemplate.getForObject(API_GET_ACTOR_PROFILE, ApiImageResults.class, id, API_KEY);
            if (!results.getProfiles().isEmpty()) {
//                String ext = id + ".jpg";
                person.setPicture(results.getProfiles().get(0).getFilePath());
                System.out.println(person.getPicture());
//                try {
//                    saveImage("http://image.tmdb.org/t/p/w185" + person.getPicture(), PROFILE_PATH + ext);
////                    person.setPicture("/img/profiles/profile" + ext);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
            return personService.save(person);
        }
    }

    public Keyword getKeywords(String name) {
        Keyword keyword = keywordService.findByName(name);
        if (keyword == null) {
            keyword = new Keyword();
            keyword.setId(null);
            keyword.setName(name);
            return keywordService.save(keyword);
        }
        return keyword;
    }

    public void deleteEpisodes(Long id) {
        TvShow tvShow = tvShowService.findOne(id);
        List<TvShowEpisode> episodes = tvShow.getTvShowEpisodes();
        if (episodes != null) {
            for (int i = 0; i < episodes.size(); i++) {
                tvShowEpisodeService.delete(episodes.get(i).getId());
                if (episodes.isEmpty()) break;
            }
        }
        tvShow.getTvShowEpisodes().clear();
        tvShowService.save(tvShow);
    }

    public void deletePersons(Long id) {
        TvShow tvShow = tvShowService.findOne(id);
//        tvShow.getPersonList().clear();
        tvShowService.save(tvShow);
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
}
