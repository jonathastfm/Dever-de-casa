// utils/payroll_calculator.dart
import '../models/employee.dart';
import '../models/payslip.dart';
import 'constants.dart';

class PayrollCalculator {
  // Calculate Production Bonus [cite: 3]
  static double calculateProductionBonus(int productionItems) {
    if (productionItems <= 1000) {
      return bonusTableData[0]['value']; // R$ 500,00 [cite: 15]
    } else if (productionItems <= 2000) {
      return bonusTableData[1]['value']; // R$ 1.250,00 [cite: 15]
    } else {
      return bonusTableData[2]['value']; // R$ 2.250,00 [cite: 15]
    }
  }

  // Calculate Gross Salary [cite: 3]
  static double calculateGrossSalary(double baseSalary, double productionBonus) {
    return baseSalary + productionBonus;
  }

  // Calculate INSS Discount [cite: 4]
  static double calculateINSS(double grossSalary) {
    for (var tier in inssTableData) {
      if (grossSalary >= tier['min'] && grossSalary <= tier['max']) {
        // For INSS, it's often a progressive calculation on salary bands.
        // Simplified: applying the rate to the entire gross salary if it falls in the band.
        // A precise calculation would apply different rates to portions of the salary.
        // For this prototype, we'll use the direct aliquot on the gross salary based on its band.
        if (tier['max'] == 1412.00) return grossSalary * tier['rate']; // 7.5% for first band
        if (tier['max'] == 2666.68) return grossSalary * tier['rate']; // 9.0% for second band
        if (tier['max'] == 4000.03) return grossSalary * tier['rate']; // 12.0% for third band
        if (tier['max'] == double.infinity) return grossSalary * tier['rate']; // 14.0% for fourth band
      }
    }
     // Fallback for the highest tier if grossSalary > last 'min'
    if (grossSalary > inssTableData.last['min'] && inssTableData.last['max'] == double.infinity) {
        return grossSalary * inssTableData.last['rate'];
    }
    return 0.0; // Should not happen if tables are correct
  }

  // Calculate IRPF Discount [cite: 4]
  static double calculateIRPF(double grossSalary, double inssDiscount, int dependents) {
    double irpfDeductionForDependents = dependents * irpfDependentDeduction;// [cite: 5]
    double calculationBase = grossSalary - inssDiscount - irpfDeductionForDependents;

    if (calculationBase <= irpfTableData[0]['max']) { // Isento [cite: 14]
      return 0.0;
    }

    for (var tier in irpfTableData) {
      if (calculationBase >= tier['min'] && calculationBase <= tier['max']) {
        // The problem [cite: 14] provides aliquots.
        // A common way to calculate IRPF is (Calculation Base * Aliquot) - Deduction Installment.
        // The "Deduction Installment" (Parcela a Deduzir) is missing from[cite: 14].
        // If it's a simple percentage of the base for that tier:
        // return calculationBase * tier['rate'];
        // If it's (Base * Rate) - Fixed_Deduction_For_Tier:
        // For this prototype, let's assume the 'deduction' in irpfTableData is the "parcela a deduzir".
        return (calculationBase * tier['rate']) - tier['deduction'];
      }
    }
    // Fallback for the highest tier
    if (calculationBase > irpfTableData.last['min'] && irpfTableData.last['max'] == double.infinity) {
        return (calculationBase * irpfTableData.last['rate']) - irpfTableData.last['deduction'];
    }
    return 0.0; // Should not happen
  }
  
  // Calculate Dependent Deduction Value shown on payslip
  static double calculateDependentDeductionValue(int dependents) {
    return dependents * irpfDependentDeduction;// [cite: 5]
  }

  // Calculate Net Salary [cite: 2]
  static double calculateNetSalary(double grossSalary, double inssDiscount, double irpfDiscount) {
    return grossSalary - inssDiscount - irpfDiscount;
  }

  static Payslip generatePayslip(Employee employee) {
    double productionBonus = calculateProductionBonus(employee.productionItems);
    double grossSalary = calculateGrossSalary(employee.baseSalary, productionBonus);
    double inss = calculateINSS(grossSalary);
    // The IRPF calculation base uses the dependent deduction [cite: 5]
    double irpf = calculateIRPF(grossSalary, inss, employee.dependents);
    if (irpf < 0) irpf = 0; // IRPF cannot be negative
    
    double dependentDeductionDisplayValue = calculateDependentDeductionValue(employee.dependents);
    double netSalary = calculateNetSalary(grossSalary, inss, irpf);

    return Payslip(
      employeeId: employee.id,
      employeeName: employee.name,
      dependents: employee.dependents,
      baseSalary: employee.baseSalary,
      productionBonus: productionBonus,
      grossSalary: grossSalary,
      inssDiscount: inss,
      irpfDiscount: irpf,
      dependentDeductionValue: dependentDeductionDisplayValue,
      netSalary: netSalary,
    );
  }
}