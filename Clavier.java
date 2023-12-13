

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Clavier {
   private static boolean finDeLigneLue;
   private static final char FIN_FICHIER = '\uffff';
   private static InputStream sourceLecture;
   private static final InputStream SOURCE_LECTURE_STANDARD;

   public Clavier() {
   }

   public static void viderTampon() {
      try {
         System.in.skip((long)System.in.available());
      } catch (IOException var1) {
         System.out.println("Le tampon d'entree ne peut etre vide.");
      }

   }

   public static void lireFinLigne() {
      lireString();
   }

   public static String lireString() {
      String resultat = "";
      boolean fini = false;

      while(!fini) {
         char prochainCaractere = lireChar();
         if (prochainCaractere == '\uffff') {
            fini = true;
         } else if (prochainCaractere == '\r') {
            prochainCaractere = lireChar();
            if (prochainCaractere == '\n') {
               fini = true;
            } else {
               System.out.println("Erreur fatale dans la methode lireLigne de la classe Clavier.");
               System.exit(1);
            }
         } else if (prochainCaractere == '\n') {
            fini = true;
         } else {
            resultat = resultat + prochainCaractere;
         }
      }

      return resultat;
   }

   public static byte lireByte() {
      return Byte.parseByte(lireMot());
   }

   public static byte lireByteLn() {
      byte byteLu = Byte.parseByte(lireMot());
      if (!finDeLigneLue) {
         lireFinLigne();
      }

      return byteLu;
   }

   public static short lireShort() {
      return Short.parseShort(lireMot());
   }

   public static short lireShortLn() {
      short shortLu = Short.parseShort(lireMot());
      if (!finDeLigneLue) {
         lireFinLigne();
      }

      return shortLu;
   }

   public static int lireInt() {
      return Integer.parseInt(lireMot());
   }

   public static int lireIntLn() {
      int intLu = Integer.parseInt(lireMot());
      if (!finDeLigneLue) {
         lireFinLigne();
      }

      return intLu;
   }

   public static long lireLong() {
      return Long.parseLong(lireMot());
   }

   public static long lireLongLn() {
      long longLu = Long.parseLong(lireMot());
      if (!finDeLigneLue) {
         lireFinLigne();
      }

      return longLu;
   }

   public static float lireFloat() {
      return Float.parseFloat(lireMot());
   }

   public static float lireFloatLn() {
      float floatLu = Float.parseFloat(lireMot());
      if (!finDeLigneLue) {
         lireFinLigne();
      }

      return floatLu;
   }

   public static double lireDouble() {
      return Double.parseDouble(lireMot());
   }

   public static double lireDoubleLn() {
      double doubleLu = Double.parseDouble(lireMot());
      if (!finDeLigneLue) {
         lireFinLigne();
      }

      return doubleLu;
   }

   public static char lireChar() {
      int codeAscii = -1;

      try {
         codeAscii = System.in.read();
      } catch (IOException var2) {
      }

      return (char)codeAscii;
   }

   public static char lireCharLn() {
      String ligneLue;
      do {
         ligneLue = lireString();
      } while(ligneLue.length() == 0);

      return ligneLue.charAt(0);
   }

   private static String lireMot() {
      String resultat = "";
      finDeLigneLue = false;

      char car;
      do {
         car = lireChar();
      } while(Character.isWhitespace(car));

      while(car != '\uffff' && !Character.isWhitespace(car)) {
         resultat = resultat + car;
         car = lireChar();
      }

      if (car == '\n') {
         finDeLigneLue = true;
      } else if (car == '\r') {
         finDeLigneLue = true;
         car = lireChar();
         if (car != '\n') {
            System.out.println("Erreur fatale dans la methode lireMot de la classe Clavier.");
            System.exit(1);
         }
      }

      return resultat;
   }

   public static void redirigerEntree(String cheminFichier) {
      try {
         fermerSourceLecture();
         sourceLecture = new FileInputStream(cheminFichier);
         System.setIn(sourceLecture);
      } catch (FileNotFoundException var2) {
         var2.printStackTrace();
      }

   }

   public static void retablirEntree() {
      fermerSourceLecture();
      sourceLecture = SOURCE_LECTURE_STANDARD;
      System.setIn(sourceLecture);
   }

   private static void fermerSourceLecture() {
      try {
         if (sourceLecture != SOURCE_LECTURE_STANDARD) {
            sourceLecture.close();
         }
      } catch (IOException var1) {
         var1.printStackTrace();
      }

   }

   static {
      sourceLecture = System.in;
      SOURCE_LECTURE_STANDARD = System.in;
   }
}
