DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS posts CASCADE;
DROP TABLE IF EXISTS comments CASCADE;

-- 회원 데이터
CREATE TABLE users
(
    id                       bigint      NOT NULL AUTO_INCREMENT,              -- 사용자 PK
    name                     varchar(10) NOT NULL,                             -- 이름
    email                    varchar(50) NOT NULL,                             -- 로그인 이메일
    password                 varchar(80) NOT NULL,                             -- 비밀번호
    login_count              int         NOT NULL DEFAULT 0,                   -- 로그인횟수
    create_at                datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP(), -- 생성일시
    PRIMARY KEY (id),
    CONSTRAINT unq_user_email UNIQUE (email)
);
--게시판 글 데이터
CREATE TABLE posts
(
    id              bigint       NOT NULL AUTO_INCREMENT,
    uid             bigint       NOT NULL,
    title           varchar(50)  NOT NULL,
    content         varchar(500) NOT NULL,
    writer          varchar(50)  NOT NULL,
    comment_count   int          NOT NULL DEFAULT 0,
    create_at       datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY (id),
    CONSTRAINT fk_post_to_user FOREIGN KEY (uid) REFERENCES users (id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

-- POST의 댓글 데이터
-- 'uid'는 댓글 작성자, 'pid' 댓글이 달린 POST
--CREATE TABLE comments
--{
--    id              bigint       NOT NULL AUTO_INCREMENT,
--    uid             bigint       NOT NULL,
--    pid             bigint       NOT NULL,
--    contents        varchar(500) NOT NULL,
--    create_at       datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP(),
--    PRIMARY KEY (id),
--    CONSTRAINT fk_post_to_user FOREIGN KEY (uid) REFERENCES users (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
--    CONSTRAINT fk_comment_to_user FOREIGN KEY (pid) REFERENCES posts(id) ON DELETE CASCADE ON UPDATE CASCADE
--};