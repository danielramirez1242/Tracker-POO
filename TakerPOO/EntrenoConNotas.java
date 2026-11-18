public class EntrenoConNotas {
    private SesionEntrenamiento sesion;
    private String notas;
    public EntrenoConNotas(SesionEntrenamiento sesion, String notas) {
        this.sesion = sesion; this.notas = notas;
    }
    public String obtenerResumenDecorado() {
        return "Fecha: " + sesion.getFecha() + " | Volumen Total: " + sesion.calcularVolumenTotalSesion() + " | Notas: [ " + notas + " ]";
    }
}