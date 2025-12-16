import tkinter as tk
from tkinter import messagebox

# =============================================================
#   CLASSES PARA REPRESENTAR AFN E AFD
# =============================================================

class AFN:
    """
    Classe que representa um Autômato Finito Não Determinístico (AFN).
    Armazena o conjunto de estados, alfabeto, tabela de transições,
    estado inicial e conjunto de estados finais.
    """
    def _init_(self, estados, alfabeto, transicoes, estado_inicial, estados_finais):
        self.estados = estados
        self.alfabeto = alfabeto
        self.transicoes = transicoes
        self.estado_inicial = estado_inicial
        self.estados_finais = estados_finais

    def _repr_(self):
        """
        Retorna uma string formatada representando a quíntupla do AFN:
        (Q, Σ, δ, q0, F)
        """
        return (f"AFN(Q={self.estados}, Σ={self.alfabeto}, δ={self.transicoes},\n"
                f"   q0={self.estado_inicial}, F={self.estados_finais})")


class AFD:
    """
    Classe que representa um Autômato Finito Determinístico (AFD).
    Cada estado pode ser um conjunto de estados do AFN.
    """
    def _init_(self, estados, alfabeto, transicoes, estado_inicial, estados_finais):
        self.estados = estados
        self.alfabeto = alfabeto
        self.transicoes = transicoes
        self.estado_inicial = estado_inicial
        self.estados_finais = estados_finais

    def _repr_(self):
        """
        Retorna uma string organizada com as transições do AFD.
        Mostra a função δ de forma legível.
        """
        transicoes_str = "\n".join([f"      {estado}: {transicao}"
                                    for estado, transicao in self.transicoes.items()])
        return (f"AFD(Q={self.estados}, Σ={self.alfabeto}, δ={{\n{transicoes_str}\n   }},\n"
                f"   q0={self.estado_inicial}, F={self.estados_finais})")


# =============================================================
#   FUNÇÃO DE CONVERSÃO: AFN → AFD
# =============================================================

def converter_afn_para_afd(afn):
    """
    Converte um AFN em um AFD utilizando o método da construção de subconjuntos.
    Cada estado do AFD representa um conjunto de estados do AFN.
    """

    # Lista para armazenar os novos estados do AFD
    novos_estados_afd = []
    # Dicionário para armazenar as novas transições determinísticas
    novas_transicoes_afd = {}

    # Estado inicial do AFD é o conjunto contendo o estado inicial do AFN
    estado_inicial_afd = frozenset([afn.estado_inicial])
    # Fila de estados que ainda precisam ser processados
    estados_nao_processados = [estado_inicial_afd]

    # Enquanto ainda houver estados para processar
    while estados_nao_processados:
        estado_atual_afd = estados_nao_processados.pop(0)  # Retira o primeiro da fila

        # Nome do estado atual no AFD (tupla ordenada para ser hashável e previsível)
        nome_estado_afd = tuple(sorted(list(estado_atual_afd)))

        # Se o estado ainda não foi registrado, adiciona e cria espaço para suas transições
        if nome_estado_afd not in novos_estados_afd:
            novos_estados_afd.append(nome_estado_afd)
            novas_transicoes_afd[nome_estado_afd] = {}

        # Para cada símbolo do alfabeto, calcular os destinos possíveis
        for simbolo in afn.alfabeto:
            proximo_estado_afn = set()

            # Para cada estado do conjunto atual, verifique as transições no AFN
            for estado_afn in estado_atual_afd:
                if estado_afn in afn.transicoes and simbolo in afn.transicoes[estado_afn]:
                    proximo_estado_afn.update(afn.transicoes[estado_afn][simbolo])

            # C0çonverte o conjunto resultante em frozenset (imutável e comparável)
            proximo_estado_afd = frozenset(proximo_estado_afn)

            # Se o novo conjunto ainda não foi visto, adiciona à fila para processar depois
            if proximo_estado_afd not in [frozenset(s) for s in novos_estados_afd + estados_nao_processados] and proximo_estado_afd:
                estados_nao_processados.append(proximo_estado_afd)

            # Registra a transição no dicionário do AFD
            nome_proximo_estado_afd = tuple(sorted(list(proximo_estado_afd))) if proximo_estado_afd else None
            novas_transicoes_afd[nome_estado_afd][simbolo] = nome_proximo_estado_afd

    # Determina quais estados do AFD são finais:
    # todo conjunto que contém pelo menos um estado final do AFN original
    estados_finais_afd = [
        estado for estado in novos_estados_afd
        if any(e in afn.estados_finais for e in estado)
    ]

    # Retorna o novo AFD resultante
    return AFD(novos_estados_afd, afn.alfabeto, novas_transicoes_afd,
               tuple(sorted(list(estado_inicial_afd))), estados_finais_afd)


