import java.math.*;

public class RSA {
	
	public static double gcd(double e, double phi) {
		double temp;
		
		while(true) {
			temp = e%phi;
			if(temp == 0)
				return phi;
			e = phi;
			phi = temp;
		}
	}
	
	public static void main(String[] args) {
		double p = 2;
		double q = 7;
		
		double msg = 5;
		
		double n = p*q;
		double phi = (p-1)*(q-1);
		double e = 2;
		
		while(e < phi) {
			if(gcd(e, phi) == 1)
				break;
			else
				e++;
		}
		System.out.println("e value: " + e);
		
		double d=0;
		for(int i=0; i<=100; i++) {
			double x = 1+(i*phi);
			
			if(x%e == 0){
				d = x/e;
				break;
			}
		}
		System.out.println("d value: " + d);
		
		double c = Math.pow(msg,e);
		c = c%n;
		System.out.println("Encryption: " + c);
		
		double m = Math.pow(c,d);
		m = m%n;
		System.out.println("Decrytion: " + m);
	}
	
}