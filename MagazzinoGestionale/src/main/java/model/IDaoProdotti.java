package model;

import java.util.List;

public interface IDaoProdotti {

	List<Prodotto> prodotti();

	void aggiungi(Prodotto p);

	void elimina(int indice);

	void modifica(int indice, Prodotto p);
}
