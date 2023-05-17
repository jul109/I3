package model;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Collections;
public class Controller{
	private ArrayList<User> users;
	private ArrayList<BibliographicProduct> products;
	private IdGenerator idGenerator;
	private AdGenerator adGenerator;
	/**
 	* Initializes a new Controller object with empty lists of users and products 
 	*and new instances of IdGenerator and AdGenerator.
 	*/

	public Controller(){
		users=new ArrayList<User>();
		products=new ArrayList<BibliographicProduct>();
		idGenerator=new IdGenerator();
		adGenerator=new AdGenerator();
	}
	/**
 	* Searches for a user with the given user ID in the list of users. Returns the found User object or null if not found.
 	*
 	* @param userId the user ID to search for
 	* @return the found User object or null if not found
 	*/

	public User searchUserById(String userId){
		boolean isFound=false;
		User userFounded=null;
		for (int i=0;i<users.size() &&!isFound;i++ ) {
			if(users.get(i).getId().equals(userId)){
				isFound=true;
				userFounded=users.get(i);
			}
		}
		return userFounded;
	}
	/**
 	* Validates a given user ID by checking if it exists in the list of users. 
 	*Returns true if the user ID is not found and false if it is found.
 	*
 	* @param userId the user ID to validate
 	* @return true if the user ID is not found and false if it is found
 	*/

	public boolean validateUserId(String userId){
		User user=searchUserById(userId);
		boolean isValid=true;
		if(user!=null){
			isValid=false;
		}
		return isValid;
	}
	/**
 	* Validates a given product ID by checking if it exists in the list of products using the `searchProductById` method. Returns true if the product ID is found and false if it is not found.
 	*
 	* @param productId the product ID to validate
 	* @return true if the product ID is found and false if it is not found
 	*/


	public boolean validateProductId(String productId){
		BibliographicProduct product=searchProductById(productId);
		boolean isValid=true;
		if(product==null){
			isValid=false;
		}
		return isValid;
	}
	/**
 	* Adds a new user with the given name, user ID, and user type to the list of users if the user ID is valid. Returns a message indicating success or failure.
 	*
 	* @param name the name of the new user
 	* @param userId the user ID of the new user
 	* @param userTypeInStr the user type of the new user as a string
 	* @return a message indicating success or failure
 	*/

	public String addUser(String name, String userId, String userTypeInStr){
		boolean isValid=validateUserId(userId);
		String msg="";
		UserType userType=UserType.valueOf(userTypeInStr.toUpperCase());
		if(isValid){
			users.add(new User(name,userId,userType));
			msg="You have registered succesfully";
  		}else{
  			msg="This id was used by another user before. It was not possible to register you";
  		}	
  		return msg;
	}

	/**
 	* Adds a new book with the given parameters to the list of products if a new ID can be generated. Returns a message indicating success or failure.
 	*
 	* @param name the name of the book
 	* @param numPages the number of pages in the book
 	* @param value the value of the book
 	* @param publicationDate the publication date of the book
 	* @param url the URL of the book
 	* @param genreInStr the genre of the book as a string
 	* @param review a review of the book
 	* @return a message indicating success or failure
 	*/


	public String addBook(String name, int numPages,double value, GregorianCalendar publicationDate, String url, String genreInStr,String review){
		String msg="";
		BookGenre genre= BookGenre.valueOf(genreInStr.toUpperCase());
		String id=idGenerator.generateHexadecimalId();
		if(id!=""){
			products.add(new Book(id,name,numPages, value, publicationDate, url, genre, review));
			msg="The product was added succesfully";
		}else{
			msg="The number of books is at limit. It was not possible to add tis product";
		}
		return msg;

	}

	/**
 	* Adds a new magazine with the given parameters to the list of products if a new ID can be generated. Returns a message indicating success or failure.
 	*
 	* @param name the name of the magazine
 	* @param numPages the number of pages in the magazine
 	* @param value the value of the magazine
 	* @param publicationDate the publication date of the magazine
 	* @param url the URL of the magazine
 	* @param categoryInStr the category of the magazine as a string
 	* @param publicationFrequency the publication frequency of the magazine
 	* @return a message indicating success or failure
 	*/

