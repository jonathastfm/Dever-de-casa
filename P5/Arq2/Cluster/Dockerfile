FROM python:3.9-slim

WORKDIR /app
COPY ./app /app



# Poderia instalar dependências aqui se necessário
# RUN pip install ...

RUN apt-get update && \
    apt-get install -y openmpi-bin libopenmpi-dev && \
    pip install mpi4py numpy

ENV PYTHONUNBUFFERED=1

# ...existing code...
CMD ["mpirun", "-np", "1", "python", "worker_script.py"]