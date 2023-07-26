import java.util.*;

public class OTP{
	
	static char a[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
	public static String encrypt(String msg, String key) {
		
		String encryptMsg = "";
		
		for(int i=0; i<msg.length(); i++) {
			char msgVal = msg.charAt(i);
			for(int j=0; j<a.length; j++) {
				if(msgVal == a[j]) {
					char keyVal = key.charAt(i);
					for(int k=0; k<a.length; k++) {
						if(keyVal == a[k]) {
							int modVal = (j + k) % 26;
							encryptMsg += a[modVal];
							break;
						}
					}
				}
			}
		}
		return encryptMsg;
	}
	
	public static String decrypt(String ciphertext, String key) {
		
		String decryptMsg = "";
		
		for(int i=0; i<ciphertext.length(); i++) {
			char ciphertextLetter = ciphertext.charAt(i);
			for(int j=0; j<a.length; j++) {
				if(ciphertextLetter == a[j]) {
					char keyLetter = key.charAt(i);
					for(int k=0; k<a.length; k++) {
						if(keyLetter == a[k]) {
							int num = (j - k);
							
							if(num < 0)
								decryptMsg += a[(num + 26) % 26];
							else
								decryptMsg += a[num];
							break;
						}
					}
				}
			}
		}
		return decryptMsg;
	}
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter message : ");
		String msg = scan.nextLine();
		
		System.out.print("Enter key : ");
		String key = scan.nextLine();
		
		if(msg.length() != key.length())
			System.out.println("Key length must be equal to message length!!!");
		else {
			String ciphertext = encrypt(msg, key);
			String plaintext = decrypt(ciphertext, key);
			
			System.out.print("Encryption : " + ciphertext);
			System.out.print("\nDecryption : " + plaintext);
		}
		
	}	
}