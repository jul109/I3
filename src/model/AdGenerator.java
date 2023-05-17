package model;
import java.util.Random;
public class AdGenerator{
	private String[] adsInTxt;
	/**
 	* Constructs a new AdGenerator object with predefined ads.
 	*/
	AdGenerator(){
		adsInTxt=new String[3];
		adsInTxt[0]="|Suscribete al Combo Plus y llevate Disney+ y Star+ a un precio increible|";
		adsInTxt[1]="|Ahora tus mascotas tienen una app favorita: Laika. Los mejores productos para tu peludito.|";
		adsInTxt[2]="|Estamos de aniversario| Visita tu exito mas cercano y sorprendete con las mejores ofertas.";
	}
	/**
 	* Returns a randomly selected ad from the predefined ads.
 	*
 	* @return a randomly selected ad
 	*/


	public String getAd(){
		Random rand=new Random();
		int pos=rand.nextInt(3);
		return adsInTxt[pos];
	}
}
