CREATE DATABASE GYNContrutora;
USE GYNContrutora;

-- Tabela de Departamentos
CREATE TABLE Departamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL
);

-- Tabela de Colaboradores
CREATE TABLE Colaborador (
    cpf CHAR(11) PRIMARY KEY,
    nome_completo VARCHAR(100) NOT NULL,
    telefone VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    tipo ENUM('comum', 'motorista', 'engenheiro', 'medico') NOT NULL,
    departamento_id INT,
    FOREIGN KEY (departamento_id) REFERENCES Departamento(id)
);

-- Tabela de Motoristas
CREATE TABLE Motorista (
    cpf CHAR(11) PRIMARY KEY,
    numero_cnh VARCHAR(20) NOT NULL,
    categoria ENUM('A', 'B', 'C', 'D', 'E') NOT NULL,
    data_vencimento_cnh DATE NOT NULL,
    FOREIGN KEY (cpf) REFERENCES Colaborador(cpf)
);

-- Tabela de Engenheiros
CREATE TABLE Engenheiro (
    cpf CHAR(11) PRIMARY KEY,
    crea VARCHAR(20) NOT NULL,
    area_engenharia VARCHAR(50) NOT NULL,
    FOREIGN KEY (cpf) REFERENCES Colaborador(cpf)
);

-- Tabela de Médicos
CREATE TABLE Medico (
    cpf CHAR(11) PRIMARY KEY,
    crm VARCHAR(20) NOT NULL,
    especialidade VARCHAR(50) NOT NULL,
    FOREIGN KEY (cpf) REFERENCES Colaborador(cpf)
);

-- Tabela de Veículos
CREATE TABLE Veiculo (
    placa CHAR(7) PRIMARY KEY,
    chassi VARCHAR(17) NOT NULL,
    renavan VARCHAR(11) NOT NULL,
    ano_fabricacao YEAR NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    marca VARCHAR(50) NOT NULL,
    km_atual INT NOT NULL,
    motorista_cpf CHAR(11),
    FOREIGN KEY (motorista_cpf) REFERENCES Motorista(cpf)
);

-- Tabela de Projetos
CREATE TABLE Projeto (
    codigo INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    orcamento_total DECIMAL(15, 2) NOT NULL,
    engenheiro_responsavel_cpf CHAR(11),
    FOREIGN KEY (engenheiro_responsavel_cpf) REFERENCES Engenheiro(cpf)
);

-- Tabela de Consultas Médicas
CREATE TABLE ConsultaMedica (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_consulta DATE NOT NULL,
    descricao_atendimento TEXT NOT NULL,
    diagnostico TEXT NOT NULL,
    medico_cpf CHAR(11),
    colaborador_cpf CHAR(11),
    FOREIGN KEY (medico_cpf) REFERENCES Medico(cpf),
    FOREIGN KEY (colaborador_cpf) REFERENCES Colaborador(cpf)
);

-- Tabela de Sinistros
CREATE TABLE Sinistro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_sinistro DATE NOT NULL,
    descricao TEXT NOT NULL,
    veiculo_placa CHAR(7),
    motorista_cpf CHAR(11),
    FOREIGN KEY (veiculo_placa) REFERENCES Veiculo(placa),
    FOREIGN KEY (motorista_cpf) REFERENCES Motorista(cpf)
);

-- Tabela de Registros Técnicos
CREATE TABLE RegistroTecnico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao TEXT NOT NULL,
    projeto_codigo INT,
    data_registro DATE NOT NULL,
    engenheiro_cpf CHAR(11),
    FOREIGN KEY (projeto_codigo) REFERENCES Projeto(codigo),
    FOREIGN KEY (engenheiro_cpf) REFERENCES Engenheiro(cpf)
);