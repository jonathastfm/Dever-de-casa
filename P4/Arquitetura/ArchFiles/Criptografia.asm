section .data
    filename db 'output.txt', 0
    buffer db 256 dup(0)  ; buffer para armazenar a entrada do usuário

section .bss
    length resb 1         ; variável para armazenar o comprimento da entrada

section .text
    global _start




_start:

    ; Open the file for reading
    mov eax, 5          ; sys_open
    mov ebx, filename   ; filename
    mov ecx, 0          ; O_RDONLY
    int 0x80
    mov ebx, eax        ; store file descriptor

    ; Read the file content
    mov eax, 3          ; sys_read
    mov ecx, buffer     ; buffer
    mov edx, 256        ; buffer length
    int 0x80
    mov [length], eax   ; store the length of the read content

    ; Write the file content to stdout
    mov eax, 4          ; sys_write
    mov ebx, 1          ; stdout
    mov ecx, buffer     ; buffer
    mov edx, [length]   ; buffer length
    int 0x80

    ; Close the file
    mov eax, 6          ; sys_close
    int 0x80


    ; Ler a entrada do usuário
    mov eax, 3          ; sys_read
    mov ebx, 0          ; stdin
    mov ecx, buffer     ; buffer
    mov edx, 256        ; tamanho do buffer
    int 0x80
    mov [length], eax   ; armazenar o comprimento da entrada

    ; Open the file
    mov eax, 5          ; sys_open
    mov ebx, filename ; filename
    mov ecx, 2          ; O_RDWR
    int 0x80
    mov ebx, eax        ; store file descriptor


    ; Read from the file
    mov eax, 3          ; sys_read
    mov ecx, buffer     ; buffer
    mov edx, 256        ; buffer length
    int 0x80

    ; Write to stdout
    mov eax, 4          ; sys_write
    mov ebx, 1          ; stdout
    mov ecx, buffer     ; buffer
    mov edx, [length]   ; buffer length
    int 0x80
    

    ; Close the file
    mov eax, 6          ; sys_close
    int 0x80

    ; Exit the program
    mov eax, 1          ; sys_exit
    xor ebx, ebx        ; status 0
    int 0x80