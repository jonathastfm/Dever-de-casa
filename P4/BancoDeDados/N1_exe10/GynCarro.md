# Modelo Cliente

Cliente(
    cpf,
    nomeCompleto,
    ddi,
    ddd,
    numero,
    rua,
    numeroCasa,
    email
)

Carro(
    placa,
    fabricacao
    modelo,
    quilometragem,
    cpfCliente
)

# Interpretação do modelo 

Deve-se relacionar os modelos com uma chave esterna na tabela do carro, pois esse sim esta limitado a um dono apenas

