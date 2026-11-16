public class Main {

    public static void main(String[] args) {
        Entreno[] entrenos = new Entreno[3];
        entrenos[0] = new Peso("Press banca", 80, 4, 10, 1);
        entrenos[1] = new Calistenia("Dominadas", 70, 10, 4, 6, 1);
        entrenos[2] = new Isometria("Plancha", 60, 3, 2);

        Objetivo[] objetivos = new Objetivo[3];
        objetivos[0] = new Volumen();
        objetivos[1] = new PerderPeso();
        objetivos[2] = new GanarPeso(2500);

        for (int i = 0; i < entrenos.length; i++) {
            Entreno e = entrenos[i];
            System.out.println("=== " + e.getNombre() + " ===");
            System.out.println("Trabajo total: " + e.calcularEsfuerzo());

            for (int j = 0; j < objetivos.length; j++) {
                System.out.println(objetivos[j].evaluar(e));
            }
            System.out.println();
        }
    }
}