
import random
import json

class GraphMatrix:
    num_vertices = 0
    matrix = []

    def __init__(self, num_vertices):
        self.num_vertices = num_vertices
        self.matrix = [[0] * num_vertices for _ in range(num_vertices)]

    def add_edge(self, u, v):
        self.matrix[u][v] = 1
        self.matrix[v][u] = 1

    def remove_edge(self, u, v):
        self.matrix[u][v] = 0
        self.matrix[v][u] = 0

    def has_edge(self, u, v):
        return self.matrix[u][v] == 1

    def print_matrix(self):
        for row in self.matrix:
            print(row)

    def to_dict(self):
        nodes = [{"id": i, "name": f"Node {i}"} for i in range(self.num_vertices)]
        links = []
        for i in range(self.num_vertices):
            for j in range(i + 1, self.num_vertices):
                if self.matrix[i][j] == 1:
                    links.append({"source": i, "target": j})
        return {"nodes": nodes, "links": links}


def randomize_matrix_data(graph, probability=0.8):
    for i in range(graph.num_vertices):
        for j in range(i + 1, graph.num_vertices):
            if random.random() < probability:
                graph.add_edge(i, j)

# Criando o objeto, randomizando os dados e imprimindo
if __name__ == "__main__":
    g = GraphMatrix(10) # Aumentando para 10 para uma visualização melhor
    randomize_matrix_data(g, 0.4)
    g.print_matrix()
    
    # Exportando dados para o visualizador
    with open("graph_data.json", "w") as f:
        json.dump(g.to_dict(), f, indent=4)
    print("Dados exportados para graph_data.json")

