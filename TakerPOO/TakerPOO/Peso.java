public class Peso extends Entreno {
    private double kilos;
    private int repeticiones;

    public Peso(String nombre, double kilos, int series, int repeticiones, int rir) {
        super(nombre, series, rir);
        setKilos(kilos);
        setRepeticiones(repeticiones);
    }

    public void setKilos(double kilos) {
        if (kilos < 0) throw new IllegalArgumentException("Los kilos no pueden ser negativos.");
        this.kilos = kilos;
    }

    public void setRepeticiones(int repeticiones) {
        if (repeticiones <= 0) throw new IllegalArgumentException("Las repeticiones deben ser mayores a cero.");
        this.repeticiones = repeticiones;
    }

    @Override
    public double calcularEsfuerzo() { return kilos * getSeries() * repeticiones; }

    @Override
    public double getUmbralVolumenAlto() { return 3000; }

    @Override
    public String aFormatoArchivo() {
        return "PESO;" + getNombre() + ";" + kilos + ";" + getSeries() + ";" + repeticiones + ";" + getRir();
    }
}