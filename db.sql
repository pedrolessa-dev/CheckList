CREATE DATABASE db_checklist;

USE db_checklist;

CREATE TABLE tbl_tarefa(
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    concluido BOOLEAN NOT NULL,
    pontuacao INTEGER NOT NULL
);