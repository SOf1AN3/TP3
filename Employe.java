package TP3;

public class Employe {

    private String matricule, prenom, nom;
    private char noteEvaluation;
    private int echelon;
    private double salaire, tauxBonus, tauxAugmentationSalaire, montantBonus, montantAugmentationSalaire;

    // Constructeur
    public Employe(String matricule, String prenom, String nom, int echelon , char noteEvaluation, double salaire) {
        this.matricule = matricule;
        this.prenom = prenom;
        this.nom = nom;
        this.noteEvaluation = noteEvaluation;
        this.echelon = echelon;
        this.salaire = salaire;
    }

    // Getters
    public String getMatricule() {
        return matricule;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public char getNoteEvaluation() {
        return noteEvaluation;
    }

    public int getEchelon() {
        return echelon;
    }

    public double getSalaire() {
        return salaire;
    }

    public double getTauxBonus() {
        return tauxBonus;
    }

    public double getTauxAugmentationSalaire() {
        return tauxAugmentationSalaire;
    }

    public double getMontantBonus() {
        return montantBonus;
    }

    public double getMontantAugmentationSalaire() {
        return montantAugmentationSalaire;
    }
    

    // Méthode pour déterminer le taux de bonus
    public void determinerTauxBonus() {
        this.tauxBonus = GestionDesEchelonsEtDesTaux.obtenirTauxBonus(echelon, noteEvaluation);
    }

    // Méthode pour déterminer le taux de l'augmentation de salaire
    public void determinerTauxAugmentationSalaire() {
        this.tauxAugmentationSalaire = GestionDesEchelonsEtDesTaux.obtenirTauxAugmentSalaire(echelon);
    }

    // Méthode pour calculer le montant de bonus
    public void calculerMontantBonus() {
        this.montantBonus = tauxBonus * salaire / 100;
    }

    // Méthode pour calculer le montant de l'augmentation de salaire
    public void calculerMontantAugmentationSalaire() {
        this.montantAugmentationSalaire = tauxAugmentationSalaire * salaire / 100;
    }


    // Redéfinition de la méthode toString()
    
    public String toString() {
        return String.format("%-8s | %-25s | %-1d | %-22s | %-10s | %-5.2f | %-9s | %-4.2f | %-8s | %-10s ",
        matricule, prenom + " " + nom, echelon, GestionDesEchelonsEtDesTaux.obtenirDescriptionNoteEvalPerf(noteEvaluation),
        formatMontant(salaire), tauxBonus, formatMontant(montantBonus), tauxAugmentationSalaire, formatMontant(montantAugmentationSalaire),
        formatMontant(salaire + montantAugmentationSalaire));


    }

    // Méthode pour formater les montants en ajoutant le symbole "$"
    private String formatMontant(double montant) {
        return String.format("%.2f$", montant);
}
}   
