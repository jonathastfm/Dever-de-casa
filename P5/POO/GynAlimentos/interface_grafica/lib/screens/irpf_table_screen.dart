import 'package:flutter/material.dart';
import '../utils/constants.dart';

class IRPFTableScreen extends StatelessWidget {
  const IRPFTableScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final data = irpfTableData; // [cite: 14]
    return Scaffold(
      appBar: AppBar(
        title: const Text('Tabela do IRPF'),
        automaticallyImplyLeading: false,
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            Text(
              "Tabela do IRPF", // [cite: 12]
              style: Theme.of(context).textTheme.headlineSmall,
            ),
            const SizedBox(height: 10),
            DataTable(
              columnSpacing: 20, // Adjust as needed
              columns: const [
                DataColumn(label: Text('Base de Cálculo (R\$)')), // "Salário Bruto" in [cite: 14] refers to the calculation base for IRPF
                DataColumn(label: Text('Alíquota')), // [cite: 14]
                DataColumn(label: Text('Parcela a Deduzir (R\$)')), // Assumed for calculation logic
              ],
              rows: data.map((item) {
                String aliquotText = item['rate'] == 0.0 ? 'Isento' : '${(item['rate'] * 100).toStringAsFixed(item['rate'] * 100 % 1 == 0 ? 0 : 1)}%'; // [cite: 14]
                return DataRow(cells: [
                  DataCell(Text(item['range'].toString())),
                  DataCell(Text(aliquotText)),
                  DataCell(Text(item['deduction'] == 0.0 && item['rate'] == 0.0 ? '-' : item['deduction'].toStringAsFixed(2))),
                ]);
              }).toList(),
            ),
            const SizedBox(height: 20),
            Text("Desconto por dependente no IRPF: R\$ ${irpfDependentDeduction.toStringAsFixed(2)}", // [cite: 5]
              style: Theme.of(context).textTheme.bodyLarge,
            ),
          ],
        ),
      ),
    );
  }
}