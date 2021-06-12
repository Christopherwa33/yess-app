package model;

import java.util.ArrayList;
import java.util.List;

public class DaoProdottiScaffolding implements IDaoProdotti {

	private List<Prodotto> scaffolding = new ArrayList<>();
	
	public DaoProdottiScaffolding() {
		scaffolding.add(new Prodotto("Auricolari", "Audio", 49.99));
		scaffolding.add(new Prodotto("Calzini", "Indumenti", 2.99));
	}
	
	@Override
	public List<Prodotto> prodotti() {
		return scaffolding;
	}

	@Override
	public void aggiungi(Prodotto p) {
		scaffolding.add(p);
	}

	@Override
	public void elimina(int indice) {
		scaffolding.remove(indice);
	}

	@Override
	public void modifica(int indice, Prodotto p) {
		scaffolding.set(indice, p);
	}

}
