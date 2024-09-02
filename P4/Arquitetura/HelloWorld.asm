section .data
section .bss

mens db "Hello World!",10
section .test

global _start 

_start:
    
    mov edx, compr
    mov ecx, mens
    muv ebx, 1
    muv eax, 4 
    int 80h

    jmp sair 

sair:
    mov eax, 1        
    xor ebx, ebx        
    int 0x80 