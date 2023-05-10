package model;
import java.util.ArrayList;
import java.util.GregorianCalendar;
public class Controller{
	private ArrayList<User> users;
	private ArrayList<BibliographicProduct> products;
	private IdGenerator idGenerator;
	public Controller(){
		users=new ArrayList<User>();
		products=new ArrayList<BibliographicProduct>();
		idGenerator=new IdGenerator();
	}
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
	public boolean validateUserId(String userId){
		User user=searchUserById(userId);
		boolean isValid=true;
		if(user!=null){
			isValid=false;
		}
		return isValid;
	}
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

/*	public String addProductToUser(){

	}*/
	public String[] getBookGenresInStr(){
		BookGenre genres[]=BookGenre.values();
		String genresInStr[]=new String[genres.length];
		for (int i=0;i<genres.length ;i++ ) {
			genresInStr[i]=genres[i].name();
		}
		return genresInStr;
	}
	public String[] getMagazineCategoriesInStr(){
		MagazineCategory categories[]=MagazineCategory.values();
		String categoriesInStr[]=new String[categories.length];
		for (int i=0;i<categories.length ;i++ ) {
			categoriesInStr[i]=categories[i].name();
		}
		return categoriesInStr;
	}
	public BibliographicProduct searchProductById(String id){
		BibliographicProduct foundedProduct=null;
		for (int i=0;i<products.size() ;i++ ) {
			if( products.get(i).getId().equals(id)) {

			}
		}
		return foundedProduct;
	}
	public int getProductTypeFlag(String id){
		int isValid=0;
		BibliographicProduct product=searchProductById(id);
		if(product!=null){
			System.out.println("Hola");
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
			msg+=products.get(i).getInfo();
			msg+="\n||||||||||||||||||";
		}
		return msg;
	}
	public String[] getUserTypesInStr(){
		String[] types={"PREMIUM", "REGULAR"};
		return types;
	}

	public void ModifyProduct(BibliographicProduct product, int field, String newStatus){
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
	public String ModifyProduct(BibliographicProduct product, int field, int newStatus){
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
		String msg="The field has been modified succesfully";
		return msg;
	}
	public String modifyProduct(BibliographicProduct product, int field, double newStatus ){
		switch (field) { //8
			case 8:
				product.setValue(newStatus);
				break;
		}
		String msg="The field has been modified succesfully";
		return msg;
	}
	public String modifyProduct(BibliographicProduct product, int field, GregorianCalendar newStatus){
		switch (field) { //9
			case 9: 
				product.setPublicationDate(newStatus);
				break;
		}
		String msg="The field has been modified succesfully";
		return msg;
	}




}
