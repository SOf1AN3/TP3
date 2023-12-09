package TP3;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



/**
 * Université du Québec à Montréal (UQAM) 
 * INF1120 - 021 - Automne 2023 
 * Travail pratique 3
 * 
 * Classe Entreprise gère la liste des employés. Elle contient les méthodes qui
 * permettent de lire les données des employés à partir d'un fichier, d'écrire
 * les données des employés dans un fichier, de rechercher un employé à partir
 * de son matricule, de calculer les montants de bonus et de l'augmentation de 
 * salaire, et d'afficher les données de tous les employés à l'écran. 
 * 
 * @author Votre prénom / votre nom
 * @since 20 novembre 2023
 *
 */

public class Entreprise {

    public static final String NOM_ENTREPRISE       = "Jeux Vidéo Pour Tous";
    public static final String ADRESSE_ENTREPRISE   = "2500 rue Hakuna, Matata, Québec J3W 4N3";
    public static final String TELEPHONE_ENTREPRISE = "(514) 884-7373";

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    public static final String MSG_REMERCIEMENT       = "Merci pour votre confiance!\n";

    private static final int    MAX_EMPLOYES                  = 25;
    private static final String FIC_EMPLOYES                  = "C:\\Users\\PC\\Desktop\\TP3\\LesEmployes.csv";
    private static final String FIC_EMPLOYES_APRES_TRAITEMENT = "C:\\Users\\PC\\Desktop\\TP3\\LesEmployesApresTraitement.csv";

    private static Employe [] lesEmployes = new Employe[MAX_EMPLOYES];

    /**
     * Cette méthode affiche le nom, l'adresse, et le numéro de téléphone
     * de l'entreprise. Elle affiche aussi la date et l'heure.
     */
    public static void afficherInformationsEntreprise() {
        
        System.out.println("\n  ============================================================================");
        System.out.println("    " + NOM_ENTREPRISE);
        System.out.println("    Adresse       : " + ADRESSE_ENTREPRISE);
        System.out.println("    Téléphone     : " + TELEPHONE_ENTREPRISE);
        System.out.println("    Date et Heure :  " + LocalDateTime.now().format(FORMATTER));
        System.out.println("  ============================================================================");
        
    }

