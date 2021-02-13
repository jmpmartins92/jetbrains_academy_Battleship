import java.io.InputStream;

class Main {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;
        int charAsNumber = inputStream.read();
        while (charAsNumber != -1) {
            byte character = (byte) charAsNumber;
            System.out.print(character);
            charAsNumber = inputStream.read();
        }
        inputStream.close();
    }
}