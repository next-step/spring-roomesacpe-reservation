DROP TABLE IF EXISTS theme, schedule, reservation;

CREATE TABLE theme
(
    id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(255) NOT NULL,
    desc  VARCHAR(255) NOT NULL,
    price BIGINT       NOT NULL
);

CREATE TABLE schedule
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    theme_id BIGINT NOT NULL,
    date     DATE   NOT NULL,
    time     TIME   NOT NULL
);

CREATE TABLE reservation
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    schedule_id BIGINT       NOT NULL,
    date        DATE         NOT NULL,
    time        TIME         NOT NULL,
    name        VARCHAR(255) NOT NULL
);
