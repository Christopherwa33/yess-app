package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import model.Prodotto;

/**
 * Questa view va a sfruttare dei template (file in html che vado a predisporre
 * da qualche parte) per renderizzare la pagina html che verrà restituita
 * dal controller
 * @author lucaf
 *
 */
public class TemplateView implements IView {
	private String percorsoTemplates;

	public TemplateView(String percorsoTemplates) {
		super();
		this.percorsoTemplates = percorsoTemplates;
	}
	
	/**
	 * Caricare il template dal file, conoscendo il suo nome
	 * e restituirlo sottoforma di stringa
	 * @param templateName
	 * @return
	 */
	public String loadTemplate(String templateName) {
		String ris = "";
		String percorso = percorsoTemplates + templateName;
		
		try {
			Scanner dati = new Scanner(new File(percorso));
			while (dati.hasNextLine()) {
				ris += dati.nextLine() + "\n";
			}
		} catch (FileNotFoundException e) {
			ris = "Template not found at: " + percorso;
		}
		
		return ris;
	}
	
	private String renderProdotto(Prodotto p) {
		return loadTemplate("prodotto.html")
				.replace("[NOME]", p.getNome())
				.replace("[CATEGORIA]", p.getCategoria())
				.replace("[PREZZO]", p.getPrezzo() + "");
	}

	@Override
	public String renderHTML(List<Prodotto> prodotti) {
		// Vado a caricare il template che mi serve per renderizzare
		// la lista dei prodotti
		String ris = loadTemplate("prodotti.html");
		
		String renderProdotti = "";
		// itero tutti i prodotti e sfrutto il metodo
		// che mi "grafica" un singolo prodotto
		// e lo concateno ad una stringa
		int index = 0;
		for (Prodotto p: prodotti) {
			renderProdotti += renderProdotto(p)
					.replace("[INDICE]", index + "");
			
			index++;
		}
		// dato il template dei prodotti, vado a sostituire
		// il placeholder che avevo identificato
		// mettendo al suo posto, la lista graficata
		ris = ris.replace("<!-- PRODOTTI -->", renderProdotti);
		
		return ris;
	}
	
}
