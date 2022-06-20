DROP TABLE IF EXISTS connections CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS posts CASCADE;
DROP TABLE IF EXISTS comments CASCADE;

-- 회원 데이터
CREATE TABLE users
{
    id             bigint      NOT NULL AUTO_INCREMENT,              -- 유저 PK
    email          varchar(50) NOT NULL,                             -- 로그인 이메일
    passwd         varchar(80) NOT NULL,                             -- 비밀번호
    name           varchar(10) NOT NULL,                             -- 표시 이름
    login_count    int         NOT NULL DEFAULT 0,                   -- 로그인 횟수
    last_login_at  datetime             DEFAULT NULL,                -- 마지막 로그인 시간
    create_at      datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP(), -- 생성 시간
    PRIMARY KEY (id),
    CONSTRAINT unq_user_email UNIQUE (email),
    CONSTRAINT unq_user_name UNIQUE (name)
};

-- 게시판 글 데이터
-- 'uid'는 POST 작성자
-- 'comment_count'는 게시글의 댓글 갯수, comments 테이블에서 추출
CREATE TABLE posts
{
    id              bigint       NOT NULL AUTO_INCREMENT,
    uid             bigint       NOT NULL,
    contents        varchar(500) NOT NULL,
    like_count      int          NOT NULL, DEFAULT 0,
    comment_count   int          NOT NULL, DEFAULT 0,
    create_at       datetime     NOT NULL, DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY (id),
    CONSTRAINT fk_post_to_user FOREIGN KEY (uid) REFERENCES users (id) ON DELETE RESTRICT ON UPDATE RESTRICT
};

-- POST의 댓글 데이터
-- 'uid'는 댓글 작성자, 'pid' 댓글이 달린 POST
CREATE TABLE comments
{
    id              bigint       NOT NULL AUTO_INCREMENT,
    uid             bigint       NOT NULL,
    pid             bigint       NOT NULL,
    contents        varchar(500) NOT NULL,
    create_at       datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY (id),
    CONSTRAINT fk_post_to_user FOREIGN KEY (uid) REFERENCES users (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_comment_to_user FOREIGN KEY (pid) REFERENCES posts(id) ON DELETE CASCADE ON UPDATE CASCADE
};