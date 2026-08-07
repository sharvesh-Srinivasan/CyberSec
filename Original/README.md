# Cryptography Lab Observation Record

This document contains the Aim, Algorithm, Sample Output, and Result for all the cryptography programs implemented in this repository.

---

## 1. AffineBruteForce.java

### Aim
To write a Java program to perform a brute-force attack on the Affine Cipher by systematically trying all possible valid key pairs (a, b) to decrypt a given ciphertext and display all possible plaintext combinations.

### Algorithm
1. Start the program.
2. Prompt the user to enter the Ciphertext and convert it to uppercase.
3. Define a list of valid values for key `a`. For the English alphabet of 26 letters, `a` must be coprime with 26. The valid values are 1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25
4. Iterate through every valid value of key `a`:
   * Compute the modular multiplicative inverse of `a` modulo 26, denoted as $a^{-1}$.
   * Iterate through every possible value of key `b` from 0 to 25.
   * For each key pair (a, b), initialize an empty string and loop through each character in the given Ciphertext.
   * Apply the Affine Cipher decryption formula: $P = (a^{-1} \times (C - b + 26)) \pmod{26}$.
   * Append the resulting character to the plaintext string.
   * Print the current key values (`a` and `b`) along with the corresponding generated plaintext.
5. Stop the program.

### Output
```text
Enter Cipher Text: RCLLA

Possible Plain Texts:

a = 1  b = 0  --> RCLLA
a = 1  b = 1  --> QBKKZ
...
a = 5  b = 8  --> HELLO
...
a = 25  b = 25  --> UXIIV
```

### Result
Thus, the Java program to perform a brute-force attack on the Affine Cipher was successfully executed and the expected plaintext was identified from the output.

---

## 2. AffineCipher.java

### Aim
To write a Java program to implement the Affine Cipher for encrypting and decrypting alphabetic text using the mathematical functions E(x) = (ax + b) mod 26 and D(y) = a⁻¹(y - b) mod 26.

### Algorithm
1. Start the program.
2. Prompt the user to input integer keys `a` (must be coprime with 26) and `b`.
3. Accept the plaintext string and convert it to uppercase.
4. **Encryption:** Loop through each character of the plaintext. Convert it to its numeric value (A=0, B=1...Z=25). Apply the formula $C = (a \times \text{value} + b) \pmod{26}$. Convert the result back to a character and append it to the ciphertext.
5. **Decryption:** First, calculate the modular multiplicative inverse of `a` modulo 26, denoted as $a^{-1}$. If it doesn't exist, terminate. Otherwise, loop through each ciphertext character. Apply the decryption formula $P = (a^{-1} \times (\text{value} - b + 26)) \pmod{26}$. Convert it back to a character.
6. Print the encrypted and decrypted strings.
7. Stop the program.

### Output
```text
Enter the value of a (must be coprime with 26): 5
Enter the value of b: 8
Enter the string to encrypt (uppercase letters only): HELLO
Encrypted String: RCLLA
Decrypted String: HELLO
```

### Result
Thus, the Java program to implement the Affine Cipher encryption and decryption was successfully executed and the output was verified.

---

## 3. AutoKeyCipher.java

### Aim
To write a program that encrypts and decrypts text using the AutoKey Cipher, where the plaintext itself acts as an extension of the encryption key.

### Algorithm
1. Start the program.
2. Prompt the user to enter the Plaintext and an initial Keyword. Convert both to uppercase.
3. **Key Generation:** Generate the full encryption key by appending characters from the original plaintext to the end of the initial keyword until the key length matches the plaintext length.
4. **Encryption:** Iterate through the plaintext characters. Add the numerical alphabet value (0-25) of the plaintext character to the numerical value of the corresponding key character. Take the result modulo 26 and convert it back to a character.
5. **Decryption:** Iterate through the ciphertext. The key character is either from the initial keyword or from previously decrypted plaintext. Subtract the key character's numerical value from the ciphertext character's value (add 26 to handle negative numbers). Take the result modulo 26 to recover the plaintext character.
6. Display the final encrypted and decrypted texts.
7. Stop the program.

### Output
```text
Enter Plain Text: HELLO
Enter Keyword: KEY
Encrypted Text : RIJSS
Decrypted Text : HELLO
```

### Result
Thus, the Java program to implement the AutoKey Cipher encryption and decryption was successfully executed and the output was verified.

---

## 4. CaesarCipher.java

### Aim
To implement the Caesar Cipher substitution technique by shifting each letter of a plaintext by a fixed integer key value.

### Algorithm
1. Start the program.
2. Prompt the user to enter an integer value for the `key` (the shift amount).
3. Accept the plaintext string to be encrypted and convert it to uppercase.
4. **Encryption:** Loop through every character in the string. If it is a letter, calculate its new shifted position by adding the `key` to its numerical index (0-25) and applying modulo 26. Convert this new index back to a character.
5. **Decryption:** Loop through the encrypted string. Revert the shift by subtracting the `key` from the character's numeric index, adding 26 before applying modulo 26 to avoid negative remainders.
6. Print the generated ciphertext and the recovered plaintext.
7. Stop the program.

