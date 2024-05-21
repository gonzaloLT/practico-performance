import java.util.Arrays;
import java.util.Comparator;

public class PlanificadorSJF {
    // Función para encontrar el tiempo de espera para todos los procesos
    public static void encontrarTiempoEspera(Proceso[] procesos, int[] tiempoEspera) {
        int n = procesos.length;
        int[] tiempoRestante = new int[n];

        for (int i = 0; i < n; i++) {
            tiempoRestante[i] = procesos[i].tiempoRafaga;
        }

        int completado = 0, tiempoActual = 0, min = Integer.MAX_VALUE;
        int masCorto = 0, tiempoFinal;
        boolean check = false;

        while (completado != n) {
            for (int j = 0; j < n; j++) {
                if ((procesos[j].tiempoRafaga <= tiempoActual) &&
                        (tiempoRestante[j] < min) && tiempoRestante[j] > 0) {
                    min = tiempoRestante[j];
                    masCorto = j;
                    check = true;
                }
            }

            if (!check) {
                tiempoActual++;
                continue;
            }

            tiempoRestante[masCorto]--;
            min = tiempoRestante[masCorto];
            if (min == 0) {
                min = Integer.MAX_VALUE;
            }

            if (tiempoRestante[masCorto] == 0) {
                completado++;
                tiempoFinal = tiempoActual + 1;
                tiempoEspera[masCorto] = tiempoFinal - procesos[masCorto].tiempoRafaga;
                if (tiempoEspera[masCorto] < 0) {
                    tiempoEspera[masCorto] = 0;
                }
            }
            tiempoActual++;
        }
    }

    // Función para calcular el tiempo de retorno
    public static void encontrarTiempoRetorno(Proceso[] procesos, int[] tiempoEspera, int[] tiempoRetorno) {
        for (int i = 0; i < procesos.length; i++) {
            tiempoRetorno[i] = procesos[i].tiempoRafaga + tiempoEspera[i];
        }
    }

    // Función para calcular el tiempo promedio
    public static void encontrarTiempoPromedio(Proceso[] procesos) {
        int n = procesos.length;
        int[] tiempoEspera = new int[n], tiempoRetorno = new int[n];
        int tiempoTotalEspera = 0, tiempoTotalRetorno = 0;

        encontrarTiempoEspera(procesos, tiempoEspera);
        encontrarTiempoRetorno(procesos, tiempoEspera, tiempoRetorno);

        System.out.printf("%-10s %-15s %-15s %-15s\n", "ID Proceso", "Tiempo de ráfaga", "Tiempo de espera", "Tiempo de retorno");

        for (int i = 0; i < n; i++) {
            tiempoTotalEspera += tiempoEspera[i];
            tiempoTotalRetorno += tiempoRetorno[i];
            System.out.printf("%-10d %-15d %-15d %-15d\n", procesos[i].idProceso, procesos[i].tiempoRafaga, tiempoEspera[i], tiempoRetorno[i]);
        }

        System.out.printf("Tiempo promedio de espera = %.2f\n", (float) tiempoTotalEspera / n);
        System.out.printf("Tiempo promedio de retorno = %.2f\n", (float) tiempoTotalRetorno / n);
    }
}
