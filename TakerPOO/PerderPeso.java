public class PerderPeso extends Objetivo {

    @Override
    public String evaluar(Entreno entreno) {
        if (entreno.calcularEsfuerzo() >= entreno.getUmbralVolumenAlto()) {
            return "¡Buen volumen total! Gastaste mucha energía.";
        } else {
            return "Volumen bajo. Para perder peso agrega series o repeticiones.";
        }
    }
    
}