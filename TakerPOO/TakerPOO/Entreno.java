public abstract class Entreno implements Persistente {
    private String nombre;
    private int series;
    private int rir;

    public Entreno(String nombre, int series, int rir) {
        this.nombre = nombre;
        setSeries(series);
        setRir(rir);
    }

    public abstract double calcularEsfuerzo();
    public abstract double getUmbralVolumenAlto();
    public abstract String aFormatoArchivo();

    public String getNombre() { return nombre; }
    public int getSeries() { return series; }
    public int getRir() { return rir; }

    public void setSeries(int series) {
        if (series <= 0) throw new IllegalArgumentException("Las series deben ser mayores a cero.");
        this.series = series;
    }

    public void setRir(int rir) {
        if (rir < 0) throw new IllegalArgumentException("El RIR no puede ser negativo.");
        this.rir = rir;
    }
}