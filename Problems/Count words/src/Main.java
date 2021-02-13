import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numWords = 0;

        int currentChar = reader.read();
        if (currentChar == -1) {
            System.out.println(numWords);
        } else {
            while (currentChar != -1) {
                if (currentChar == 32) {
                    reader.read();
                } else if (currentChar != 32) {
                    numWords++;
                    currentChar = reader.read();
                    while (currentChar != 32 && currentChar != -1) {
                        currentChar = reader.read();
                    }
                }
                currentChar = reader.read();
            }
        }
        reader.close();
        System.out.println(numWords);
    }
}