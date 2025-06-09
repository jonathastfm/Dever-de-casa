import 'package:cloud_firestore/cloud_firestore.dart';

class Employee {
  String? docId; // Opcional: para guardar o ID do documento do Firestore
  String id;
  String name;
  int dependents;
  double baseSalary;
  int productionItems;

  Employee({
    this.docId,
    required this.id,
    required this.name,
    required this.dependents,
    required this.baseSalary,
    required this.productionItems,
  });

  // Converte um objeto Employee para um Map (JSON) para o Firestore
  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'name': name,
      'dependents': dependents,
      'baseSalary': baseSalary,
      'productionItems': productionItems,
    };
  }

  // Cria um objeto Employee a partir de um DocumentSnapshot do Firestore
  factory Employee.fromSnapshot(DocumentSnapshot doc) {
    Map<String, dynamic> data = doc.data() as Map<String, dynamic>;
    return Employee(
      docId: doc.id, // Armazena o ID do documento
      id: data['id'],
      name: data['name'],
      dependents: data['dependents'],
      baseSalary: data['baseSalary'],
      productionItems: data['productionItems'],
    );
  }
}