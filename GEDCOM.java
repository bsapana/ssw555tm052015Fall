
/* 

This program reads the input GEDCOM text file as a command line argument and prints records, level numbers and tags on the output console.

*/


import java.io.*;

import java.util.ArrayList;
 

public class GEDCOM {

 

      public static void main(String[] args) {

	File inFile = null;

        if (args.length > 0) {

            inFile = new File(args[0]);

        } else {

           System.err.println("Invalid arguments count:" + args.length);

           System.exit(1);

        }


            BufferedReader br = null;

            String strLine = "";

            @SuppressWarnings("serial")

            ArrayList<String> tagName = new ArrayList<String>(){{

                  add("INDI");

                  add("NAME");

                  add("SEX");

                  add("BIRT");

                  add("DEAT");

                  add("FAMC");

                  add("FAMS");

                  add("FAM");

                  add("MARR");

                  add("HUSB");

                  add("WIFE");

                  add("CHIL");
		
		  add("DIV");
		
		  add("DATE");
		
		  add("HEAD");
		
		  add("TRLR");
		
		  add("NOTE");
		
            }};

            

            try {

                  br = new BufferedReader(new FileReader(inFile));

                  while ((strLine = br.readLine()) != null) {
			
			char a = strLine.charAt(0);
			
			int levelnumber = Character.getNumericValue(a); 


                        String[] tag = strLine.split(" ");

                        //System.out.println(tag.length);  

                        
			System.out.println(strLine); // Prints each line

                        

                        System.out.println(levelnumber); // Prints the level number of each line
          

			
                        if(levelnumber == 0 && tag.length > 2 && tagName.contains(tag[2]))

                              System.out.println(tag[2]);    // Prints the tag when level number is 0 and tag is located at third place in line
  

                        else if(levelnumber == 0 && tagName.contains(tag[1]))   

                                System.out.println(tag[1]); // Prints the tag when level number is 0 and tag is located at second place in line


 			else if(levelnumber != 0 && tagName.contains(tag[1]))   

                                System.out.println(tag[1]); // Prints the tag for level 1 and 2


                        else  System.out.println("Invalid tag"); // Prints when tag is not from the valid list of tags 

                  	}
 

            } catch (IOException e) {

                  System.err.println("Unable to read the input file");

            }
                  

      }

}
