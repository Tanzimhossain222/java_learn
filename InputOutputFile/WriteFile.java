
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class WriteFile {

    public static void main(String[] args) {
        File file = new File("MyText.txt");

        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(isr);
            PrintWriter out = new PrintWriter(file);
            System.out.println("Write something: ");
            String s = in.readLine();
            out.println(s);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
