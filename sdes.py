# Simplified DES (S-DES) Implementation in Python

# --- Permutation Tables (1-based indices) ---
P10 = [3, 5, 2, 7, 4, 10, 1, 9, 8, 6]
P8  = [6, 3, 7, 4, 8, 5, 10, 9]
P4  = [2, 4, 3, 1]
EP  = [4, 1, 2, 3, 2, 3, 4, 1]
IP  = [2, 6, 3, 1, 4, 8, 5, 7]
IP_INV = [4, 1, 3, 5, 7, 2, 8, 6]

# --- S-Boxes ---
S0 = [
    [1, 0, 3, 2],
    [3, 2, 1, 0],
    [0, 2, 1, 3],
    [3, 1, 3, 2]
]

S1 = [
    [0, 1, 2, 3],
    [2, 0, 1, 3],
    [3, 0, 1, 0],
    [2, 1, 0, 3]
]

# --- Helper Functions ---
def permute(bits, permutation):
    return [bits[i - 1] for i in permutation]

def shift(bits, n):
    return bits[n:] + bits[:n]

def xor(bits1, bits2):
    return [b1 ^ b2 for b1, b2 in zip(bits1, bits2)]

def s_box(bits, sbox):
    row = (bits[0] << 1) | bits[3]
    col = (bits[1] << 1) | bits[2]
    val = sbox[row][col]
    return [(val >> 1) & 1, val & 1]

# --- Key Generation ---
def generate_keys(key):
    p10 = permute(key, P10)
    left = p10[:5]
    right = p10[5:]
    
    # LS-1
    left_shift_1 = shift(left, 1)
    right_shift_1 = shift(right, 1)
    k1 = permute(left_shift_1 + right_shift_1, P8)
    
    # LS-2
    left_shift_2 = shift(left_shift_1, 2)
    right_shift_2 = shift(right_shift_1, 2)
    k2 = permute(left_shift_2 + right_shift_2, P8)
    
    return k1, k2

# --- Core f_K Function ---
def f_k(bits, key):
    left = bits[:4]
    right = bits[4:]
    
    ep = permute(right, EP)
    x = xor(ep, key)
    
    l_sbox = s_box(x[:4], S0)
    r_sbox = s_box(x[4:], S1)
    
    p4 = permute(l_sbox + r_sbox, P4)
    return xor(left, p4) + right

# --- Encryption/Decryption ---
def encrypt_block(block, k1, k2):
    bits = permute(block, IP)
    bits = f_k(bits, k1)
    bits = bits[4:] + bits[:4] # Switch
    bits = f_k(bits, k2)
    return permute(bits, IP_INV)

def decrypt_block(block, k1, k2):
    bits = permute(block, IP)
    bits = f_k(bits, k2) # Notice k2 is used first for decryption
    bits = bits[4:] + bits[:4] # Switch
    bits = f_k(bits, k1)
    return permute(bits, IP_INV)

# --- String Parsers ---
def str_to_bits(s):
    return [int(c) for c in s]

def bits_to_str(bits):
    return "".join(str(b) for b in bits)

if __name__ == "__main__":
    print("--- S-DES (Simplified DES) Algorithm ---")
    
    key_str = input("Enter 10-bit key (e.g. 1010000010): ")
    while len(key_str) != 10 or not all(c in '01' for c in key_str):
        key_str = input("Invalid key! Please enter exactly 10 bits: ")
        
    plain_str = input("Enter 8-bit plaintext (e.g. 10010111): ")
    while len(plain_str) != 8 or not all(c in '01' for c in plain_str):
        plain_str = input("Invalid text! Please enter exactly 8 bits: ")
        
    key_bits = str_to_bits(key_str)
    plain_bits = str_to_bits(plain_str)
    
    # Generate keys
    k1, k2 = generate_keys(key_bits)
    print(f"\n[+] Generated Subkeys:")
    print(f"    K1 = {bits_to_str(k1)}")
    print(f"    K2 = {bits_to_str(k2)}")
    
    # Encrypt
    encrypted_bits = encrypt_block(plain_bits, k1, k2)
    cipher_str = bits_to_str(encrypted_bits)
    print(f"\n[+] Encrypted Ciphertext: {cipher_str}")
    
    # Decrypt
    decrypted_bits = decrypt_block(encrypted_bits, k1, k2)
    decrypted_str = bits_to_str(decrypted_bits)
    print(f"[+] Decrypted Plaintext:  {decrypted_str}")
