package model;
import java.util.ArrayList;
import java.util.GregorianCalendar;
public class Controller{
	private ArrayList<User> users;
	private ArrayList<BibliographicProduct> products;
	public Controller(){
		users=new ArrayList<User>();
		products=new ArrayList<BibliographicProduct>();
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
	public String addUser(String name, String userId){
		boolean isValid=validateUserId(userId);
		String msg="";
		UserType userType=UserType.valueOf("REGULAR");
		if(isValid){
			users.add(new User(name,userId,userType));
			msg="You have registered succesfully";
  		}else{
  			msg="This id was used by another user before. It was not possible to register you";
  		}	
  		return msg;
	}

	public String addBook(String name, int numPages,double value, GregorianCalendar publicationDate, String url, BookGenre genre,String review){
		String msg="";
		products.add(new Book(name,numPages, value, publicationDate, url, genre, review));
		msg="The product was added succesfully";
		return msg;

	}
	public String addMagazine(String name, int numPages,double value, GregorianCalendar publicationDate, String url,MagazineCategory category, String publicationFrequency){
		String msg="";
		products.add(new Magazine(name,numPages, value, publicationDate, url, category, publicationFrequency));
		msg="The product was added sucessfully";
		return msg;
	}
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
	
}
