

CREATE TABLE T_TEAM
(
    id               BIGINT  PRIMARY KEY,
    name             text,
    product          text,
    created_at       TIMESTAMP,
    modified_at      TIMESTAMP,
    default_location varchar(20)

);

CREATE TABLE T_RACK
(
    id               BIGINT PRIMARY KEY,
    serial_number    text,
    status           varchar(20),
    team_id          BIGINT,
    default_location varchar(20),
    created_at       TIMESTAMP,
    modified_at      TIMESTAMP,
    FOREIGN KEY (team_id) REFERENCES T_TEAM (id)
);


CREATE TABLE T_RACK_ASSET
(
    id        BIGINT PRIMARY KEY,
    asset_tag varchar(20),
    rack_id   BIGINT,
    FOREIGN KEY (rack_id) REFERENCES T_RACK (id)
);



CREATE TABLE T_TEAM_MEMBER
(
    id          BIGINT  PRIMARY KEY,
    team_id     BIGINT,
    ctw_id      text,
    name        text,
    created_at  TIMESTAMP,
    modified_at TIMESTAMP,
    FOREIGN KEY (team_id) REFERENCES T_TEAM (id)
);

CREATE TABLE T_BOOKING
(
    id            BIGINT PRIMARY KEY,
    rack_id       BIGINT,
    requester_id  BIGINT,
    book_from     TIMESTAMP,
    book_to       TIMESTAMP,
    created_at    TIMESTAMP,
    modified_at   TIMESTAMP,
    FOREIGN KEY (requester_id) REFERENCES T_TEAM_MEMBER (id)
);

CREATE SEQUENCE team_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE rack_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE rack_asset_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE team_member_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE booking_seq START WITH 1 INCREMENT BY 1;



INSERT INTO T_RACK (id, serial_number, status, team_id, default_location, created_at, modified_at)
VALUES
     (1, '1000-1001', 'AVAILABLE', NULL, 'PORTO', '2025-07-31 14:00:00.000000', '2025-07-31 14:00:00.000000'),
     (2, '1000-1002', 'AVAILABLE', NULL, 'COIMBRA', '2025-07-31 14:15:30.000000', '2025-07-31 14:15:30.000000'),
     (3, '1000-1003', 'AVAILABLE', NULL, 'BRAGA', '2025-07-31 14:30:00.000000', '2025-07-31 14:30:00.000000'),
     (4, '1000-1004', 'UNAVAILABLE', NULL, 'FARO', '2025-07-31 14:45:00.000000', '2025-07-31 14:45:00.000000');


