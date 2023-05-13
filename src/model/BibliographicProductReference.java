package model;
import java.util.GregorianCalendar;

public class BibliographicProductReference {
    private BibliographicProduct product;
    private GregorianCalendar operationDate;
    private double invoiceValue;
    private int numReadPages;

    BibliographicProductReference(BibliographicProduct product){
    	this.product=product;
        numReadPages=0;
    	invoiceValue= product.getValue();
    	operationDate= new GregorianCalendar();
    }
    public boolean readPage(int numPage){
        boolean ad=false;
        if(numPage>numReadPages){
            numReadPages=numPage;
            product.readNewPage();
            if(product instanceof Magazine && (numReadPages%Magazine.NUM_PAGES_BEETWEEN_ADS==0) ){
                ad=true;
                System.out.println(Magazine.NUM_PAGES_BEETWEEN_ADS);
            }
            if(product instanceof Book&& (numReadPages%Book.NUM_PAGES_BEETWEEN_ADS==0)){
                ad=true;
            }
        }
        return ad;
    }
    public BibliographicProduct getProduct(){
    	return this.product;
    }
    public String getId(){
    	return this.product.getId();
    }


    

}
