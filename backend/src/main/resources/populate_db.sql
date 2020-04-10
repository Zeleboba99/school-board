insert into roles (id, name) values (1, 'ROLE_STUDENT');
insert into roles (id, name) values (2, 'ROLE_TEACHER');
insert into roles (id, name) values (3, 'ROLE_ADMIN');

insert into users (id, username, password) VALUES (1, 'student1', '$2a$08$esRn5UiewveN4jO2ZTVkveBP30jiaeO16qusPL29PyvkP3Nl9WYO6');
insert into users (id, username, password) VALUES (2, 'student2', '$2a$08$esRn5UiewveN4jO2ZTVkveBP30jiaeO16qusPL29PyvkP3Nl9WYO6');
insert into users (id, username, password) VALUES (3, 'student3', '$2a$08$esRn5UiewveN4jO2ZTVkveBP30jiaeO16qusPL29PyvkP3Nl9WYO6');
insert into users (id, username, password) VALUES (4, 'teacher1', '$2a$08$esRn5UiewveN4jO2ZTVkveBP30jiaeO16qusPL29PyvkP3Nl9WYO6');
insert into users (id, username, password) VALUES (5, 'teacher2', '$2a$08$esRn5UiewveN4jO2ZTVkveBP30jiaeO16qusPL29PyvkP3Nl9WYO6');
insert into users (id, username, password) VALUES (6, 'teacher3', '$2a$08$esRn5UiewveN4jO2ZTVkveBP30jiaeO16qusPL29PyvkP3Nl9WYO6');
insert into users (id, username, password) VALUES (7, 'admin', '$2a$08$esRn5UiewveN4jO2ZTVkveBP30jiaeO16qusPL29PyvkP3Nl9WYO6');

insert into users_roles (user_id, role_id) VALUES (1, 1);
insert into users_roles (user_id, role_id) VALUES (2, 1);
insert into users_roles (user_id, role_id) VALUES (3, 1);
insert into users_roles (user_id, role_id) VALUES (4, 2);
insert into users_roles (user_id, role_id) VALUES (5, 2);
insert into users_roles (user_id, role_id) VALUES (6, 2);
insert into users_roles (user_id, role_id) VALUES (7, 3);

insert into posts (id, header, text, user_id) values (1, 'header1', 'text1', 4);
insert into posts (id, header, text, user_id) values (2, 'header2', 'text2', 4);
insert into posts (id, header, text, user_id) values (3, 'header3', 'text3', 5);
insert into posts (id, header, text, user_id) values (4, 'header4', 'text4', 5);
insert into posts (id, header, text, user_id) values (5, 'header5', 'text5', 6);
insert into posts (id, header, text, user_id) values (6, 'header6', 'text6', 6);

insert into comments (id, text, user_id, post_id) values (1, 'text1', 1, 1);
insert into comments (id, text, user_id, post_id) values (2, 'text2', 2, 1);
insert into comments (id, text, user_id, post_id) values (3, 'text3', 3, 2);
insert into comments (id, text, user_id, post_id) values (4, 'text4', 3, 2);
insert into comments (id, text, user_id, post_id) values (5, 'text5', 4, 2);
insert into comments (id, text, user_id, post_id) values (6, 'text6', 2, 3);
insert into comments (id, text, user_id, post_id) values (7, 'text7', 6, 3);
insert into comments (id, text, user_id, post_id) values (8, 'text8', 1, 3);