	public String addMagazine(String name, int numPages,double value, GregorianCalendar publicationDate, String url,String categoryInStr, String publicationFrequency){
		String msg="";
		MagazineCategory category= MagazineCategory.valueOf(categoryInStr.toUpperCase());
		String id=idGenerator.generateAlphanumericId();
		if(id!=""){
			products.add(new Magazine(id,name,numPages, value, publicationDate, url, category, publicationFrequency));
			msg="The product was added sucessfully";
		}else{
			msg="The number of magazines is at limit. It was not possible to add this product";
		}
		return msg;
	}

	/**
 	* Adds a product to a user's list of products.
 	*
 	* @param userId the ID of the user
 	* @param productId the ID of the product to add
 	* @return a message indicating whether the product was successfully added
 	*/

	public String addProductToUser(String userId, String productId){
		BibliographicProduct product=searchProductById(productId);
		User user=searchUserById(userId);
		String msg="";
		msg=user.addProduct(product);
		return msg;
	}

	/**
 	* Returns an array of strings representing the names of all book genres.
 	*
 	* @return an array of strings representing the names of all book genres
 	*/
	public String[] getBookGenresInStr(){
		BookGenre genres[]=BookGenre.values();
		String genresInStr[]=new String[genres.length];
		for (int i=0;i<genres.length ;i++ ) {
			genresInStr[i]=genres[i].name();
		}
		return genresInStr;
	}
	/**
 	* Returns an array of strings representing the names of all magazine categories.
 	*
 	* @return an array of strings representing the names of all magazine categories
 	*/
	public String[] getMagazineCategoriesInStr(){
		MagazineCategory categories[]=MagazineCategory.values();
		String categoriesInStr[]=new String[categories.length];
		for (int i=0;i<categories.length ;i++ ) {
			categoriesInStr[i]=categories[i].name();
		}
		return categoriesInStr;
	}
	/**
 	* Searches for a product by its ID.
 	*
 	* @param id the ID of the product to search for
 	* @return the product with the specified ID, or null if no such product is found
 	*/

	public BibliographicProduct searchProductById(String id){
		BibliographicProduct foundedProduct=null;
		for (int i=0;i<products.size() ;i++ ) {
			if( products.get(i).getId().equals(id)) {
				foundedProduct=products.get(i);
			}
		}
		return foundedProduct;
	}
	/**
 	* Deletes a product from a user's list of products.
 	* The product must be a magazine.
 	* @param userId the ID of the user
 	* @param productId the ID of the product to delete
 	* @return a message indicating whether the product was successfully deleted
 	*/

	public String deleteProductToUser(String userId, String productId){
		String msg="";
		User user=searchUserById(userId);
		msg=user.deleteProduct(productId);
		return msg;
	}
	/**
 	* Returns an integer indicating the type of a product.
 	*
 	* @param id the ID of the product
 	* @return 0 if the product is not found, 1 if the product is a book, 2 if the product is a magazine
 	*/
	public int getProductTypeFlag(String id){
		int isValid=0;
		BibliographicProduct product=searchProductById(id);
		if(product!=null){
			if(product instanceof Book){
				isValid=1;
			}
			if(product instanceof Magazine){
				isValid=2;
			}
		}
		return isValid;
	}
	public String getProductsInfo(){
		String msg="";
		for(int i=0;i<products.size();i++){
			msg+=products.get(i);
			msg+="\n||||||||||||||||||";
		}
		return msg;
	}
 	
 	/**
 	* Returns an array of strings representing the names of all user types.
 	*
 	* @return an array of strings representing the names of all user types
 	*/

