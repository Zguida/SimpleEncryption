package Project_1;
import java.io.File;
public class Driver {
    
    public static void main(String args[]) {
        if (args.length != 5) {
            System.err.println("incorrect number of arguments.");
            System.exit(1);
        }

        char cipher_type = args[0].charAt(0);
        char operation_mode = args[4].charAt(0);
        File plain_text_file = new File("./" + args[1]); //ASCII formatted
        File output_file = new File("./" + args[2]);
        File key_file = new File(args[3]);

       //Input Verifiers
        if (cipher_type != 'S' && cipher_type != 'B') {
            System.err.println("Cipher Type Invalid");
            System.exit(1);
        } else if (!plain_text_file.exists()) {
            System.err.println("Plain Text File Does Not Exist.");
            System.exit(1);
        } else if (output_file.exists()) {
            System.out.println("No output file defined. Creating File...");
            output_file = new File("./ouput.txt");
        } else if (!key_file.exists()) {
            System.err.println("Key File Does Not Exist");
            System.exit(1);
        } else if (operation_mode != 'E' && operation_mode != 'D') {
            System.err.println("Operation Mode Invalid");
            System.exit(1);
        }

    }
}