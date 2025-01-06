
import java.util.Scanner;

public class ScannerTest {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Write something: ");
        String str = s.next();
        System.out.println(str);

        System.out.println("Write a number: ");
        int num = s.nextInt();
        System.out.println(num);

        s.close();
    }

}
