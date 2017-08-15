INSERT INTO `onlinemoviedatabase`.`user`(`email`, `username`, `password`, `password_temp`, `code_for_activation`, `active`, `status`, `blocked_until`, `subscription`, `created_date`, `updated_date`) VALUES ('aleksandar.madjarev@gmail.com', 'madjarica', 'password', NULL , NULL , TRUE, TRUE, NULL, TRUE, NOW(), NOW());

INSERT INTO `onlinemoviedatabase`.`role`(`type`) VALUES ('ROLE_USER');
INSERT INTO `onlinemoviedatabase`.`role`(`type`) VALUES ('ROLE_ADMIN');

INSERT INTO `onlinemoviedatabase`.`user_roles`(`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `onlinemoviedatabase`.`user_roles`(`user_id`, `role_id`) VALUES (1, 2);
