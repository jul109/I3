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
			System.out.println("3 to modify a modify a bibliographic product");
			System.out.println("4 to delete a bibliographic product");
			System.out.println("5 to create biliographic and user objects");
			System.out.println("6 to buy a book or magazine");
			option=reader.nextInt();
			switch (option) {
				case 1:
					addUser();
					break;
				case 2:
					addBibliographicProduct();
					break;
				case 3:
					validateProductToModify(); //if the id is valid, this functions calls modifyProduct();
					break;
				case 4:
					deleteProduct();
					break;
				case 5:
					createObjects();
					break;
				case 6:
					addProductToUser();
					break;
				default:
					System.out.println(controller.getProductsInfo());
			}
			reader.nextLine();
		}


	}
	public void addUser(){
		String name="";
		String id="";
		String userType="";
		String msg="";
		System.out.println("Type your name");
		name=reader.next();
		System.out.println("Type your id");
		id=reader.next();
		System.out.println("Type your user type");
		userType=validateStringGivenAnArrayOfPossibleValidStrings(controller.getUserTypesInStr());

		msg=controller.addUser(name,id,userType);
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
		value=validateNonNegativeDouble();
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
					reader.nextLine();
					System.out.println("Type the  review of the book");
					review=reader.nextLine();
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
	public int validateNonNegativeInt(){
		int value=-1;
		while(value<0){
			value=validateIntegerInput();
			if(value<0){
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
    /** Puts the user in a loop until he types a non negative double
    * 
    * @return A non negative double
    */
	public double validateNonNegativeDouble(){
		double value=-1.0;
		while(value<0){
			value=validateDoubleInput();
			if(value<0){
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
			System.out.println("Type an string. These are the possible options");
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

	public void requestFieldToModify(String productId, int productTypeFlag){

		int option=-1;
		while(  !(1<=option &&option<=9)){
			System.out.println("Type an integer according to the field that you want to modify");
			System.out.println("1. name");
			System.out.println("2. url");
			System.out.println("3. genre (book) /category (magazine)");
			System.out.println("4. review (book)/publicationFrequency (magazine)");
			System.out.println("5. number of pages");
			System.out.println("6. number of read pages");
			System.out.println("7. number of solds");
			System.out.println("8. price (book) / suscription cost (magazine)");
			System.out.println("9 publication date");
			option=validatePositiveInt();
			if(  !(1<=option &&option<=9) ){
				System.out.println("This is not a possible option");
			}
		}
		int dataType=0;
		if(1<=option && option<=4){
			dataType=1; //String
		}
		if(5<=option && option<=7){
			dataType=2; //entero
		}
		if(option==8){
			dataType=3; //double
		}
		if(option==9){
			dataType=4; //gregorianCalendar
		}
		modifyProduct(productId, productTypeFlag, option, dataType);





	}//¿Como las personas adquieren los productos, con el id;
	//Lo de la referencia sirve?
	//Si se elimina un producto, se le elimina tambien a las personas personas que lo compraron?
	//si se modifica el numero de paginas de un libro, se modifica tambien ese arreglo de booleanos, la cuenta regresa a 
	//a 0 o que

	public void validateProductToModify(){
		String id="";
		System.out.println("Type the id of the product");
		id=reader.next();
		int productTypeFlag= controller.getProductTypeFlag(id);
		if(productTypeFlag==0){
			System.out.println("There is no any product with this id");
		}else{
			requestFieldToModify(id, productTypeFlag);
		}
	}
	public void addProductToUser(){
		boolean val1=false; //
		boolean val2=false;
		String userId="";
		String productId="";
		String msg="";
		System.out.println("Type your id");
		userId=reader.next();
		if(! (controller.validateUserId(userId)) ){
			val1=true;
		}else{
			System.out.println("There is no any user with this id");
		}
		if(val1){
			System.out.println("Type the id of the product");
			productId=reader.next();
			if(controller.validateProductId(productId)){
				msg=controller.addProductToUser(userId,productId);
				System.out.println(msg);
			}else{
				System.out.println("There is no any product with this id");
			}

		}

	}

	public void modifyProduct(String productId, int productTypeFlag, int field, int dataType){
		System.out.println("Type the new value of the field");
		switch (dataType) {
			case 1: //String
				String newStrValue="";
				if(field==3&&productTypeFlag==1){ //Book genre
					newStrValue=validateStringGivenAnArrayOfPossibleValidStrings(controller.getBookGenresInStr());
				}
				if(field==3&&productTypeFlag==2){ //Magazine Category
					newStrValue=validateStringGivenAnArrayOfPossibleValidStrings(controller.getMagazineCategoriesInStr());
				}
				if(field==1|| (field==4&& productTypeFlag==1)){ //product name|| book review
					//reader.nextLine();
					newStrValue=reader.nextLine();

				}
				if(field==2){ //url
					newStrValue=reader.next();
				}
				controller.modifyProduct(productId,field,newStrValue);
				break;
			case 2: //integer
				int newIntValue=-1;
				if(field==5){// num pages
					newIntValue=validatePositiveInt(); 
				}else{ //read Pages/solds
					newIntValue=validateNonNegativeInt(); 
				}
				controller.modifyProduct(productId,field,newIntValue);

				break;
			case 3:
				double newDoubleValue=-1; //price ||suscription
				newDoubleValue=validateNonNegativeDouble();
				controller.modifyProduct(productId,field,newDoubleValue);
				break;
			case 4:
				GregorianCalendar newGregorianCalendarValue= requestDate();
				controller.modifyProduct(productId,field,newGregorianCalendarValue);
				break;

		}


	}
	public void deleteProduct(){
		String msg="";
		String id="";
		System.out.println("Type the id of the product that you want to delete");
		id=reader.next();
		msg=controller.deleteProduct(id);
		System.out.println(msg);
	}
	public void createObjects(){
		int num;
		System.out.println("Type the number of objects that will be created");
		num=validatePositiveInt();
		controller.initProducts(num);
		controller.initUsers(num);
		System.out.println("The objects have been created succesfully");
	}




}