package view;

import model.Client;
import model.Compte;
import model.Transaction;
import model.enums.TypeCompte;
import model.enums.TypeTransaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class ConsoleView {
    private final Scanner scanner = new Scanner(System.in);

    public void printHeader(String title) {
        System.out.println("==================================");
        System.out.println("  " + title);
        System.out.println("==================================");
    }

    public int readInt(String prompt) {
        System.out.print(prompt + ": ");
        while (!scanner.hasNextInt()) {
            System.out.print("Entree invalide. " + prompt + ": ");
            scanner.next();
        }
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }

    public String readString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }

    public BigDecimal readBigDecimal(String prompt) {
        System.out.print(prompt + ": ");
        while (true) {
            String s = scanner.nextLine().trim();
            try {
                return new BigDecimal(s);
            } catch (Exception e) {
                System.out.print("Format invalide. " + prompt + ": ");
            }
        }
    }

    public void displayClient(Client c) {
        System.out.println("Client: " + c.getIdClient() + " - " + c.getPrenom() + " " + c.getNom() + " (" + c.getEmail() + ")");
        System.out.println("Comptes:");
        for (Compte cp : c.getComptes()) {
            System.out.println("  - " + cp);
        }
    }

    public void displayComptes(List<Compte> comptes) {
        comptes.forEach(c -> System.out.println(c));
    }

    public void displayTransactions(List<Transaction> txs) {
        if (txs.isEmpty()) {
            System.out.println("Aucune transaction.");
            return;
        }
        txs.forEach(t -> System.out.println(t));
    }

    public void pause() {
        System.out.println("Appuyez sur Entree pour continuer...");
        scanner.nextLine();
    }

    public TypeCompte chooseTypeCompte() {
        System.out.println("Type compte: 1) COURANT 2) EPARGNE 3) DEPOT_A_TERME");
        int choice = readInt("Choix");
        switch (choice) {
            case 1: return TypeCompte.COURANT;
            case 2: return TypeCompte.EPARGNE;
            default: return TypeCompte.DEPOTATERME;
        }
    }

    public void forEachWithIndex(List<?> list, Consumer<Integer> consumer) {
        for (int i = 0; i < list.size(); i++) consumer.accept(i);
    }
}
