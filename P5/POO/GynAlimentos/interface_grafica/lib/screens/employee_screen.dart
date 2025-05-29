import 'package:flutter/material.dart';
import '../models/employee.dart';
import '../models/payslip.dart';
import '../utils/payroll_calculator.dart';
import '../widgets/payslip_detail_item.dart';

class EmployeeScreen extends StatefulWidget {
  const EmployeeScreen({super.key});

  @override
  State<EmployeeScreen> createState() => _EmployeeScreenState();
}

class _EmployeeScreenState extends State<EmployeeScreen> {
  final _formKey = GlobalKey<FormState>();
  final _employeeIdController = TextEditingController();
  final _nameController = TextEditingController();
  final _dependentsController = TextEditingController(text: '0');
  final _baseSalaryController = TextEditingController();
  final _productionItemsController = TextEditingController(text: '0');

  Payslip? _payslip;

  void _calculatePayslip() {
    if (_formKey.currentState!.validate()) {
      final employee = Employee(
        id: _employeeIdController.text,
        name: _nameController.text,
        dependents: int.tryParse(_dependentsController.text) ?? 0,
        baseSalary: double.tryParse(_baseSalaryController.text) ?? 0.0,
        productionItems: int.tryParse(_productionItemsController.text) ?? 0,
      );

      setState(() {
        _payslip = PayrollCalculator.generatePayslip(employee);
      });
    }
  }

  @override
  void dispose() {
    _employeeIdController.dispose();
    _nameController.dispose();
    _dependentsController.dispose();
    _baseSalaryController.dispose();
    _productionItemsController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      padding: const EdgeInsets.all(16.0),
      child: Form(
        key: _formKey,
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Text("Dados do Funcionário", style: Theme.of(context).textTheme.titleLarge),
            TextFormField(
              controller: _employeeIdController,
              decoration: const InputDecoration(labelText: 'Matrícula do funcionário'), // [cite: 9]
              validator: (value) => (value == null || value.isEmpty) ? 'Campo obrigatório' : null,
            ),
            TextFormField(
              controller: _nameController,
              decoration: const InputDecoration(labelText: 'Nome do funcionário'), // [cite: 9]
              validator: (value) => (value == null || value.isEmpty) ? 'Campo obrigatório' : null,
            ),
            TextFormField(
              controller: _dependentsController,
              decoration: const InputDecoration(labelText: 'Número de dependentes'), // [cite: 9]
              keyboardType: TextInputType.number,
              validator: (value) {
                if (value == null || value.isEmpty) return 'Campo obrigatório';
                if (int.tryParse(value) == null) return 'Número inválido';
                return null;
              },
            ),
            TextFormField(
              controller: _baseSalaryController,
              decoration: const InputDecoration(labelText: 'Salário base (R\$)'), // [cite: 9]
              keyboardType: const TextInputType.numberWithOptions(decimal: true),
              validator: (value) {
                if (value == null || value.isEmpty) return 'Campo obrigatório';
                if (double.tryParse(value) == null) return 'Valor inválido';
                return null;
              },
            ),
            TextFormField(
              controller: _productionItemsController,
              decoration: const InputDecoration(labelText: 'Itens Produzidos (para gratificação)'),
              keyboardType: TextInputType.number,
               validator: (value) {
                if (value == null || value.isEmpty) return 'Campo obrigatório';
                if (int.tryParse(value) == null) return 'Número inválido';
                return null;
              },
            ),
            const SizedBox(height: 20),
            Center(
              child: ElevatedButton(
                onPressed: _calculatePayslip,
                child: const Text('Calcular Contracheque'),
              ),
            ),
            const SizedBox(height: 20),
            if (_payslip != null) ...[
              Text("Resultado do Contracheque", style: Theme.of(context).textTheme.titleLarge),
              PayslipDetailItem(label: 'Matrícula:', value: _payslip!.employeeId), // [cite: 9]
              PayslipDetailItem(label: 'Nome:', value: _payslip!.employeeName), // [cite: 9]
              PayslipDetailItem(label: 'Nº Dependentes:', value: _payslip!.dependents.toString()), // [cite: 9]
              PayslipDetailItem(label: 'Salário Base:', value: 'R\$ ${_payslip!.baseSalary.toStringAsFixed(2)}'), // [cite: 9]
              PayslipDetailItem(label: 'Valor da Gratificação:', value: 'R\$ ${_payslip!.productionBonus.toStringAsFixed(2)}'), // [cite: 10]
              PayslipDetailItem(label: 'Salário Bruto:', value: 'R\$ ${_payslip!.grossSalary.toStringAsFixed(2)}'), // [cite: 10]
              PayslipDetailItem(label: 'Desconto INSS:', value: 'R\$ ${_payslip!.inssDiscount.toStringAsFixed(2)}'), // [cite: 10]
              PayslipDetailItem(label: 'Desconto IRPF:', value: 'R\$ ${_payslip!.irpfDiscount.toStringAsFixed(2)}'), // [cite: 10]
              PayslipDetailItem(label: 'Dedução IRPF por Dependentes (Base):', value: 'R\$ ${_payslip!.dependentDeductionValue.toStringAsFixed(2)}'), // [cite: 10] (Valor do desconto dos filhos)
              PayslipDetailItem(label: 'Salário Líquido:', value: 'R\$ ${_payslip!.netSalary.toStringAsFixed(2)}', isTotal: true), // [cite: 11]
            ],
          ],
        ),
      ),
    );
  }
}