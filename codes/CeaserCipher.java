import java.util.*;

public class CeaserCipher {
	
	static String a = "abcdefghijklmnopqrstuvwxyz";
	
	public static String toSimpleText(String msg) {
		return msg = msg.toLowerCase();
	}
	
	public static String encrypt(String msg, int key) {
		String encryptMsg = "";
		
		for(int i=0; i<msg.length(); i++) {
			int val = a.indexOf(msg.charAt(i));
			int modVal = (val + key) % 26;
			encryptMsg += a.charAt(modVal);
		}
		return encryptMsg;
	}
	
	public static String decrypt(String ciphertext, int key) {
		String decryptMsg = "";
		
		for(int i=0; i<ciphertext.length(); i++) {
			int val = a.indexOf(ciphertext.charAt(i));
			int modVal = (val - key + 26) % 26;
			decryptMsg += a.charAt(modVal);
		}
		return decryptMsg;
	}
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter message : ");
		String msg = scan.nextLine();
		
		System.out.print("Enter key : ");
		int key = scan.nextInt();
		
		msg = toSimpleText(msg);
		
		String ciphertext = encrypt(msg, key);
		System.out.print("Encryption : " + ciphertext);
		
		String plaintext = decrypt(ciphertext, key);
		System.out.print("\nDecryption : " + plaintext);
		
	}
}