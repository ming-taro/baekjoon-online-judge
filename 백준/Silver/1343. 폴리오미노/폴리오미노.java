import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();

        input = input.replaceAll("XXXX", "AAAA");
        input = input.replaceAll("XX", "BB");

        if (input.indexOf('X') == -1) {
            System.out.println(input);
        }
        else {
            System.out.println(-1);
        }
    }
}