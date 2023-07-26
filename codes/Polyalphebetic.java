import java.util.*;

public class Polyalphebetic{
	
	public static String generateKeyMethod(String msg, String key) {
		
		for(int i=0; ; i++) {
			if(msg.length() == i)
				i  = 0;
			if(msg.length() == key.length())
				break;
			
			key += key.charAt(i);
		}
		return key;
	}
	
	public static String encrypt(String msg, String key) {
		
		String encryptMsg = "";
		
		for(int i=0; i<msg.length(); i++) {
			int val = (msg.charAt(i) + key.charAt(i)) % 26;
			val += 'A';
			encryptMsg += (char) val;
		}
		return encryptMsg;
		
	}
	
	public static String decrypt(String ciphertext, String key) {
		
		String decryptMsg = "";
		
		for(int i=0; i<ciphertext.length(); i++) {
			int val = (ciphertext.charAt(i) - key.charAt(i) + 26) % 26;
			val += 'A';
			decryptMsg += (char) val;
		}
		return decryptMsg;
		
	}
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter message : ");
		String msg = scan.nextLine();
		
		System.out.print("Enter key : ");
		String key = scan.nextLine();
		
		key = generateKeyMethod(msg, key);
		
		System.out.println(key);
		
		String ciphertext = encrypt(msg, key);
		String plaintext = decrypt(ciphertext, key);
		
		System.out.print("Encryption : " + ciphertext);
		System.out.print("\nDecryption : " + plaintext);
		
	}
	
}