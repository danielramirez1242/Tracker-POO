import javax.swing.*;
import java.awt.*;

public class VentanaTracker extends JFrame {

    private JComboBox<String> cbTipo = new JComboBox<>(new String[]{"Peso", "Calistenia", "Isometria"});
    private JTextField txtFecha = new JTextField("2026-11-17");
    private JTextField txtNombre = new JTextField();
    private JTextField txtSeries = new JTextField();
    private JTextField txtRir = new JTextField();
    private JTextField txtKilos = new JTextField();
    private JTextField txtLastre = new JTextField();
    private JTextField txtReps = new JTextField();
    private JTextField txtSegundos = new JTextField();

    private JComboBox<String> cbObjetivo = new JComboBox<>(new String[]{"Hipertrofia", "Perder peso", "Ganar peso"});
    private JTextField txtMarca = new JTextField();
    private JTextField txtNotas = new JTextField();

    private JTextArea txtResultado = new JTextArea(7, 35);
    private RepositorioHistorial repositorio = new RepositorioHistorial();
    private GestorNotificaciones gestorNotis = new GestorNotificaciones();

    public VentanaTracker() {
        setTitle("TrackerPOO");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));

        gestorNotis.suscribir(new NotificadorAtleta("Carlos"));

        JPanel campos = new JPanel(new GridLayout(0, 2, 5, 5));
        campos.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        campos.add(new JLabel("Fecha (YYYY-MM-DD):")); campos.add(txtFecha);
        campos.add(new JLabel("Tipo de entreno:")); campos.add(cbTipo);
        campos.add(new JLabel("Ejercicio:")); campos.add(txtNombre);
        campos.add(new JLabel("Series:")); campos.add(txtSeries);
        campos.add(new JLabel("RIR (0 al fallo):")); campos.add(txtRir);
        campos.add(new JLabel("Kilos / peso corporal:")); campos.add(txtKilos);
        campos.add(new JLabel("Lastre (solo calistenia):")); campos.add(txtLastre);
        campos.add(new JLabel("Repeticiones:")); campos.add(txtReps);
        campos.add(new JLabel("Segundos (solo isometría):")); campos.add(txtSegundos);
        campos.add(new JLabel("Objetivo:")); campos.add(cbObjetivo);
        campos.add(new JLabel("Marca anterior (ganar peso):")); campos.add(txtMarca);
        campos.add(new JLabel("Notas de sesión:")); campos.add(txtNotas);

        JButton btnCalcular = new JButton("Registrar y Calcular");
        btnCalcular.addActionListener(e -> calcular());

        txtResultado.setEditable(false);

        add(campos, BorderLayout.NORTH);
        add(new JScrollPane(txtResultado), BorderLayout.CENTER);
        add(btnCalcular, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private double numero(JTextField campo) {
        String texto = campo.getText().trim();
        if (texto.isEmpty()) return 0;
        return Double.parseDouble(texto);
    }

    private void calcular() {
        try {
            String fecha = txtFecha.getText().trim();
            String nombre = txtNombre.getText().trim();
            if (nombre.isEmpty()) {
                throw new EntrenamientoInvalidoException("Escribe el nombre del ejercicio.");
            }

            int series = (int) numero(txtSeries);
            int rir = (int) numero(txtRir);

            Entreno entreno;
            String tipo = (String) cbTipo.getSelectedItem();

            if ("Peso".equals(tipo)) {
                entreno = new Peso(nombre, numero(txtKilos), series, (int) numero(txtReps), rir);
            } else if ("Calistenia".equals(tipo)) {
                entreno = new Calistenia(nombre, numero(txtKilos), numero(txtLastre), series, (int) numero(txtReps), rir);
            } else {
                entreno = new Isometria(nombre, (int) numero(txtSegundos), series, rir);
            }

            Objetivo objetivo;
            String meta = (String) cbObjetivo.getSelectedItem();

            if ("Hipertrofia".equals(meta)) {
                objetivo = new Volumen();
            } else if ("Perder peso".equals(meta)) {
                objetivo = new PerderPeso();
            } else {
                objetivo = new GanarPeso(numero(txtMarca));
            }

            SesionEntrenamiento sesion = new ConstructorEntreno(fecha)
                    .agregar(entreno)
                    .build();

            EntrenoConNotas decorado = new EntrenoConNotas(sesion, txtNotas.getText().trim());
            repositorio.guardarSesion(sesion);
            gestorNotis.evaluar(sesion.calcularVolumenTotalSesion());

            txtResultado.setText(decorado.obtenerResumenDecorado()
                    + "\nTrabajo total: " + entreno.calcularEsfuerzo()
                    + "\n" + objetivo.evaluar(entreno)
                    + "\n(Guardado en historial_entrenamientos.txt)");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Revisa los números, hay un campo con formato inválido.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (EntrenamientoInvalidoException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Datos inválidos", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaTracker().setVisible(true));
    }
}