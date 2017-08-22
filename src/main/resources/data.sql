INSERT INTO `onlinemoviedatabase`.`user`(`email`, `hashed_email`, `username`, `password`, `password_temp`, `code_for_activation`, `active`, `status`, `blocked_until`, `subscription`, `created_date`, `updated_date`) VALUES ('aleksandar.madjarev@gmail.com', MD5('aleksandar.madjarev@gmail.com'), 'madjarica', 'password', NULL , NULL , TRUE, TRUE, NULL, TRUE, NOW(), NOW());
INSERT INTO `onlinemoviedatabase`.`user`(`email`, `hashed_email`, `username`, `password`, `password_temp`, `code_for_activation`, `active`, `status`, `blocked_until`, `subscription`, `created_date`, `updated_date`) VALUES ('zorica@gmail.com', MD5('zorica@gmail.com'), 'zoricab', 'blabla', NULL , NULL , TRUE, TRUE, NULL, TRUE, NOW(), NOW());
INSERT INTO `onlinemoviedatabase`.`user`(`email`, `hashed_email`, `username`, `password`, `password_temp`, `code_for_activation`, `active`, `status`, `blocked_until`, `subscription`, `created_date`, `updated_date`) VALUES ('zoranjankov99@gmail.com', MD5('zoranjankov99@gmail.com'), 'zoki', 'password', NULL , NULL , TRUE, TRUE, NULL, TRUE, NOW(), NOW());
INSERT INTO `onlinemoviedatabase`.`user`(`email`, `hashed_email`, `username`, `password`, `password_temp`, `code_for_activation`, `active`, `status`, `blocked_until`, `subscription`, `created_date`, `updated_date`) VALUES ('zoranjankov991@gmail.com', MD5('zoranjankov991@gmail.com'), 'zoki1', 'password', NULL , NULL , TRUE, TRUE, NULL, TRUE, NOW(), NOW());
INSERT INTO `onlinemoviedatabase`.`user`(`email`, `hashed_email`, `username`, `password`, `password_temp`, `code_for_activation`, `active`, `status`, `blocked_until`, `subscription`, `created_date`, `updated_date`) VALUES ('zoranjankov992@gmail.com', MD5('zoranjankov992@gmail.com'), 'zoki2', 'password', NULL , NULL , TRUE, TRUE, NULL, TRUE, NOW(), NOW());
INSERT INTO `onlinemoviedatabase`.`user`(`email`, `hashed_email`, `username`, `password`, `password_temp`, `code_for_activation`, `active`, `status`, `blocked_until`, `subscription`, `created_date`, `updated_date`) VALUES ('zoranjankov993@gmail.com', MD5('zoranjankov993@gmail.com'), 'zoki3', 'password', NULL , NULL , TRUE, TRUE, NULL, TRUE, NOW(), NOW());
INSERT INTO `onlinemoviedatabase`.`user`(`email`, `hashed_email`, `username`, `password`, `password_temp`, `code_for_activation`, `active`, `status`, `blocked_until`, `subscription`, `created_date`, `updated_date`) VALUES ('zoranjankov994@gmail.com', MD5('zoranjankov994@gmail.com'), 'zoki4', 'password', NULL , NULL , TRUE, TRUE, NULL, TRUE, NOW(), NOW());
INSERT INTO `onlinemoviedatabase`.`user`(`email`, `hashed_email`, `username`, `password`, `password_temp`, `code_for_activation`, `active`, `status`, `blocked_until`, `subscription`, `created_date`, `updated_date`) VALUES ('zoranjankov995@gmail.com', MD5('zoranjankov995@gmail.com'), 'zoki5', 'password', NULL , NULL , TRUE, TRUE, NULL, TRUE, NOW(), NOW());
INSERT INTO `onlinemoviedatabase`.`user`(`email`, `hashed_email`, `username`, `password`, `password_temp`, `code_for_activation`, `active`, `status`, `blocked_until`, `subscription`, `created_date`, `updated_date`) VALUES ('zoranjankov996@gmail.com', MD5('zoranjankov996@gmail.com'), 'zoki6', 'password', NULL , NULL , TRUE, TRUE, NULL, TRUE, NOW(), NOW());
INSERT INTO `onlinemoviedatabase`.`user`(`email`, `hashed_email`, `username`, `password`, `password_temp`, `code_for_activation`, `active`, `status`, `blocked_until`, `subscription`, `created_date`, `updated_date`) VALUES ('zoranjankov997@gmail.com', MD5('zoranjankov997@gmail.com'), 'zoki7', 'password', NULL , NULL , TRUE, TRUE, NULL, TRUE, NOW(), NOW());