	public String[] getUserTypesInStr(){
		String[] types={"PREMIUM", "REGULAR"};
		return types;
	}
	/**
 	* Modifies a product by updating one of its fields.
 	*
 	* @param productId the ID of the product to modify
 	* @param field the field to update (1 for name, 2 for URL, 3 for genre/category, 4 for review/publication frequency)
 	* @param newStatus the new value to set for the specified field
 	*/
	public void modifyProduct(String productId, int field, String newStatus){
		BibliographicProduct product=searchProductById(productId);
		switch (field) { //1-4
			case 1: //name
				product.setName(newStatus);
				break;
			
			case 2: //url
				product.setUrl(newStatus);
				break;
			
			case 3: // genre|| category
				if(product instanceof Book){
					BookGenre genre= BookGenre.valueOf(newStatus.toUpperCase());
					( (Book ) product).setGenre(genre );
				}
				if(product instanceof Magazine){
					MagazineCategory category=MagazineCategory.valueOf (newStatus.toUpperCase());
					((Magazine )product).setCategory(category);
				}
				break;
			
			case 4: //review ||publicationfrequency
				if(product instanceof Book){
						((Book) product).setReview(newStatus);
				}
				if(product instanceof Magazine){
					((Magazine)product).setPublicationFrequency(newStatus);
				}
				break;				
		}
	}
	/**
 	* Modifies a product by updating one of its fields.
 	*
 	* @param productId the ID of the product to modify
 	* @param field the field to update (5 for number of pages, 6 for number of read pages, 7 for number of solds)
 	* @param newStatus the new value to set for the specified field
 	*/

	public void modifyProduct(String productId, int field, int newStatus){
		BibliographicProduct product=searchProductById(productId);
		switch (field) { //5-7
			case 5: //NumPages
				product.setNumPages(newStatus);
				break;
			case 6: //readPages
				product.setReadPages(newStatus);
				break;
			case 7: //Solds
				product.setSolds(newStatus);
				break;
				
		}
	}
	/**
 	* Modifies a product by updating one of its fields.
 	*
 	* @param productId the ID of the product to modify
 	* @param field the field to update (8 for value)
 	* @param newStatus the new value to set for the specified field
 	*/
	public void modifyProduct(String productId, int field, double newStatus ){
		BibliographicProduct product=searchProductById(productId);
		switch (field) { //8
			case 8:
				product.setValue(newStatus);
				break;
		}
	}
	/**
 	* Modifies a product by updating one of its fields.
 	*
 	* @param productId the ID of the product to modify
 	* @param field the field to update (9 for publication date)
 	* @param newStatus the new value to set for the specified field
 	*/
	public void modifyProduct(String productId, int field, GregorianCalendar newStatus){
		BibliographicProduct product=searchProductById(productId);
		switch (field) { //9
			case 9: 
				product.setPublicationDate(newStatus);
				break;
		}

	}
	/**
 	* Initializes objects by creating a specified number products(books/magazines). It also register a regular user
 	* with id 1, and a premium user with id 2
 	* 
 	* @param num the number of products to create
 	*/

	public void initObjects(int num){
		initUsers();
		User user1=searchUserById("1");
		User user2=searchUserById("2");
		for (int i=0;i<num ;i++ ) {
			String id= idGenerator.generateAlphanumericId();
			String name="name";
			int numPages=10;
			double value=5.5;
			GregorianCalendar publicationDate=new GregorianCalendar();
			String url="url";
			MagazineCategory category= MagazineCategory.valueOf("VARIETY");
			String publicationFrequency="daily";
			BibliographicProduct product=new Magazine(id,name,numPages, value, publicationDate, url, category, publicationFrequency);
			products.add(product);
			user1.addProduct(product);
			user2.addProduct(product);

		}
		for(int i=0;i< num;i++){
			String id=idGenerator.generateHexadecimalId();
			String name="nombre";
			int numPages=10;
			double value=5.5;
			GregorianCalendar publicationDate=new GregorianCalendar();
			String url="url";
			BookGenre genre= BookGenre.valueOf("FANTASY");
			String review="awesome !";
			BibliographicProduct product=new Book(id,name,numPages, value, publicationDate, url, genre, review);
			products.add(product);
			user1.addProduct(product);
			user2.addProduct(product);
		}
		
	}
	/**
 	* Initializes users by creating two users with id=1 and id=2 if they do not already exist.
 	*/
	public void initUsers(){
		if(searchUserById("1")==null){
			users.add(new User("Pedroregular","1",UserType.valueOf("REGULAR")));
		}
		if(searchUserById("2")==null){
			users.add(new User("pedro premium","2",UserType.valueOf("PREMIUM")));
		}
	}
	/**
 	* Deletes a product by its ID.
 	*
 	* @param id the ID of the product to delete
 	* @return a message indicating whether the product was successfully deleted
 	*/

