package Project_1;

import java.io.File;
import java.io.FileInputStream;

public class Fileformatter {
    File unformatted;
    FileInputStream unformatted_reader;
    FileReader formatted_writer;

    Fileformatter(String file_path) {
        this.unformatted = new File(file_path);
        if (!unformatted.exists()) {
            System.err.println("No File To Format");
            System.exit(1);
        }

        try {
            unformatted_reader = new FileReader(unformatted);
            formatted_writer = new FileReader(unformatted);
        } catch (Exception e) {
            System.err.println("Cannot Find File To Access");
            System.exit(1);
        }
    }
    
    boolean check_byte_length() {
        while (unformatted_reader.read()) {
            
        }

    }
}
