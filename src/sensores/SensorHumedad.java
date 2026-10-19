package sensores;

import java.util.Random;

public class SensorHumedad extends Sensor {

    public static final int MIN = 0;
    public static final int MAX = 100;
    private static final Random RND = new Random();

    public SensorHumedad(String id, int capacidad) {
        super(id, "%", capacidad);
    }

    @Override
    public int generarLectura() {
        int minGen = MIN - 10;
        int maxGen = MAX + 10;
        int valor = RND.nextInt((maxGen - minGen) + 1) + minGen;
        registrarLectura(valor);
        return valor;
    }

    @Override
    public boolean fueraDeRango(int valor) {
        return valor < MIN || valor > MAX;
    }
}
