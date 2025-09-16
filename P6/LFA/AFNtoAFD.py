import tkinter as tk
from tkinter import messagebox

# (Inclua aqui as classes AFN e AFD, e a função converter_afn_para_afd do código anterior)

# A classe AFN e AFD, e a função de conversão devem ser coladas aqui.
# Por simplicidade, vamos usar as mesmas classes e funções do código anterior.

class AFN:
    def __init__(self, estados, alfabeto, transicoes, estado_inicial, estados_finais):
        self.estados = estados
        self.alfabeto = alfabeto
        self.transicoes = transicoes
        self.estado_inicial = estado_inicial
        self.estados_finais = estados_finais

    def __repr__(self):
        return (f"AFN(Q={self.estados}, Σ={self.alfabeto}, δ={self.transicoes},\n"
                f"   q0={self.estado_inicial}, F={self.estados_finais})")

class AFD:
    def __init__(self, estados, alfabeto, transicoes, estado_inicial, estados_finais):
        self.estados = estados
        self.alfabeto = alfabeto
        self.transicoes = transicoes
        self.estado_inicial = estado_inicial
        self.estados_finais = estados_finais

    def __repr__(self):
        transicoes_str = "\n".join([f"      {estado}: {transicao}" for estado, transicao in self.transicoes.items()])
        return (f"AFD(Q={self.estados}, Σ={self.alfabeto}, δ={{\n{transicoes_str}\n   }},\n"
                f"   q0={self.estado_inicial}, F={self.estados_finais})")

def converter_afn_para_afd(afn):
    novos_estados_afd = []
    novas_transicoes_afd = {}
    
    estado_inicial_afd = frozenset([afn.estado_inicial])
    estados_nao_processados = [estado_inicial_afd]
    
    while estados_nao_processados:
        estado_atual_afd = estados_nao_processados.pop(0)
        
        nome_estado_afd = tuple(sorted(list(estado_atual_afd)))
        if nome_estado_afd not in novos_estados_afd:
            novos_estados_afd.append(nome_estado_afd)
            novas_transicoes_afd[nome_estado_afd] = {}
        
        for simbolo in afn.alfabeto:
            proximo_estado_afn = set()
            for estado_afn in estado_atual_afd:
                if estado_afn in afn.transicoes and simbolo in afn.transicoes[estado_afn]:
                    proximo_estado_afn.update(afn.transicoes[estado_afn][simbolo])
            
            proximo_estado_afd = frozenset(proximo_estado_afn)
            
            if proximo_estado_afd not in [frozenset(s) for s in novos_estados_afd + estados_nao_processados] and proximo_estado_afd:
                estados_nao_processados.append(proximo_estado_afd)

            nome_proximo_estado_afd = tuple(sorted(list(proximo_estado_afd))) if proximo_estado_afd else None
            novas_transicoes_afd[nome_estado_afd][simbolo] = nome_proximo_estado_afd

    estados_finais_afd = [
        estado for estado in novos_estados_afd
        if any(e in afn.estados_finais for e in estado)
    ]
    
    return AFD(novos_estados_afd, afn.alfabeto, novas_transicoes_afd, tuple(sorted(list(estado_inicial_afd))), estados_finais_afd)

# --- Criação da GUI com Tkinter ---

class ConversorGUI:
    def __init__(self, master):
        self.master = master
        master.title("Conversor de AFN para AFD")

        # Configura a interface para entrada de dados
        self.frame_entrada = tk.LabelFrame(master, text="Entrada do AFN", padx=10, pady=10)
        self.frame_entrada.pack(padx=10, pady=10)

        tk.Label(self.frame_entrada, text="Estados (separados por vírgula):").grid(row=0, column=0, sticky=tk.W)
        self.entry_estados = tk.Entry(self.frame_entrada)
        self.entry_estados.grid(row=0, column=1)

        tk.Label(self.frame_entrada, text="Alfabeto (separado por vírgula):").grid(row=1, column=0, sticky=tk.W)
        self.entry_alfabeto = tk.Entry(self.frame_entrada)
        self.entry_alfabeto.grid(row=1, column=1)

        tk.Label(self.frame_entrada, text="Estado Inicial:").grid(row=2, column=0, sticky=tk.W)
        self.entry_estado_inicial = tk.Entry(self.frame_entrada)
        self.entry_estado_inicial.grid(row=2, column=1)

        tk.Label(self.frame_entrada, text="Estados Finais (separados por vírgula):").grid(row=3, column=0, sticky=tk.W)
        self.entry_estados_finais = tk.Entry(self.frame_entrada)
        self.entry_estados_finais.grid(row=3, column=1)

        tk.Label(self.frame_entrada, text="Transições (q,s->q1,q2;...)").grid(row=4, column=0, sticky=tk.W)
        self.entry_transicoes = tk.Entry(self.frame_entrada)
        self.entry_transicoes.grid(row=4, column=1)

        self.btn_converter = tk.Button(master, text="Converter para AFD", command=self.processar_conversao)
        self.btn_converter.pack(pady=5)

        # Configura a interface para a saída de dados
        self.frame_saida = tk.LabelFrame(master, text="Quíntupla do AFD Resultante", padx=10, pady=10)
        self.frame_saida.pack(padx=10, pady=10)

        self.texto_saida = tk.Text(self.frame_saida, height=15, width=60)
        self.texto_saida.pack()

    def processar_conversao(self):
        try:
            # 1. Obter dados da interface e processá-los
            estados_str = self.entry_estados.get()
            alfabeto_str = self.entry_alfabeto.get()
            estado_inicial_str = self.entry_estado_inicial.get()
            estados_finais_str = self.entry_estados_finais.get()
            transicoes_str = self.entry_transicoes.get()

            estados = {e.strip() for e in estados_str.split(',') if e.strip()}
            alfabeto = {s.strip() for s in alfabeto_str.split(',') if s.strip()}
            estado_inicial = estado_inicial_str.strip()
            estados_finais = {f.strip() for f in estados_finais_str.split(',') if f.strip()}

            # Lógica para converter a string de transições para um dicionário
            transicoes = {}
            for t in transicoes_str.split(';'):
                if '->' in t:
                    parte_origem, parte_destino = t.split('->')
                    estado_origem, simbolo = parte_origem.split(',')
                    estados_destino = {ed.strip() for ed in parte_destino.split(',') if ed.strip()}
                    
                    estado_origem = estado_origem.strip()
                    simbolo = simbolo.strip()
                    
                    if estado_origem not in transicoes:
                        transicoes[estado_origem] = {}
                    transicoes[estado_origem][simbolo] = estados_destino

            # 2. Criar o objeto AFN
            afn_de_entrada = AFN(estados, alfabeto, transicoes, estado_inicial, estados_finais)

            # 3. Converter para AFD e exibir o resultado
            afd_resultante = converter_afn_para_afd(afn_de_entrada)
            self.texto_saida.delete("1.0", tk.END)
            self.texto_saida.insert(tk.END, afd_resultante)

        except Exception as e:
            messagebox.showerror("Erro de Entrada", f"Ocorreu um erro ao processar os dados. Verifique a sintaxe.\nDetalhes: {e}")

# Iniciar a aplicação
root = tk.Tk()
gui = ConversorGUI(root)
root.mainloop()