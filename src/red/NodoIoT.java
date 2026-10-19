package red;

import sensores.*;
import java.util.ArrayList;
import java.util.List;

public class NodoIoT {

    private String id;
    private ArrayList<Sensor> sensores;

    public NodoIoT(String id) {
        this.id = id;
        this.sensores = new ArrayList<>();
    }

    public Sensor agregarSensor(String tipo, String idSensor, int capacidad) {
        if (tipo == null || idSensor == null) {
            return null;
        }

        Sensor nuevoSensor = null;

        switch (tipo.toUpperCase().trim()) {
            case "TEMPERATURA":
                nuevoSensor = new SensorTemperatura(idSensor, capacidad);
                break;
            case "HUMEDAD":
                nuevoSensor = new SensorHumedad(idSensor, capacidad);
                break;
            case "LUMINOSIDAD":
                nuevoSensor = new SensorLuminosidad(idSensor, capacidad);
                break;
            default:
                return null;
        }

        sensores.add(nuevoSensor);
        return nuevoSensor;
    }

    public double promedioTemperatura() {
        double sumaPromedios = 0;
        int contadorTemp = 0;

        for (Sensor s : sensores) {
            if (s instanceof SensorTemperatura) {
                sumaPromedios += s.promedioUltimasLecturas();
                contadorTemp++;
            }
        }

        if (contadorTemp == 0) {
            return 0.0;
        }

        return sumaPromedios / contadorTemp;
    }

    public List<Sensor> sensoresFueraDeRango() {
        List<Sensor> anomalos = new ArrayList<>();
        for (Sensor s : sensores) {
            if (s != null && s.getCantidadLecturas() > 0) {
                int ultimaLectura = s.getUltimaLectura();
                if (s.fueraDeRango(ultimaLectura)) {
                    anomalos.add(s);
                }
            }
        }
        return anomalos;
    }

    public String getId() { return id; }
    public ArrayList<Sensor> getSensores() { return new ArrayList<>(sensores); }
}
