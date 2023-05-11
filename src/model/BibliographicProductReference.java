package model;
import java.util.GregorianCalendar;

public class BibliographicProductReference {
    private BibliographicProduct product;
    private boolean[] readPages;
    private GregorianCalendar operationDate;
    private double invoiceValue;

    BibliographicProductReference(BibliographicProduct product){
    	this.product=product;
    	readPages= new boolean[product.getNumPages()+1];
    	invoiceValue= product.getValue();
    	operationDate= new GregorianCalendar();
    }
    public void readPage(int numPage){
    	if(!readPages[numPage]){
    		readPages[numPage]=true;
    		product.readNewPage();
    	}
    }
    public BibliographicProduct getProduct(){
    	return this.product;
    }
    public String getId(){
    	return this.product.getId();
    }
    

}
