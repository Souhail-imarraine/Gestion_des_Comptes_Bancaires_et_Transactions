
import controller.MainController;
import repository.ClientRepository;
import repository.CompteRepository;
import repository.TransactionRepository;
import view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        // init repositories
        ClientRepository clientRepo = new ClientRepository();
        CompteRepository compteRepo = new CompteRepository();
        TransactionRepository txRepo = new TransactionRepository();

        // view
        ConsoleView view = new ConsoleView();

        // main controller
        MainController mainController = new MainController(view, clientRepo, compteRepo, txRepo);
        mainController.start();
    }
}
