import java.util.*;

public class Rail {
	
	public static char[][] fillingTheArrayWithCharacters(String msg, int key, char ch) {
		
		char[][] arr = new char[key][msg.length()];
		
		for(int i=0; i<key; i++) {
			for(int j=0; j<msg.length(); j++) {
				arr[i][j] = ch;
			}
		}
		
		return arr;
		
	}
	
	public static String encrypt(String msg, int key) {
		
		char[][] rail = fillingTheArrayWithCharacters(msg, key, '\n');
		
		boolean dirDown = false;
		int row=0, col=0;
		
		for(int i=0; i<msg.length(); i++) {
			if(row == 0 || row == key-1)
				dirDown = !dirDown;
			
			rail[row][col++] = msg.charAt(i);
			
			if(dirDown)
				row++;
			else
				row--;
		}
		
		String ciphertext = "";
		for(int i=0; i<key; i++) {
			for(int j=0; j<msg.length(); j++) {
				if(rail[i][j] != '\n')
					ciphertext += rail[i][j];
			}
		}
		
		return ciphertext;	
	}
	
	public static String decrypt(String msg, int key) {
		
		char[][] rail = fillingTheArrayWithCharacters(msg, key, '\n');
		
		boolean dirDown = true;
		int row=0, col=0;
		
		for(int i=0; i<msg.length(); i++) {
			if(row == 0)
				dirDown = true;
			if(row == key-1)
				dirDown = false;
			
			rail[row][col++] = '*';
			
			if(dirDown)
				row++;
			else
				row--;
		}
		
		int index = 0;
		for(int i=0; i<key; i++) {
			for(int j=0; j<msg.length(); j++){
				if(rail[i][j] == '*' && index < msg.length())
					rail[i][j] = msg.charAt(index++);
			}
		}
		
		String plaintext = "";
		row=0;
		col=0;
		
		for(int i=0; i<msg.length(); i++) {
			if(row == 0)
				dirDown = true;
			if(row == key-1)
				dirDown = false;
			
			plaintext += rail[row][col++];
			
			if(dirDown)
				row++;
			else
				row--;
		}
		
		return plaintext;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter message: ");
		String msg = scan.nextLine();
		
		System.out.print("Enter key: ");
		int key = scan.nextInt();
		
		String ciphertext = encrypt(msg, key);
		System.out.println("Cipher text: " + ciphertext);
		
		String plaintext = decrypt(ciphertext, key);
		System.out.println("Plain text: " + plaintext);
	}

}