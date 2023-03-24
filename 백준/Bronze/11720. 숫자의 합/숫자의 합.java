import java.io.*;
import java.util.*;


public class Main {
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		//StringTokenizer st = new StringTokenizer(br.readLine());
		//StringBuilder sb = new StringBuilder();		
		
		int N = Integer.parseInt(br.readLine());
		String[] num = br.readLine().split("");
		int sum = 0;
		
		for(int i=0; i<N; i++) sum += Integer.parseInt(num[i]);
		
		System.out.print(sum);
	}

}