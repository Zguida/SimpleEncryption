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

    private int[][] xor_blocks(){
        String[] blocks = this.split_into_blocks();
        int[][] encrypted_blocks = new int[blocks.length][16];
        int encrypted_blocks_index = 0;
        for (String block : blocks){
            for(int i = 0; i < 16; i++){
                encrypted_blocks[encrypted_blocks_index][i] = (int)((block.charAt(i)) ^ (key.charAt(i)));
            }
            encrypted_blocks_index++;
        }
        return encrypted_blocks;
    }

    private int[][] swap_xor_indexes(){
        int[][] encrypted_blocks = this.xor_blocks();
        int[][] swapped_blocks = new int[encrypted_blocks.length][16];
        int swapped_blocks_index = 0;

        for (int[] block : encrypted_blocks){
            int key_index = 0;
            int block_start = 0;
            int block_end = block.length - 1;
            
            while(block_start < block_end){
                if (key.charAt(key_index) % 2 == 0) {
                    key_index++;                    
                    block_start++;
                }
                else{
                    int save = block[block_start];
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
        int[][] final_encryption = this.swap_xor_indexes();
        StringBuilder concat_blocks = new StringBuilder();
        String test = "";
        for (int[] block : final_encryption) {
            for (int cast: block){
                concat_blocks.append((char) cast);
            }
           
        }
        return concat_blocks.toString();
    }
}
