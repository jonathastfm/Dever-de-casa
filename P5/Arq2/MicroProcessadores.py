# Simulador de Microprogramação em Python [cite: 38]

class Processor:
    def __init__(self): # Corrected from _init_
        self.registers = {'R1': 0, 'R2': 0, 'R3': 0} # [cite: 38]
        self.data_memory = [10, 20, 0] * 100 # [cite: 38]
        self.program_memory = [ # [cite: 38]
            ('MOV', 'R1', 'mem[0]'),
            ('MOV', 'R2', 'mem[1]'), # [cite: 38]
            ('ADD', 'R1', 'R2'), # [cite: 38]
            ('HALT',) # [cite: 39]
        ]
        self.control_memory = [ # [cite: 39]
            # MOV R1, mem[0]
            {'op': 'LOAD_MEM_TO_R1', 'addr': 0, 'next': 1}, # [cite: 39]
            {'op': 'FETCH', 'next': 10}, # [cite: 39]
            # MOV R2, mem[1]
            {'op': 'LOAD_MEM_TO_R2', 'addr': 1, 'next': 3}, # [cite: 39]
            {'op': 'FETCH', 'next': 10}, # [cite: 39]
            # ADD R1, R2
            {'op': 'LOAD_R1_TO_ALU_A', 'next': 5}, # [cite: 39]
            {'op': 'LOAD_R2_TO_ALU_B', 'next': 6}, # [cite: 39]
            {'op': 'ALU_ADD', 'next': 7}, # [cite: 39]
            {'op': 'STORE_ALU_TO_R1', 'next': 8}, # [cite: 39]
            {'op': 'FETCH', 'next': 10}, # [cite: 39]
            # HALT
            {'op': 'HALT', 'next': -1}, # [cite: 39]
            # FETCH
            {'op': 'FETCH', 'next': 0} # [cite: 39] # This 'next' is used if FETCH doesn't decode a specific instruction to jump to.
        ]
        self.pc = 0 # [cite: 39]
        self.upc = 10  # inicia com FETCH [cite: 39]
        self.alu_a = 0 # [cite: 39]
        self.alu_b = 0 # [cite: 39]
        self.alu_result = 0 # [cite: 40]
        self.running = True # [cite: 40]

    def execute_microinstruction(self, microinst): # [cite: 40]
        op = microinst['op'] # [cite: 40]

        if op == 'LOAD_MEM_TO_R1': # [cite: 40]
            self.registers['R1'] = self.data_memory[microinst['addr']] # [cite: 40]
        elif op == 'LOAD_MEM_TO_R2': # [cite: 40]
            self.registers['R2'] = self.data_memory[microinst['addr']] # [cite: 40]
        elif op == 'LOAD_R1_TO_ALU_A': # [cite: 40]
            self.alu_a = self.registers['R1'] # [cite: 40]
        elif op == 'LOAD_R2_TO_ALU_B': # [cite: 40]
            self.alu_b = self.registers['R2'] # [cite: 40]
        elif op == 'ALU_ADD': # [cite: 40]
            self.alu_result = self.alu_a + self.alu_b # [cite: 40]
        elif op == 'STORE_ALU_TO_R1': # [cite: 40]
            self.registers['R1'] = self.alu_result # [cite: 40]
        elif op == 'HALT': # [cite: 40]
            self.running = False # [cite: 40]
        elif op == 'FETCH': # [cite: 40]
            if self.pc < len(self.program_memory): # [cite: 40]
                inst = self.program_memory[self.pc] # [cite: 40]
                self.pc += 1 # [cite: 40]
                if inst[0] == 'MOV' and inst[1] == 'R1': # [cite: 40]
                    self.upc = 0 # [cite: 40]
                elif inst[0] == 'MOV' and inst[1] == 'R2': # [cite: 40]
                    self.upc = 2 # [cite: 40]
                elif inst[0] == 'ADD': # [cite: 40]
                    self.upc = 4 # [cite: 41]
                elif inst[0] == 'HALT': # [cite: 41]
                    self.upc = 9 # [cite: 41]
                else: # [cite: 41]
                    # Unknown instruction
                    self.running = False # [cite: 41]
            else:
                # No more instructions
                self.running = False # [cite: 41]

    def run(self): # [cite: 41]
        while self.running: # [cite: 41]
            microinst = self.control_memory[self.upc] # [cite: 41]
            self.execute_microinstruction(microinst) # [cite: 41]

            if microinst['next'] == -1: # Corrected from ['next' J==-1] [cite: 41] # HALT microinstruction
                break # [cite: 41]
            
            # If the microinstruction was FETCH, upc is already set by execute_microinstruction.
            # Otherwise, go to the next microinstruction in the sequence.
            if microinst['op'] != 'FETCH': # [cite: 41]
                 self.upc = microinst['next'] # Corrected from "self.upc microinst['next']" [cite: 41]

if __name__ == "__main__": # [cite: 41]
    proc = Processor() # [cite: 41]
    print(f"Estado inicial: R1={proc.registers['R1']}, R2={proc.registers['R2']}") # [cite: 41]
    proc.run() # [cite: 41]
    print(f"Estado final: R1={proc.registers['R1']}, R2={proc.registers['R2']}") # [cite: 41]