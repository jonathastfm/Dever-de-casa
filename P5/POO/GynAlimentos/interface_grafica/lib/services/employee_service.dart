import 'package:cloud_firestore/cloud_firestore.dart';
import '../models/employee.dart';

class EmployeeService {
  final CollectionReference _employeesCollection =
      FirebaseFirestore.instance.collection('employees');

  // Adicionar um novo funcionário
  Future<void> addEmployee(Employee employee) {
    return _employeesCollection.add(employee.toJson());
  }

  // Obter a lista de funcionários em tempo real
  Stream<List<Employee>> getEmployees() {
    return _employeesCollection.snapshots().map((snapshot) {
      return snapshot.docs.map((doc) => Employee.fromSnapshot(doc)).toList();
    });
  }

  // Atualizar um funcionário existente
  Future<void> updateEmployee(Employee employee) {
    if (employee.docId == null) return Future.error("Document ID is required for update");
    return _employeesCollection.doc(employee.docId).update(employee.toJson());
  }

  // Deletar um funcionário
  Future<void> deleteEmployee(String docId) {
    return _employeesCollection.doc(docId).delete();
  }
}