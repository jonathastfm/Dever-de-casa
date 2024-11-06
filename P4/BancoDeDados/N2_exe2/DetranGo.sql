

CREATE TABLE veiculo(
    placa VARCHAR(7) NOT NULL,
    renavan VARCHAR(11) NOT NULL,
    combustivel VARCHAR(20) NOT NULL CHECK(combustivel IN ('Gasolina', 'Álcool', 'Diesel', 'Flex')),
    anoFabricacao VARCHAR(10) NOT NULL,
    cor VARCHAR(20) NOT NULL,
    
    chassi VARCHAR(20),
  	anoModelo VARCHAR(20),
);

CREATE TABLE condutor(
    cpf VARCHAR(11) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    sexo VARCHAR(10) NOT NULL,
    identidade VARCHAR(20) NOT NULL UNIQUE,
    categoriaHabilitacao VARCHAR(2) NOT NULL CHECK(categoriaHabilitacao IN ('A', 'B', 'C', 'D', 'E', 'AB', 'AC', 'AD', 'AE'))
);