### Output
```text
Enter the key: 3
Enter the string to encrypt: HELLO
Encrypted String: KHOOR
Decrypted String: HELLO
```

### Result
Thus, the Java program to implement Caesar Cipher encryption and decryption was successfully executed and the output was verified.

---

## 5. ColumnarCipher.java

### Aim
To encrypt text using a Columnar Transposition Cipher, writing text in rows and reading by columns based on a numeric key order.

### Algorithm
1. Start the program.
2. Accept the plaintext string from the user, convert to uppercase, and remove all spaces.
3. Prompt the user for the number of columns, and then accept an array of integers representing the specific column ordering (the key).
4. Calculate the required matrix size. If the length of the plaintext is not a perfect multiple of the number of columns, pad the text with the character 'X'.
5. Initialize a 2D character matrix. Iterate through the plaintext and fill the matrix row by row from left to right.
6. Generate the ciphertext by iterating through numbers 1 to `N` (number of columns). For each number, locate its column index in the key array, read the characters in that column from top to bottom, and append them to the ciphertext.
7. Print the final ciphertext.
8. Stop the program.

### Output
```text
Enter Plain Text: HELLOWORLD
Enter Number of Columns: 3
Enter Key:
3 1 2
Cipher Text : EORXLWLXHLOD
```

### Result
Thus, the Java program to implement the Columnar Transposition Cipher was successfully executed and the output was verified.

---

## 6. GCD.java

### Aim
To write a recursive program to compute the Greatest Common Divisor (GCD) of two numbers and check whether they are coprime.

### Algorithm
1. Start the program.
2. Prompt the user to input two integer numbers, `a` and `b`.
3. Define a recursive Euclidean function `gcd(a, b)`:
   * Base case: If `a % b == 0`, return `b` as the greatest common divisor.
   * Recursive step: Otherwise, recursively call the function passing `b` and the remainder `(a % b)`.
4. Define a checking function. If the computed GCD of the two numbers equals 1, the numbers are coprime (return true). Otherwise, they are not (return false).
5. Print the calculated GCD and a message stating whether the two input numbers are coprime.
6. Stop the program.

### Output
```text
Enter the first number: 14
Enter the second number: 15
GCD: 1
14 and 15 are coprime.
```

### Result
Thus, the Java program to compute the GCD of two numbers and check for coprimality was successfully executed and the output was verified.

---

## 7. HillCipher.java

### Aim
To encrypt a given plaintext block by block using the Hill Cipher technique by applying matrix multiplication.

### Algorithm
1. Start the program.
2. Prompt the user to input values for a 2x2 integer key matrix.
3. Prompt the user for the plaintext string. Convert it to uppercase and remove all non-alphabetic characters.
4. If the length of the plaintext is an odd number, append the character 'X' to the end to make it an even length.
5. Iterate through the plaintext in blocks of 2 characters. Convert each character in the block into its numerical equivalent (0-25) to form a 2x1 column vector.
6. Multiply the 2x2 key matrix by this 2x1 plaintext vector.
7. Apply modulo 26 to each resulting element. Convert these values back into characters to form the ciphertext block.
8. Print the complete concatenated ciphertext.
9. Stop the program.

### Output
```text
Enter 2x2 Key Matrix:
3 3
2 5
Enter Plain Text (Even Length): HELP
Cipher Text: HIAT
```

### Result
Thus, the Java program to implement the Hill Cipher encryption using a 2x2 matrix was successfully executed and the output was verified.

---

## 8. MultiplicativeInverse.java

### Aim
To write a program that finds the modular multiplicative inverse of a given number modulo `m` using the Extended Euclidean Algorithm.

### Algorithm
1. Start the program.
2. Prompt the user to enter a number `a` and a modulus `m`.
3. Implement the Extended Euclidean Algorithm in a function to find coefficients `x` and `y` such that `(a * x) + (m * y) = gcd(a, m)`.
   * In the base case where `m = 0`, set `x = 1`, `y = 0` and return `a`.
   * Otherwise, perform recursive calls to update `x` and `y` using the remainders and quotients.
4. Calculate the GCD of `a` and `m` using the function.
5. If the calculated GCD is not equal to 1, print a message stating that the Multiplicative Inverse does not exist.
6. If the GCD is exactly 1, calculate the inverse using the coefficient `x` by applying `(x % m + m) % m` to ensure a positive result.
7. Print the final multiplicative inverse.
8. Stop the program.

### Output
```text
Enter the number: 5
Enter the modulus: 26
Multiplicative Inverse = 21
```