    /**
     * Lire les données des employés dans le fichier LesEmployes.csv. Chaque
     * ligne est composée de : 
     *    - Matricule 
     *    - Prénom 
     *    - Nom 
     *    - échelon 
     *    - Note d'évaluation de la performance - Salaire
     * 
     * La première ligne dans ce fichier est la description des autres lignes et
     * elle doit être ignorée lors de la lecture. Les autres lignes de ce fichier
     * sont composées des données ci-dessus mentionnées séparées entre elles par
     * des points-virgules. Voir le fichier "LesEmployes.csv" pour plus de détails.
     * 
     * Chacune de ces lignes doit être lue et découpée pour créer un objet de
     * type Employe, et cet objet doit être ajouté dans le tableau des employés.
     * La première ligne doit être ignorée lors de la lecture.
     * 
     */
    public static void lireFichierEmployes() {
        try (BufferedReader br = new BufferedReader(new FileReader(FIC_EMPLOYES))) {
            String line;
            int index = 0;
    
            // Ignorer la première ligne (description)
            br.readLine();
    
            while ((line = br.readLine()) != null && index < MAX_EMPLOYES) {
                // Découper la ligne en utilisant le point-virgule comme séparateur
                String[] data = line.split(";");
    
                // Créer un objet Employe et l'ajouter au tableau
                lesEmployes[index++] = new Employe(data[0], data[1], data[2],Integer.parseInt(data[3]),
                    data[4].charAt(0) , Double.parseDouble(data[5]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    /**
     * Écrire les données de toutes les employés dans le fichier
     * LesEmployesApresTraitement.csv.
     * 
     * Chaque employé doit être écrit sur une seule ligne dans le fichier.
	 * Les données de chaque ligne doivent être separées entre elles par 
	 * des points-virgules.
     *
     * Pour plus de détails, voir le fichier LesEmployesApresTraitement.csv.
     * 
     */
    
    public static void ecrireLesEmployes() {
        try (PrintWriter writer = new PrintWriter(FIC_EMPLOYES_APRES_TRAITEMENT)) {
            // Écrire l'en-tête du fichier CSV
            writer.println("Matricule;Prénom et nom;échelon;Description de la note d'évaluation de la performance;salaire;Taux de bonus;Montant de bonus;Taux de l'augmentation de salaire;Montant de l'augmentation de salaire;Nouveau salaire");

            for (int i = 0; i < MAX_EMPLOYES && lesEmployes[i] != null; i++) {
                
                lesEmployes[i].determinerTauxBonus();
                lesEmployes[i].calculerMontantBonus();
                lesEmployes[i].determinerTauxAugmentationSalaire();
                lesEmployes[i].calculerMontantAugmentationSalaire(); 
                // Utiliser String.format pour formater la ligne
                String ligne = String.format("%-8s | %-25s | %-1d | %-22s | %-10s | %-5.2f | %-9s | %-5.2f | %-9s | %-10s ",
                        lesEmployes[i].getMatricule(), 
                        lesEmployes[i].getPrenom() + " " + lesEmployes[i].getNom(),
                        lesEmployes[i].getEchelon(),
                        GestionDesEchelonsEtDesTaux.obtenirDescriptionNoteEvalPerf(lesEmployes[i].getNoteEvaluation()),
                        formatMontant(lesEmployes[i].getSalaire()), 
                        lesEmployes[i].getTauxBonus(),
                        formatMontant(lesEmployes[i].getMontantBonus()),
                        lesEmployes[i].getTauxAugmentationSalaire(),
                        formatMontant(lesEmployes[i].getMontantAugmentationSalaire()),
                        formatMontant(lesEmployes[i].getSalaire() + lesEmployes[i].getMontantAugmentationSalaire()));

                // Écrire la ligne dans le fichier
                writer.println(ligne);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    // Méthode pour formater les montants en ajoutant le symbole "$"
    private static String formatMontant(double montant) {
        return String.format("%.2f$", montant);
    }
    

    /**
     * Cette méthode affiche tous les employés qui sont dans le tableau 
     * des employés en appelant implicitement la méthode toString de la
     * classe Employe. Ainsi, les attributs de chaque employé doivent 
     * être affichés sur la même ligne et séparés entre eux par une barre 
     * verticale.
     *  
     */
    public static void afficherLesEmployes() {
        for (int i = 0; i < MAX_EMPLOYES && lesEmployes[i] != null; i++) {
            // Appeler la méthode toString de la classe Employe
            System.out.println(lesEmployes[i].toString());
        }
    }
    

    /**
     * Cette méthode recherche l'employé dans le tableau des employés 
     * (lesEmployes) dont le matricule est égal au matricule passé en
     * paramètre (sans tenir compte de la casse). Si aucun employé
     * n'est trouvé, cette méthode retourne null. 
     * 
     * @param matricule le matricule de l'employé
     * @return l'employé dont le matricule est au paramètre, ou null
     */
    public static Employe rechercherParMatricule(String matricule) {
        for (int i = 0; i < MAX_EMPLOYES && lesEmployes[i] != null; i++) {
            // Comparer le matricule sans tenir compte de la casse
            if (lesEmployes[i].getMatricule().equalsIgnoreCase(matricule)) {
                return lesEmployes[i];
            }
        }
        return null; // Aucun employé trouvé avec ce matricule
    }
    

    /**
     * Cette méthode calcule le montant de bonus de l'employé dont le matricule est égal
     * au matricule passé en paramètre (sans tenir compte de la casse). Vous devez d'abord
     * rechercher l'employé dans le tableau des employés (lesEmployes). Une fois l'employé
     * trouvé, vous devez déterminer le taux de bonus et calculer le montant de bonus.
     * Si aucun employé avec ce matricule n'est trouvé, cette méthode retourne null.
     *
     * @param matricule le matricule de l'employé
     * @return l'employé dont le montant de bonus est calculé, ou null
     */
    public static Employe calculerMontantBonus(String matricule) {
        Employe employe = rechercherParMatricule(matricule);

        if (employe != null) {
            employe.determinerTauxBonus();
            employe.calculerMontantBonus();
        }

        return employe;
    }



    /**
     * Cette méthode calcule le montant de l'augmentation de salaire de l'employé dont 
     * le matricule est égal au matricule passé en paramètre (sans tenir compte de la casse). 
     * Vous devez d'abord rechercher l'employé dans dans le tableau des employés (lesEmployes),
     * une fois l'employé est trouvé, vous devez déterminer le taux de l'augmentation de salaire
     * et calculer le montant de l'augmentation de salaire. Si aucun employé avec ce matricule 
     * n'est trouvé, cette méthode retourne null. 
     * 
     * @param matricule le matricule de l'employé
     * @return l'employé dont le montant de l'augmentation de salaire est calculé, ou null
     */
    public static Employe calculerMontantAugSalaire(String matricule) {
        Employe employe = rechercherParMatricule(matricule);

        if (employe != null) {
            employe.determinerTauxAugmentationSalaire();
            employe.calculerMontantAugmentationSalaire();
        }
        return employe;
    }

    /**
     * Cette méthode calcule les montants de bonus et de l’augmentation de salaire 
     * de tous les employés qui sont dans le tableau des employés (lesEmployes).
     *  
     */
    public static void calculerMontantsBonusEtAugSalaire() {

        for (int i = 0; i < MAX_EMPLOYES && lesEmployes[i] != null; i++) {
                 lesEmployes[i].calculerMontantBonus();
                 lesEmployes[i].calculerMontantAugmentationSalaire();

        }
    }

}
