# Tasks schema

# --- !Ups

CREATE TABLE task (
    id     integer NOT NULL AUTO_INCREMENT,
    user_id varchar(255),
    label varchar(255),
    PRIMARY KEY (id)
);
 
# --- !Downs
 
DROP TABLE task;

