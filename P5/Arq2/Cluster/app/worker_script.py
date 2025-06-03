from mpi4py import MPI
import numpy as np

# worker_script.py
comm = MPI.COMM_WORLD
rank = comm.Get_rank()
size = comm.Get_size()
# Parâmetros
N = 1000000 # Número de intervalos
h = 1.0 / N # Largura de cada intervalo
def f(x):
    return 4.0 / (1.0 + x * x)
# Cada processo calcula uma parte da soma
local_n = N // size
local_a = rank * local_n * h
local_b = local_a + local_n * h
local_sum = 0.0
for i in range(local_n):
    x = local_a + (i + 0.5) * h
    local_sum += f(x)
    local_sum *= h
# Reduzir todas as somas locais para a soma global
global_sum = comm.reduce(local_sum, op=MPI.SUM, root=0)
if rank == 0:
    pi = global_sum
    with open('/app/results/output.txt', 'w') as f:
        f.write(f"Valor estimado de π: {pi}\n")
    print(f"Valor estimado de π: {pi}")
