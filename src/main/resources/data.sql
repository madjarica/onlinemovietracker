/*
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
INSERT INTO `movie_person`(`movie_id`, `person_id`) VALUEs ('2', '3');*/

INSERT INTO `video`(`title`, `dtype`) VALUE ('serija', 'TvShow');
INSERT INTO `video`(`title`, `dtype`, `released`) VALUE ('film', 'Movie', true);

INSERT INTO `tv_show_episodes`(`episode`, `season`, `tv_show_id`) VALUES ('3', '2', '1');
INSERT INTO `tv_show_episodes`(`episode`, `season`, `tv_show_id`) VALUES ('1', '1', '1');

INSERT INTO `persons`(`role`) VALUES ('Actor');
INSERT INTO `persons`(`role`) VALUES ('Actor');
INSERT INTO `persons`(`name`, `biography`) VALUES ('neko', 'blabla');
INSERT INTO `persons`(`name`, `biography`) VALUES ('babaab', 'gaegaegae');

INSERT INTO `characters`(`name`, `person_id`) VALUE ('nesto', '1');
INSERT INTO `characters`(`name`, `person_id`) VALUE ('monstrum2', '2');
INSERT INTO `characters`(`name`, `person_id`) VALUE ('monstrum1', '1');

INSERT INTO `video_character_list`(`video_id`, `character_list_id`) VALUES ('1', '1');
INSERT INTO `video_character_list`(`video_id`, `character_list_id`) VALUES ('2', '2');
INSERT INTO `video_character_list`(`video_id`, `character_list_id`) VALUES ('2', '3');