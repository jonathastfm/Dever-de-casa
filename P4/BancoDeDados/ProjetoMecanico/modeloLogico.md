Modelo Lógico 

Peças (idPeças, descrição, codigoFab, quantidade, valorFinal)  

Serviço (idServiço, descrição, valorServiço, outrosServiços, valorOutrosServiços) 

Funcionário (idFuncionario, nomeCompleto, telefone)  

Marca (idMarca, marca)  

ItensServiço (idItensServiço, quantidade, preçoUnitario, valorFinal, idServiço, idFuncionario)

idServiço referencia Serviço  

idFuncionario referencia Funcionário 

ItensPeças (idItensPeças, quantidade, preçoUnitario, valorFinal, idPeças)  

idPeças referencia Peças  

Modelo (idModelo, modelo, anoModelo, idMarca)  

OrdemDeServiço (idOS, dataInicio, dataFim, Status, valorFinal, valorPago, valorRestante, idItensServiço, idItensPeças)  
idItensServiço referencia ItensServiço  
idItensPeças referencia ItensPeças  
Cliente (idCliente, nomeCompleto, e-mail, endereco, tipo_de_pessoa, cpfCliente, telefones, cnpjCliente, Contato, InsEsta) 
Proprietarios (idProprietarios, dataInicio, dataFim, idCliente)  
idCliente referencia Cliente  
Veiculo (placa, numPatrimonio, kilometragem, dataEntrada, anoFabricação, chassi, idModelo, idOS, idProprietarios)  
idModelo referencia Modelo 
IdOS referencia OrdemDeServiço 
IdProprietarios referencia Proprietarios 
Acessorios (idAcessorios, descrição)  
PossuiAcessorios (idAcessorios, placa)  
idAcessorios referencia Acessorios  
placa referencia Veiculo
