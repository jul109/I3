package model;
import java.util.GregorianCalendar;

public class BibliographicProductReference implements Comparable<BibliographicProductReference> {
    private BibliographicProduct product;
    private GregorianCalendar operationDate;
    private double invoiceValue;
    private int numReadPages;
    /**
    * Constructs a new BibliographicProductReference object with the given BibliographicProduct.
    *
    * @param product the BibliographicProduct to reference
    */

    BibliographicProductReference(BibliographicProduct product){
    	this.product=product;
        numReadPages=0;
    	invoiceValue= product.getValue();
    	operationDate= new GregorianCalendar();
    }
    /**
    * Reads a page and returns whether an ad must be shown.
    *
    * @param numPage the page number to read
    * @return true if an ad must be shown, false otherwise
    */
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
    /**
    *  Returns the BibliographicProduct associated with this object.
    *
    * @return the BibliographicProduct associated with this object
    */
    public BibliographicProduct getProduct(){
    	return this.product;
    }
    /**
    * Returns the ID of the BibliographicProduct associated with this object.
    *
    * @return the ID of the BibliographicProduct associated with this object
    */
    public String getId(){
    	return this.product.getId();
    }
    /**
    * Compares this BibliographicProductReference with the specified BibliographicProductReference for order.
    * Returns a negative integer, zero, or a positive integer as this object's publication date is less than,
    * equal to, or greater than the specified object's publication date.
    *
    * @param product the BibliographicProductReference to be compared
    * @return a negative integer, zero, or a positive integer as this object's publication date is less than,
    * equal to, or greater than the specified object's publication date
    */
    public int compareTo(BibliographicProductReference product){
        return this.getProduct().getPublicationDate().compareTo( product.getProduct().getPublicationDate() );
    }


    

}
