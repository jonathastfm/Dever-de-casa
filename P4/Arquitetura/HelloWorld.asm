section .data

    mens db "Bom Semana!",10
    compr equ $ - mens

section .bss
    buff resb 20
    qde resd 1


section .text

    global _start 

    _start:

        mov [mens + 2], byte "a"
         

        
        mov edx, compr
        mov ecx, mens
        mov ebx, 1
        mov eax, 4 
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

        jmp sair

    sair:
        mov eax, 1                      
        int 0x80 

