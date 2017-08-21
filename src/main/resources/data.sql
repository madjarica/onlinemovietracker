INSERT INTO `onlinemoviedatabase`.`user`(`email`, `hashed_email`, `username`, `password`, `password_temp`, `code_for_activation`, `active`, `status`, `blocked_until`, `subscription`, `created_date`, `updated_date`) VALUES ('aleksandar.madjarev@gmail.com', MD5('aleksandar.madjarev@gmail.com'), 'madjarica', 'password', NULL , NULL , TRUE, TRUE, NULL, TRUE, NOW(), NOW());
INSERT INTO `onlinemoviedatabase`.`user`(`email`, `hashed_email`, `username`, `password`, `password_temp`, `code_for_activation`, `active`, `status`, `blocked_until`, `subscription`, `created_date`, `updated_date`) VALUES ('zorica@gmail.com', MD5('zorica@gmail.com'), 'zoricab', 'blabla', NULL , NULL , TRUE, TRUE, NULL, TRUE, NOW(), NOW());
INSERT INTO `onlinemoviedatabase`.`user`(`email`, `hashed_email`, `username`, `password`, `password_temp`, `code_for_activation`, `active`, `status`, `blocked_until`, `subscription`, `created_date`, `updated_date`) VALUES ('zoranjankov99@gmail.com', MD5('zoranjankov99@gmail.com'), 'zoki', 'password', NULL , NULL , TRUE, TRUE, NULL, TRUE, NOW(), NOW());

INSERT INTO `onlinemoviedatabase`.`role`(`type`) VALUES ('ROLE_USER');
INSERT INTO `onlinemoviedatabase`.`role`(`type`) VALUES ('ROLE_ADMIN');

INSERT INTO `onlinemoviedatabase`.`user_roles`(`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `onlinemoviedatabase`.`user_roles`(`user_id`, `role_id`) VALUES (1, 2);
INSERT INTO `onlinemoviedatabase`.`user_roles`(`user_id`, `role_id`) VALUES (2, 1);
INSERT INTO `onlinemoviedatabase`.`user_roles`(`user_id`, `role_id`) VALUES (2, 2);
INSERT INTO `onlinemoviedatabase`.`user_roles`(`user_id`, `role_id`) VALUES (3, 1);
INSERT INTO `onlinemoviedatabase`.`user_roles`(`user_id`, `role_id`) VALUES (3, 2);

INSERT INTO `video`(`dtype`, `created_date`, `original_language`, `original_title`, `overview`, `title`, `released`, `runtime`) VALUES ('Movie', NOW(), 'english', 'Tea', 'It is a horror based on a true story', 'Tea', 1, 122);
INSERT INTO `video`(`dtype`, `created_date`, `original_language`, `original_title`, `overview`, `title`, `released`, `runtime`) VALUES ('Movie', NOW(), 'english', 'aegaeg', '1321', '123', 1, 122);

INSERT INTO `watchlists`(`watchlist_user`, `visible_to_others`, `favourite`, `video_id`) VALUES ('madjarica', true, false, 2);
INSERT INTO `watchlists`(`watchlist_user`, `visible_to_others`, `favourite`, `video_id`) VALUES ('madjarica', true, false, 1);
INSERT INTO `watchlists`(`watchlist_user`, `visible_to_others`, `favourite`, `video_id`) VALUES ('zoki', true, false, 1);

#
# INSERT INTO `watchlists_videos`(`watchlist_id`, `video_id`) VALUES ('1', '1');
# INSERT INTO `watchlists_videos`(`watchlist_id`, `video_id`) VALUES ('1', '2');