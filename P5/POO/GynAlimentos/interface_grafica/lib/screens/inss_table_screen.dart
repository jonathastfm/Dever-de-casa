import 'package:flutter/material.dart';
import '../utils/constants.dart';

class INSSTableScreen extends StatelessWidget {
  const INSSTableScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final data = inssTableData; // [cite: 13]
    return Scaffold(
      appBar: AppBar(
        title: const Text('Tabela do INSS'),
        automaticallyImplyLeading: false,
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            Text(
              "Tabela do INSS", // [cite: 12]
              style: Theme.of(context).textTheme.headlineSmall,
            ),
            const SizedBox(height: 10),
            DataTable(
              columnSpacing: 38, // Adjust as needed
              columns: const [
                DataColumn(label: Text('Salário Bruto (R\$)')), // [cite: 13]
                DataColumn(label: Text('Alíquota')), // [cite: 13]
              ],
              rows: data.map((item) {
                return DataRow(cells: [
                  DataCell(Text(item['range'].toString())),
                  DataCell(Text('${(item['rate'] * 100).toStringAsFixed(1)}%')),
                ]);
              }).toList(),
            ),
          ],
        ),
      ),
    );
  }
}