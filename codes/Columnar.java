import java.util.*;

public class Columnar{
	
	private static Scanner scan = new Scanner(System.in);
	
	private static int[] kywdNumAssign(String key) {
		String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		int[] kywdNumList = new int[key.length()];
		
		int z = 0;
		for(int i=0; i<alph.length(); i++) {
			for(int j=0; j<key.length(); j++) {
				if(alph.charAt(i) == key.charAt(j)){
					z++;
					kywdNumList[j] = z;
				}
			}
		}
		return kywdNumList;
	}
	
	private static void encrypt() {
		System.out.print("Enter plaintext: ");
		String plaintext = scan.nextLine();
		
		System.out.print("Enter key: ");
		String key = scan.nextLine();
		
		int[] kywdNumList = kywdNumAssign(key);
		
		int extraLetters = plaintext.length()%key.length();
		
		int dummyCharacters = key.length() - extraLetters;
		
		if(extraLetters != 0) {
			for(int i=0; i<dummyCharacters; i++)
				plaintext += '_';
		}
		
		int numOfRows = plaintext.length()/key.length();
		
		char[][] arr = new char[numOfRows][key.length()];
		
		int z=0;
		for(int i=0; i<numOfRows; i++) {
			for(int j=0; j<key.length(); j++)  {
				arr[i][j] = plaintext.charAt(z);
				z++;
			}
		}
		
		String ciphertext = "";
		for(int i=1; i<=key.length(); i++) {
			int column = -1;
			for(int c=0; c<key.length(); c++) {
				if(kywdNumList[c] == i) {
					column = c;
					break;
				}
			}
			
			for(int j=0; j<numOfRows; j++) {
				ciphertext += arr[j][column];
			}
		}
		System.out.println("Cipher text: " + ciphertext);
	}
	
	private static void decrypt() {
		System.out.print("Enter ciphertext: ");
		String ciphertext = scan.nextLine();
		
		System.out.print("Enter key: ");
		String key = scan.nextLine();
		
		int[] kywdNumList = kywdNumAssign(key);
		
		int numOfRows = ciphertext.length()/key.length();
		
		char[][] arr = new char[numOfRows][key.length()];
		
		int index = 0;
		for(int i=1; i<=key.length(); i++) {
			int column = -1;
			for(int c=0; c<key.length(); c++) {
				if(kywdNumList[c] == i) {
					column = c;
					break;
				}
			}
			
			for(int j=0; j<numOfRows; j++) {
				arr[j][column] = ciphertext.charAt(index++);
			}
		}
		
		String plaintext = "";
		for(int i=0; i<numOfRows; i++) {
			for(int j=0; j<key.length(); j++) {
				plaintext += arr[i][j];
			}
		}
		
		String finalPlaintext = "";
		for(int i=0; i<plaintext.length(); i++) {
			if(plaintext.charAt(i) != '_')
				finalPlaintext += plaintext.charAt(i);
		}
		
		System.out.println("Plain text: " + finalPlaintext);
	}
	
	public static void main(String[] args) {
		
		System.out.println("Select Option - ");
		System.out.println("1 - Encryption");
		System.out.println("2 - Decryption");
		
		System.out.print("Option: ");
		int option = scan.nextInt();
		scan.nextLine();
		
		if(option == 1)
			encrypt();
		else if(option == 2)
			decrypt();
		else {
			System.out.println("Wrong option!");
			System.exit(0);
		}
	}
	
}