
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

    public static void main(String[] args) {
        File file = new File("MyText.txt");

        try {

            BufferedReader in = new BufferedReader(new FileReader(file));
            String str = in.readLine();

            while (str != null) {
                System.out.println("Read: " + str);
                str = in.readLine();
            }
            in.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file);
        } catch (IOException e2) {
            System.out.println("Input / output problem");
        }
    }
}
