package model;
import java.util.GregorianCalendar;
import java.util.ArrayList;

public class User{
	private String name;
	private String id;
	private GregorianCalendar vinculationDate;
	private ArrayList<BibliographicProductReference> products;
	private UserType type;
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

			products.add(new BibliographicProductReference (product));
			msg="The product was added succesfully";
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

	


}