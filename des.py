from Crypto.Cipher import DES
import base64

def pad(text):
    """Pad the text to be a multiple of 8 bytes (DES block size)"""
    n = 8 - (len(text) % 8)
    return text + (chr(n) * n)

def unpad(text):
    """Remove the padding"""
    n = ord(text[-1])
    return text[:-n]

def encrypt(message, key):
    """Encrypt a message using DES and encode it in Base64"""
    # Create the DES cipher object in ECB mode
    cipher = DES.new(key.encode('utf-8'), DES.MODE_ECB)
    
    # Pad message and convert to bytes
    padded_message = pad(message).encode('utf-8')
    
    # Encrypt
    encrypted_bytes = cipher.encrypt(padded_message)
    
    # Encode to Base64 to make it readable
    return base64.b64encode(encrypted_bytes).decode('utf-8')

def decrypt(encrypted_message, key):
    """Decrypt a Base64-encoded DES message"""
    cipher = DES.new(key.encode('utf-8'), DES.MODE_ECB)
    
    # Decode Base64 back to bytes
    encrypted_bytes = base64.b64decode(encrypted_message)
    
    # Decrypt
    decrypted_padded_bytes = cipher.decrypt(encrypted_bytes)
    
    # Unpad and decode to string
    return unpad(decrypted_padded_bytes.decode('utf-8'))

if __name__ == "__main__":
    print("--- Simple Python DES ---")
    
    key = input("Enter exactly 8-byte key (e.g. 12345678): ")
    while len(key) != 8:
        key = input("Invalid key length! Enter exactly 8-byte key: ")
        
    message = input("Enter the message to encrypt: ")
    
    try:
        print("\n[+] Encrypting...")
        encrypted = encrypt(message, key)
        print(f"Encrypted message (Base64): {encrypted}")
        
        print("\n[+] Decrypting...")
        decrypted = decrypt(encrypted, key)
        print(f"Decrypted message: {decrypted}")
    except Exception as e:
        print(f"\n[!] An error occurred: {e}")
