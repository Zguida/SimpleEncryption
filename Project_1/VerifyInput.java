package Project_1;
import java.io.File;
import java.io.IOException;

public class VerifyInput {
    VerifyInput(String args[], File[] files){
        this.type_check(args, files);
    }
    private void type_check(String args[], File[] files){
        //Input Verifiers 
        if (args[0].charAt(0) != 'S' && args[0].charAt(0) != 'B') {
            System.err.println("Cipher Type Invalid");
            System.exit(1);
        } else if (!files[0].exists()) {
            System.err.println("Plain Text File Does Not Exist.");
            System.exit(1);
        } else if (!files[2].exists()) {
            System.out.println("No output file defined. Creating File...");
            try {
                files[2].createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (!files[1].exists()) {
            System.err.println("Key File Does Not Exist");
            System.exit(1);
        } else if (args[4].charAt(0) != 'E' && args[4].charAt(0) != 'D') {
            System.err.println("Operation Mode Invalid");
            System.exit(1);
        }
    }
}
