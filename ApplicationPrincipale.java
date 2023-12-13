import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * Université du Québec à  Montréal (UQAM) 
 * INF1120 - 021 - Automne 2023 
 * Travail pratique 3
 * 
 * Classe ApplicationPrincipale contient les méthodes d'affichage du menu
 * d'options, de saisies et de validations du choix de menu et du matricule.
 * Également elle contient la méthode "main". Cette classe permet de tester
 * toutes les autres classes en créant des objets et en appelant leurs méthodes
 * lors de l'application des règles d'affaires liées aux différentes options du
 * menu principal.
 * 
 * @author Votre prénom / votre nom
 * @since 20 novembre 2023
 *
 */
public class ApplicationPrincipale {

    // Déclaration des constantes
    public static final int CHOIX_1 = 1;
    public static final int CHOIX_2 = 2;
    public static final int CHOIX_3 = 3;
    public static final int CHOIX_4 = 4;
    public static final int CHOIX_5 = 5;
    public static final int CHOIX_6 = 6;

    public static final String MENU = """
         \n  *** Menu de choix ***
         \n  1. Calculer le montant de bonus d'un employé
           2. Calculer le montant de l’augmentation de salaire d'un employé
           3. Calculer les montants de bonus et de l’augmentation de salaire d'un employé
           4. Calculer les montants de bonus et de l’augmentation de salaire de tous les employés
           5. Afficher tous les employés
           6. Quitter le programme\n\n""";

    public static final char   DOLLAR                    = '$';
    public static final String QUITTER                   = "Q";
    public static final String MSG_FIN                   = "\n\n  Merci et à la prochaine ! ";
    public static final String MSG_RETOUR_MENU_PRINCIPAL = "\n  Retour dans le menu principal!";
    public static final String MSG_AUCUN_EMPLOYE         = "\n  Aucun employé avec ce matricule!";
    public static final String MSG_TOUS_LES_EMPLOYES     = "\n  Les montants de bonus et de l'augmentation"
                                                                + " de salaire sont calculés pour tous les employés!";
    public static final String MSG_FIN_OPERATION = "\n\n\t\t------------------------------------------------------\n 		           Merci pour votre confiance!";                                                   
    /**
     * Affiche le message de bienvenue
     */
    public static void afficherMessageBienvenue() {

        System.out.println(MENU);
    }

