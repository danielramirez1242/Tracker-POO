import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaTracker extends JFrame {

    private JComboBox<String> cbTipo = new JComboBox<String>(
            new String[]{"Peso", "Calistenia", "Isometria"});
    private JTextField txtNombre = new JTextField();
    private JTextField txtSeries = new JTextField();
    private JTextField txtRir = new JTextField();
    private JTextField txtKilos = new JTextField();      // kilos (Peso) o peso corporal (Calistenia)
    private JTextField txtLastre = new JTextField();     // solo Calistenia
    private JTextField txtReps = new JTextField();       // Peso y Calistenia
    private JTextField txtSegundos = new JTextField();   // solo Isometria

    private JComboBox<String> cbObjetivo = new JComboBox<String>(
            new String[]{"Hipertrofia", "Perder peso", "Ganar peso"});
    private JTextField txtMarca = new JTextField();      // solo Ganar peso

    private JTextArea txtResultado = new JTextArea(5, 30);

    public VentanaTracker() {
        setTitle("TrackerPOO");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel campos = new JPanel(new GridLayout(0, 2, 5, 5));
        campos.add(new JLabel("Tipo de entreno:"));
        campos.add(cbTipo);
        campos.add(new JLabel("Ejercicio:"));
        campos.add(txtNombre);
        campos.add(new JLabel("Series:"));
        campos.add(txtSeries);
        campos.add(new JLabel("RIR:"));
        campos.add(txtRir);
        campos.add(new JLabel("Kilos / peso corporal:"));
        campos.add(txtKilos);
        campos.add(new JLabel("Lastre (solo calistenia):"));
        campos.add(txtLastre);
        campos.add(new JLabel("Repeticiones:"));
        campos.add(txtReps);
        campos.add(new JLabel("Segundos (solo isometría):"));
        campos.add(txtSegundos);
        campos.add(new JLabel("Objetivo:"));
        campos.add(cbObjetivo);
        campos.add(new JLabel("Marca anterior (solo ganar peso):"));
        campos.add(txtMarca);

        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcular();
            }
        });

        txtResultado.setEditable(false);

        add(campos, BorderLayout.NORTH);
        add(new JScrollPane(txtResultado), BorderLayout.CENTER);
        add(btnCalcular, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // centrar en pantalla
    }

    // Si el campo está vacío devuelve 0
    private double numero(JTextField campo) {
        String texto = campo.getText().trim();
        if (texto.isEmpty()) {
            return 0;
        }
        return Double.parseDouble(texto);
    }

    private void calcular() {
        try {
            String nombre = txtNombre.getText();
            int series = (int) numero(txtSeries);
            int rir = (int) numero(txtRir);

            // 1. Crear el Entreno según el tipo elegido
            Entreno entreno;
            String tipo = (String) cbTipo.getSelectedItem();

            if (tipo.equals("Peso")) {
                entreno = new Peso(nombre, numero(txtKilos), series, (int) numero(txtReps), rir);
            } else if (tipo.equals("Calistenia")) {
                entreno = new Calistenia(nombre, numero(txtKilos), numero(txtLastre),
                        series, (int) numero(txtReps), rir);
            } else {
                entreno = new Isometria(nombre, (int) numero(txtSegundos), series, rir);
            }

            // 2. Crear el Objetivo según lo elegido
            Objetivo objetivo;
            String meta = (String) cbObjetivo.getSelectedItem();

            if (meta.equals("Hipertrofia")) {
                objetivo = new Volumen();
            } else if (meta.equals("Perder peso")) {
                objetivo = new PerderPeso();
            } else {
                objetivo = new GanarPeso(numero(txtMarca));
            }

            // 3. Mostrar resultado
            txtResultado.setText("Trabajo total: " + entreno.calcularEsfuerzo()
                    + "\n" + objetivo.evaluar(entreno));

        } catch (NumberFormatException ex) {
            txtResultado.setText("Revisa los números, hay un campo con formato inválido.");
        }
    }

    public static void main(String[] args) {
        new VentanaTracker().setVisible(true);
    }
}