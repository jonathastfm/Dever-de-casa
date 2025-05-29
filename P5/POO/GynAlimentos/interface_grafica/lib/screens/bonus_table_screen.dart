import 'package:flutter/material.dart';
import '../utils/constants.dart';

class BonusTableScreen extends StatelessWidget {
  const BonusTableScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final data = bonusTableData; // [cite: 15]
    return Scaffold(
      appBar: AppBar(
        title: const Text('Tabela de Gratificação por Produção'),
        automaticallyImplyLeading: false, // Remove back button if part of BottomNav
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            Text(
              "Tabela de Gratificação", // [cite: 15]
              style: Theme.of(context).textTheme.headlineSmall,
            ),
            const SizedBox(height: 10),
            DataTable(
              columns: const [
                DataColumn(label: Text('Produção')), // [cite: 15]
                DataColumn(label: Text('Valor da Gratificação (R\$)')), // [cite: 15] (Aliquota in this table means value)
              ],
              rows: data.map((item) {
                return DataRow(cells: [
                  DataCell(Text(item['production'].toString())),
                  DataCell(Text(item['value'].toStringAsFixed(2))),
                ]);
              }).toList(),
            ),
          ],
        ),
      ),
    );
  }
}