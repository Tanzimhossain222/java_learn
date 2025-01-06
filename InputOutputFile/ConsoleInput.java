
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput {

    public static void main(String[] args) {
        String s;

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(isr);

        try {
            System.out.println("Write something: ");
            s = in.readLine();
            System.out.println("Read: " + s);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
