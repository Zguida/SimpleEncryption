package Project_1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class FileFormatter {
    private File unformatted;
    private FileOutputStream formatted_writer;
    private FileInputStream formatted_reader;

    FileFormatter(File unformatted) {
        this.unformatted = unformatted;
        if (!unformatted.exists()) {
            System.err.println("No File To Format");
            System.exit(1);
        }

        try {
            formatted_reader = new FileInputStream(unformatted);
            formatted_writer = new FileOutputStream(unformatted, true);
        } catch (Exception e) {
            System.err.println("Cannot Find File To Access");
            System.exit(1);
        }
    }
    
    private boolean is_correct_block_size() {
        long short_block = unformatted.length() % 16;
        if (short_block != 0) {
            return false;
        }
        return true;
    }

    public void add_pad() {
        if (this.is_correct_block_size()) {
            return;
        }
        long short_block = unformatted.length() % 16;
        int bytes_needed = (int)(16 - short_block);
        try {
            for (int i = 0; i < bytes_needed; i++) {
                formatted_writer.write(0x81);
            }
        } catch (IOException e) {
            System.err.println("IO Exception In Fileformatter");
            System.exit(1);
        }
    }

    @Override
    public String toString(){
        String convert_string = "";
        for(int i = 0; i < unformatted.length(); i++){
            try {
                convert_string = convert_string + (char)formatted_reader.read();
            } catch (IOException e) {
                System.out.println("No File TO Convert (FileFormatter toString())");
                System.exit(1);
            }
        }
        return convert_string;
    }
}
