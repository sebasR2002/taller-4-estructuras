import java.util.ArrayList;
import edu.princeton.cs.algs4.*;

public class AnalisisDatos {
    public static void main(String[] args) throws Exception {

        System.out.println("Inicio de lectura");

        String route = "548634059_T_ONTIME_REPORTING.csv";
        ArrayList<DelayRecord> a = DelayRecord.readCSVFile(route);

        System.out.println("Fin lectura");

        ST<String, Integer> t = meanRouteDelay(a);

        for (String string : t) {

            System.out.println("ST= " + string + " p= " + t.get(string));

        }
    }

    public static ST<String, Integer> meanRouteDelay(ArrayList<DelayRecord> delays) {

        ST<String, Integer> T = new ST<>();

        DelayRecord[] arreglo = new DelayRecord[delays.size()];

        int cont = 0;

        for (DelayRecord delayRecord : delays) {
            arreglo[cont] = delayRecord;
            cont++;
        }

        Quick.sort(arreglo);

        String ruta = arreglo[0].toString();
        int suma = 0;
        cont = 1;

        for (int i = 0; i < arreglo.length; i++) {

            if (i == arreglo.length - 1) {

                if (arreglo[i].getArrDelayNew() != null)
                    suma += arreglo[i].getArrDelayNew();
                cont++;
                T.put(ruta, suma / cont);

            } else {
                if (arreglo[i].toString() == ruta) {

                    if (arreglo[i].getArrDelayNew() != null)
                        suma += arreglo[i].getArrDelayNew();
                    cont++;

                } else {

                    T.put(ruta, suma / cont);
                    cont = 1;
                    ruta = arreglo[i].toString();

                    if (arreglo[i].getArrDelayNew() != null)
                        suma = arreglo[i].getArrDelayNew();

                }
            }
        }

        return T;
    }
}
