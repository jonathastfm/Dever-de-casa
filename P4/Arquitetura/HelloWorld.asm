section .data

    mens db "Entre com uma String: ",10
    compr equ $ - mens

    mens2 db "A string começa com A", 10
    compr2 equ $ - mens2

    mens3 db "A string nao começa com A",10
    compr3 equ $ - mens3


section .bss
    buff resb 20
    qde resd 1

section .text

    global _start 

    _start:

        mov eax, 4 
        mov ebx, 1
        mov ecx, mens
        mov edx, compr
        
        int 80h

        mov eax, 3
        mov ebx, 0
        mov ecx, buff
        mov edx, 20
        int 80h

        mov [qde], eax 

        mov eax, 4
        mov ebx, 1
        mov ecx, buff
        mov edx, [qde]
        int 80h

        cmp byte[buff], "a"

        je igual
        jne diferente

        jmp sair


    

    diferente: 
        mov eax, 4
        mov ebx, 1
        mov ecx, mens3
        mov edx, compr3
        int 80h

        jmp sair

    igual:
        mov eax, 4
        mov ebx, 1
        mov ecx, mens2
        mov edx, compr2
        int 80h   

        jmp sair  



    sair:
        mov eax, 1                      
        int 0x80 

