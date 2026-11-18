public class Calistenia extends Entreno {
    private double pesoCorporal;
    private double lastre;
    private int repeticiones;

    public Calistenia(String nombre, double pesoCorporal, double lastre, int series, int repeticiones, int rir) {
        super(nombre, series, rir);
        this.pesoCorporal = pesoCorporal;
        this.lastre = lastre;
        this.repeticiones = repeticiones;
    }

    @Override
    public double calcularEsfuerzo() { return (pesoCorporal + lastre) * getSeries() * repeticiones; }

    @Override
    public double getUmbralVolumenAlto() { return 3000; }

    @Override
    public String aFormatoArchivo() {
        return "CALISTENIA;" + getNombre() + ";" + pesoCorporal + ";" + lastre + ";" + getSeries() + ";" + repeticiones + ";" + getRir();
    }
}