#!/bin/bash

# Script para configurar headers do Kernel no WSL2
# Necessario para compilar modulos de kernel

KERNEL_VERSION=$(uname -r)
# Remove o sufixo -microsoft-standard-WSL2 para pegar a versao base se necessario, 
# mas as tags do github usam a versao completa geralmente.
# No caso do WSL, a tag costuma ser linux-msft-wsl-X.Y.Z.W
# Ex: 6.6.87.1 -> linux-msft-wsl-6.6.87.1

# Extrair apenas os numeros da versao (ex: 6.6.87.1)
VERSION_NUM=$(uname -r | cut -d'-' -f1)

echo "--- Configurando ambiente para Kernel $VERSION_NUM no WSL2 ---"

# 1. Instalar dependencias
echo "[1/5] Instalando dependencias..."
sudo apt-get update
sudo apt-get install -y build-essential flex bison libssl-dev libelf-dev bc

# 2. Criar diretorio de fontes
mkdir -p ~/wsl_kernel_src
cd ~/wsl_kernel_src

# 3. Baixar o codigo fonte do Kernel (pode demorar)
TAG_NAME="linux-msft-wsl-${VERSION_NUM}"
TAR_FILE="${TAG_NAME}.tar.gz"
URL="https://github.com/microsoft/WSL2-Linux-Kernel/archive/refs/tags/${TAR_FILE}"

if [ ! -d "WSL2-Linux-Kernel-${TAG_NAME}" ]; then
    echo "[2/5] Baixando codigo fonte do Kernel ($TAG_NAME)..."
    wget $URL -O $TAR_FILE
    
    if [ $? -ne 0 ]; then
        echo "ERRO: Nao foi possivel baixar o kernel. Verifique a versao ou a conexao."
        exit 1
    fi

    echo "[3/5] Extraindo arquivos..."
    tar -xf $TAR_FILE
else
    echo "[2/5] Codigo fonte ja baixado."
fi

CDIR="WSL2-Linux-Kernel-${TAG_NAME}"
if [ ! -d "$CDIR" ]; then
    # Tenta ajustar o nome do diretorio caso o tar tenha extraido diferente
    CDIR=$(ls -d WSL2-Linux-Kernel-linux-msft-wsl-* | head -n 1)
fi

cd $CDIR

# 4. Preparar o kernel
echo "[4/5] Preparando headers do kernel..."
if [ -f /proc/config.gz ]; then
    zcat /proc/config.gz > .config
else
    echo "ERRO: /proc/config.gz nao encontrado."
    exit 1
fi

# Ajustar config para evitar problemas com versao
# As vezes o 'uname -r' tem um sufixo que o .config nao tem, ou vice-versa.
# O importante e que o 'make modules_prepare' configure a versao correta.

make prepare modules_prepare

# 5. Criar o link simbolico
echo "[5/5] Criando link simbolico em /lib/modules/$(uname -r)/build..."
sudo mkdir -p /lib/modules/$(uname -r)
sudo rm -f /lib/modules/$(uname -r)/build
sudo ln -s $(pwd) /lib/modules/$(uname -r)/build

echo "--- Concluido! Tente compilar seu modulo agora. ---"
