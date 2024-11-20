import numpy as np
import matplotlib.pyplot as plt

# Parâmetros
lambda_ = 1  # Chegadas por minuto
mu = 2       # Atendimentos por minuto
sim_time = 100  # Tempo de simulação em minutos

def simulate_mm1(lambda_, mu, sim_time):
    # Inicializações
    np.random.seed()
    arrivals = np.cumsum(np.random.exponential(1/lambda_, size=sim_time * lambda_))
    service_times = np.random.exponential(1/mu, size=len(arrivals))
    wait_times = []
    time_in_system = []
    current_time = 0
    
    for i in range(len(arrivals)):
        if i == 0 or arrivals[i] > current_time:
            wait_time = 0
        else:
            wait_time = current_time - arrivals[i]
        
        current_time = max(arrivals[i], current_time) + service_times[i]
        wait_times.append(wait_time)
        time_in_system.append(wait_time + service_times[i])
    
    return wait_times, time_in_system, current_time

# Simulações
simulations = 5
rho_values = []
avg_wait_times = []

for sim in range(simulations):
    wait_times, time_in_system, current_time = simulate_mm1(lambda_, mu, sim_time)
    avg_wait_time = np.mean(wait_times)
    rho = lambda_ / mu
    
    avg_wait_times.append(avg_wait_time)
    rho_values.append(rho)
    
    # Plotar resultados da simulação
    plt.plot(range(len(wait_times)), wait_times, label=f'Simulação {sim+1}')
    
plt.title("Evolução do Tempo Médio de Espera")
plt.xlabel("Clientes")
plt.ylabel("Tempo Médio de Espera")
plt.legend()
plt.show()

# Resultados finais
print(f"Média do Tempo Médio de Espera: {np.mean(avg_wait_times):.2f} minutos")
print(f"Variância do Tempo Médio de Espera: {np.var(avg_wait_times):.2f}")
print(f"Média de Rho: {np.mean(rho_values):.2f}")
print(f"Variância de Rho: {np.var(rho_values):.2f}")
