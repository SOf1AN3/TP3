
package TP3;

import java.util.Scanner;

/**
 * Université du Québec à Montréal (UQAM) 
 * INF1120 - 021 - Automne 2023 
 * Travail pratique 3
 * 
 * Classe GestionDesEchelonsEtDesTaux est responsable de la gestion
 * des notes d'évaluation de la performance, des échelons, des taux
 * de bonus, et des taux d'augmentation.
 * 
 * @author Votre prénom / votre nom
 * @since 20 novembre 2023
 *
 */
public class GestionDesEchelonsEtDesTaux {
   
    // Constantes pour les notes d'évaluation de la performance
    public static final char NOTE_DEPASSE = 'D';
    public static final char NOTE_ATTEINT = 'A';
    public static final char NOTE_PARTIELLEMENT_ATTEINT = 'P';
    public static final char NOTE_NON_ATTEINT = 'N';

    // Descriptions correspondantes aux notes d'évaluation de la performance
    public static final String DESCRIPTION_DEPASSE = "Dépasse";
    public static final String DESCRIPTION_ATTEINT = "Atteint";
    public static final String DESCRIPTION_PARTIELLEMENT_ATTEINT = "Partiellement Atteint";
    public static final String DESCRIPTION_NON_ATTEINT = "Non Atteint";

    // Constantes pour les échelons
    public static final int ECHELON_1 = 1;
    public static final int ECHELON_2 = 2;
    public static final int ECHELON_3 = 3;

    // Constantes pour les taux de bonus et d'augmentation de salaire
    public static final float TAUX_BONUS_ECHELON_1 = 0.05f;
    public static final float TAUX_BONUS_ECHELON_2 = 0.08f;
    public static final float TAUX_BONUS_ECHELON_3 = 0.1f;

    public static final float TAUX_AUGMENT_SALAIRE_ECHELON_1 = 0.03f;
    public static final float TAUX_AUGMENT_SALAIRE_ECHELON_2 = 0.05f;
    public static final float TAUX_AUGMENT_SALAIRE_ECHELON_3 = 0.08f;

   /**
    * Obtenir la description de la note d'évaluation de la performance
    * à partir de la note d'évaluation de la performance 
    * 
    * @param noteEvalPerf la note d'évaluation de la performance
    * @return la description de la note d'évaluation de la performance
    */
   public static String obtenirDescriptionNoteEvalPerf(char noteEvalPerf) {
      // PLUSIEURS RETOURS

      switch (noteEvalPerf) {
         case NOTE_DEPASSE:
            return DESCRIPTION_DEPASSE;
         case NOTE_ATTEINT:
            return DESCRIPTION_ATTEINT;
         case NOTE_PARTIELLEMENT_ATTEINT:
            return DESCRIPTION_PARTIELLEMENT_ATTEINT;
         case NOTE_NON_ATTEINT:
            return DESCRIPTION_NON_ATTEINT;
         default:
            return "Note inconnue";
      }
   }
   
   /**
    * Obtenir le taux de bonus à partir de l'échelon et de la note
    * d'évaluation de la performance de l'employé.
    * 
    * @param echelonEmpl échelon de l'employé
    * @param noteEvalPerfEmpl la note d'évaluation de la performance de l'employé
    * @return le taux de bonus
    */
   public static float obtenirTauxBonus(int echelonEmpl, char noteEvalPerfEmpl) {
      switch (echelonEmpl) {
         case ECHELON_1:
            return TAUX_BONUS_ECHELON_1;
         case ECHELON_2:
            return TAUX_BONUS_ECHELON_2;
         case ECHELON_3:
            return TAUX_BONUS_ECHELON_3;
         default:
            return 0.0f;
      }
   }
   
   /**
    * Obtenir le taux de l'augmentation de salaire à 
    * partir de l'échelon de l'employé saisi 
    * 
    * @param echelonEmpl échelon de l'employé
    * @return le taux de de l'augmentation de salaire
    */
   public static float obtenirTauxAugmentSalaire(int echelonEmpl) {
      
      switch (echelonEmpl) {
         case ECHELON_1:
            return TAUX_AUGMENT_SALAIRE_ECHELON_1;
         case ECHELON_2:
            return TAUX_AUGMENT_SALAIRE_ECHELON_2;
         case ECHELON_3:
            return TAUX_AUGMENT_SALAIRE_ECHELON_3;
         default:
            return 0.0f;
      }
   }
}
