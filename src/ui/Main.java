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

	/**
	* Displays a menu to the user and allows them to interact with the system.
 	* The user can choose from several options to manage users and bibliographic products.
 	*/
 

	public void menu(){
		boolean execute=true;
		int option;
		while(execute){
			System.out.println("Type a number,");
			System.out.println("1 to register a user");
			System.out.println("2 to register a bibliographic product");
			System.out.println("3 to modify a bibliographic product");
			System.out.println("4 to delete a bibliographic product");
			System.out.println("5 to create biliographic and user objects");
			System.out.println("6 to buy a book or magazine");
			System.out.println("7 to cancel a magazin suscription");
			System.out.println("8 to see your library");
			System.out.println("9 to show the total number of read pages in books and magazines");
			System.out.println("10 show which are the genres and categories with the greater number of read pages");
			System.out.println("11 to show a top 5 of the most read books and magazines");
			System.out.println("12 to show the number of sold books and the total sales value for each book genre");
			System.out.println("13 to show the number of active suscriptions and total value paid in suscriptions");
			System.out.println("14 to exit the program");
			option=validateIntegerInput();
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
				case 7:
					deleteProductToUser();
					break;
				case 8:
					showUserLibrary();
					break;
				case 9:
					showReadPages();
					break;
				case 10:
					showGenreAndCategoryWithTheGreaterNumberOfReadPages();
					break;
				case 11:
					showTop5Products();
					break;
				case 12:
					showBookSolds();
					break;
				case 13:
					showMagazineSuscriptions();
					break;
				case 14:
					execute=false;
					break;

				default:
					System.out.println("Invalid option");
					break;	
			}
			reader.nextLine();
		}


	}
	/**
	* Prompts the user to enter their name, ID, and user type.
 	* Adds a new user to the system with the provided information.
 	*/


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

	/**
	* Prompts the user to enter information about a new bibliographic product,
 	* including its name, number of pages, value, publication date, and URL.
 	* The user can choose to add either a book or a magazine.
 	*/


	public void addBibliographicProduct(){
		reader.nextLine();
		String name="";
		int numPages=-1;
		double value=-1;
		GregorianCalendar publicationDate=null;
		String url="";
		System.out.println("Type the name of the product");
		name=reader.next();
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
					//reader.nextLine();
					System.out.println("Type the  review of the book");
					review=reader.next();
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

	/**
	* Prompts the user to enter the ID of the product they want to modify.
 	* Validates that a product with the given ID exists and, if so,
 	* calls the `requestFieldToModify()` method to allow the user to modify the product.
 	*/



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

	/**
 	* Prompts the user to choose which field of the product they want to modify.
    * Calls the `modifyProduct()` method to perform the modification.
    *
    * @param productId The ID of the product to modify.
    * @param productTypeFlag An integer indicating the type of product (book or magazine).
    */


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

	}

  	/**
 	* Prompts the user to enter the new value for the field they want to modify.
 	* Calls the `controller.modifyProduct()` method to perform the modification.
 	*
 	* @param productId The ID of the product to modify.
 	* @param productTypeFlag An integer indicating the type of product (book or magazine).
 	* @param field An integer indicating which field of the product to modify.
 	* @param dataType An integer indicating the data type of the new value.
 	*/
         

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
					newStrValue=reader.next();

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
		System.out.println("The change was made succesfully");


	}
	/**
 	* Prompts the user to enter the ID of the product they want to delete.
 	* Calls the `controller.deleteProduct()` method to perform the deletion.
 	*/
	public void deleteProduct(){
		String msg="";
		String id="";
		System.out.println("Type the id of the product that you want to delete");
		id=reader.next();
		msg=controller.deleteProduct(id);
		System.out.println(msg);
	}
	/**
 	* Prompts the user to enter the number of objects to create.
 	* Calls the `controller.initObjects()` method to create the specified number of books and magazines.
 	*/


	public void createObjects(){
		int num=0;
		System.out.println("Type the number of objects that will be created");
		num=validatePositiveInt();
		controller.initObjects(num);
		
		System.out.println(num+ " books and "+num+ " magazines were registered ");
		System.out.println("These products were added to the library of the premium user with id 2.");
		System.out.println("If the number of product is not at the limit, the regular user with id 1 also has the same products in his library");
	}
 
	/**
 	* Adds a product to a user by requesting user and product IDs and calling the controller's `addProductToUser` method.
 	*/

	public void addProductToUser(){
		String[] userAndProductId=requestUserAndProductId();
		if(!userAndProductId[0].equals("")){
			String msg=controller.addProductToUser(userAndProductId[0], userAndProductId[1]);
			System.out.println(msg);
		}

	}
	/**
 	* Deletes a product from a user by requesting user and product IDs and calling the controller's `deleteProductToUser` method.
 	*/


	public void deleteProductToUser(){
		String [] userAndProductId=requestUserAndProductId();
		if(!userAndProductId[0].equals("")){
			String msg=controller.deleteProductToUser(userAndProductId[0],userAndProductId[1]);
			System.out.println(msg);
		}
	}
	/**
 	* This method displays the user's library and allows them to navigate through its pages.
 	* The user can choose to go to the previous or next page, exit the library view,
 	* start a reading session by typing the coordinates of a product or by typing its id.
 	*/

	public void showUserLibrary(){
		String userId=requestUserId();
		if(!userId.equals("")){
			String[][][] library=controller.getLibrary(userId);
			int numPages=library.length;
			int currentPage=0;
			String option="";
			boolean execute=true;
			while(execute){

				showMatrix(library[currentPage]);
				System.out.println("A to go to the previous page");
				System.out.println("S to go the the next page");
				System.out.println("B to exit");
				System.out.println("C to type the coordinates of a product and start a reading session");
				System.out.println("V to type the id of a product and start a reading session");
				System.out.println("current page: " +(currentPage+1));
				option=reader.next();
				if(option.equalsIgnoreCase("A")){
					if(currentPage>0){
						currentPage--;
					}
				}
				if(option.equalsIgnoreCase("S")){
					if(currentPage<numPages-1){
						currentPage++;
					}

				}
				if(option.equalsIgnoreCase("B")){
					execute=false;
				}

				if( !(option.equalsIgnoreCase("B")||option.equalsIgnoreCase("A") ||option.equalsIgnoreCase("S") ||option.equalsIgnoreCase("C")||option.equalsIgnoreCase("V"))){
					System.out.println("Invalid Option");
				}
				if(option.equalsIgnoreCase("C")){
					int[] coordinates=validateCoordinates();
					int x=coordinates[0];
					int y=coordinates[1];
					if(!library[currentPage][y][x].equals("")){
						startReadingSession(userId, library[currentPage][y][x]);
					}else{
						System.out.println("These coordinates are empty");
					}
				}
				if(option.equalsIgnoreCase("V")){
					System.out.println("Type the product id");
					String productId=reader.next();
					if(controller.userHasProduct(userId,productId)){
						startReadingSession(userId,productId);
					}else{
						System.out.println("There is no any product with this id in your library");
					}
				}
				
			}


		}
	}
	/**
 	* Starts a reading session for a specified user and bibliographic product.
 	*
 	* @param userId    the ID of the user starting the reading session
 	* @param productId the ID of the bibliographic product to read
 	*/


	public void startReadingSession(String userId, String productId){
		String option="";
		boolean execute=true;
		Object []readingSessionInfo=controller.getReadingSessionInfo(productId);
		int numPages= (int) readingSessionInfo[1];
		String name=(String)readingSessionInfo[0];
		int currentPage=1;
		while(execute){
			boolean ad=controller.readingSessionAd(userId,productId,currentPage);
			if(ad){
				System.out.println("");
				System.out.println(controller.getAd());
				System.out.println("");
			}
			System.out.println("Reading session in progress\n");
			System.out.println("Reading: "+ name);
			System.out.println("Page: "+currentPage);
			System.out.println("A to go to the previous page");
			System.out.println("S to go the the next page");
			System.out.println("B to exit");
			option=reader.next();
			
			if(option.equalsIgnoreCase("S")){
				if(currentPage<numPages){
					currentPage++;
				}
			}
			if(option.equalsIgnoreCase("A")){
				if(currentPage>1){
					currentPage--;
				}
			}
			if(option.equalsIgnoreCase("B")){
				execute=false;
			}
			if( !(option.equalsIgnoreCase("B")||option.equalsIgnoreCase("A") ||option.equalsIgnoreCase("S") )){
				System.out.println("Invalid Option");
			}
			

		}
		
	}
	/**
 	* Displays a 2D array in a matrix format.
 	*
 	* @param matrix the 2D array to display
 	*/

	public void showMatrix(String matrix[][]){
		int row=matrix.length;
		int col=matrix[0].length;
		System.out.println("       0    1    2    3    4");

		for (int i=0;i<row ;i++ ) {
			System.out.print("  "+i+"  ");
			for (int j=0;j<col ;j++ ) {
				System.out.print(" "+matrix[i][j]+" ");

			}
			System.out.print("\n\n");
		}
	}
	/**
 	* This method displays the number of pages read in books and magazines.
 	*/

	public void showReadPages(){
		String msg=controller.getReadPagesInBooksAndMagazines();
		System.out.println(msg);
	}
	/**
 	* This method displays the genre and category with the greatest number of read pages.
 	*/
	public void showGenreAndCategoryWithTheGreaterNumberOfReadPages(){
		String msg=controller.getGenreAndCategoryWithTheGreaterNumberOfReadPages();
		System.out.println(msg);
	}
	/**
 	* This method displays the top 5 products with the greatest number of read pages.
 	*/
	public void showTop5Products(){
		String msg=controller.getTop5();
		System.out.println(msg);
	}
	/**
 	* This method displays the number of books sold by genre.
 	*/
	public void showBookSolds(){
		String msg=controller.getSoldsByGenre();
		System.out.println(msg);
	}
	/**
 	* This method displays the number of magazine subscriptions by category.
 	*/
	public void showMagazineSuscriptions(){
		String msg=controller.getSuscriptionsByCategory();
		System.out.println(msg);
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
	/**
 	* This method validates that the user's input is a non-negative integer.
 	* If the input is not valid, the user is prompted to enter a new value until a valid value is entered.
 	* @return The validated non-negative integer value entered by the user.
 	*/
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
	/**
 	* Prompts the user to enter a string and validates it against an array of possible valid options. Returns the validated string.
 	* This function puts the user in a loop until the input is valid.
 	* @param options an array of possible valid strings
 	* @return the validated string entered by the user
 	*/



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
	/**
 	* Prompts the user to enter their user ID and validates it using the controller's `validateUserId` method. Returns the validated user ID or an empty string
 	*
 	* @return the validated user ID entered by the user, or an empty string
 	*/


	public String requestUserId(){
		String id="";
		System.out.println("Type your id");
		id=reader.next();
		if(controller.validateUserId(id)){
			id="";
			System.out.println("There is no any user with this id");
		}
		return id;
	}
	/**
 	* Prompts the user to enter a product ID and validates it using the controller's `validateProductId` method. Returns the validated product ID or an empty string if validation fails.
 	*
 	* @return the validated product ID entered by the user or an empty string if validation fails
 	*/


	public String requestProductId(){
		String id="";
		System.out.println("Type the product id");
		id=reader.next();
		if(!controller.validateProductId(id)){
			id="";
			System.out.println("There is no any product with this id");
		}
		return id;
	}
	/**
 	* Requests and validates user and product IDs using the `requestUserId` and `requestProductId` methods. Returns an array containing the validated user and product IDs or empty strings if validation fails.
 	*
 	* @return an array containing the validated user and product IDs or empty strings if validation fails
 	*/


	public String[] requestUserAndProductId(){
		String [] userAndProductId=new String[2];
		String userId="";
		String productId="";
		userId=requestUserId(); //If the UserId is invalid, this will be an empty String
		if(!userId.equals("")){ //if the Userid is valid, this will be a non empty String;
			productId=requestProductId(); //in that case, the user will be type the product id
		}
		
		if( !productId.equals("")){ //if the productId is valid, the array will contain both ids
			userAndProductId[0]=userId;
			userAndProductId[1]=productId;
		}else{                      //otherwiese the array will contain empty strings;
			userAndProductId[0]="";
			userAndProductId[1]="";
		}
		return userAndProductId;
	}
	/**
 	* Prompts the user to enter X and Y coordinates and validates them to be within the range of 0 to 4 using the `validateNonNegativeInt` method. Returns an array containing the validated coordinates. 
 	* This function puts the user in loop.
	*
 	* @return an array containing the validated X and Y coordinates
 	*/


	public int[] validateCoordinates(){
		int x=-1;
		int y=-1;
		while( !(0<=x &&x<=4)){
			System.out.println("Type the coordinate X");
			x=validateNonNegativeInt();
			if( !(0<=x &&x<=4) ){
				System.out.println("Invalid value");
			}
		}
		while( !(0<=y &&y<=4) ) {
			System.out.println("Type the coordinate Y");
			y=validateNonNegativeInt();
			if( !(0<=y &&y<=4) ){
				System.out.println("Invalid value");
			}
		}
		int[] coordinates={x,y};
		return coordinates;

	}

	public int squareRoot (int x){
		return x*x;
	}






}