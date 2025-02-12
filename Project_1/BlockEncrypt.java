package Project_1;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class BlockEncrypt {
    private String key;
    private String plain_text;
    BlockEncrypt(String key, String plain_text){
        this.key = key;
        this.plain_text = plain_text;
    }

    private String[] split_into_blocks(){
        int number_of_blocks = plain_text.length() / 16;
        String[] blocks = new String[number_of_blocks];

        for (int i = 0; i < number_of_blocks; i++){
            blocks[i] = plain_text.substring(16 * i, 16 * (i+1));
        }
        return blocks;
    }

    private byte[][] xor_blocks(){
        String[] blocks = this.split_into_blocks();
        byte[][] encrypted_blocks = new byte[blocks.length][16];
        int encrypted_blocks_index = 0;
        for (String block : blocks){
            for(int i = 0; i < 16; i++){
                encrypted_blocks[encrypted_blocks_index][i] = (byte)((block.charAt(i)) ^ (key.charAt(i)));
            }
            encrypted_blocks_index++;
        }
        return encrypted_blocks;
    }

    private byte[][] swap_xor_indexes(){
        byte[][] encrypted_blocks = this.xor_blocks();
        byte[][] swapped_blocks = new byte[encrypted_blocks.length][16];
        int swapped_blocks_index = 0;

        for (byte[] block : encrypted_blocks){
            int key_index = 0;
            int block_start = 0;
            int block_end = block.length - 1;
            
            while(block_start < block_end){
                if (key.charAt(key_index) % 2 == 0) {
                    key_index++;                    
                    block_start++;
                }
                else{
                    byte save = block[block_start];
                    block[block_start] = block[block_end];
                    block[block_end] = save;
                    block_end--;
                    block_start++; 
                    key_index++;
                }
            }
            swapped_blocks[swapped_blocks_index] = block;
            swapped_blocks_index++;
        }
        return swapped_blocks;
    }

    public String encrypt(){
        byte[][] final_encryption = this.swap_xor_indexes();
        StringBuilder concat_blocks = new StringBuilder();
        String test = "";
        for (byte[] block : final_encryption) {
            String str = new String(block, java.nio.charset.StandardCharsets.ISO_8859_1);
            concat_blocks.append(str);
        }
        return concat_blocks.toString();
    }
}