# Treasure-Hunt
Finds "treasures" (strings) hidden through MD5 hashing.

Pirate.java takes in three parameters: 1. the position of the start of the first hash code, 2. a text file filled with ASCII characters, some of which form valid MD5 hashes, and 3. a text file containing the characters that comprise the treasure string.

Hashes resolve to either "normal" or "compound" numbers. Normal numbers represent the indices in the second text file of the characters of the treasure string. Compound numbers are large ints that resolve to four offsets, such that an int n = offset_1 + 10^3 * offset_2 + 10^6 * offset_3 + 10^9 * offset_4. Each of these offsets give us the start position of the next hash code to crack, in that start_of_next_hash = offset * 32 + start_of_current_hash. Essentially, compound numbers lead to normal numbers, and normal numbers lead to characters in the treasure string.

If we imagine all the numbers as a tree and read the normal numbers in BFS order, we get the indices in the second text file of the characters that, in order, form the treasure string.

In the example attached, 45063 is the starting index of the first hash code in map.txt, map.txt is the text file containing MD5 hashes, and island.txt is the text file containing the treasure string characters.

Running Pirate.java on the inputs 45063, map.txt, and island.txt (in that order) should print "Arrr the treasure? It's right across the crocodile-infested creek, at the bottom of the cobra snake pit, mate".
