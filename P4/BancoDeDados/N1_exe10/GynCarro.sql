-- Criação do banco de dados
CREATE DATABASE GynCarro;

-- Utilização do banco de dados
USE GynCarro;

-- Criação da tabela Cliente
CREATE TABLE Cliente (
    cpfCliente VARCHAR(11) PRIMARY KEY NOT NULL,
    nomeCompleto VARCHAR(100) NOT NULL,

    ddi INT NOT NULL,
    ddd INT NOT NULL,
    numero INT NOT NULL,
    unique (ddi, ddd, numero),

    rua VARCHAR(100) NOT NULL,
    numeroCasa INT NOT NULL,
    unique (rua, numeroCasa),

    email VARCHAR(100) NOT NULL
);

-- Criação da tabela Carro
CREATE TABLE Carro (
    placa VARCHAR(7) PRIMARY KEY NOT NULL,
    fabricacao INT NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    quilometragem INT NOT NULL,
    cpfCliente VARCHAR(11) NOT NULL,
    FOREIGN KEY (cpfCliente) REFERENCES Cliente(cpfCliente)
);