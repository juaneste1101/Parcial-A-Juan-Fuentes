package sensores;

import java.util.Random;

public class SensorLuminosidad extends Sensor {

    public static final int MIN = 0;
    public static final int MAX = 2000;
    private static final Random RND = new Random();

    public SensorLuminosidad(String id, int capacidad) {
        super(id, "lux", capacidad);
    }

    @Override
    public int generarLectura() {
        int minGen = MIN - 100;
        int maxGen = MAX + 100;
        int valor = RND.nextInt((maxGen - minGen) + 1) + minGen;
        registrarLectura(valor);
        return valor;
    }

    @Override
    public boolean fueraDeRango(int valor) {
        return valor < MIN || valor > MAX;
    }
}
