package model;
import java.util.ArrayList;
import java.util.GregorianCalendar;
public class Controller{
	private ArrayList<User> users;
	private ArrayList<BibliographicProduct> products;
	private IdGenerator idGenerator;
	private AdGenerator adGenerator;
	public Controller(){
		users=new ArrayList<User>();
		products=new ArrayList<BibliographicProduct>();
		idGenerator=new IdGenerator();
		adGenerator=new AdGenerator();
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

	public boolean validateProductId(String productId){
		BibliographicProduct product=searchProductById(productId);
		boolean isValid=true;
		if(product==null){
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

	public String addProductToUser(String userId, String productId){
		BibliographicProduct product=searchProductById(productId);
		User user=searchUserById(userId);
		String msg="";
		msg=user.addProduct(product);
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
	public BibliographicProduct searchProductById(String id){
		BibliographicProduct foundedProduct=null;
		for (int i=0;i<products.size() ;i++ ) {
			if( products.get(i).getId().equals(id)) {
				foundedProduct=products.get(i);
			}
		}
		return foundedProduct;
	}

	public String deleteProductToUser(String userId, String productId){
		String msg="";
		User user=searchUserById(userId);
		msg=user.deleteProduct(productId);
		return msg;
	}
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
			msg+=products.get(i).getInfo();
			msg+="\n||||||||||||||||||";
		}
		return msg;
	}
	public String[] getUserTypesInStr(){
		String[] types={"PREMIUM", "REGULAR"};
		return types;
	}

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
	public void modifyProduct(String productId, int field, double newStatus ){
		BibliographicProduct product=searchProductById(productId);
		switch (field) { //8
			case 8:
				product.setValue(newStatus);
				break;
		}
	}
	public void modifyProduct(String productId, int field, GregorianCalendar newStatus){
		BibliographicProduct product=searchProductById(productId);
		switch (field) { //9
			case 9: 
				product.setPublicationDate(newStatus);
				break;
		}

	}

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
	
	public void initUsers(){
		if(searchUserById("1")==null){
			users.add(new User("Pedroregular","1",UserType.valueOf("REGULAR")));
		}
		if(searchUserById("2")==null){
			users.add(new User("pedro premium","2",UserType.valueOf("PREMIUM")));
		}
	}

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
	public boolean readingSessionAd(String userId, String productId, int page){
		User user=searchUserById(userId);
		boolean flag=user.readPage(productId,page);
		return flag;
		
	}

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
	public String getAd(){
		return adGenerator.getAd();
	}

	public String[][][] getLibrary(String userId){
		User user= searchUserById(userId);
		String[][][] library=user.getLibrary();
		return library;
	}
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





}
