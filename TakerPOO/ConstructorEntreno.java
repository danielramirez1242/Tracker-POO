import java.util.ArrayList;
import java.util.List;
public class ConstructorEntreno {
    private String fecha;
    private List<Entreno> lista = new ArrayList<>();
    public ConstructorEntreno(String fecha) { this.fecha = fecha; }
    public ConstructorEntreno agregar(Entreno e) { lista.add(e); return this; }
    public SesionEntrenamiento build() throws EntrenamientoInvalidoException {
        if (fecha == null || fecha.isBlank()) throw new EntrenamientoInvalidoException("La fecha es obligatoria.");
        if (lista.isEmpty()) throw new EntrenamientoInvalidoException("Debe agregar al menos un ejercicio.");
        return new SesionEntrenamiento(fecha, lista);
    }
}