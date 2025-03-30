import java.io.*;
import java.nio.Buffer;

public class main{

    public static void main(String[] args) {
        System.out.println("Hello, bitches");

        InputStreamReader istream = new InputStreamReader(System.in); 
        BufferedReader bufRead = new BufferedReader(istream);

        System.out.println("Welcome bitch");

        try {
            System.out.println("Please Enter your first name");
            String firstName = bufRead.readLine();

            System.out.println("Please Enter year born");
            String born = bufRead.readLine();

            System.out.println("Please Enter current year");
            String current = bufRead.readLine();

            int byear = Integer.parseInt(born);
            int cyear = Integer.parseInt(current);
            int age = cyear - byear;
            System.out.println("Hello " + firstName + "Your are " + age + "years old");
        }
        catch (IOException err) {
            System.out.println(err);
        }
        catch (NumberFormatException err) {
            System.out.println(err);
        }
    }

}
