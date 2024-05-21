import java.util.Arrays;
import java.util.Comparator;
public class Main {
    public static void main(String[] args) {
        Proceso[] procesos = {
                new Proceso(1, 6),
                new Proceso(2, 8),
                new Proceso(3, 7),
                new Proceso(4, 3)
        };

        Arrays.sort(procesos, Comparator.comparingInt(p -> p.tiempoRafaga));

        PlanificadorSJF.encontrarTiempoPromedio(procesos);
    }
}
