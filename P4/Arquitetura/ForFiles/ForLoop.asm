segment .data 

    orient db "Entre com uma string de até 20 caracteres",10 
    tamori equ $-orient 

 

segment .bss 

    buffer resb 50 
    quantidade resd 1  
    copia resb 50 

    

 

 

 

segment .text 

global _start 

_start: 

 

    ;Apontando para a mensagem inicial 
    mov ecx,orient ;ponteiro da string 
    mov edx,tamori 

    call exibe ;mostra a mensagem inicial 

    ;retorna aqui 

 

;Apontar para o buffer de entrada 

mov ecx,buffer 

mov edx,50 

call ler 

mov [quantidade],eax ;salvando quantos bytes entraram 

 

 

; FOR de transferência (com modificação) 

xor esi,esi ;Índice já inicializado em zero, aponta para o primeiro byte 

mov ecx,[quantidade] 

iniloop: 

mov al,[buffer+esi] ;caracter atual na variável auxiliar 

;modificação 

add al,5 ; sem essa linha, é o exercício 6 

mov [copia+esi],al ; transferência 

 

inc esi ; aponta para o próximo caracter 

dec ecx ;decrementando o contador 

cmp ecx,0 ;ja chegou ao final? 

jne iniloop 

;sai quando o contador (ECX) zera 

;imprimindo a string copia 

;Aponta 

mov ecx,copia 

mov edx,[quantidade] 

call exibe 

 

 

 

fim: 

mov eax,1 

int 80h 

 

;Area de procedimentos 

exibe: 

;ponteiro e tamanho passados por registradores 

mov eax,4 ;serviço PRINT 

mov ebx,1 ;FD da tela 

int 80h 

ret 

 

ler: 

;Dois parâmetros foram passados... 

mov eax,3 ;serviço READ 

mov ebx,0 ;FD do teclado 

int 80h 

ret 