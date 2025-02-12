package Project_1;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;


public class WriteOutputFile {
    String content;
    Path to_write_path;

    WriteOutputFile(File to_write, String content){
        this.content = content;
        this.to_write_path = Paths.get(to_write.getAbsolutePath());
        this.write_file();
    }

    private void write_file(){
        try {
            Files.write(to_write_path, content.getBytes(java.nio.charset.StandardCharsets.ISO_8859_1));
        } catch (IOException e) {
            System.out.println("No Input Found");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
