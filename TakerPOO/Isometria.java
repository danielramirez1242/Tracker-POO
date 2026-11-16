public class Isometria extends Entreno {
    private int segundos;

    public Isometria(String nombre, int segundos, int series, int rir) {
        super(nombre, series, rir);
        this.segundos = segundos;
    }

    @Override
    public double calcularEsfuerzo() { return segundos * getSeries(); }

    @Override
    public double getUmbralVolumenAlto() { return 240; }

    @Override
    public String aFormatoArchivo() {
        return "ISOMETRIA;" + getNombre() + ";" + segundos + ";" + getSeries() + ";" + getRir();
    }
}