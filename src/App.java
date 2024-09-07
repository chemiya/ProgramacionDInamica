import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Segunda práctica de la asignatura Análisis y Diseño de Algoritmos
 * del grado de Ingeniería Informática en la Universidad de Valladolid.
 * 
 * @author
 * @author
 * @author
 */
public class App {
   static ArrayList<Contenedor> contenedores = new ArrayList<Contenedor>();
   static int numContenedores;
   static ArrayList<Contenedor> pilaOrdenadaMax = new ArrayList<Contenedor>();

   public static void main(String[] arg) {
      ArrayList<Contenedor> pilaInit = new ArrayList<Contenedor>();

      // Leemos fichero
      contenedores = leerFichero();

      // Calculamos pila
      try {
         // Ordenamos sacamos de los contenedores la pila máxima
         obtenerPilaBACKTRACKING(0, pilaInit);
      } catch (Error e) {
         System.out.println("\n\nError encontrado:   " + e);
      }

      // Imprime el resultado de la pila de contenedores máxima calculada
      imprimirResultado(pilaOrdenadaMax);
   }

   /**
    * Lee el fichero en directorio "/src/entrada.txt", sacando de él todos los
    * contenedores que hay con su peso y carga a soportar.
    */
   public static ArrayList<Contenedor> leerFichero() {

      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;

      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).