INSERT INTO `onlinemoviedatabase`.`role`(`type`) VALUES ('ROLE_USER');
INSERT INTO `onlinemoviedatabase`.`role`(`type`) VALUES ('ROLE_ADMIN');

INSERT INTO `onlinemoviedatabase`.`user_roles`(`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `onlinemoviedatabase`.`user_roles`(`user_id`, `role_id`) VALUES (1, 2);
INSERT INTO `onlinemoviedatabase`.`user_roles`(`user_id`, `role_id`) VALUES (2, 1);
INSERT INTO `onlinemoviedatabase`.`user_roles`(`user_id`, `role_id`) VALUES (2, 2);
INSERT INTO `onlinemoviedatabase`.`user_roles`(`user_id`, `role_id`) VALUES (3, 1);
INSERT INTO `onlinemoviedatabase`.`user_roles`(`user_id`, `role_id`) VALUES (3, 2);
INSERT INTO `onlinemoviedatabase`.`user_roles`(`user_id`, `role_id`) VALUES (4, 1);
INSERT INTO `onlinemoviedatabase`.`user_roles`(`user_id`, `role_id`) VALUES (5, 1);
INSERT INTO `onlinemoviedatabase`.`user_roles`(`user_id`, `role_id`) VALUES (6, 1);
INSERT INTO `onlinemoviedatabase`.`user_roles`(`user_id`, `role_id`) VALUES (7, 1);
INSERT INTO `onlinemoviedatabase`.`user_roles`(`user_id`, `role_id`) VALUES (8, 1);
INSERT INTO `onlinemoviedatabase`.`user_roles`(`user_id`, `role_id`) VALUES (9, 1);
INSERT INTO `onlinemoviedatabase`.`user_roles`(`user_id`, `role_id`) VALUES (10, 1);

INSERT INTO `video`(`dtype`, `created_date`, `original_language`, `original_title`, `overview`, `title`, `released`, `runtime`) VALUES ('Movie', NOW(), 'english', 'Tea', 'It is a horror based on a true story', 'Tea', 1, 122);
INSERT INTO `video`(`dtype`, `created_date`, `original_language`, `original_title`, `overview`, `title`, `released`, `runtime`) VALUES ('Movie', NOW(), 'english', 'aegaeg', '1321', '123', 1, 122);

INSERT INTO `watchlists`(`watchlist_user`, `visible_to_others`, `favourite`, `video_id`) VALUES ('zoricab', true, false, 2);
INSERT INTO `watchlists`(`watchlist_user`, `visible_to_others`, `favourite`, `video_id`) VALUES ('zoricab', true, false, 1);
INSERT INTO `watchlists`(`watchlist_user`, `visible_to_others`, `favourite`, `video_id`) VALUES ('zoki', true, false, 1);

INSERT INTO `comments`(`comment_content`, `created_date`, `updated_date`, `watchlist_id`, `comment_user`) VALUES ('Strava film', NOW(), NOW(), 1, 'zoki');
INSERT INTO `comments`(`comment_content`, `created_date`, `updated_date`, `watchlist_id`, `comment_user` ) VALUES ('Ok je', NOW(), NOW(), 2, 'madjarica');


