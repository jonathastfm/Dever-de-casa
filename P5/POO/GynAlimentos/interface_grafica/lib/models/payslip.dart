class Payslip {
  final String employeeId;
  final String employeeName;
  final int dependents;
  final double baseSalary;
  final double productionBonus; 
  final double grossSalary; 
  final double inssDiscount; 
  final double irpfDiscount; 
  final double dependentDeductionValue; // Valor do desconto dos filhos 
  final double netSalary; 

  Payslip({
    required this.employeeId,
    required this.employeeName,
    required this.dependents,
    required this.baseSalary,
    required this.productionBonus,
    required this.grossSalary,
    required this.inssDiscount,
    required this.irpfDiscount,
    required this.dependentDeductionValue,
    required this.netSalary,
  });
}