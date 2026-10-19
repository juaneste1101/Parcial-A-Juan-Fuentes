package sensores;

import java.util.Random;

public class SensorTemperatura extends Sensor {

    public static final int MIN = -10;
    public static final int MAX = 50;
    private static final Random RND = new Random();

    public SensorTemperatura(String id, int capacidad) {
        super(id, "C", capacidad);
    }

    @Override
    public int generarLectura() {
        int minGen = MIN - 5;
        int maxGen = MAX + 5;
        int valor = RND.nextInt((maxGen - minGen) + 1) + minGen;
        registrarLectura(valor);
        return valor;
    }

    @Override
    public boolean fueraDeRango(int valor) {
        return valor < MIN || valor > MAX;
    }
}
