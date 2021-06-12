package view;

import java.util.List;

import model.Prodotto;

public interface IView {
	
	String renderHTML(List<Prodotto> prodotti);
	
}
