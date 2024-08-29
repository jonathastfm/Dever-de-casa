import numpy as np

import matplotlib.pyplot as plt

# Definir parâmetros da caminhada aleatória
num_steps = 1000  # Número de passos
step_size = 1  # Tamanho do passo

# Inicializar a posição inicial
position = 0

# Inicializar uma lista para armazenar as posições em cada passo
positions = [position]

# Simular a caminhada aleatória
for _ in range(num_steps):
    # Gerar um passo aleatório
    step = np.random.choice([-step_size, step_size])
    
    # Atualizar a posição
    position += step
    
    # Adicionar a posição atual à lista de posições
    positions.append(position)
    # Calcular a média da caminhada
    mean_position = np.mean(positions)

    # Plotar a média da caminhada
    plt.axhline(mean_position, color='red', linestyle='--', label='Média')

    # Mostrar a legenda
    plt.legend()


# Plotar o gráfico da caminhada aleatória
plt.plot(positions)
plt.xlabel('Passo')
plt.ylabel('Posição')
plt.title('Caminhada Aleatória 1D')
plt.show()