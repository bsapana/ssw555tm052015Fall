import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class MainC {
	// lists to hold data
	private static HashMap<String, family> families = new HashMap<String, family>();
	private static HashMap<String, individual> individuals = new HashMap<String, individual>();
	private static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	private static String fileName=null;

	public static void main(String[] args) throws IOException, ParseException {
		System.out.println("Enter the Input File Path and Filename: ");
		try {
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			String fileName = bufferRead.readLine();
			createNewOutputFile();
			readAndParseFile(fileName);
			printMaps();
			siblingSpacing(families);
			multiplebirthslessthan5(families);
		

		} catch (FileNotFoundException ex) {
			System.out.println("File Not Found. Please Check Path and Filename");
			main(null);
		}
	}

	static void readAndParseFile(String fileName) throws IOException {
		// valid tags
		String[] validTags = { "INDI", "NAME", "SEX", "BIRT", "DEAT", "FAMC", "FAMS", "FAM", "MARR", "HUSB", "WIFE",
				"CHIL", "DIV", "DATE", "HEAD", "TRLR", "NOTE" };
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = br.readLine();
		// loop through each line of the file
		writeToFile(
				"*************************************************LINES****************************************************");
		
		while (line != null) {
			writeToFile(line);
			String[] parts = line.split(" ");
			writeToFile("Level: " + parts[0]);
			if (Arrays.asList(validTags).contains(parts[1])) {
				writeToFile("TAG: " + parts[1]+"\n");
				line = br.readLine();
			} else {
				if (parts[0].equals("0") && Arrays.asList(validTags).contains(parts[2])) {
					writeToFile("TAG: " + parts[2] +"\n");
					// if it's an INDI record
					if (parts[2].equals("INDI")) {
						individual indi = new individual(parts[1]);
						String individualParts = br.readLine();
						do {
							String[] indParts = individualParts.split(" ");
							if (indParts[1].equals("NAME"))
								indi.setName(indParts[2] + " " + indParts[3].substring(1, indParts[3].length() - 1));
							if (indParts[1].equals("SEX"))
								indi.setSex(indParts[2]);
							if (indParts[1].equals("FAMS"))
								indi.setSpouseOf(indParts[2]);
							if (indParts[1].equals("FAMC"))
								indi.setChildOf(indParts[2]);
							if (indParts[1].equals("BIRT")) {
								individualParts = br.readLine();
								indParts = individualParts.split(" ");
								String month = getMonth(indParts[3]);
								indi.setBirth(month + "/" + indParts[2] + "/" + indParts[4]);
							}
							if (indParts[1].equals("DEAT") && indParts[2].equals("Y")) {
								individualParts = br.readLine();
								indParts = individualParts.split(" ");
								String month = getMonth(indParts[3]);
								indi.setDeath(month + "/" + indParts[2] + "/" + indParts[4]);
							}
							individualParts = br.readLine();
						} while (!individualParts.startsWith("0"));
						line = individualParts;
						individuals.put(indi.getId(), indi);
					} else if (parts[2].equals("FAM")) {
						ArrayList<String> children = new ArrayList<String>();
						family fam = new family(parts[1]);
						String familyParts = br.readLine();
						do {
							String[] indFamParts = familyParts.split(" ");
							if (indFamParts[1].equals("HUSB"))
								fam.setHusb(indFamParts[2]);
							if (indFamParts[1].equals("WIFE"))
								fam.setWife(indFamParts[2]);
							if (indFamParts[1].equals("CHIL")) {
								children.add(indFamParts[2]);
								fam.setChild(children);
							}
							if (indFamParts[1].equals("MARR")) {
								familyParts = br.readLine();
								indFamParts = familyParts.split(" ");
								String month = getMonth(indFamParts[3]);
								fam.setMarriage(month + "/" + indFamParts[2] + "/" + indFamParts[4]);
							}
							if (indFamParts[1].equals("DIV")) {
								familyParts = br.readLine();
								indFamParts = familyParts.split(" ");
								String month = getMonth(indFamParts[3]);
								fam.setDivorce(month + "/" + indFamParts[2] + "/" + indFamParts[4]);
							}
							familyParts = br.readLine();
						} while (!familyParts.startsWith("0"));
						families.put(fam.getId(), fam);
						line = familyParts;
					}
				}
				// if the tag is not valid print invalid tag
				else {
					writeToFile("TAG: INVALID\n");
					line = br.readLine();
				}
			}	
		}
		writeToFile(
				"**********************************************************************************************************\n");
		
		br.close();
	}

	public static void printMaps() throws FileNotFoundException, IOException {
		// This method just prints out the values of both hashmaps
		Map<String, individual> indMap = new TreeMap<String, individual>(individuals);
		Iterator<Map.Entry<String, individual>> indEntries = indMap.entrySet().iterator();
		writeToFile(
				"*********************************************INDIVIDUALS**************************************************");
		while (indEntries.hasNext()) {
			Map.Entry<String, individual> indEntry = indEntries.next();
			individual ind = indEntry.getValue();
			writeToFile(indEntry.getKey() + " - " + " Name: " + ind.getName() + ", Sex: " + ind.getSex() + ", DOB: "
					+ ind.getBirth() + ", DOD: " + ind.getDeath() + ", Spouse of: " + ind.getSpouseOf() + ", Child of: "
					+ ind.getChildOf());

		}
		writeToFile(
				"**********************************************************************************************************\n");

		System.out.println("\n");
		// loop through the family collection and print out
		Map<String, family> famMap = new TreeMap<String, family>(families);
		Iterator<Map.Entry<String, family>> famEntries = famMap.entrySet().iterator();
		writeToFile(
				"*********************************************FAMILIES*****************************************************");
		while (famEntries.hasNext()) {
			Map.Entry<String, family> famEntry = famEntries.next();
			family fam = famEntry.getValue();
			writeToFile(famEntry.getKey() + " - Husband: " + fam.getHusb() + ", Wife: " + fam.getWife() + ", Children: "
					+ fam.getChild() + ", Marriage Date: " + fam.getMarriage() + ", Divorce Date: " + fam.getDivorce());
		}
		writeToFile(
				"**********************************************************************************************************\n");

	}

	public static void createNewOutputFile() throws IOException{
		System.out.println("Enter Output File Path: ");
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String fp = bufferRead.readLine();
		Path filePath = Paths.get(fp);
		if (Files.exists(filePath)){
			fileName = filePath+"\\output.txt";
			try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, false)))) {
			} catch (IOException e) {
				// exception handling left as an exercise for the reader
			}
		}
		else{
			System.out.println("The Output Path You Entered Does Not Exist.  Please Try Again");
			createNewOutputFile();
		}
	}
	public static void writeToFile(String output) throws FileNotFoundException, IOException {
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)))) {
		out.println(output);
		System.out.println(output);
		} catch (IOException e) {
			// exception handling left as an exercise for the reader
		}
		
	}

	public static String getMonth(String month) {
		String numMonth = null;
		switch (month) {
		case "JAN":
			numMonth = "01";
			break;
		case "FEB":
			numMonth = "02";
			break;
		case "MAR":
			numMonth = "03";
			break;
		case "APR":
			numMonth = "04";
			break;
		case "MAY":
			numMonth = "05";
			break;
		case "JUN":
			numMonth = "06";
			break;
		case "JUL":
			numMonth = "07";
			break;
		case "AUG":
			numMonth = "08";
			break;
		case "SEP":
			numMonth = "09";
			break;
		case "OCT":
			numMonth = "10";
			break;
		case "NOV":
			numMonth = "11";
			break;
		case "DEC":
			numMonth = "12";
			break;
		}
		return numMonth;
	}

	// USER STORY METHODS

	static void siblingSpacing(HashMap<String, family> families) throws ParseException, FileNotFoundException, IOException {
		Map<String, family> famMap = new HashMap<String, family>(families);
		Iterator<Map.Entry<String, family>> famEntries = famMap.entrySet().iterator();
		Date sib1;
		Date sib2;
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		//System.out.println(calendar.get(Calendar.YEAR));
		while (famEntries.hasNext()) {
			HashMap<String, String> childMap = new HashMap<String, String>();
			Map.Entry<String, family> famEntry = famEntries.next();
			family fam = famEntry.getValue();
			System.out.println("FAMILY ID: " + fam.getId() );
			if (fam.getChild()!=null && fam.getChild().size() > 1){
				ArrayList<String> children = fam.getChild();
				for (int i=0;i<children.size();i++){
					individual ind=individuals.get(children.get(i));
					childMap.put(ind.getId(), ind.getBirth());
				}
				Iterator it = childMap.entrySet().iterator();	
			    while (it.hasNext()) {
			        Map.Entry pair = (Map.Entry)it.next();
			        sib1 = sdf.parse(pair.getValue().toString());
			        calendar1.setTime(sib1);
			        System.out.println(pair.getKey() + " = " + pair.getValue());
			        //it.remove(); // avoids a ConcurrentModificationException
			        Iterator it2 = childMap.entrySet().iterator();	
				    while (it2.hasNext()) {
				        Map.Entry pair2 = (Map.Entry)it2.next();
				        sib2 = sdf.parse(pair2.getValue().toString());
				        calendar2.setTime(sib2);
				        if ((calendar1.get(Calendar.YEAR)==calendar2.get(Calendar.YEAR)) && pair.getKey()!=pair2.getKey()){
				        	int diffMonth =  calendar2.get(Calendar.MONTH) - calendar1.get(Calendar.MONTH);
				        	int diffDay=calendar2.get(Calendar.DAY_OF_MONTH) - calendar1.get(Calendar.DAY_OF_MONTH);
				        	if ((diffMonth<8)){
							writeToFile("***************************ERROR: User Story US13: Birth dates of siblings should be more than 8 months apart or less than 2 days apart****************************\nMonths less than 8: " + pair.getKey() + " - " + pair2.getKey() + " DiffMonth: " + diffMonth );
				        	}else if (diffMonth==0 && diffDay<2){
				        		writeToFile("***************************ERROR: User Story US13: Birth dates of siblings should be more than 8 months apart or less than 2 days apart****************************\nDays greater than 2: " + pair.getKey() + " - " + pair2.getKey() + " DiffDay: " + diffDay );
				        	}else {
				        		writeToFile("***************************NO ;ERROR: User Story US13: Birth dates of siblings should be more than 8 months apart or less than 2 days apart****************************\n");
				        	}
						}
				    }
			    }
				childMap=null;
			}
		}
	}
	static void multiplebirthslessthan5(HashMap<String, family> families) throws ParseException, FileNotFoundException, IOException {
		Map<String, family> famMap = new HashMap<String, family>(families);
		Iterator<Map.Entry<String, family>> famEntries = famMap.entrySet().iterator();
		Date birthDate = null;
		
		while (famEntries.hasNext()) {
			Map.Entry<String, family> famEntry = famEntries.next();
			Map<String, individual> indMap = new HashMap<String, individual>(individuals);
			family fam = famEntry.getValue();
			try{
			if (fam.getChild() != null) {
				for (int i = 0; i < fam.getChild().size(); i++) {
					individual indi = indMap.get(fam.getChild().get(i));
					birthDate = sdf.parse(indi.getBirth());
					if (birthDate.equals(birthDate))
				if(fam.getChild().size()<5){
					writeToFile("***************************NO ERROR: User Story US14: No more than five siblings should be born at the same time****************************\n");
				}else{
					writeToFile("***************************ERROR: User Story US14: No more than five siblings should be born at the same time****************************\nFamily ID: "
												+ fam.getId() + "\nIndividual: " + indi.getId() + ": " + indi.getName() + "born at same time");
				}
			}
	}}catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}}}