# =============================================================
#   INTERFACE GRÁFICA COM TKINTER
# =============================================================

class ConversorGUI:
    """
    Classe que constrói a interface gráfica (GUI) do conversor usando Tkinter.
    Permite inserir um AFN manualmente, realizar a conversão e visualizar o AFD resultante.
    """
    def _init_(self, master):
        self.master = master
        master.title("Conversor de AFN para AFD")

        # ----------- SEÇÃO DE ENTRADA DE DADOS -----------
        self.frame_entrada = tk.LabelFrame(master, text="Entrada do AFN", padx=10, pady=10)
        self.frame_entrada.pack(padx=10, pady=10)

        # Campos para cada parte da quíntupla
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

        tk.Label(self.frame_entrada, text="Transições (formato q,s->q1,q2;...)").grid(row=4, column=0, sticky=tk.W)
        self.entry_transicoes = tk.Entry(self.frame_entrada)
        self.entry_transicoes.grid(row=4, column=1)

        # Botão principal para iniciar a conversão
        self.btn_converter = tk.Button(master, text="Converter para AFD", command=self.processar_conversao)
        self.btn_converter.pack(pady=5)

        # ----------- SEÇÃO DE SAÍDA -----------
        self.frame_saida = tk.LabelFrame(master, text="Quíntupla do AFD Resultante", padx=10, pady=10)
        self.frame_saida.pack(padx=10, pady=10)

        # Caixa de texto para mostrar o resultado do AFD
        self.texto_saida = tk.Text(self.frame_saida, height=15, width=60)
        self.texto_saida.pack()

    # =============================================================
    #   MÉTODO PRINCIPAL DE CONVERSÃO (chamado ao clicar no botão)
    # =============================================================
    def processar_conversao(self):
        """
        Lê os dados digitados pelo usuário, constrói o AFN correspondente,
        converte-o em AFD e exibe o resultado na interface.
        """
        try:
            # 1. Captura e tratamento dos campos de entrada
            estados_str = self.entry_estados.get()
            alfabeto_str = self.entry_alfabeto.get()
            estado_inicial_str = self.entry_estado_inicial.get()
            estados_finais_str = self.entry_estados_finais.get()
            transicoes_str = self.entry_transicoes.get()

            # Transforma as strings em conjuntos
            estados = {e.strip() for e in estados_str.split(',') if e.strip()}
            alfabeto = {s.strip() for s in alfabeto_str.split(',') if s.strip()}
            estado_inicial = estado_inicial_str.strip()
            estados_finais = {f.strip() for f in estados_finais_str.split(',') if f.strip()}

            # 2. Converte as transições de texto para dicionário
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

            # 3. Cria o objeto AFN a partir dos dados
            afn_de_entrada = AFN(estados, alfabeto, transicoes, estado_inicial, estados_finais)

            # 4. Realiza a conversão para AFD
            afd_resultante = converter_afn_para_afd(afn_de_entrada)

            # 5. Exibe o resultado formatado na área de texto
            self.texto_saida.delete("1.0", tk.END)
            self.texto_saida.insert(tk.END, afd_resultante)

        except Exception as e:
            # Mostra mensagem de erro se algo der errado na entrada
            messagebox.showerror("Erro de Entrada",
                                 f"Ocorreu um erro ao processar os dados. Verifique a sintaxe.\nDetalhes: {e}")


# =============================================================
#   EXECUÇÃO DO PROGRAMA
# =============================================================

# Cria a janela principal e inicializa a interface
root = tk.Tk()
gui = ConversorGUI(root)
root.mainloop()