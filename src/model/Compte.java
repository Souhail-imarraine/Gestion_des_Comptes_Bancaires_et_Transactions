package model;
import enums.TypeCompte;

import java.util.ArrayList;
import java.util.UUID;

public class Compte {
    private UUID idCompte;
    private int solde ;
    private TypeCompte typeCompte;
    private ArrayList<Transaction> transactions ;
    private boolean actif;


}
