import java.util.ArrayList;
import edu.princeton.cs.algs4.*;

public class AnalisisDatos {
    public static void main(String[] args) throws Exception {
        String route = "548634059_T_ONTIME_REPORTING.csv";
        System.out.println("Inicio de lectura");
        ArrayList<DelayRecord> a = DelayRecord.readCSVFile(route);
        System.out.println("Fin lectura");
    }

    public ST<String, Integer> meanRouteDelay(ArrayList<DelayRecord> delays) {
        ST<String, Integer> T = new ST<>();

        return T;
    }
}
