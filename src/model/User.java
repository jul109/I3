package model;
import java.util.GregorianCalendar;
import java.util.ArrayList;

public class User{
	private String name;
	private String id;
	private GregorianCalendar vinculationDate;
	private ArrayList<BibliographicProductReference> products;
	private UserType type;
	public static final int MAX_BOOKS=2;
	public static final int MAX_MAGAZINES=2;
	public static final int MATRIX_SIZE=5;
	/**
 	* Constructs a new User object with the specified name, ID, and type.
 	*
 	* @param name  the name of the user
 	* @param id    the ID of the user
 	* @param type  the type of user
 	*/
	User(String name,String id,UserType type){
		this.name=name;
		this.id=id;
		this.type=type;
		products=new ArrayList<BibliographicProductReference>();
	}
	/**
 	* Returns the ID of this user.
 	*
 	* @return the ID of this user
 	*/
	public String getId(){
		return this.id;
	}
	/**
 	* Adds a bibliographic product to this user's library.
 	*
 	* @param product the bibliographic product to add
 	* @return a message indicating whether the product was added successfully or not
 	*/
	public String addProduct(BibliographicProduct product){
		String msg="";
		if(searchProduct(product.getId())!=null){
			msg="This product was added to your library previusly";
		}else{
			if(product instanceof Book){
				if(validateBookAddition()){
					products.add(new BibliographicProductReference (product));
					msg="The book was added succesfully";
				}else{
					msg="The number of books in your account is at limit";
				}
			}

			if(product instanceof Magazine){
				if(validateMagazineAddition()){
					products.add(new BibliographicProductReference (product));
					msg="The magazine was added succesfully";
				}else{
					msg="The number of magazines in your account is at limit";
				}
			}
		}	
		return msg;
	}
	/**
 	* Searches for a bibliographic product in this user's library by its ID.
 	*
 	* @param id the ID of the bibliographic product to search for
 	* @return the bibliographic product reference if found, null otherwise
 	*/
	public BibliographicProductReference searchProduct(String id){
		BibliographicProductReference foundedProduct=null;
		for(int i=0;i<products.size();i++){
			if( products.get(i).getId().equals(id) ){
				foundedProduct=products.get(i);
			}
		}
		return foundedProduct;
	}
	/**
 	* Determines if it is possible to add a book to this user's library.
 	*
 	* @return true if it is possible to add a book, false otherwise
 	*/

	public boolean validateBookAddition(){
		boolean isPossibleToAdd=false;;
		if(type.name()=="REGULAR"){
			int cont=0;
			boolean flag=false; 
			for (int i=0;i<products.size()&&!flag;i++ ) {
				if(products.get(i).getProduct() instanceof Book){
					cont++;
				}
				if(cont>=MAX_BOOKS){
					flag=true;
				}
			}
			if(cont<MAX_BOOKS){
				isPossibleToAdd=true;
			}

			
		}else{	
			isPossibleToAdd=true;
		}	
		return isPossibleToAdd;
	}
	/**
 	* Determines if it is possible to add a magazine to this user's library.
 	*
 	* @return true if it is possible to add a magazine, false otherwise
 	*/

	public boolean validateMagazineAddition(){
		boolean isPossibleToAdd=false;;
		if(type.name()=="REGULAR"){
			int cont=0;
			boolean flag=false; 
			for (int i=0;i<products.size()&&!flag;i++ ) {
				if(products.get(i).getProduct() instanceof Magazine){
					cont++;
				}
				if(cont>=MAX_MAGAZINES){
					flag=true;
				}
			}
			if(cont<MAX_MAGAZINES){
				isPossibleToAdd=true;
			}

			
		}else{	
			isPossibleToAdd=true;
		}
		return isPossibleToAdd;	
	}
	/**
 	* Deletes a magazine from this user's library by its ID.
 	*
 	* @param id the ID of the magazine to delete
 	* @return a message indicating whether the magazine was deleted successfully or not
 	*/

	public String deleteProduct(String id){
		String msg="There is no any magazine with this id in your library";
		for (int i=0;i<products.size() ;i++ ) {
			if( (products.get(i).getProduct() instanceof Magazine) &&   (products.get(i).getId().equals(id)) ){
				((Magazine) products.get(i).getProduct() ).cancelSuscription();
				products.remove(i);
				msg="The product was deleted succesfully";
			}
		}
		return msg;
	}
	/**
 	* Informs if the Ui must show an ad.
 	*
 	* @param productId the ID of the bibliographic product 
 	* @param numPage   the page number that was read
 	* @return true if an advertisement must be shown, false otherwise
 	*/
	public boolean readPage(String productId, int numPage){
		boolean ad=false;
		for (int i=0;i<products.size() ;i++ ) {
			if(products.get(i).getId().equals(productId)){
				BibliographicProductReference product= products.get(i);
				ad=product.readPage(numPage);
				
			}
		}
		if(type.name().equals("PREMIUM")){
			ad=false;
		}
		return ad;
	}
	/**
 	* Returns the list of bibliographic product references in this user's library.
 	*
 	* @return the list of bibliographic product references
 	*/
	public ArrayList<BibliographicProductReference> getReferences(){
		return this.products;
	}
	/**
 	* Returns a 3D array representation of this user's library.
 	*
 	* @return a 3D array where each element represents a bibliographic product ID or an empty string if there is no product at that position
 	*/

	public String[][][] getLibrary(){
		int numberOfMatrices=(int)(MATRIX_SIZE*MATRIX_SIZE+products.size()-1)/(MATRIX_SIZE*MATRIX_SIZE);
		if(numberOfMatrices==0){
			numberOfMatrices=1;
		}
		String matrix[][][]=new String[numberOfMatrices][MATRIX_SIZE][MATRIX_SIZE];
		
		for(int h=0;h<numberOfMatrices;h++){
			for (int i=0;i<MATRIX_SIZE ;i++ ) {
				for (int j=0;j<MATRIX_SIZE ;j++ ) {
					int pos=(h*MATRIX_SIZE*MATRIX_SIZE)+(i* MATRIX_SIZE)+j;
					if(pos<products.size()){
						matrix[h][i][j]=products.get(pos).getId();

					}else{
						matrix[h][i][j]="";
					}
				}
			}
		}
		return matrix;

	}

	


}