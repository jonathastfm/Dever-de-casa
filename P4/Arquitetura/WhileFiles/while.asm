section .data
    counter db 10

section .text
    global _start

_start:
    mov ecx, [counter]  ; Load the counter value into ecx

loop_start:
    ; Your loop code here

    loop loop_start     ; Decrement ecx and jump to loop_start if ecx is not zero

    ; Exit the program
    mov eax, 60         ; syscall: exit
    xor edi, edi        ; status: 0
    syscall