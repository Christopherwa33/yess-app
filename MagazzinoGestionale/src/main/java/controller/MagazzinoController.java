package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DaoProdottiScaffolding;
import model.IDaoProdotti;
import model.Prodotto;
import view.IView;
import view.TemplateView;

@WebServlet("/MagazzinoController")
public class MagazzinoController extends HttpServlet {
	private static final long serialVersionUID = 4415234724368016973L;
	
	private static final String TEMPLATE_FOLDER = 
			"C:\\Users\\Utente\\Documents\\NetBeansProjects\\MagazzinoGestionale\\src\\main\\webapp\\WEB-INF\\templates\\";
	
	private IDaoProdotti dao = new DaoProdottiScaffolding();
	private IView view = new TemplateView(TEMPLATE_FOLDER);
	
    public MagazzinoController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		
		// faccio l'equals "invertito" perch� risulta essere "null safety"
		// in quanto, a differenza della variabile "cmd"
		// la stringa "add" non sar� mai null
    	if ("add".equals(cmd)) {
    		// in realt� non siamo mai sicuri del fatto che arrivino tutti questi
    		// parametri
    		// per� facciamo finta che arrivi sempre tutto, altrimenti
    		// dovrei gestire gli errori
    		String nome = request.getParameter("nome");
    		String categoria = request.getParameter("categoria");
    		double prezzo = Double.parseDouble(request.getParameter("prezzo"));
    		Prodotto nuovoProdotto = new Prodotto(nome, categoria, prezzo);
    		dao.aggiungi(nuovoProdotto);
    	}
    	
    	if ("elimina".equals(cmd)) {
    		int index = Integer.parseInt(request.getParameter("index"));
    		dao.elimina(index);
    	}
    	
    	String ris = view.renderHTML(dao.prodotti());
    	response.getWriter().append(ris);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
