package controller;

import model.Client;
import model.Compte;
import model.Transaction;
import model.enums.TypeTransaction;
import service.ClientService;
import service.CompteService;
import service.ReportService;
import service.TransactionService;
import view.ConsoleView;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ClientController {
    private final ConsoleView view;
    private final ClientService clientService;
    private final CompteService compteService;
    private final TransactionService transactionService;
    private final ReportService reportService;

    public ClientController(ConsoleView view, ClientService clientService, CompteService compteService,
                            TransactionService transactionService, ReportService reportService) {
        this.view = view;
        this.clientService = clientService;
        this.compteService = compteService;
        this.transactionService = transactionService;
        this.reportService = reportService;
    }

    public void menuClient() {
        view.printHeader("Espace Client");
        String clientId = view.readString("Entrer votre id client (ex: CL-1)");
        Optional<Client> maybe = clientService.findById(clientId);
        if (!maybe.isPresent()) {
            System.out.println("Client introuvable.");
            view.pause();
            return;
        }
        Client client = maybe.get();
        boolean back = false;
        while (!back) {
            view.printHeader("Client: " + client.getPrenom());
            System.out.println("1) Voir mes infos et comptes");
            System.out.println("2) Voir historique d'un compte");
            System.out.println("3) Faire un depot");
            System.out.println("4) Faire un retrait");
            System.out.println("5) Faire un virement");
            System.out.println("0) Retour");
            int ch = view.readInt("Choix");
            switch (ch) {
                case 1:
                    view.displayClient(client);
                    view.pause();
                    break;
                case 2:
                    handleShowHistory(client);
                    break;
                case 3:
                    handleDepot(client);
                    break;
                case 4:
                    handleRetrait(client);
                    break;
                case 5:
                    handleVirement(client);
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        }
    }

    private Compte chooseCompte(Client client) {
        List<Compte> comptes = client.getComptes();
        if (comptes.isEmpty()) {
            System.out.println("Aucun compte.");
            return null;
        }
        for (int i = 0; i < comptes.size(); i++) {
            System.out.println((i + 1) + ") " + comptes.get(i));
        }
        int idx = view.readInt("Choisir numero du compte");
        if (idx < 1 || idx > comptes.size()) {
            System.out.println("Index invalide");
            return null;
        }
        return comptes.get(idx - 1);
    }

    private void handleShowHistory(Client client) {
        Compte c = chooseCompte(client);
        if (c == null) { view.pause(); return; }
        System.out.println("Filtrer par: 0=tout 1=DEPOT 2=RETRAIT 3=VIREMENT");
        int f = view.readInt("Choix filtre");
        List<Transaction> txs;
        switch (f) {
            case 1:
                txs = transactionService.findTransactionsForCompte(c.getIdCompte(), t -> t.getType() == TypeTransaction.DEPOT);
                break;
            case 2:
                txs = transactionService.findTransactionsForCompte(c.getIdCompte(), t -> t.getType() == TypeTransaction.RETRAIT);
                break;
            case 3:
                txs = transactionService.findTransactionsForCompte(c.getIdCompte(), t -> t.getType() == TypeTransaction.VIREMENT);
                break;
            default:
                txs = c.getTransactions();
        }
        view.displayTransactions(txs);
        view.pause();
    }

    private void handleDepot(Client client) {
        Compte c = chooseCompte(client);
        if (c == null) { view.pause(); return; }
        BigDecimal montant = view.readBigDecimal("Montant à deposer (MAD)");
        String motif = view.readString("Motif");
        try {
            Transaction t = transactionService.effectuerDepot(c.getIdCompte(), montant, motif);
            System.out.println("Depot effectue: " + t);
        } catch (Exception ex) {
            System.out.println("Erreur: " + ex.getMessage());
        }
        view.pause();
    }

    private void handleRetrait(Client client) {
        Compte c = chooseCompte(client);
        if (c == null) { view.pause(); return; }
        BigDecimal montant = view.readBigDecimal("Montant à retirer (MAD)");
        String motif = view.readString("Motif");
        try {
            Transaction t = transactionService.effectuerRetrait(c.getIdCompte(), montant, motif);
            System.out.println("Retrait effectue: " + t);
        } catch (Exception ex) {
            System.out.println("Erreur: " + ex.getMessage());
        }
        view.pause();
    }

    private void handleVirement(Client client) {
        Compte src = chooseCompte(client);
        if (src == null) { view.pause(); return; }
        String destId = view.readString("Entrer id compte destination (ex: CP-xxxx)");
        BigDecimal montant = view.readBigDecimal("Montant à virer (MAD)");
        String motif = view.readString("Motif");
        try {
            Transaction t = transactionService.effectuerVirement(src.getIdCompte(), destId, montant, motif);
            System.out.println("Virement effectue: " + t);
        } catch (Exception ex) {
            System.out.println("Erreur: " + ex.getMessage());
        }
        view.pause();
    }
}
