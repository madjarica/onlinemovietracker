INSERT INTO `video`(`title`, `dtype`) VALUE ('serija', 'TvShow');
INSERT INTO `video`(`title`, `dtype`, `released`) VALUE ('film', 'Movie', true);

INSERT INTO `tv_show_episodes`(`episode`, `season`, `tv_show_id`) VALUES ('3', '2', '1');
INSERT INTO `tv_show_episodes`(`episode`, `season`, `tv_show_id`) VALUES ('1', '1', '1');

INSERT INTO `persons`(`role`) VALUES ('Actor');
INSERT INTO `persons`(`role`) VALUES ('Actor');
INSERT INTO `persons`(`name`, `biography`) VALUES ('neko', 'blabla');
INSERT INTO `persons`(`name`, `biography`) VALUES ('babaab', 'gaegaegae');

INSERT INTO `tv_show_person`(`tv_show_id`, `person_id`) VALUEs ('1', '2');
INSERT INTO `tv_show_person`(`tv_show_id`, `person_id`) VALUEs ('1', '4');
INSERT INTO `movie_person`(`movie_id`, `person_id`) VALUEs ('2', '2');
INSERT INTO `movie_person`(`movie_id`, `person_id`) VALUEs ('2', '4');