    /**
     * Saisit et valide le choix de menu de l'utilisateur entre 1 et 6
     * inclusivement. 
     * 
     * Vous devez gérer l'exception NumberFormatException dans cette 
     * méthode en ajoutant un bloc try / catch. Ainsi, lorsqu'un 
     * utilisateur entre un caractère autre qu'un entier, la méthode
     * ne doit pas "planter".
     * 
     * @return l'option du menu choisi par l'utilisateur
     */
    public static int saisirValiderChoixMenu() {
        boolean est_pas_valide = true;
        int bon_choix = 0;

        while (est_pas_valide) {
            est_pas_valide = false;
            try {
                System.out.print("Veuillez saisir un choix entre 1 et 6 inclusivement : ");
                String userInput = Clavier.lireString();
                int choix = Integer.parseInt(userInput);

                // Validate that the choice is between 1 and 6
                if (choix >= 1 && choix <= 6) {
                    bon_choix = choix;
                } else {
                    System.out.println("Choix invalide. Veuillez réessayer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Veuillez entrer un nombre entier.");
                // Clear the buffer
                Clavier.lireFinLigne();
                est_pas_valide = true;
            }
            }
            return bon_choix;
        }

    public static String saisirValiderMatriculeEmploye() {
        String matricule;
        boolean est_pas_valide = false;
        int i;
        char character;

        do {
            est_pas_valide = false;
            System.out.print("Veuillez entrer le matricule de l'employé (ou q/Q pour quitter) : ");
            matricule = Clavier.lireString();

            if (matricule.length() == 1 && matricule.charAt(0) == 'q' || matricule.charAt(0) == 'Q') {
                return "q";
            }

            // Validate the matricule format
            if (matricule.length() == 8) {
                // Verifier si les 4 premiers caracteres sont des lettres :
                for(i=0; i<4; i++)
                {   
                    character = matricule.charAt(i);
                    if(!Character.isLetter(character)){
                        est_pas_valide = true;
                    }
                }
                // Verifier si les 4 derniers caracteres sont des chiffres :
                for(i=4; i<8; i++)
                {   
                    character = matricule.charAt(i);
                    if(!Character.isDigit(character)){
                        est_pas_valide = true;
                    }
                }
                
            } else {
                System.out.println("Le matricule est invalide! Veuillez réessayer.");
                est_pas_valide = true;
            }
        } while (est_pas_valide);

        return matricule;
    }

    /**
     * Cette méthode recherche un employé à partir de son matricule. Elle fait
     * appel aux méthodes 'saisirValiderMatriculeEmploye' et
     * Entreprise.rechercherParMatricule.
     * 
     * @return l'employé dont le matricule est saisi ou null si aucun employé
     *         avec ce matricule
     */
    public static Employe rechercherEmploye() {

        String matricule;
        Employe employe = null;

        do {
            // Saisir et valider le matricule de l'employé
            matricule = saisirValiderMatriculeEmploye();

            if (matricule.equalsIgnoreCase(QUITTER)) {
                System.out.println(MSG_RETOUR_MENU_PRINCIPAL);

            } else {
                employe = Entreprise.rechercherParMatricule(matricule);
                if (employe == null) {
                    System.out.println(MSG_AUCUN_EMPLOYE);
                }
            }

        } while (employe == null && !matricule.equalsIgnoreCase(QUITTER));

        return employe;
    }

    /**
     * Cette methode marque une pause en demandant a l'utilisateur d'appuyer sur
     * la touche [ENTREE] pour continuer.
     */
    public static void pause() {
        System.out.print("\n  Appuyer sur <ENTREE> pour réafficher le menu...");
        Clavier.lireFinLigne();
    }

    /**
     * Afficher les informations de l'employé après le calcul du montant de bonus
     * (Option 1)
     * 
     * @param employe l'employé dont les informations doivent être affichés.
     */
    public static void afficherInformationsOption1(Employe employe) {

        Entreprise.afficherInformationsEntreprise();
        System.out.println("Prenom et Nom : " + employe.getPrenom() + " " + employe.getNom());
        System.out.println("Matricule     : " + employe.getMatricule());
        System.out.println("Echelon       : " + employe.getEchelon());
        System.out.println("Salaire       : " + String.format("%.2f", employe.getSalaire()) + DOLLAR +"\n");
        System.out.println("Taux de bonus  : " + employe.getTauxBonus());
        System.out.println("Montant bonus : " +  String.format("%.2f", employe.getMontantBonus()) + DOLLAR);
        System.out.println("Note d'évaluation de la performance : " + GestionDesEchelonsEtDesTaux.obtenirDescriptionNoteEvalPerf(employe.getNoteEvaluation()));

        System.out.println("\n  " + MSG_RETOUR_MENU_PRINCIPAL);
    }

    /**
     * Afficher les informations de l'employé après le calcul du montant de
     * l’augmentation de salaire, et du nouveau salaire (Option 2)
     * 
     * @param employe l'employé dont les informations doivent être affichés.
     */
    public static void afficherInformationsOption2(Employe employe) {

        Entreprise.afficherInformationsEntreprise();
        System.out.println("Prenom et Nom : " + employe.getPrenom() + " " + employe.getNom());
        System.out.println("Matricule     : " + employe.getMatricule());
        System.out.println("Echelon       : " + employe.getEchelon());
        System.out.println("Salaire       : " + String.format("%.2f", employe.getSalaire()) + DOLLAR +"\n");

        System.out.println("Taux d'augmentation de salaire : " + employe.getTauxAugmentationSalaire());
        System.out.println("Montant augmentation salaire : " + String.format("%.2f", employe.getMontantAugmentationSalaire()) + DOLLAR);
        System.out.println("Nouveau salaire : "  + String.format("%.2f",(employe.getSalaire() + employe.getMontantAugmentationSalaire())) + DOLLAR);
        System.out.println("\n  " + MSG_RETOUR_MENU_PRINCIPAL);

    }

    /**
     * Afficher les informations de l'employé après le calcul des montants de
     * bonus, de l’augmentation de salaire, et du nouveau salaire (Option 3)
     * 
     * @param employe l'employé dont les informations doivent être affichés.
     */
    public static void afficherInformationsOption3(Employe employe) {

        Entreprise.afficherInformationsEntreprise();
        System.out.println("Prenom et Nom : " + employe.getPrenom() + " " + employe.getNom());
        System.out.println("Matricule     : " + employe.getMatricule());
        System.out.println("Echelon       : " + employe.getEchelon());
        System.out.println("Salaire       : " + String.format("%.2f", employe.getSalaire()) + DOLLAR +"\n");
        System.out.println("Taux de bonus  : " + employe.getTauxBonus());
        System.out.println("Montant bonus : " + String.format("%.2f", employe.getMontantBonus()) + DOLLAR);
        System.out.println("Note d'évaluation de la performance : " + GestionDesEchelonsEtDesTaux.obtenirDescriptionNoteEvalPerf(employe.getNoteEvaluation()));

        System.out.println("\n");
        System.out.println("Taux d'augmentation de salaire : " + employe.getTauxAugmentationSalaire());
        System.out.println("Montant augmentation salaire : " + String.format("%.2f", employe.getMontantAugmentationSalaire()) + DOLLAR);
        System.out.println("Nouveau salaire : " + String.format("%.2f",(employe.getSalaire() + employe.getMontantAugmentationSalaire())) + DOLLAR);
        System.out.println("\n  " + MSG_RETOUR_MENU_PRINCIPAL);
    }

    // Le point d'entrée de la machine virtuelle
    public static void main(String[] args) {

        // Déclaration des variables locales
        boolean sortie = false;
        Employe employe = null;

        int choixMenu;

        // Message de bienvenue
        afficherMessageBienvenue();

        // Lire les données dans le fichier
        Entreprise.lireFichierEmployes();

        do {

            // Affichage et saisie du choix d'options
            choixMenu = saisirValiderChoixMenu();

            if (choixMenu == CHOIX_1 || choixMenu == CHOIX_2 || choixMenu == CHOIX_3) {
                employe = rechercherEmploye();
            }

            // Si l'objet employe est différent de null
            if (employe != null) {

                if (choixMenu == CHOIX_1 || choixMenu == CHOIX_3) {

                    // Calculer le montant de bonus d'un employé
                    Entreprise.calculerMontantBonus(employe.getMatricule());
                }


                
                if (choixMenu == CHOIX_2 || choixMenu == CHOIX_3) {
                    // Calculer le montant de l'augmentation de salaire
                    // d'un employé
                    
                    Entreprise.calculerMontantAugSalaire(employe.getMatricule());
                }

            }

            if (choixMenu == CHOIX_4) {
                // Calculer les montants de bonus et de l'augmentation de salaire
                // de tous les employés
                Entreprise.calculerMontantsBonusEtAugSalaire();
                Entreprise.ecrireLesEmployes();
            }

            if (employe != null) {

                if (choixMenu == CHOIX_1) {

                    // Afficher les informations après le calcul du montant 
                    // de bonus d'un employé (Option 1)
                    afficherInformationsOption1(employe);
                    System.out.println(MSG_FIN_OPERATION);
                    // Cette méthode marque une pause en demandant à l'utilisateur
                    // d'appuyer sur la touche [ENTREE] pour continuer
                    pause();

                } else if (choixMenu == CHOIX_2) {

                    // Afficher les informations après le calcul du montant de
                    // l’augmentation de salaire d'un employé (Option 2)
                    afficherInformationsOption2(employe);
                    System.out.println(MSG_FIN_OPERATION);
                    // Cette methode marque une pause en demandant à l'utilisateur
                    // d'appuyer sur la touche [ENTREE] pour continuer
                    pause();

                } else if (choixMenu == CHOIX_3) {

                    // Afficher les informations après le calcul des montants de
                    // bonus, et de l’augmentation de salaire d'un employé
                    afficherInformationsOption3(employe);
                    System.out.println(MSG_FIN_OPERATION);
                    // Cette méthode marque une pause en demandant à l'utilisateur
                    // d'appuyer sur la touche [ENTREE] pour continuer
                    pause();

                } 

            }

            if (choixMenu == CHOIX_4) {
                // Afficher un message après le calcul des montants de
                // bonus, de l’augmentation de salaire de tous les employés
                System.out.println(MSG_TOUS_LES_EMPLOYES);
                // Cette méthode marque une pause en demandant à l'utilisateur
                // d'appuyer sur la touche [ENTREE] pour continuer
                pause();

            } else if (choixMenu == CHOIX_5) {
                // Afficher tous les employés
                Entreprise.afficherInformationsEntreprise();
                Entreprise.afficherLesEmployes();
                System.out.println(MSG_FIN_OPERATION);

                // Cette méthode marque une pause en demandant à l'utilisateur
                // d'appuyer sur la touche [ENTREE] pour continuer
                pause();

            } else if (choixMenu == CHOIX_6) {

                // Afficher le message de fin
                System.out.println(MSG_FIN);

                // Écrire les employés dans le fichier
                Entreprise.ecrireLesEmployes();

                sortie = true;

            }
        } while (!sortie);

    }

}
