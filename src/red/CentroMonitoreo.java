package red;

import java.util.ArrayList;
import java.util.List;

public class CentroMonitoreo {

    private String nombre;
    private ArrayList<NodoIoT> nodos;

    public CentroMonitoreo(String nombre) {
        this.nombre = nombre;
        this.nodos = new ArrayList<>();
    }

    public void agregarNodo(NodoIoT nodo) {
        if (nodo != null && !nodos.contains(nodo)) {
            nodos.add(nodo);
        }
    }

    public List<String> reporteTemperaturas() {
        List<String> reporte = new ArrayList<>();
        for (NodoIoT nodo : nodos) {
            if (nodo != null) {
                String linea = "Nodo: " + nodo.getId() + " - Promedio Temp: " + nodo.promedioTemperatura();
                reporte.add(linea);
            }
        }
        return reporte;
    }

    public String getNombre() { return nombre; }
    public ArrayList<NodoIoT> getNodos() { return new ArrayList<>(nodos); }
}
