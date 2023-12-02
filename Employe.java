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
        determinerTauxBonus();
        determinerTauxAugmentationSalaire();
        calculerMontantBonus();
        calculerMontantAugmentationSalaire();
    }
    public Employe(String matricule, String prenom, String nom, int echelon, double salaire) {
        this.matricule = matricule;
        this.prenom = prenom;
        this.nom = nom;
        this.echelon = echelon;
        this.salaire = salaire;
        determinerTauxBonus();
        determinerTauxAugmentationSalaire();
        calculerMontantBonus();
        calculerMontantAugmentationSalaire();
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
        tauxBonus = GestionDesEchelonsEtDesTaux.obtenirTauxBonus(echelon, noteEvaluation);
    }

    // Méthode pour déterminer le taux de l'augmentation de salaire
    public void determinerTauxAugmentationSalaire() {
        tauxAugmentationSalaire = GestionDesEchelonsEtDesTaux.obtenirTauxAugmentSalaire(echelon);
    }

    // Méthode pour calculer le montant de bonus
    public void calculerMontantBonus() {
        montantBonus = tauxBonus * salaire;
    }

    // Méthode pour calculer le montant de l'augmentation de salaire
    public void calculerMontantAugmentationSalaire() {
        montantAugmentationSalaire = tauxAugmentationSalaire * salaire;
    }

    // Redéfinition de la méthode toString()
    @Override
    public String toString() {
        return String.format("%s | %s %s | %d | %s | %.2f$ | %.2f | %.2f$ | %.2f | %.2f$ | %.2f$",
                matricule, prenom, nom, echelon, GestionDesEchelonsEtDesTaux.obtenirDescriptionNoteEvalPerf(noteEvaluation),
                salaire, tauxBonus, montantBonus, tauxAugmentationSalaire, montantAugmentationSalaire, salaire + montantAugmentationSalaire);
    }
}