import java.util.*;

public class Mono{
	
	public static char plaintext[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	public static char codedchar[] = {'d','k','v','q','f','i','b','j','w','p','e','s','c','x','h','t','m','y','a','u','o','l','r','g','z','n'};
	
	public static String encrypt(String msg) {
		msg = msg.toLowerCase();
		String encryptMsg = "";
		
		for(int i=0; i<msg.length(); i++) {
			for(int j=0; j<26; j++) {
				if(msg.charAt(i) == plaintext[j]) {
					encryptMsg += codedchar[j];
					break;
				}
			}
		}
		return encryptMsg;
	}
	
	public static String decrypt(String msg) {
		msg = msg.toLowerCase();
		String decryptMsg = "";
		
		for(int i=0; i<msg.length(); i++) {
			for(int j=0; j<26; j++) {
				if(msg.charAt(i) == codedchar[j]) {
					decryptMsg += plaintext[j];
					break;
				}
			}
		}
		return decryptMsg;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter message : ");
		String msg = scan.nextLine();
		
		System.out.println("Options : 1 - Encrypt , 2 - Decrypt");
		System.out.print("Enter Option : ");
		int option = scan.nextInt();
		
		if(option == 1) {
			
			System.out.print("Encryption : " + encrypt(msg));
			
		} else if(option == 2){
			
			System.out.print("Decryption : " + decrypt(msg));
			
		} else {
			System.out.println("Wrong option");
		}
	}
	
}