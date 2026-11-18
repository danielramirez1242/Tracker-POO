public class Volumen extends Objetivo {

    @Override
    public String evaluar(Entreno entreno) {
        if (entreno.getRir() <= 2) {
            return "¡Excelente intensidad para hipertrofia!";
        } else {
            return "Quedaste lejos del fallo. Para hipertrofia acércate a RIR 0-2.";
        }
    }
}