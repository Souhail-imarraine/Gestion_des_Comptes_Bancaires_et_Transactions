# Gestion des Comptes Bancaires et Transactions

## Description
Application console Java pour la gestion des comptes bancaires et des transactions. Ce système permet aux clients de gérer leurs comptes bancaires et aux administrateurs de superviser l'ensemble des opérations.

## Fonctionnalités

### Espace Client
- **Connexion sécurisée** : Les clients se connectent avec leur ID unique
- **Consultation des informations** : Affichage des détails du compte et du solde
- **Historique des transactions** : Consultation de l'historique avec filtrage par type
- **Opérations bancaires** :
  - Dépôt d'argent
  - Retrait d'argent
  - Virement entre comptes

### Espace Gestionnaire (Admin)
- **Gestion des clients** :
  - Création de nouveaux clients
  - Suppression de clients
  - Liste de tous les clients
- **Gestion des comptes** :
  - Création de comptes pour les clients
  - Suppression de comptes
  - Support de différents types de comptes (Courant, Épargne, Dépôt à terme)
- **Rapports** : Génération de rapports sur les soldes par client

## Architecture du Projet

```
src/
├── Main.java                          # Point d'entrée de l'application
├── controller/                        # Contrôleurs (logique de présentation)
│   ├── AdminController.java          # Gestion des fonctionnalités admin
│   ├── ClientController.java         # Gestion des fonctionnalités client
│   └── MainController.java           # Contrôleur principal et navigation
├── model/                             # Modèles de données
│   ├── Client.java                   # Modèle Client
│   ├── Compte.java                   # Modèle Compte bancaire
│   ├── Gestionnaire.java             # Modèle Gestionnaire
│   ├── Personne.java                 # Classe abstraite Personne
│   ├── Transaction.java              # Modèle Transaction
│   └── enums/
│       ├── TypeCompte.java           # Types de comptes (Courant, Épargne, etc.)
│       └── TypeTransaction.java      # Types de transactions
├── repository/                        # Couche d'accès aux données
│   ├── ClientRepository.java
│   ├── CompteRepository.java
│   ├── TransactionRepository.java
│   └── implementation/               # Interfaces des repositories
│       ├── ClientRepositoryInterface.java
│       ├── CompteRepositoryInterface.java
│       └── TransactionRepositoryInterface.java
├── service/                          # Logique métier
│   ├── AuthService.java              # Service d'authentification
│   ├── ClientService.java            # Service de gestion des clients
│   ├── CompteService.java             # Service de gestion des comptes
│   ├── ReportService.java             # Service de génération de rapports
│   └── TransactionService.java       # Service de gestion des transactions
├── util/                             # Utilitaires
│   ├── IdGenerator.java              # Générateur d'ID uniques
│   ├── Validators.java               # Validateurs de données
│   └── exceptions/                   # Exceptions personnalisées
│       ├── InsufficientFundsException.java
│       └── InvalidTransactionException.java
└── view/                             # Interface utilisateur
    └── ConsoleView.java              # Interface console
```

## Technologies Utilisées
- **Java** : Langage de programmation principal
- **Architecture MVC** : Séparation des préoccupations
- **Collections Java** : Gestion des données en mémoire
- **BigDecimal** : Calculs monétaires précis
- **Optional** : Gestion des valeurs nulles

## Installation et Utilisation

### Prérequis
- Java JDK 8 ou plus récent
- Un terminal/invite de commande

### Compilation
```bash
javac -d . src/Main.java src/controller/*.java src/model/*.java src/model/enums/*.java src/repository/*.java src/repository/implementation/*.java src/service/*.java src/util/*.java src/util/exceptions/*.java src/view/*.java
```

### Exécution
```bash
java Main
```

## Guide d'utilisation

### 1. Démarrage
Au lancement, l'application présente trois options :
- **1) Espace Client** : Pour les clients existants
- **2) Espace Gestionnaire** : Pour les administrateurs
- **3) Seed demo data** : Pour créer des données de test
- **0) Quitter** : Pour fermer l'application

### 2. Espace Gestionnaire (Première utilisation recommandée)
1. Sélectionnez l'option 2
2. Créez un nouveau client (option 2)
3. Créez un compte pour ce client (option 4)
4. Le client peut maintenant utiliser l'espace client

### 3. Espace Client
1. Sélectionnez l'option 1
2. Entrez votre ID client (format: CL-xxx)
3. Accédez aux différentes fonctionnalités :
   - Consulter vos informations et comptes
   - Voir l'historique des transactions
   - Effectuer des dépôts, retraits, virements

## Types de Comptes Supportés
- **COURANT** : Compte courant classique
- **EPARGNE** : Compte d'épargne
- **DEPOTATERME** : Compte de dépôt à terme

## Types de Transactions
- **DEPOT** : Dépôt d'argent sur le compte
- **RETRAIT** : Retrait d'argent du compte
- **VIREMENT** : Transfert entre comptes

## Gestion des Erreurs
L'application gère plusieurs types d'erreurs :
- Client ou compte introuvable
- Fonds insuffisants pour les retraits/virements
- Transactions invalides
- Entrées utilisateur incorrectes

## Exemples d'utilisation

### Création d'un client (Admin)
1. Menu principal → Espace Gestionnaire
2. Sélectionner "Créer client"
3. Entrer : nom, prénom, email, mot de passe
4. L'ID client est généré automatiquement (ex: CL-001)

### Création d'un compte (Admin)
1. Utiliser l'ID client créé précédemment
2. Choisir le type de compte (Courant/Épargne/Dépôt à terme)
3. L'ID compte est généré automatiquement (ex: CP-001)

### Effectuer un dépôt (Client)
1. Se connecter avec l'ID client
2. Choisir "Faire un dépôt"
3. Sélectionner le compte (par numéro dans la liste)
4. Entrer le montant et le motif

## Contributeurs
Projet développé dans le cadre de la formation YouCode.

## Licence
Ce projet est à des fins éducatives.
