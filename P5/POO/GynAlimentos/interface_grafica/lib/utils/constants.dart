// utils/constants.dart

// Tabela de Gratificação [cite: 15]
const List<Map<String, dynamic>> bonusTableData = [
  {'production': 'Até 1000 itens', 'value': 500.00},
  {'production': 'De 1001 até 2000 itens', 'value': 1250.00},
  {'production': 'Acima de 2000 itens', 'value': 2250.00},
];

// Tabela do INSS [cite: 13]
const List<Map<String, dynamic>> inssTableData = [
  {'range': 'Até R\$ 1.412,00', 'rate': 0.075, 'min': 0.0, 'max': 1412.00},
  {'range': 'De R\$ 1.412,01 até R\$ 2.666,68', 'rate': 0.09, 'min': 1412.01, 'max': 2666.68},
  {'range': 'De R\$ 2.666.69 até R\$ 4.000,03', 'rate': 0.12, 'min': 2666.69, 'max': 4000.03},
  {'range': 'Acima de R\$ 4.000,04', 'rate': 0.14, 'min': 4000.04, 'max': double.infinity},
];

// Tabela do IRPF [cite: 14]
const List<Map<String, dynamic>> irpfTableData = [
  {'range': 'Até R\$ 2.259,20', 'rate': 0.0, 'min': 0.0, 'max': 2259.20, 'deduction': 0.0}, // Isento
  {'range': 'De R\$ 2.259,21 até R\$ 2.826,65', 'rate': 0.075, 'min': 2259.21, 'max': 2826.65, 'deduction': 169.44}, // Deduction for this tier needs to be calculated or provided. Assuming a fixed deduction for example.
  {'range': 'De R\$ 2.826,66 até R\$ 3.751,05', 'rate': 0.15, 'min': 2826.66, 'max': 3751.05, 'deduction': 381.44},
  {'range': 'De R\$ 3.751,06 até R\$ 4.664,68', 'rate': 0.225, 'min': 3751.06, 'max': 4664.68, 'deduction': 662.77},
  {'range': 'Acima de R\$ 4.664,68', 'rate': 0.275, 'min': 4664.69, 'max': double.infinity, 'deduction': 896.00},
];

const double irpfDependentDeduction = 123.00; 