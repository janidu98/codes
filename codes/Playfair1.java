import java.util.*;

public class Playfair1 {
	
	public static String removeDuplicateElementFromKey(String key) {
		
		if(key != null && key != "") {
			
			char[] array = new char[key.length()];
			
			for(int i = 0; i < key.length(); i++) {
				if(isNotContains(array, key.charAt(i))) {
					array[i] = key.charAt(i);
				}
			}
			
			return removeEmptyElements(array);
		}
		
		return "";
	}
	
	public static boolean isNotContains(char[] array, char c) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == c) {
				return false;
			}
		}
		return true;
	}
	
	public static String removeEmptyElements(char[] array) {
		String keyString = "";
		
		for(int i = 0; i < array.length; i++) {
			if(array[i] != '\u0000') {
				keyString += array[i];
			}
		}
		return keyString;
	}
	
	public static String createArrayString(String uniqueKeyString, char[] alphabet) {
		char[] a = new char[25];
		
		for(int i = 0; i < uniqueKeyString.length(); i++) {
			a[i] = uniqueKeyString.charAt(i);
		}
		
		int count = uniqueKeyString.length();
		
		for(int i = 0; i < a.length; i++) {
			
			if(isNotContains(a, alphabet[i])) {
				
				a[count] = alphabet[i];
				count++;
			}
		}
		
		return removeEmptyElements(a);
	}
	
	public static char[][] createKeyMatrix(String uniqueArrayString) {
		char[][] matrix = new char[5][5];
		
		int key = 0;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				matrix[i][j] = uniqueArrayString.charAt(key);
				key++;
			}
		}
		return matrix;
	}
	
	public static void printKeyMatrix(char[][] matrix) {
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static String toString(char[] array) {
		String cipher = "";
		
		for(int i = 0; i < array.length; i++) {
			cipher += array[i];
		}
		
		return cipher;
	}
	
	public static int rowIndex(char c, char[][] matrix) {
		int index = 0;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(matrix[i][j] == c) {
					index = i;
					break;
				}
			}
		}
		return index;
	}
	
	public static int colIndex(char c, char[][] matrix) {
		int index = 0;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(matrix[i][j] == c) {
					index = j;
					break;
				}
			}
		}
		return index;
	}
	
	
	public static char[] toArray(String cipherText){
		
		char array[] = new char[cipherText.length()-2];
		
		for(int i=0; i<array.length; i++){
			array[i] = cipherText.charAt(i);
		}
		
		return array;
	}
	
	public static String removeFillerElement(char[] cipherArr){
		String plainText = "";
		
		for(int i=0; i<cipherArr.length; i++){
			if(cipherArr[i] != 'X')
				plainText += cipherArr[i];
		}
		
		return plainText;
	}
	
	
	public static char[] breakPlainTextIntoPairs(String plainText) {
		// 1. call arrayLength method to find out the correct length, after replacing duplicates as well
		char[] textArr = new char[arrayLength(plainText)];
		
		// 2. now we know the correct length, so we have to fill the textArr
		
		int q = 0; // q is to track the characters of the plainText
		int y = 0; // y is to track the current available position in textArr
		
		while(y != textArr.length) {
			if(plainText.charAt(q) == plainText.charAt(q+1)) {
				// near characters are equal
				textArr[y] = plainText.charAt(q);
				textArr[y+1] = 'X';
				q++;
				y = y + 2;
			} else {
				if(plainText.charAt(q+1) == ' ') {
					// the last character
					textArr[y] = plainText.charAt(q);
					textArr[y+1] = 'X';
					q++;
					y = y + 2;
				} else {
					// two near elements are not equal
					textArr[y] = plainText.charAt(q);
					textArr[y+1] = plainText.charAt(q+1);
					q = q + 2;
					y = y + 2;
				}
			}
		}
		return textArr;
	}
	
	public static int arrayLength(String plainText) {
		int q = 0; // q is to track the character in plainText
		int y = 0; // y is to track the length

		while(plainText.charAt(q) != ' ') {
			
			if(plainText.charAt(q) == plainText.charAt(q+1)) {
				// the two near characters are equal, (LL), so since we are going to replace 'LL' as 'LX', so there will be additional 'X'. Therefore y will be increase by 2
				q++;
				y = y + 2;
			} else {
				if(plainText.charAt(q+1) == ' ') {
					// this indicates there are no more elements
					q++;
					y = y + 2;
				} else {
					// two near characters are not equal & there is a pair
					q = q + 2;
					y = y + 2;
				}
			}
		}
		
		return y;
	}
	
	
	
	// ENCRYPTION
	public static String encrypt(String plainText, String key, char[][] keyMatrix) {
		
		// 1. break plain text into pairs
		char[] textArr = breakPlainTextIntoPairs(plainText);
		
		// 2. declare a cipher array
		char[] cipherArr = new char[arrayLength(plainText)];
		
		// 3. encrypt the textArr
		for(int i = 0; i < textArr.length; i=i+2) {
			
			char first = textArr[i];
			char second = textArr[i+1];
			
			int r1 = rowIndex(first, keyMatrix);
			int c1 = colIndex(first, keyMatrix);
			
			int r2 = rowIndex(second, keyMatrix);
			int c2 = colIndex(second, keyMatrix);
			
			if(r1 == r2) {
				// two characters are at the same row
				if(c1 == 4) {
					// character 1 is at last column
					cipherArr[i] = keyMatrix[r1][(c1+1)%5];
				} else {
					cipherArr[i] = keyMatrix[r1][c1+1];
				}
				
				if(c2 == 4) {
					cipherArr[i+1] = keyMatrix[r2][(c2+1)%5];
				} else {
					cipherArr[i+1] = keyMatrix[r2][c2+1];
				}
				
			} else if (c1 == c2) {
				// two characters are at the same column (so the row will change)
				if(r1 == 4) {
					cipherArr[i] = keyMatrix[(r1+1)%5][c1];
				} else {
					cipherArr[i] = keyMatrix[r1+1][c1];
				}
				
				if(r2 == 4) {
					cipherArr[i+1] = keyMatrix[(r2+1)%5][c2];
				} else {
					cipherArr[i+1] = keyMatrix[r2+1][c2];
				}
				
				
			} else {
				// two characters are neither at the same row nor at the same column
				cipherArr[i] = keyMatrix[r1][c2];
				cipherArr[i+1] = keyMatrix[r2][c1];
			}
			
			
		}
		
		return toString(cipherArr);
		
		
	}
	
	
	// DECRYPTION
	public static String decrypt(String cipherText, String key, char[][] keyMatrix) {
		
		// 1. break plain text into pairs
		char[] textArr = toArray(cipherText);
		//char[] textArr = breakPlainTextIntoPairs(cipherText);
		
		// 2. declare a cipher array
		char[] cipherArr = new char[arrayLength(cipherText)];
		
		//System.out.println("textArr lenght : " + textArr.length);
		//System.out.println("cipherArr lenght : " + cipherArr.length);
		
		// 3. encrypt the textArr
		for(int i = 0; i < textArr.length; i=i+2) {
			
			char first = textArr[i];
			char second = textArr[i+1];
			
			int r1 = rowIndex(first, keyMatrix);
			int c1 = colIndex(first, keyMatrix);
			
			int r2 = rowIndex(second, keyMatrix);
			int c2 = colIndex(second, keyMatrix);
			
			if(r1 == r2) {
				// two characters are at the same row
				if(c1 == 0) {
					// character 1 is at last column
					cipherArr[i] = keyMatrix[r1][(c1+4)%5];
				} else {
					cipherArr[i] = keyMatrix[r1][c1-1];
				}
				
				if(c2 == 0) {
					cipherArr[i+1] = keyMatrix[r2][(c2+4)%5];
				} else {
					cipherArr[i+1] = keyMatrix[r2][c2-1];
				}
				
			} else if (c1 == c2) {
				// two characters are at the same column (so the row will change)
				if(r1 == 0) {
					cipherArr[i] = keyMatrix[(r1+4)%5][c1];
				} else {
					cipherArr[i] = keyMatrix[r1-1][c1];
				}
				
				if(r2 == 0) {
					cipherArr[i+1] = keyMatrix[(r2+4)%5][c2];
				} else {
					cipherArr[i+1] = keyMatrix[r2-1][c2];
				}
				
				
			} else {
				// two characters are neither at the same row nor at the same column
				cipherArr[i] = keyMatrix[r1][c2];
				cipherArr[i+1] = keyMatrix[r2][c1];
			}
			
			
		}	
		return removeFillerElement(cipherArr);
	}
	

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		
		System.out.println("Enter message : ");
		String message = sc.nextLine();
		
		message = (message.toUpperCase() + " " + " ");
		
		System.out.println("Enter the key : ");
		String key = sc.nextLine();
		
		key = key.toUpperCase();
		
		String uniqueKeyString = removeDuplicateElementFromKey(key);
		System.out.println("Unique key string : " + uniqueKeyString);
		
		String uniqueArrayString = createArrayString(uniqueKeyString, alphabet);
		System.out.println("Unique array string : " + uniqueArrayString);
		
		char[][] keyMatrix = createKeyMatrix(uniqueArrayString);
		
		System.out.println("Select the option : ");
		System.out.println("    1. Encryption");
		System.out.println("    1. Decryption");
		
		System.out.println("Option (1/2) : ");
		int option = sc.nextInt();
		
		if(option == 1){
			String cipher = encrypt(message, uniqueKeyString, keyMatrix);
			System.out.println("Ciphertext : " + cipher);
		} else if(option == 2){
			String plain = decrypt(message, uniqueKeyString, keyMatrix);
			System.out.println("Plaintext : " + plain);
		} else {
			System.out.println("Wrong option");
		}
	}
}