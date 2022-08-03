package control;

import dao.IProduitDao;
import dao.ProductDAOImpl;
import model.Produit;

public class Main {

    public static void main(String[] args) {
	    String url = "jdbc:mysql://localhost:3306/demo";
        String username = "root";
        String password = "";

        Produit produit = new Produit();
        produit.setNom("AMD Ryzen 10 12X");

        IProduitDao iProduitDao = new ProductDAOImpl(url, username, password);
        System.out.println(iProduitDao.getProduitById(3L));
    }
}
