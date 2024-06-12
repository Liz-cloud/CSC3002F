
import jdk.jfr.Unsigned;
import sun.util.resources.cldr.om.LocaleNames_om;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.lang.*;
import java.text.DecimalFormat;

public class OS1Assignment {
    //left pad bianary number with zeros
    public static String intToString(long num, int digits) {
        assert digits > 0 : "Invalid number of digits";
        // create variable length array of zeros
        char[] zeros = new char[digits];
        Arrays.fill(zeros, '0');
        // format number as String
        DecimalFormat df = new DecimalFormat(String.valueOf(zeros));
        return df.format(num);
    }
    public static void main(String [] args){
        String input_file=args[0];
        if(args.length==0){
            System.out.println("Enter input file as parameter!");
        }
        //page_table[0]=Page Table
        //page_table[1]=Physical Frames
        int[][] page_table={{2,4,1,7,3,5,6},{0,1,2,3,4,5,6,7}};
        ArrayList<Long> file_data=new ArrayList<>();
        ArrayList<String> file_output=new ArrayList<>();

        try (
            FileInputStream fileInputStream =new FileInputStream(input_file);
            DataInputStream dataInputStream=new DataInputStream(fileInputStream);
            ){
            while (dataInputStream.available()>0){
                long read_line=dataInputStream.readLong();
                long reversed_long=Long.reverseBytes(read_line);
                String long_string=String.valueOf(reversed_long);

                long unsigned_long=Long.parseUnsignedLong(long_string);
                file_data.add(reversed_long);
            }
            } catch (IOException e) {
            e.printStackTrace();
        }
        for( int i=0;i<file_data.size();i++){
            String toHex =Long.toHexString(file_data.get(i));
            System.out.println("Virtual Address:"+toHex);
            String binary=Long.toBinaryString(file_data.get(i));
            String virtual_address=intToString(Long.parseUnsignedLong(binary),32);
            System.out.println("Binary value: "+virtual_address);
            //virtual address is 32 bit long
            //hence there are 2^32 addresses in virtual address space
            // virtual page size and physical address given is 2^7
            //pages =2^32/ 2^7 =2^25
            // to address each page 25 bits are required
            // the most significant 25 bits in virtual address will denote the page number being referred
//             //remaining 7 will will be the page offset

            String first_25=virtual_address.substring(0,25);
            int index= Integer.parseInt(first_25,2);
            int page_num=page_table[0][index];
            String physical_address=Integer.toBinaryString(page_num)+virtual_address.substring(25);
            Long result =Long.parseUnsignedLong(physical_address,2);
            System.out.println("Physical address: "+Long.toHexString(result));
            file_output.add(Long.toHexString(result));
        }
        try (
                FileOutputStream outputStream=new FileOutputStream("output-OS1.txt");
                DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
                ){
            for (int i=0;i<file_output.size();i++){
                String appended="00000000".substring(file_output.get(i).length())+file_output.get(i);
                String output="0x"+appended;
                //byte[] data=output.getBytes(StandardCharsets.UTF_8);

                dataOutputStream.writeBytes(output+"\n");
            }
            dataOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