	public String deleteProduct(String id){
		boolean wasFound=false;
		String msg="There is no any product with this id";
		for (int i=0;i<products.size() &&!wasFound ;i++ ) {
			if(products.get(i).getId().equals(id)){
				
				wasFound=true;
				for(int j=0;j<users.size();j++){
					ArrayList<BibliographicProductReference> references= users.get(j).getReferences();
					for(int k=0;k<references.size();k++){
						if(references.get(k).getId().equals(id)){
							references.remove(k);
						}
					}
				}
				products.remove(i);
				msg="The product was deleted succesfully";
			}
		}
		return msg;
	}
	/**
 	* Determines whether an advertisement should be shown during a reading session.
 	*
 	* @param userId the ID of the user
 	* @param productId the ID of the product
 	* @param page the number of readPages
 	* @return true if an advertisement should be shown, false otherwise
 	*/

	public boolean readingSessionAd(String userId, String productId, int page){
		User user=searchUserById(userId);
		boolean flag=user.readPage(productId,page);
		return flag;
		
	}
	/**
 	* Returns information about a reading session for a product.
 	*
 	* @param productId the ID of the product
 	* @return an array containing the name and number of pages of the product
 	*/

	public Object[] getReadingSessionInfo(String productId){
		int pages=0;
		String name="";
		Object []info=new Object[2];
		boolean found=false;
		for(int i=0;i<products.size()&&!found;i++){
			if(products.get(i).getId().equals(productId)){
				pages= products.get(i).getNumPages();
				name=products.get(i).getName();
				found=true;
			}
		}
		info[0]=name;
		info[1]=pages;
		return info;
	}
	/**
 	* Returns an advertisement.
 	*
 	* @return a string representing an advertisement
 	*/
	public String getAd(){
		return adGenerator.getAd();
	}
	/**
 	* Returns a user's library.
 	*
 	* @param userId the ID of the user
 	* @return a 3D array representing the user's library
 	*/
	public String[][][] getLibrary(String userId){
		User user= searchUserById(userId);
		String[][][] library=user.getLibrary();
		return library;
	}

	/**
 	* Determines if a user has a specified product.
 	*
 	* @param userId    the ID of the user to search for
 	* @param productId the ID of the product to search for
 	* @return true if the user has the specified product, false otherwise
 	*/
	public boolean userHasProduct(String userId,String productId){
		boolean flag=false;
		User user=searchUserById(userId);
		if(user!=null){
			if(user.searchProduct(productId)!=null){
				flag=true;
			}
		}
		return flag;
	}

	public String getReadPagesInBooksAndMagazines(){
		int bookAcum=0;
		int magazineAcum=0;
		for(int i=0;i<products.size();i++){
			if(products.get(i) instanceof Book){
				bookAcum+=products.get(i).getReadPages();
			}
			if(products.get(i) instanceof Magazine){
				magazineAcum+=products.get(i).getReadPages();
			}
		}

		String msg="";
		msg+="Books: "+bookAcum+"\n";
		msg+="Magazines: "+magazineAcum+" \n";
		msg+="Total: "+(bookAcum+magazineAcum)+"\n";
		return msg;
	}

