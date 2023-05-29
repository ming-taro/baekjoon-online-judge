import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static char[][] chess;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringBuffer sb = new StringBuffer();
		
		int[][] apart = new int[15][14];
		
		for(int i=0; i<15; i++) {
			for(int j=0; j<14; j++) {
				if(j == 0) apart[i][j] = 1;
				else if(i == 0) apart[i][j] = j+1;
				else apart[i][j] = apart[i][j-1] + apart[i-1][j];
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i=0; i<T; i++) {
			int k = Integer.parseInt(br.readLine());    //층
			int n = Integer.parseInt(br.readLine());    //호
			
			sb.append(apart[k][n-1]+"\n");
		}
		System.out.print(sb);
		
		br.close();
		//bw.flush();
		//bw.close();
	}	
}