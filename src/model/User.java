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
	//private 
	User(String name,String id,UserType type){
		this.name=name;
		this.id=id;
		this.type=type;
		products=new ArrayList<BibliographicProductReference>();
	}
	public String getId(){
		return this.id;
	}
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
	public BibliographicProductReference searchProduct(String id){
		BibliographicProductReference foundedProduct=null;
		for(int i=0;i<products.size();i++){
			if( products.get(i).getId().equals(id) ){
				foundedProduct=products.get(i);
			}
		}
		return foundedProduct;
	}

	public boolean validateBookAddition(){
		boolean isPossibleToAdd=false;;
		if(type.name()=="REGULAR"){
			int cont=0;
			boolean flag=false; 
			for (int i=0;i<products.size()&&!flag;i++ ) {
				if(products.get(i).getProduct() instanceof Book){
					cont++;
				}
				if(cont==MAX_BOOKS){
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

	public boolean validateMagazineAddition(){
		boolean isPossibleToAdd=false;;
		if(type.name()=="REGULAR"){
			int cont=0;
			boolean flag=false; 
			for (int i=0;i<products.size()&&!flag;i++ ) {
				if(products.get(i).getProduct() instanceof Magazine){
					cont++;
				}
				if(cont==MAX_MAGAZINES){
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

	


}