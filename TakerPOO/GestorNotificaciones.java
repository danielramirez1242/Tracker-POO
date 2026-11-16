import java.util.*;
public class GestorNotificaciones {
    private List<ObservadorMeta> obsList = new ArrayList<>();
    private double umbral = 3000.0;
    public void suscribir(ObservadorMeta obs) { obsList.add(obs); }
    public void evaluar(double total) {
        if (total >= umbral) {
            for (ObservadorMeta o : obsList) o.notificarMetaSuperada(total);
        }
    }
}