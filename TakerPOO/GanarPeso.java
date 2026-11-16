public class GanarPeso extends Objetivo {

    private double esfuerzoAnterior; // tu marca de la sesión pasada

    public GanarPeso(double esfuerzoAnterior) {
        this.esfuerzoAnterior = esfuerzoAnterior;
    }

    @Override
    public String evaluar(Entreno entreno) {
        if (entreno.calcularEsfuerzo() > esfuerzoAnterior) {
            return "¡Progresión de cargas! Superaste tu marca anterior.";
        } else {
            return "No superaste tu marca anterior. Intenta subir el peso.";
        }
    }
}