import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int current = reader.read();
        StringBuilder input = new StringBuilder();
        while (current != -1) {
            input.append(Character.toChars(current));
            current = reader.read();
        }
        System.out.println(input.reverse());
        reader.close();
    }
}