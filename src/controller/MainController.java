package controller;

import repository.ClientRepository;
import repository.CompteRepository;
import repository.TransactionRepository;
import service.ClientService;
import service.CompteService;
import service.TransactionService;
import service.ReportService;
import view.ConsoleView;

import java.math.BigDecimal;

public class MainController {
    private final ConsoleView view;
    private final ClientService clientService;
    private final CompteService compteService;
    private final TransactionService transactionService;
    private final ReportService reportService;

    private final ClientController clientController;
    private final AdminController adminController;

    public MainController(ConsoleView view, ClientRepository clientRepo, CompteRepository compteRepo, TransactionRepository txRepo) {
        this.view = view;
        this.clientService = new ClientService(clientRepo);
        this.compteService = new CompteService(compteRepo, clientRepo);
        this.transactionService = new TransactionService(compteRepo, txRepo);
        this.reportService = new ReportService(compteRepo);
        this.clientController = new ClientController(view, clientService, compteService, transactionService, reportService);
        this.adminController = new AdminController(view, clientService, compteService, transactionService, reportService);
    }

    public void start() {
        boolean running = true;
        while (running) {
            view.printHeader("Banque - Console");
            System.out.println("1) Espace Client");
            System.out.println("2) Espace Gestionnaire");
            System.out.println("3) Seed demo data");
            System.out.println("0) Quitter");
            int ch = view.readInt("Choix");
            switch (ch) {
                case 1:
                    clientController.menuClient();
                    break;
                case 2:
                    adminController.menuAdmin();
                    break;
//                case 3:
//                    seedDemo();
//                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        }
        System.out.println("A bientot !");
    }

//    private void seedDemo() {
//        // seed simple: create two clients and some comptes + operations
//        System.out.println("Seeding demo data...");
//        // create clients
//        var c1 = clientService.createClient("Ben", "Ahmed", "a.ben@example.com", "pass", "CL-1");
//        var c2 = clientService.createClient("El", "Fatima", "f.el@example.com", "pass", "CL-2");
//        // create comptes
//        var cp1 = compteService.createCompte(c1.getIdClient(), model.enums.TypeCompte.COURANT);
//        var cp2 = compteService.createCompte(c2.getIdClient(), model.enums.TypeCompte.EPARGNE);
//        // operations
//        transactionService.effectuerDepot(cp1.getIdCompte(), new BigDecimal("10000"), "Salaire");
//        transactionService.effectuerDepot(cp2.getIdCompte(), new BigDecimal("5000"), "Epargne");
//        transactionService.effectuerVirement(cp1.getIdCompte(), cp2.getIdCompte(), new BigDecimal("1500"), "Transfert familly");
//        System.out.println("Demo seed done.");
//        view.pause();
//    }
}
