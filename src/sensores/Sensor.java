package sensores;

/**
 * Clase abstracta que representa un sensor genérico de la red IoT.
 * Mantiene un historial de las últimas N lecturas en un arreglo de enteros
 * (buffer circular).
 */
public abstract class Sensor {

    private String id;
    private String unidad;
    private int[] ultimasLecturas;
    private int indiceActual;
    private int cantidadLecturas;

    public Sensor(String id, String unidad, int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad del buffer debe ser mayor a 0.");
        }
        this.id = id;
        this.unidad = unidad;
        this.ultimasLecturas = new int[capacidad];
        this.indiceActual = 0;
        this.cantidadLecturas = 0;
    }

    public abstract int generarLectura();

    /**
     * Registra un valor en el buffer circular de tamaño fijo.
     * Si el buffer está lleno, sobrescribe la lectura más antigua.
     */
    public void registrarLectura(int valor) {
        ultimasLecturas[indiceActual] = valor;
        indiceActual = (indiceActual + 1) % ultimasLecturas.length;
        if (cantidadLecturas < ultimasLecturas.length) {
            cantidadLecturas++;
        }
    }

    /**
     * Promedio de las lecturas registradas. Si no hay, retorna 0.
     */
    public double promedioUltimasLecturas() {
        if (cantidadLecturas == 0) {
            return 0.0;
        }
        double suma = 0;
        for (int i = 0; i < cantidadLecturas; i++) {
            suma += ultimasLecturas[i];
        }
        return suma / cantidadLecturas;
    }

    /**
     * Retorna la última lectura registrada.
     */
    public int getUltimaLectura() {
        if (cantidadLecturas == 0) {
            return 0;
        }
        int ultimoIndice = (indiceActual - 1 + ultimasLecturas.length) % ultimasLecturas.length;
        return ultimasLecturas[ultimoIndice];
    }

    public abstract boolean fueraDeRango(int valor);

    // Getters encapsulados
    public String getId() { return id; }
    public String getUnidad() { return unidad; }
    public int[] getUltimasLecturas() { return ultimasLecturas; }
    public int getCantidadLecturas() { return cantidadLecturas; }
    public int getIndiceActual() { return indiceActual; }

    protected void setIndiceActual(int indiceActual) { this.indiceActual = indiceActual; }
    protected void setCantidadLecturas(int cantidadLecturas) { this.cantidadLecturas = cantidadLecturas; }
}