	public String getGenreAndCategoryWithTheGreaterNumberOfReadPages(){
		int []acumGenre=new int[BookGenre.values().length];
		int []acumCategory=new int[MagazineCategory.values().length];
		String msg="";
		for(int i=0;i<products.size();i++){
			if(products.get(i) instanceof Book){
				int pos=fromBookToAcumArrayPos(products.get(i));
				acumGenre[pos]+=products.get(i).getReadPages();
			}

			if(products.get(i) instanceof Magazine){
				int pos=fromMagazineToAcumArrayPos(products.get(i));
				acumCategory[pos]+=products.get(i).getReadPages();
			}
		}
		int maxPosGenre=getMaxPos(acumGenre);
		int maxPosCategory=getMaxPos(acumCategory);
		msg+="Book genre: "+getBookGenresInStr()[maxPosGenre]+" | Read pages: "+acumGenre[maxPosGenre]+"\n";
		msg+="Magazine category: "+getMagazineCategoriesInStr()[maxPosCategory]+" | Read pages: "+acumCategory[maxPosCategory];
		return msg;
		


	}
	private int fromBookToAcumArrayPos(BibliographicProduct book){
		int pos=-1;
		boolean flag=false;
		BookGenre[] genres=BookGenre.values();
		for (int i=0;i<genres.length &&!flag;i++ ) {
			if(genres[i]==((Book) book).getGenre()){
				pos=i;
				flag=true;
			}
		}
		return pos;
	}
	private int fromMagazineToAcumArrayPos(BibliographicProduct magazine){
		int pos=0;
		boolean flag=false;
		MagazineCategory[] categories=MagazineCategory.values();
		for (int i=0;i<categories.length&&!flag;i++ ) {
			if(categories[i]==((Magazine) magazine) .getCategory()){
				pos=i;
				flag=true;
			}
		}
		return pos;
	}
	private int getMaxPos(int arr[]){
		int maxPos=0;	
		for(int i=0;i<arr.length;i++){
			if(arr[maxPos]<arr[i]){
				maxPos=i;
			}
		}
		return maxPos;
	}
	public String getTop5(){
		String msgBook="";
		int bookAdditions=0;
		int magazineAdditions=0;
		String msgMagazine="";
		Collections.sort(products);
		for(int i=0;i<products.size()&& (magazineAdditions<5 ||bookAdditions<5 )  ;i++){
			if(products.get(i) instanceof Book&&bookAdditions<5){
				msgBook+="TOP: "+(bookAdditions+1)+"\n";
				msgBook+=products.get(i)+"\n";
				msgBook+="||||||||||||||||||||||||||||||\n";
				bookAdditions++;
			}
			if(products.get(i) instanceof Magazine&&magazineAdditions<5){
				msgMagazine+="TOP: "+(magazineAdditions+1)+"\n";
				msgMagazine+=products.get(i)+"\n";
				msgMagazine+="||||||||||||||||||||||||||||||\n";
				magazineAdditions++;
			}
		}
		return "BOOKS "+"\n"+msgBook+"\n"+"MAGAZINES"+"\n"+msgMagazine;

	}

	public String getSoldsByGenre(){
		String[] genres=getBookGenresInStr();
		String msg="";
		int acumSolds[]=new int[BookGenre.values().length];
		double acumValuePaid[]= new double[BookGenre.values().length];
		for(int i=0;i<products.size();i++){
			if(products.get(i) instanceof Book){
				int pos=fromBookToAcumArrayPos(products.get(i));
				acumSolds[pos]+=products.get(i).getSolds();
				acumValuePaid[pos]+=products.get(i).getTotalValuePaid();
			}
		}
		for (int i=0;i<genres.length;i++ ) {
			msg+=genres[i]+" | Solds: "+acumSolds[i]+" | Total value paid: "+acumValuePaid[i];
			msg+="\n";
		}
		return msg;
	}
	public String getSuscriptionsByCategory(){
		String categories[]=getMagazineCategoriesInStr();
		String msg="";
		int []acumSolds=new int[categories.length];
		double []acumValuePaid=new double[categories.length];
		for (int i=0;i< products.size() ;i++ ) {
			if(products.get(i) instanceof Magazine){
				int pos=fromMagazineToAcumArrayPos(products.get(i));
				acumSolds[pos]+=products.get(i).getSolds();
				acumValuePaid[pos]+=products.get(i).getTotalValuePaid();
			}
		}
		for (int i=0;i<categories.length ;i++ ) {
			msg+=categories[i]+" | Solds: "+acumSolds[i]+" | Total value paid: "+acumValuePaid[i];
			msg+="\n";
		}
		return msg;
	}






}
