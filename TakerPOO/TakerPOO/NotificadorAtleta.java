public class NotificadorAtleta implements ObservadorMeta {
    private String nombre;
    public NotificadorAtleta(String nombre) { this.nombre = nombre; }
    @Override
    public void notificarMetaSuperada(double total) {
        System.out.println("¡Meta superada por " + nombre + "! Volumen: " + total);
    }
}