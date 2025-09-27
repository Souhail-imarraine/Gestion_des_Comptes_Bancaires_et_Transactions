package controller;

import model.Client;
import model.Compte;
import model.enums.TypeCompte;
import service.ClientService;
import service.CompteService;
import service.ReportService;
import service.TransactionService;
import util.IdGenerator;
import view.ConsoleView;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

public class AdminController {
    private final ConsoleView view;
    private final ClientService clientService;
    private final CompteService compteService;
    private final TransactionService transactionService;
    private final ReportService reportService;

    public AdminController(ConsoleView view, ClientService clientService, CompteService compteService,
                           TransactionService transactionService, ReportService reportService) {
        this.view = view;
        this.clientService = clientService;
        this.compteService = compteService;
        this.transactionService = transactionService;
        this.reportService = reportService;
    }

    public void menuAdmin() {
        view.printHeader("Espace Gestionnaire (Admin)");
        boolean back = false;
        while (!back) {
            System.out.println("1) Lister clients");
            System.out.println("2) Creer client");
            System.out.println("3) Supprimer client");
            System.out.println("4) Creer compte pour client");
            System.out.println("5) Supprimer compte");
            System.out.println("6) Rapports simples");
            System.out.println("0) Retour");
            int ch = view.readInt("Choix");
            switch (ch) {
                case 1:
                    listClients();
                    break;
                case 2:
                    createClient();
                    break;
                case 3:
                    deleteClient();
                    break;
                case 4:
                    createCompte();
                    break;
                case 5:
                    deleteCompte();
                    break;
                case 6:
                    reports();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        }
    }

    private void listClients() {
        List<Client> all = clientService.findAll();
        all.forEach(c -> view.displayClient(c));
        view.pause();
    }

    private void createClient() {
        String nom = view.readString("Nom");
        String prenom = view.readString("Prenom");
        String email = view.readString("Email");
        String pwd = view.readString("Mot de passe");
        String id = IdGenerator.nextId("CL");
        Client c = clientService.createClient(nom, prenom, email, pwd, id);
        System.out.println("Client cree: " + c.getIdClient());
        view.pause();
    }

    private void deleteClient() {
        String id = view.readString("Id client a supprimer");
        try {
            clientService.deleteClient(id);
            System.out.println("Client supprime si existait.");
        } catch (Exception ex) {
            System.out.println("Erreur: " + ex.getMessage());
        }
        view.pause();
    }

    private void createCompte() {
        String clientId = view.readString("Id client");
        try {
            TypeCompte type = view.chooseTypeCompte();
            Compte cp = compteService.createCompte(clientId, type);
            System.out.println("Compte cree: " + cp.getIdCompte());
        } catch (NoSuchElementException ex) {
            System.out.println("Erreur: " + ex.getMessage());
        }
        view.pause();
    }

    private void deleteCompte() {
        String id = view.readString("Id compte a supprimer");
        compteService.deleteCompte(id);
        System.out.println("Suppression effectuee si compte existait.");
        view.pause();
    }

    private void reports() {
        String clientId = view.readString("Id client pour total solde (ou vide pour annuler)");
        if (clientId == null || clientId.isEmpty()) return;
        try {
            BigDecimal total = reportService.totalSoldeParClient(clientId);
            System.out.println("Total solde client " + clientId + " = " + total + " MAD");
        } catch (Exception ex) {
            System.out.println("Erreur: " + ex.getMessage());
        }
        view.pause();
    }
}
