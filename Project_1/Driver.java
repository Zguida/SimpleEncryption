package Project_1;
import java.io.File;
public class Driver {
    
    public static void main(String args[]) {
        if (args.length != 5) {
            System.err.println("incorrect number of arguments.");
            System.exit(1);
        }

        //Input variable
        char cipher_type = args[0].charAt(0);
        char operation_mode = args[4].charAt(0);
        File plain_text_file = new File("./Project_1/" + args[1]); //ASCII formatted
        File output_file = new File("./Project_1/" + args[2]);
        File key_file = new File("./Project_1/" + args[3]);
        //for use in input_verify
        File[] file_index = new File[3];
        file_index[0] = plain_text_file;
        file_index[1] = key_file;
        file_index[2] = output_file;
        VerifyInput input_verify = new VerifyInput(args, file_index);



        //Fileformatter test
        FileFormatter convert_plain_text = new FileFormatter(plain_text_file);
        convert_plain_text.add_pad();
        String plain_text_string = convert_plain_text.toString();

        FileFormatter convert_key = new FileFormatter(key_file);
        String key_string = convert_key.toString();

        BlockEncrypt testBlock = new BlockEncrypt(key_string, plain_text_string);
        WriteOutputFile write_encrypted_block = new WriteOutputFile(output_file, testBlock.encrypt());
        
    }
}