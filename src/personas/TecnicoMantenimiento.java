package personas;

import red.NodoIoT;
import java.util.ArrayList;

public class TecnicoMantenimiento {

    private String nombre;
    private ArrayList<NodoIoT> nodosAsignados;

    public TecnicoMantenimiento(String nombre) {
        this.nombre = nombre;
        this.nodosAsignados = new ArrayList<>();
    }

    public void asignarNodo(NodoIoT nodo) {
        if (nodo != null && !nodosAsignados.contains(nodo)) {
            nodosAsignados.add(nodo);
        }
    }

    public String getNombre() { return nombre; }
    public ArrayList<NodoIoT> getNodosAsignados() { return new ArrayList<>(nodosAsignados); }
}
