package Project_1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Fileformatter {
    private File unformatted;
    private FileInputStream unformatted_reader;
    private FileOutputStream formatted_writer;

    Fileformatter(File unformatted) {
        this.unformatted = unformatted;
        if (!unformatted.exists()) {
            System.err.println("No File To Format");
            System.exit(1);
        }

        try {
            unformatted_reader = new FileInputStream(unformatted);
            formatted_writer = new FileOutputStream(unformatted, true);
        } catch (Exception e) {
            System.err.println("Cannot Find File To Access");
            System.exit(1);
        }
    }
    
    boolean is_correct_block_size() {
        long short_block = unformatted.length() % 16;
        if (short_block != 0) {
            return false;
        }
        return true;
    }

    void add_pad() {
        if (this.is_correct_block_size()) {
            return;
        }
        long short_block = unformatted.length() % 16;
        int bytes_needed = (int) ((int) 16 - short_block);
        try {
            for (int i = 0; i < bytes_needed; i++) {
                formatted_writer.write(0);
            }
        } catch (IOException e) {
            System.err.println("IO Exception In Fileformatter");
            System.exit(1);
        }
    }
}