### Result
Thus, the Java program to find the modular multiplicative inverse using the Extended Euclidean Algorithm was successfully executed and the output was verified.

---

## 9. PlayfairCipher.java

### Aim
To encrypt text using the Playfair Cipher, substituting digraphs (pairs of letters) based on a dynamically generated 5x5 key matrix.

### Algorithm
1. Start the program.
2. Prompt the user for a keyword. Generate a 5x5 key matrix by placing the unique characters of the keyword (treating 'J' as 'I'), followed by the remaining unused letters of the alphabet.
3. Accept plaintext, convert to uppercase, replace 'J' with 'I', and remove non-letters.
4. Prepare the plaintext by breaking it into digraphs (pairs). If a pair contains two identical letters, insert an 'X' between them. If the final length is odd, append an 'X'.
5. Encrypt pairs using matrix rules:
   * **Same row:** Replace each letter with the letter to its immediate right (wrapping around).
   * **Same column:** Replace each letter with the letter immediately below it (wrapping around).
   * **Rectangle:** Replace each letter with the letter in its own row but in the column of the other letter.
6. Print the generated key matrix, prepared plaintext, and ciphertext.
7. Stop the program.

### Output
```text
Enter Key: MONARCHY

Playfair Matrix:
M O N A R 
C H Y B D 
E F G I K 
L P Q S T 
U V W X Z 

Enter Plain Text: HELLO
Prepared Text : HELXLO
Cipher Text   : CFSUPM
```

### Result
Thus, the Java program to implement the Playfair Cipher encryption was successfully executed and the output was verified.

---

## 10. PolyAlphhabeticCipher.java

### Aim
To implement a Polyalphabetic (Vigenère) Cipher, encrypting text using a repeating string keyword.

### Algorithm
1. Start the program.
2. Prompt the user to enter the Plaintext and the repeating Keyword string. Convert both to uppercase.
3. **Encryption:** Loop through the plaintext character by character. For each character, find the corresponding character in the key (repeating the key if necessary). Add their numerical values (A=0, B=1, etc.) together, take the result modulo 26, and convert it back to a character to form the ciphertext.
4. **Decryption:** Loop through the generated ciphertext. Align it with the repeating key string. Subtract the numerical value of the key character from the ciphertext character, add 26 (to avoid negative results), take it modulo 26, and convert it back to plaintext.
5. Print both the Encrypted text and the Decrypted text to the console.
6. Stop the program.

### Output
```text
Enter Plain Text: HELLO
Enter Key: KEY
Encrypted Text: RIJVS
Decrypted Text: HELLO
```

### Result
Thus, the Java program to implement the Polyalphabetic (Vigenère) Cipher encryption and decryption was successfully executed and the output was verified.

---

## 11. RowTranspositionCipher.java

### Aim
To encrypt text using a Row Transposition Cipher by arranging it in a grid and extracting it based on a predefined column sequence.

### Algorithm
1. Start the program.
2. Accept the plaintext string from the user, convert it to uppercase, and remove all whitespace.
3. Prompt the user for the number of columns and read the column-order key array elements.
4. Determine the required number of rows. If the text does not perfectly fill the grid, append 'X' characters until the length is a multiple of the columns.
5. Initialize a 2D matrix of characters. Iterate through the plaintext string and populate the matrix row by row.
6. Construct the ciphertext by extracting characters column by column. The order of columns extracted is dictated by the numerical values in the user-provided key array.
7. Display the final ciphertext.
8. Stop the program.

### Output
```text
Enter Plain Text: HELLOWORLD
Enter Number of Columns: 3
Enter Key:
3 1 2
Cipher Text: EORXLWLXHLOD
```

### Result
Thus, the Java program to implement the Row Transposition Cipher was successfully executed and the output was verified.

---

## 12. TranspositionCipher.java

### Aim
To encrypt and decrypt text using a simple grid-based Transposition Cipher that writes text row-wise and reads it column-wise.

### Algorithm
1. Start the program.
2. Prompt the user to enter the Plaintext string and an integer key representing the number of columns in the grid.
3. **Encryption:** Remove whitespace and convert to uppercase. Pad the string with 'X' until its length is evenly divisible by the key. Create a 2D matrix and fill it with the characters row by row. Read the characters out column by column to create the ciphertext string.
4. **Decryption:** Calculate the number of rows based on the ciphertext length and the key. Create an empty 2D matrix. Fill this matrix with the ciphertext characters column by column. Read the characters out row by row to reconstruct the padded plaintext.
5. Output the encrypted text followed by the decrypted text.
6. Stop the program.

### Output
```text
Enter Plain Text: HELLOWORLD
Enter Key (Columns): 3
Encrypted Text : HLODEORXLWLX
Decrypted Text : HELLOWORLDXX
```

### Result
Thus, the Java program to implement the basic grid Transposition Cipher encryption and decryption was successfully executed and the output was verified.
