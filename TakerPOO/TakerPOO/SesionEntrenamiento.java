import java.util.List;
public class SesionEntrenamiento {
    private String fecha;
    private List<Entreno> entrenos;
    public SesionEntrenamiento(String fecha, List<Entreno> entrenos) {
        this.fecha = fecha; this.entrenos = entrenos;
    }
    public String getFecha() { return fecha; }
    public List<Entreno> getEntrenos() { return entrenos; }
    public double calcularVolumenTotalSesion() {
        double total = 0;
        for (Entreno e : entrenos) total += e.calcularEsfuerzo();
        return total;
    }
}