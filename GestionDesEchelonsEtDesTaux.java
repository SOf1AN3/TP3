
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

    public static final char NOTE_DEPASSE_MIN = 'd';
    public static final char NOTE_ATTEINT_MIN = 'a';
    public static final char NOTE_PARTIELLEMENT_ATTEINT_MIN = 'p';
    public static final char NOTE_NON_ATTEINT_MIN = 'n';
 
    // Constantes pour les échelons
    public static final int ECHELON_1 = 1;
    public static final int ECHELON_2 = 2;
    public static final int ECHELON_3 = 3;

   /**
    * Obtenir la description de la note d'évaluation de la performance
    * à partir de la note d'évaluation de la performance 
    * 
    * @param noteEvalPerf la note d'évaluation de la performance
    * @return la description de la note d'évaluation de la performance
    */
   public static String obtenirDescriptionNoteEvalPerf(char noteEvalPerf) {
      String description = "Note inconnue";
      switch (noteEvalPerf) {
         case NOTE_DEPASSE:
            description = "dépasse";
            break;
         case NOTE_ATTEINT:
            description = "atteint";
            break;
         case NOTE_PARTIELLEMENT_ATTEINT:
            description = "partiellement atteint";
            break;
         case NOTE_NON_ATTEINT:
            description = "non atteint";
            break;
         default:
            description = "Note inconnue";
      }
      return description;
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
      float taux_bonus = 0;
        if(echelonEmpl == 1)
            {
                if(noteEvalPerfEmpl == 'D' || noteEvalPerfEmpl == 'd') taux_bonus = 8.50f;
                if(noteEvalPerfEmpl == 'A' || noteEvalPerfEmpl == 'a') taux_bonus = 6.00f;
                if(noteEvalPerfEmpl == 'P' || noteEvalPerfEmpl == 'p') taux_bonus = 4.50f;
            }
        else if(echelonEmpl == 2)
            {
                if(noteEvalPerfEmpl == 'D' || noteEvalPerfEmpl == 'd') taux_bonus = 12.50f;
                if(noteEvalPerfEmpl == 'A' || noteEvalPerfEmpl == 'a') taux_bonus = 10.50f;
                if(noteEvalPerfEmpl == 'P' || noteEvalPerfEmpl == 'p') taux_bonus = 8.00f;
            }
        else if(echelonEmpl == 3)
            {
                if(noteEvalPerfEmpl == 'D' || noteEvalPerfEmpl == 'd') taux_bonus = 15.00f;
                if(noteEvalPerfEmpl == 'A' || noteEvalPerfEmpl == 'a') taux_bonus = 12.50f;
                if(noteEvalPerfEmpl == 'P' || noteEvalPerfEmpl == 'p') taux_bonus = 10.50f;
            }
            return taux_bonus;
   }
   
   /**
    * Obtenir le taux de l'augmentation de salaire à 
    * partir de l'échelon de l'employé saisi 
    * 
    * @param echelonEmpl échelon de l'employé
    * @return le taux de de l'augmentation de salaire
    */
   public static float obtenirTauxAugmentSalaire(int echelonEmpl) {
      
      float taux = 0;
        if(echelonEmpl == 1) taux = 3.5f;
        if(echelonEmpl == 2) taux = 3.75f;
        if(echelonEmpl == 3) taux = 4f;
        return taux;
      }
   }
