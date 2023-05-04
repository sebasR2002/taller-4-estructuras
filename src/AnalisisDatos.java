import java.util.Map.Entry;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.Queue;

import java.util.*;
import java.util.Date;

public class AnalisisDatos {
    public static void main(String[] args) throws Exception {

        System.out.println("Inicio de lectura");

        String route = "548634059_T_ONTIME_REPORTING.csv";
        ArrayList<DelayRecord> a = DelayRecord.readCSVFile(route);

        System.out.println("Fin lectura");

        ST<String, Integer> t = meanRouteDelay(a);

        t = topMDelayed(t, 15);

        listTopMDelayed(t);

        System.out.println(longestDelayCarrier(a, null, null));



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
        cont = 0;

        for (int i = 0; i < arreglo.length; i++) {

            if (i != arreglo.length - 1) {

                if (arreglo[i].toString().compareTo(ruta) == 0) {

                    cont++;
                    if (arreglo[i].getArrDelayNew() != null)
                        suma = suma + arreglo[i].getArrDelayNew();

                }

                else {

                    int x = suma / cont;
                    T.put(ruta, x);
                    cont = 1;
                    if (arreglo[i].getArrDelayNew() != null)
                        suma = arreglo[i].getArrDelayNew();
                    else
                        suma = 0;
                    ruta = arreglo[i].toString();

                }
            }

            else {

                cont++;
                suma = suma + arreglo[i].getArrDelayNew();
                int x = suma / cont;
                T.put(ruta, x);

            }

        }

        return T;
    }

    public static ST<String, Integer> topMDelayed(ST<String, Integer> meanDelayRoute, int m) {
        ST<String, Integer> result = new ST<>();
        MinPQ<String> pq = new MinPQ<>(m, new Comparator<String>() {
            public int compare(String a, String b) {
                return meanDelayRoute.get(a).compareTo(meanDelayRoute.get(b));
            }
        });

        for (String key : meanDelayRoute.keys()) {
            pq.insert(key);
            if (pq.size() > m) {
                pq.delMin();
            }
        }

        while (!pq.isEmpty()) {
            String key = pq.delMin();
            result.put(key, meanDelayRoute.get(key));
        }

        return result;
    }

    static void listTopMDelayed(ST<String, Integer> topM) {

        MaxPQ<String> pq = new MaxPQ<>(new Comparator<String>() {
            public int compare(String a, String b) {
                return topM.get(a).compareTo(topM.get(b));
            }
        });
        for (String key : topM.keys()) {
            pq.insert(key);
        }

        // Imprimimos las claves en orden descendente utilizando la MaxPQ
        while (!pq.isEmpty()) {
            String key = pq.delMax();
            System.out.println(key + ": " + topM.get(key));
        }
    }

    public static class AirlineComparator implements Comparator<DelayRecord> {
        public int compare(DelayRecord a, DelayRecord b) {
            return a.getCarrier().compareTo(b.getCarrier());
        }
    }

    public static String longestDelayCarrier(ArrayList<DelayRecord> delays, Date from, Date to) {

        // Eliminar las que no corresponden
        for (DelayRecord record : delays) {
            Date date = record.getDate();
            if (date.after(to) && date.before(from)) {
                delays.remove(record);
            }
        }

        // Ordenar por aerolinea
        Collections.sort(delays, new AirlineComparator());

        // For para sacar el promedio de cada una y comparar con el resultado del
        // anterior

        // Inicializar variables
        String currentAirline = null;
        String maxDelayAirline = null;
        int maxDelaySum = 0;
        int currentDelaySum = 0;

        // Iterar sobre el ArrayList
        for (DelayRecord record : delays) {
            // Si la aerolínea actual es diferente de la aerolínea del registro actual,
            // actualizar la aerolínea actual y la suma de delays de la aerolínea actual
            if (currentAirline == null || !currentAirline.equals(record.getCarrier())) {
                currentAirline = record.getCarrier();
                currentDelaySum = 0;
            }

            // Actualizar la suma de delays de la aerolínea actual
            Integer delay = record.getArrDelayNew();
            if (delay != null) {
                currentDelaySum += delay;
            }

            // Si la suma de delays de la aerolínea actual es mayor que la suma de delays
            // de la aerolínea con mayor delay hasta el momento, actualizar la aerolínea
            // con mayor delay y la suma de delays de la aerolínea con mayor delay
            if (currentDelaySum > maxDelaySum) {
                maxDelaySum = currentDelaySum;
                maxDelayAirline = currentAirline;
            }
        }

        // El resultado se encuentra en maxDelayAirline
        String result = "La aerolínea con mayor delay es " + maxDelayAirline;

        return result;
    }

}