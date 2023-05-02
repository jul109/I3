package model;
import java.util.GregorianCalendar;
import java.util.ArrayList;

public class User{
	private String name;
	private String id;
	private GregorianCalendar vinculationDate;
	private ArrayList<BibliographicProduct> products;
	private UserType type;
	User(String name,String id,UserType type){
		this.name=name;
		this.id=id;
		this.type=type;
		products=new ArrayList<BibliographicProduct>();
	}


}