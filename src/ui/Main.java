package ui;
import java.util.Scanner;
import model.Controller;
import java.util.GregorianCalendar;
public class Main{ 
	private Scanner reader;
	private Controller controller;
	Main(){
		reader=new Scanner(System.in);
		controller=new Controller();
	}
	public static void main(String args[]){
		Main exe=new Main();
		exe.menu();


	}
	public void menu(){
		boolean execute=true;
		int option;
		while(execute){
			System.out.println("Type a number");
			System.out.println("1 to register");
			System.out.println("2 to register a bibliographic product");
			option=reader.nextInt();
			switch (option) {
				case 1:
					addUser();
					break;
				case 2:
					addBibliographicProduct();
					break;
				default:
					execute=false;
			}
			reader.nextLine();
		}


	}
	public void addUser(){
		String name="";
		String id="";
		String msg="";
		System.out.println("Type your name");
		name=reader.next();
		System.out.println("Type your id");
		id=reader.next();
		msg=controller.addUser(name,id);
		System.out.println(msg);

	}
	public void addBibliographicProduct(){
		reader.nextLine();
		String name="";
		int numPages=-1;
		double value=-1;
		GregorianCalendar publicationDate=null;
		String url="";
		System.out.println("Type the name of the product");
		name=reader.nextLine();
		System.out.println("Type the number of pages");
		numPages=validatePositiveInt();
		System.out.println("Type the value of the book or the value of the suscription");
		value=validateDoubleInput();
		System.out.println("Type the publication date of the product");
		publicationDate=requestDate();
		System.out.println("Type the url");
		url=reader.next();

		
		boolean isFinished=false;
		int option;
		while(!isFinished){
			System.out.println("Type the product type");
			System.out.println("1 to add a book");
			System.out.println("2 to add a magazine");
			option=validatePositiveInt();
			switch (option) {
				case 1:
					String review="";
					String genre="";
					String msg="";
					System.out.println("Type the  review of the book");
					review=reader.nextLine();
					reader.nextLine();
					genre=validateStringGivenAnArrayOfPossibleValidStrings(controller.getBookGenresInStr());
					msg=controller.addBook(name,numPages,value,publicationDate,url,genre,review);
					System.out.println(msg);
					isFinished=true;
					break;
				case 2:
					String publicationFrequency="";
					String category="";
					System.out.println("Type the publication frequency of the magazine");
					publicationFrequency=reader.next();
					category=validateStringGivenAnArrayOfPossibleValidStrings(controller.getMagazineCategoriesInStr());
					msg=controller.addMagazine(name,numPages,value,publicationDate,url,category,publicationFrequency);
					System.out.println(msg);
					isFinished=true;
					break;
				default:
					System.out.println("Invalid Option");
			}
		}




	}

	/*||||||||||||||||||||||||||||VALIDATION FUNCTIONS||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||*/
	/** Reads the input of the user. If the user typed a non integer value, returns -1. Otherwise, returns the input.
    * 
    * @return The value that was registered by the user, or -1 if the user didn't type an integer.
    */

	public int validateIntegerInput(){
		int num = -1;
		if(reader.hasNextInt()){
			num = reader.nextInt();
		}else{
			reader.next();
		}
		return num;
	}
	/** Put the user in a loop until he types a positive integer
    * Puts the user in a loop until he types a positive integer
    * 
    * @return An positive double
    */
	public int validatePositiveInt(){
		int value=-1;
		while(value<=0){
			value=validateIntegerInput();
			if(value<=0){
				System.out.println("Invalid value");
			}
		}
		return value;
	}
    /** Reads the input of the user. If the user typed a non double value, returns -1.
    * 
    * @return The value that was registered by the user, or -1 if the user didn't type a double.
    */
	public double validateDoubleInput(){
		double num=-1.0;
		if(reader.hasNextDouble()){
			num=reader.nextDouble();
		}else{
			reader.next();
		}
		return num;
	}
    /** Put the user in a loop until he types a positive double
    * 
    * @return A positive double
    */
	public double validatePositiveDouble(){
		double value=-1.0;
		while(value<=0){
			value=validateDoubleInput();
			if(value<=0){
				System.out.println("Invalid value");
			}
		}
		return value;
	}
	/** Puts the user in a loop until he types a valid date.
	* @return Return a valid GregorianCalendar date. A valid day is an integer from [1 to 31]. A valid month is an integer from [1 to 12]. A valid year is an integer greater than 0.
	*/
	public GregorianCalendar requestDate(){
		int day=-1,month=-1,year=-1;
		while(! (1<=day&& day<=31  ) ){
			System.out.println("Type the day");
			day=validateIntegerInput();
			if( !(1<=day&& day<=31)  ){
				System.out.println("Invalid value");
			}
		}
		
		while(! (1<=month&&month<=12  ) ){
			
			System.out.println("Type the month");
			month=validateIntegerInput();
			if(!  (1<=month&&month<=12)  ){
				System.out.println("Invalid value");
			}
		}
		
		while(year<=0){
			System.out.println("Type the year");
			year=validateIntegerInput();
			if(year<=0){
				System.out.println("Invalid year");
			}
		}
		GregorianCalendar date=new GregorianCalendar(year,month-1,day);
		return date;
	}
	public String validateStringGivenAnArrayOfPossibleValidStrings(String[]options){

		boolean isValid=false;
		String option="";
		while(!isValid){
			System.out.println("Type an string. These are the possible genres/categories of the product");
			for(int i=0;i<options.length;i++){
				System.out.println(options[i]);
			}
			option=reader.next();
			for (int i=0;i<options.length&&!isValid ;i++ ) {
				if(options[i].equalsIgnoreCase(option)){
					isValid=true;
				}
			}
			if(!isValid){
				System.out.println("These is not a valid genre/category");
			}
		}
		return option;
		
	}



}