# Driver de Arvore AVL para Linux

Este modulo do kernel Linux implementa uma arvore AVL e registra logs de operacoes e memoria.

## Compilacao

Para compilar o modulo, voce precisa dos headers do kernel instalados (comum em ambientes Linux de desenvolvimento). Execute:

```bash
make
```

## Uso

1. **Carregar o modulo:**
   ```bash
   sudo insmod avl_mod.ko
   ```
   Ao carregar, o modulo insere automaticamente alguns valores de teste (10, 20, 30, 40, 50, 25).

2. **Ver os logs:**
   Os logs de memoria e operacoes podem ser vistos lendo o arquivo no `/proc`:
   ```bash
   cat /proc/avl_tree
   ```

3. **Inserir novos valores:**
   Voce pode inserir novos valores escrevendo no arquivo `/proc`:
   ```bash
   echo "100" > /proc/avl_tree
   ```

4. **Remover o modulo:**
   ```bash
   sudo rmmod avl_mod
   ```
   Isso liberara toda a memoria e limpará os logs.

## Logs

O driver registra:
- Alocacao de memoria (endereco e chave).
- Rotacoes (Esquerda/Direita).
- Liberacao de memoria.
