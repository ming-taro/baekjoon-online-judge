import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		//StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<n; i++) {
			String str = br.readLine();
			int pre = 1, cnt = 0;
			for(int j=0; j<str.length(); j++) {
				if(str.charAt(j) == 'O') cnt += (pre++);
				else pre = 1;
			}
			sb.append(cnt + "\n");
		}
		
		System.out.print(sb);
	}

}