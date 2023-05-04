import java.lang.reflect.Array;
import java.util.ArrayList;
import edu.princeton.cs.algs4.*;

public class AnalisisDatos {
    public static void main(String[] args) throws Exception {
        String route = "548634059_T_ONTIME_REPORTING.csv";
        System.out.println("Inicio de lectura");
        ArrayList<DelayRecord> a = DelayRecord.readCSVFile(route);
        System.out.println("Fin lectura");
        DelayRecord[] arreglo = new DelayRecord[a.size()];
        int cont = 0;
        for (DelayRecord delayRecord : a) {
            arreglo[cont] = delayRecord;
            cont++;
        }
        Quick.sort(arreglo);

        for (int i = 0; i <= arreglo.length; i++) {
            System.out.println(arreglo[1].toString());
        }
    }

    public ST<String, Integer> meanRouteDelay(ArrayList<DelayRecord> delays) {
        ST<String, Integer> T = new ST<>();

        return T;
    }
}
