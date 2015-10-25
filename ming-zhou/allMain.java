import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*

public class allMain
{
	private static String line="",line1="";
	private static String[] lineParts,lineParts1;

  public static void main(String args[])
  {
	// TODO Auto-generated method stub
	 
		//	File inputFile = new File(inputDirectory +"/TEAM3");
	  int count=0;
	  File inputFile1 = new File("Ming Zhou P1.ged");
	  BufferedReader br1 = null;
	  boolean checkFAMS=false,checkFAMC=false;
	        try {
	        	FileReader fr1 = new FileReader(inputFile1);  		
	            br1 = new BufferedReader(fr1);
	            line1 = br1.readLine();
	            lineParts1 = line1.trim().split(" ");
	            while(line1 != null)
	            {
	            	lineParts1 = line1.trim().split(" ");
	            if(lineParts1[1].equalsIgnoreCase("GIVN")) count++;
	            line1 = br1.readLine();
	            }
	        }
	        catch(Exception e)
	        {
	        	
	        }
	            //System.out.println("count"+count);
	  Database listofObject[]=new Database[count];
	  for(int i=0;i<count;i++)
	  {//System.out.println("enter for");
		 
		  listofObject[i]=new Database("                    ","                 ","           ","               ","              ","","","","");
	  }
	  File inputFile = new File("Ming Zhou P1.ged");
	  BufferedReader br = null;
	        try {
	        	FileReader fr = new FileReader(inputFile);  		
	            br = new BufferedReader(fr);
	            line = br.readLine();
	            lineParts = line.trim().split(" ");
	            //System.out.println("inside try");
	           while(line  != null )  //to get first data
            {
	            	lineParts = line.trim().split(" ");
	            	
	            	if(lineParts.length>2){
	            	if(lineParts[2].equals("INDI")){
	            //System.out.println(" got 1 INDI");	
	            	
           break;
	            	}
	            	//System.out.println("inside first INDI check"+lineParts[2]);
	            	}
	            	line = br.readLine();
	            	lineParts = line.trim().split(" ");
            }
	           //System.out.println("outside 1 while");
	           line = br.readLine();
	           for(int i=0;line != null;i++) //to get first data
	            {   
	        	   listofObject[i]=new Database("","","","","","","","","");
		            	lineParts = line.trim().split(" ");
		            	//System.out.println("line part"+lineParts[2]);
		            	boolean check=false;
		            	if(lineParts[2].equals("INDI")) check=true;
		            	//System.out.println("just outside while");
		            	while(!check)
		            	{
		            		//System.out.println("entered while"+lineParts[1]);
		            		if(lineParts[1].equalsIgnoreCase("GIVN")) { 
		            			 //System.out.println(" name"+lineParts[2]);
		            			 //System.out.println(" name default"+listofObject[i].getName());
		            			 String name=lineParts[2];
		            			 //System.out.println(" name"+name);
		            			listofObject[i].setName(name);
		            		                           //System.out.println(" name"+listofObject[i].getName());
		            		}
		            		else if(lineParts[1].equalsIgnoreCase("SEX")){ listofObject[i].setSex(lineParts[2]);
		            	//System.out.println("inside sex"+listofObject[i].getSex());
		            		}
		            		else if(lineParts[1].equalsIgnoreCase("BIRT")) {
		            			                            line = br.readLine();
				            	                            lineParts = line.trim().split(" ");
				            	                            //System.out.println("values"+lineParts[1]);
				            	                           // System.out.println("lineParts[3]++lineParts[4]++lineParts[5]"+lineParts[2]+"/"+lineParts[3]+"/"+lineParts[4]);
				            	                            if(lineParts[1].equalsIgnoreCase("DATE"))
				            	                            {
				            	                            	if(lineParts.length>5)
		            			                           listofObject[i].setBdate(lineParts[3]+"/"+lineParts[4]+"/"+lineParts[5]);
				            	                            	else 
				            	                            		 listofObject[i].setBdate(lineParts[2]+"/"+lineParts[3]+"/"+lineParts[4]);
				            	                            }
				            	                            if(lineParts[1].equalsIgnoreCase("_EMAIL"))
				            	                            	listofObject[i].setEmail(lineParts[2]);
		            		}
		            	    else if(lineParts[1].equalsIgnoreCase("DEAT")) {
			            			                        line = br.readLine();
					            	                        lineParts = line.trim().split(" ");
					            	                        if(lineParts[1].equalsIgnoreCase("DATE"))
				            	                            {
				            	                            	if(lineParts.length>5)
		            			                           listofObject[i].setDdate(lineParts[3]+"/"+lineParts[4]+"/"+lineParts[5]);
				            	                            	else 
				            	                            		 listofObject[i].setDdate(lineParts[2]+"/"+lineParts[3]+"/"+lineParts[4]);
				            	                            }
			            			                        
		            		}
		            	    else if(lineParts[1].equalsIgnoreCase("FAMS") && checkFAMS==false) 
		            	    	{
		            	    	listofObject[i].setFAMS(lineParts[2]);
		            	    	listofObject[i].setNoOfspouse((listofObject[i].getNoOfspouse()+1));
		            	    	checkFAMS=true;
		            	    	}
		            	    else if(lineParts[1].equalsIgnoreCase("FAMS") && checkFAMS==true) 
	            	    	{
	            	    	listofObject[i].setFAMS1(lineParts[2]);
	            	    	listofObject[i].setNoOfspouse((listofObject[i].getNoOfspouse()+1));
	            	    	}
		            	    else if(lineParts[1].equalsIgnoreCase("_EMAIL"))
                            	listofObject[i].setEmail(lineParts[2]);
		            	    else if(lineParts[1].equalsIgnoreCase("FAMC") && checkFAMC==false) 
		            	    	{listofObject[i].setFAMS(lineParts[2]);
		            	    	checkFAMC=true;
		            	    	}
		            	    else if(lineParts[1].equalsIgnoreCase("FAMC") && checkFAMC==true) 
	            	    	{
	            	    	listofObject[i].setFAMS(lineParts[2]);
	            	    	}
		            		line = br.readLine();
		            		lineParts = line.trim().split(" ");
		            		//System.out.println("outside else if "+lineParts[1]);
		            		if(lineParts.length<3)
		            			check=false;
		            		else if((lineParts[2].equals("INDI") )||((lineParts[0].equalsIgnoreCase("0") &&lineParts[2].equalsIgnoreCase("FAM")))) check=true;
		            		else check=false;
		            	}
		            	if((lineParts[0].equalsIgnoreCase("0") &&lineParts[2].equalsIgnoreCase("FAM"))) break;
		            	line = br.readLine();
		            	
	            }
	          //Check if data is strored properly
	           //System.out.println("NAME,SEX,Birt,Deat   ,FAMS   ,FAMC");
	           //System.out.println(" size"+listofObject.length);
	          /* for(int i=0;i<listofObject.length;i++)
	           {
	        	  // //System.out.println("inside for loop");
	        	  // System.out.println(listofObject[i].getName()+" "+listofObject[i].getSex()+" "+listofObject[i].getBdate()+" "+listofObject[i].getDdate()+" "+listofObject[i].getFAMS()+" "+listofObject[i].getFAMC()+" "+listofObject[i].getEmail());
	           }*/
	           
	           //User Cases
	           
	           //To display peoples birth date
	           boolean check;
	           System.out.println("Display US24:");
	           System.out.println("To display birth date");
	           System.out.println("NAME           Birth date");
	           for(int i=0;i<listofObject.length;i++)
	           {
	        	   if(!(listofObject[i].getBdate().equalsIgnoreCase("")))
	        	   System.out.println(listofObject[i].getName()+"          "+listofObject[i].getBdate());
	  	         
	           }
	           
	           
	           
//To check death date valid or invalid
	   System.out.println("To display US06:");         
	   System.out.println("Check for valid death date");
	           for(int i=0;i<listofObject.length;i++)
	           {
	        	   if(!(listofObject[i].getDdate().equalsIgnoreCase(""))){
	        	   if(!((listofObject[i].getDdate().equalsIgnoreCase("NA"))||(listofObject[i].getDdate().equalsIgnoreCase(""))))
	        	   {
	        	 String  date[]=new String[3];
	        	 date=listofObject[i].getDdate().trim().split("/");
	        	 check=checkValidDate(date[0],date[1],date[2]);
	        	   }
	        	   else check=false;
	        	 if(check)
	        		 System.out.println(listofObject[i].getDdate()+" the value for death date is valid");
	        	 else
	        		 System.out.println("Us 06 Error :"+listofObject[i].getDdate()+" the value for death date is invalid");
	        	   } 
	  	         
	           }
	           //Gender count
	           
	        /*   System.out.println("");
	           System.out.println(" Gender Count");    
	           int gender_male=0,gender_female=0;
	           for(int i=0;i<listofObject.length;i++)
	           {
	        	   if(!(listofObject[i].getSex().equalsIgnoreCase(""))){
	        	   if(listofObject[i].getSex().equalsIgnoreCase("M"))gender_male++;
	        	   if(listofObject[i].getSex().equalsIgnoreCase("F"))gender_female++;
	        	   }
	           }
System.out.println("The number of Male is :"+gender_male);
System.out.println("The number of Female is :"+gender_female);
	           
*/
//email check if valid
/*System.out.println("");
System.out.println("Check for email id");
for(int i=0;i<listofObject.length;i++)
{
	if(!(listofObject[i].getEmail().equalsIgnoreCase(""))){
	  if(listofObject[i].getEmail().contains("@") &&listofObject[i].getEmail().contains("."))
		  System.out.println(" The email value "+listofObject[i].getEmail()+" is valid");
	  else  System.out.println(" The email value "+listofObject[i].getEmail()+" is invalid");
	}
}
*/
/*
//Gender of Dead
System.out.println("");
System.out.println(" Display gender of the dead");
for(int i=0;i<listofObject.length;i++)
{
	if(!(listofObject[i].getSex().equalsIgnoreCase(""))){
	   if(!((listofObject[i].getDdate().equalsIgnoreCase("NA")||listofObject[i].getDdate().equalsIgnoreCase("")))&& listofObject[i].getSex().equalsIgnoreCase("M"))
		   System.out.println(" Dead Person "+listofObject[i].getName()+" gender is Male");
	   if(!((listofObject[i].getDdate().equalsIgnoreCase("NA")||listofObject[i].getDdate().equalsIgnoreCase("")))&& listofObject[i].getSex().equalsIgnoreCase("F"))
		   System.out.println(" Dead Person "+listofObject[i].getName()+" gender is Female");
	}
}
*/
//Check if more than 1 spouse
/*System.out.println("");
System.out.println("Check for people married more than 1s");
for(int i=0;i<listofObject.length;i++)
{
	  
	   if(listofObject[i].getNoOfspouse()>1)
		   System.out.println(listofObject[i].getName()+" has more than 1 spouse");
}*/
  }
	        catch(Exception e) {
	        	//System.out.println("Exception:" + e.getMessage());
	        	e.printStackTrace();
	        }
	
	
}
  public static boolean checkValidDate(String day,String month,String year)
  {
	  //System.out.println(" values ********"+day+" "+month+" "+year);
	  if(!(
			  (day.equalsIgnoreCase("NA")||(day.equalsIgnoreCase("")))
			  ||(month.equalsIgnoreCase("NA"))||(month.equalsIgnoreCase(""))
					  ||(year.equalsIgnoreCase("NA")||(year.equalsIgnoreCase("")))
			  ))
	  try {
		  
	      int date = Integer.parseInt(day);
	      int yr = Integer.parseInt(year);
	      if(!(month.equalsIgnoreCase("JAN")||month.equalsIgnoreCase("FEB")||month.equalsIgnoreCase("MAR")
	    		  ||month.equalsIgnoreCase("APR")||month.equalsIgnoreCase("MAY")||month.equalsIgnoreCase("JUN")
	    		  ||month.equalsIgnoreCase("JUL")||month.equalsIgnoreCase("AUG") ||month.equalsIgnoreCase("SEP")
	    		  ||month.equalsIgnoreCase("OCT")||month.equalsIgnoreCase("NOV")||month.equalsIgnoreCase("DEC")))
	      return false;
	      if(date<1 ||date>31) return false;
	      if(yr>2015) return false;
	      return true;
	    	 
	  } catch (NumberFormatException e) {
	      //Will Throw exception!
	      //do something! anything to handle the exception.
	}
	  return true;
  }
}