         archivo = new File("src/entrada.txt");
         System.out.println("Intentando leer el fichero con la ruta: " + archivo.getCanonicalPath());
         fr = new FileReader(archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String linea;
         int peso;
         int carga_a_soportar;

         while ((linea = br.readLine()) != null) {

            System.out.println(linea);

            // Leo el peso y la carga a soportar, separadas por un espacio " "
            String[] valores = linea.split(" ");
            if (valores.length == 1) { // Es el numero de contenedores eespecificado en la primera linea
               numContenedores = Integer.parseInt(valores[0]);
            } else { // Son las lineas de los demás contenedores
               peso = Integer.parseInt(valores[0]);
               carga_a_soportar = Integer.parseInt(valores[1]);
               contenedores.add(new Contenedor(contenedores.size(), peso, carga_a_soportar));
            }
         }

         /**
          * System.out.println("El número de contenedores leido es: " + numContenedores +
          * "\n");
          * System.out.println("El número de contenedores calculado es: " +
          * contenedores.size() + "\n");
          * System.out.println("El contenedor con id 1 tiene un peso: " +
          * contenedores.get(0).getPeso() + "\n"
          * + "y una carga a soportar de : " + contenedores.get(0).getCarga_a_soportar()
          * + "\n");
          * System.out.println("El contenedor con id " + numContenedores + " tiene un
          * peso: " + contenedores.get(numContenedores-1).getPeso()
          * + "\n" + "y una carga a soportar de : " +
          * contenedores.get(numContenedores-1).getCarga_a_soportar() + "\n");
          */

      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta
         // una excepcion.
         try {
            if (null != fr) {
               fr.close();
            }
         } catch (Exception e2) {
            e2.printStackTrace();
         }
      }
      return contenedores;
   }

   /**
    * Comprueba si un contenedor se puede añadir a una pila
    * 
    * @param contenedor Contenedor a apilar
    * @param pila       Pila donde introducir el contenedor
    * @return TRUE si puede, FALSE si no
    */
   private static boolean aceptable(Contenedor contenedor, ArrayList<Contenedor> pila) {

      boolean aceptable = false;

      // la primera carga a soportar es la pila más baja
      int carga_soportar_total = pila.get(0).getCarga_a_soportar();

      // calcula carga a soportar de la pila
      for (int i = 1; i < pila.size(); i++) {

         // restamos el peso
         carga_soportar_total = carga_soportar_total - pila.get(i).getPeso();

         if (carga_soportar_total >= pila.get(i).getCarga_a_soportar()) {
            carga_soportar_total = pila.get(i).getCarga_a_soportar();
         }
      }

      // comprueba si puede apilar el conetenedor a la pila
      int nueva_carga_soportar = carga_soportar_total - contenedor.getPeso();

      /*
       * System.out.println("// comprueba si puede apilar el conetenedor " +
       * contenedor.getId() + " a la pila de rama: "
       * + pila.get(0).getId() +
       * "\n" + "   ultimo contenedor de la pila: " + pila.get(pila.size() -
       * 1).getId() + "\n   cs:  "
       * + carga_soportar_total + "\n   cp:  " + contenedor.getPeso() + "\n   ncs:  "
       * + nueva_carga_soportar);
       */

      if (nueva_carga_soportar > 0 && contenedor.getId() != pila.get(pila.size() - 1).getId()) {
         aceptable = true;
         // System.out.println(" -> CARGA ACEPTADA");
      }

      return aceptable;
   }

   /**
    * Imprime el resultado de la fila final
    * 
    * @param contenedores_imp Array de contenedores a imprimir
    */
   public static void imprimirResultado(ArrayList<Contenedor> contenedores_imp) {
      System.out.println("\n\n");
      System.out.println("------------------------------------SOLUCION------------------------------------\n");
      System.out.println("Numero de contenedores:  " + contenedores_imp.size());

      for (int i = 0; i < contenedores_imp.size(); i++) {
         System.out.println("Contenedor " + (contenedores_imp.get(i).getId() + 1));
      }
      System.out.print("\n");
   }

   /**
    * Imprime el resultado de la fila final
    * 
    * @param contenedores_imp Array de contenedores a imprimir
    */
   public static void imprimirResultado2(ArrayList<Contenedor> contenedores_imp) {
      System.out.println("\n\n");
      System.out.println("------------------------------------SOLUCION------------------------------------\n");
      System.out.println("Numero de contenedores:  " + contenedores_imp.size());

      for (int i = 0; i < contenedores_imp.size(); i++) {
         System.out.println("Contenedor " + (contenedores_imp.get(i).getId() + 1) +
               " con Peso: " + contenedores_imp.get(i).getPeso() + " y Soporte: "
               + contenedores_imp.get(i).getCarga_a_soportar() + "\n");
      }
   }


   /**
    * Calcula algunos de los contenedores del barco para formar una única pila, 
    * de manera que la pila contenga el máximo número de contenedores posible.
    * La solucion se guarda en la variable global 'pilaOrdenadaMax'.
    *
    * @param n Numero de iteracion del array 'contenedores'
    * @param pila Contenedores apilados
    */
   public static void obtenerPilaBACKTRACKING(int n, ArrayList<Contenedor> pila) {

      //System.out.println("\nLLAMADO A ObtenerPila() con n: " + n + "\n Tamaño pila: " + pila.size() + "\n");

      for (int i = n; i < contenedores.size(); i++) {
         if (pila.size() == 0) {
            
            //System.out.println("Primer contenedor añadido a la pila: " + contenedores.get(i).getId() );
            
            pila.add(contenedores.get(i));
            obtenerPilaBACKTRACKING(i + 1, pila);
            pila.remove(pila.size() - 1);
         } else {

            if (aceptable(contenedores.get(i), pila)) {

               //System.out.println("Contenedor "+ contenedores.get(i).getId() + " añadido a la pila" );
               pila.add(contenedores.get(i));
               //System.out.println("longitud de la pila: " + pila.size());
               if (i == (contenedores.size()-1)) {
                  // System.out.println("Detectada ultima iteracion i (" + i + ") == " + (contenedores.size()-1));
                  if (pila.size() >= pilaOrdenadaMax.size()) {

                     /*
                     System.out.println("Detectada que i (" + pila.size() + ") >= " + (pilaOrdenadaMax.size()));
                     System.out.println("(!!!) Añadida solucion especifica de rama del contenedor: "
                           + pila.get(0).getId() + " con longitud: " + pila.size() + "\n");
                     */
                     
                     pilaOrdenadaMax = new ArrayList<>(pila);
                  }
               } else {
                  //System.out.println("no se ha detectado que i (" + i + ") sea igual al tamaño-1 de contenedores " + (contenedores.size()-1));
                  obtenerPilaBACKTRACKING(i + 1, pila);
               }
               pila.remove(pila.size() - 1);
            } else {
               if (i == contenedores.size() - 1) {
                  if (pila.size() > pilaOrdenadaMax.size()){
                     /*
                     System.out.println("(!!!) Añadida solucion especifica de rama del contenedor: "
                           + pila.get(0).getId() + " con longitud: " + pila.size() + "\n");
                     */
                     pilaOrdenadaMax = pila;
                  }
               }
            }
         }
      }
   }
}
