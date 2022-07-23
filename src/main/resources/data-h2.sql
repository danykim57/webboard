INSERT INTO users(id, name, email, password) VALUES (null,'tester00','test00@gmail.com','1234');
INSERT INTO posts(id, uid, title, content, writer) VALUES(null, 1, '테스트!', '테스트임', '테스트');
INSERT INTO roles(id, name) VALUES(null,'USER');
INSERT INTO roles(id, name) VALUES(null,'ADMIN');
INSERT INTO users_roles(user_id, role_id) VALUES(1, 1);