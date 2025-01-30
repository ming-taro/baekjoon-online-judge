import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();

        for (int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());

            switch (X){
                case 1:
                    int a = Integer.parseInt(st.nextToken());
                    stack.push(a);
                    break;
                case 2:
                    if (!stack.isEmpty()){
                        System.out.println(stack.pop());
                    }
                    else {
                        System.out.println(-1);
                    }
                    break;
                case 3:
                    System.out.println(stack.size());
                    break;
                case 4:
                    if (stack.isEmpty()){
                        System.out.println(1);
                    }
                    else {
                        System.out.println(0);
                    }
                    break;
                case 5:
                    if (stack.isEmpty()){
                        System.out.println(-1);
                    }
                    else {
                        System.out.println(stack.peek());
                    }
            }
        }
    }
}