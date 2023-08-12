import java.io.*;
import java.nio.charset.Charset;
import java.util.*;


public class Main {
	
	public static boolean isPrime(int num) {
		if(num == 1) return false;
		
		for(int i=2; i*i<=num; i++) {
			if(num%i == 0) return false;
		}
		
		return true;
	}
	
	public static int isPrimeCount(int num) {
		int cnt = 0;
		
		for(int i=num+1; i<=2*num; i++) {
			if(isPrime(i)) cnt+=1;
		}
		
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0) break;
			sb.append(isPrimeCount(n) + "\n");
		}
		
		System.out.print(sb);
	}

}