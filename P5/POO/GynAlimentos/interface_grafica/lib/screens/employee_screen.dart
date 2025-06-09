import 'package:flutter/material.dart';
import '../models/employee.dart';
import '../models/payslip.dart';
import '../services/employee_service.dart';
import '../utils/payroll_calculator.dart';
import '../widgets/payslip_detail_item.dart';

class EmployeeScreen extends StatefulWidget {
  const EmployeeScreen({super.key});

  @override
  State<EmployeeScreen> createState() => _EmployeeScreenState();
}

class _EmployeeScreenState extends State<EmployeeScreen> {
  final EmployeeService _employeeService = EmployeeService();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      // O AppBar agora é gerenciado pelo MainScreen
      body: StreamBuilder<List<Employee>>(
        stream: _employeeService.getEmployees(),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(child: CircularProgressIndicator());
          }
          if (snapshot.hasError) {
            return Center(child: Text('Erro: ${snapshot.error}'));
          }
          if (!snapshot.hasData || snapshot.data!.isEmpty) {
            return const Center(child: Text('Nenhum funcionário cadastrado.'));
          }

          final employees = snapshot.data!;

          return ListView.builder(
            itemCount: employees.length,
            itemBuilder: (context, index) {
              final employee = employees[index];
              return ListTile(
                title: Text(employee.name),
                subtitle: Text('Matrícula: ${employee.id}'),
                trailing: Row(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    IconButton(
                      icon: const Icon(Icons.edit, color: Colors.blue),
                      onPressed: () => _showEmployeeForm(employee: employee),
                    ),
                    IconButton(
                      icon: const Icon(Icons.delete, color: Colors.red),
                      onPressed: () => _employeeService.deleteEmployee(employee.docId!),
                    ),
                  ],
                ),
                onTap: () => _showPayslipDialog(employee),
              );
            },
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () => _showEmployeeForm(),
        tooltip: 'Adicionar Funcionário',
        child: const Icon(Icons.add),
      ),
    );
  }

  void _showPayslipDialog(Employee employee) {
    final payslip = PayrollCalculator.generatePayslip(employee);
    showDialog(
      context: context,
      builder: (context) {
        return AlertDialog(
          title: Text('Contracheque de ${payslip.employeeName}'),
          content: SingleChildScrollView(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              mainAxisSize: MainAxisSize.min,
              children: [
                PayslipDetailItem(label: 'Matrícula:', value: payslip.employeeId),
                PayslipDetailItem(label: 'Nº Dependentes:', value: payslip.dependents.toString()),
                const Divider(),
                PayslipDetailItem(label: 'Salário Base:', value: 'R\$ ${payslip.baseSalary.toStringAsFixed(2)}'),
                PayslipDetailItem(label: 'Gratificação:', value: 'R\$ ${payslip.productionBonus.toStringAsFixed(2)}'),
                PayslipDetailItem(label: 'Salário Bruto:', value: 'R\$ ${payslip.grossSalary.toStringAsFixed(2)}'),
                const Divider(),
                PayslipDetailItem(label: 'Desconto INSS:', value: 'R\$ ${payslip.inssDiscount.toStringAsFixed(2)}'),
                PayslipDetailItem(label: 'Desconto IRPF:', value: 'R\$ ${payslip.irpfDiscount.toStringAsFixed(2)}'),
                const Divider(),
                PayslipDetailItem(label: 'Salário Líquido:', value: 'R\$ ${payslip.netSalary.toStringAsFixed(2)}', isTotal: true),
              ],
            ),
          ),
          actions: [
            TextButton(
              onPressed: () => Navigator.of(context).pop(),
              child: const Text('Fechar'),
            ),
          ],
        );
      },
    );
  }

  void _showEmployeeForm({Employee? employee}) {
    final _formKey = GlobalKey<FormState>();
    final _employeeIdController = TextEditingController(text: employee?.id ?? '');
    final _nameController = TextEditingController(text: employee?.name ?? '');
    final _dependentsController = TextEditingController(text: employee?.dependents.toString() ?? '0');
    final _baseSalaryController = TextEditingController(text: employee?.baseSalary.toString() ?? '');
    final _productionItemsController = TextEditingController(text: employee?.productionItems.toString() ?? '0');

    showDialog(
      context: context,
      builder: (context) {
        return AlertDialog(
          title: Text(employee == null ? 'Adicionar Funcionário' : 'Editar Funcionário'),
          content: SingleChildScrollView(
            child: Form(
              key: _formKey,
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: <Widget>[
                  TextFormField(controller: _employeeIdController, decoration: const InputDecoration(labelText: 'Matrícula')),
                  TextFormField(controller: _nameController, decoration: const InputDecoration(labelText: 'Nome')),
                  TextFormField(controller: _dependentsController, decoration: const InputDecoration(labelText: 'Nº Dependentes'), keyboardType: TextInputType.number),
                  TextFormField(controller: _baseSalaryController, decoration: const InputDecoration(labelText: 'Salário Base (R\$)'), keyboardType: const TextInputType.numberWithOptions(decimal: true)),
                  TextFormField(controller: _productionItemsController, decoration: const InputDecoration(labelText: 'Itens Produzidos'), keyboardType: TextInputType.number),
                ],
              ),
            ),
          ),
          actions: [
            TextButton(onPressed: () => Navigator.of(context).pop(), child: const Text('Cancelar')),
            ElevatedButton(
              onPressed: () {
                if (_formKey.currentState!.validate()) {
                  final newEmployee = Employee(
                    docId: employee?.docId,
                    id: _employeeIdController.text,
                    name: _nameController.text,
                    dependents: int.parse(_dependentsController.text),
                    baseSalary: double.parse(_baseSalaryController.text),
                    productionItems: int.parse(_productionItemsController.text),
                  );

                  if (employee == null) {
                    _employeeService.addEmployee(newEmployee);
                  } else {
                    _employeeService.updateEmployee(newEmployee);
                  }
                  Navigator.of(context).pop();
                }
              },
              child: const Text('Salvar'),
            ),
          ],
        );
      },
    );
  }
}