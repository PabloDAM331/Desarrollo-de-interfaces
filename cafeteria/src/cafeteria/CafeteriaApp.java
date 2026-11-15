/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cafeteria;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 *
 * @author pablo
 */
public class CafeteriaApp extends Application {
   private TextField txtNombre;
   private ComboBox<String> cmbBebida;
   private RadioButton rbPequeno, rbGrande, rbMediano;
   private ToggleGroup grupoTamano;
   private CheckBox chkAvena, chkSirope, chkNata;
   private Spinner<Integer> spnCantidad;
   private Label lblPrecioLinea;
   private ListView<LineaPedido> lvPedido;
   private Label lblTotal;
   private Button btnAnadir, btnEliminar, btnVaciar;
     private Label lblEstado;
   private ObservableList<LineaPedido> listaPedidos;
   
   @Override
   public void start (Stage primaryStage){
       listaPedidos=FXCollections.observableArrayList();
       
       //interfaz
       BorderPane root = new BorderPane();
       root.setPadding(new Insets(10));
       
       //Formulario en el centro
       root.setCenter(crearFormulario());
       
       //Pedido en la derecha
       root.setRight(crearPanelPedido());
       
       //Barra del estado abajo
       lblEstado = new Label("Bienvenido a la cafeteria. Introduce tu pedido: ");
       lblEstado.setPadding(new Insets(10,0,0,0));
       root.setBottom(lblEstado);
       
       //Escena
       Scene scene= new Scene(root, 900, 600);
       primaryStage.setTitle("Cafeteria del Instituto Leonardo Da Vinci-UT1");
       primaryStage.setScene(scene);
       primaryStage.show();
       
       //Inicializacion de los eventos y estados
       configurarEventos();
       actualizarPrecioLinea();
   }
   
   private GridPane crearFormulario(){
       GridPane grid = new GridPane();
       grid.setHgap(10);
       grid.setVgap(10);
       grid.setPadding(new Insets(10));
       grid.setAlignment(Pos.TOP_LEFT);
       
       //Nombre del cliente
       grid.add(new Label("Nombre del cliente: "), 0, 0);
       txtNombre= new TextField();
       txtNombre.setPromptText("Pablo Parreno");
        grid.add(txtNombre, 1, 0);
        
        //Bebida
        grid.add(new Label("Bebida:"), 0, 1);
        cmbBebida = new ComboBox<>();
        cmbBebida.getItems().addAll("Café", "Té", "Cola Cao", "Zumo de naranja");
        cmbBebida.setPromptText("Elige bebida");
        grid.add(cmbBebida, 1, 1);
        
        //Tamano de la bebida
        grid.add(new Label("Tamaño:"), 0, 2);
        HBox hbTamanos = new HBox(10);
        grupoTamano = new ToggleGroup();
        rbPequeno = new RadioButton("Pequeño");
        rbMediano = new RadioButton("Mediano");
        rbGrande = new RadioButton("Grande");
        rbPequeno.setToggleGroup(grupoTamano);
        rbMediano.setToggleGroup(grupoTamano);
        rbGrande.setToggleGroup(grupoTamano);
        rbPequeno.setSelected(true); // Por defecto
        hbTamanos.getChildren().addAll(rbPequeno, rbMediano, rbGrande);
        grid.add(hbTamanos, 1, 2);
        
        //extras
        grid.add(new Label("Extras:"), 0, 3);
        HBox hbExtras = new HBox(10);
        chkAvena = new CheckBox("Leche de avena");
        chkSirope = new CheckBox("Sirope");
        chkNata = new CheckBox("Nata");
        hbExtras.getChildren().addAll(chkAvena, chkSirope, chkNata);
        grid.add(hbExtras, 1, 3);
        
        //Cantidad
        grid.add(new Label("Cantidad:"), 0, 4);
        spnCantidad = new Spinner<>(1, 10, 1);
        spnCantidad.setEditable(true);
        grid.add(spnCantidad, 1, 4);
        
        //Precio de linea
        grid.add(new Label("Precio Línea:"), 0, 5);
        lblPrecioLinea = new Label("0,00 €");
        lblPrecioLinea.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        grid.add(lblPrecioLinea, 1, 5);
        
        //El boton anadir
        btnAnadir = new Button("Añadir");
        btnAnadir.setTooltip(new Tooltip("Añadir línea al pedido"));
        btnAnadir.setDisable(true); // E4: Deshabilitado al inicio
        grid.add(btnAnadir, 1, 6);

        return grid;
   }
   
       private VBox crearPanelPedido() {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(10));
        panel.setPrefWidth(400);

        Label lblTitulo = new Label("Pedido Actual");
        lblTitulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // se crea el ListView 
        lvPedido = new ListView<>(listaPedidos);
        VBox.setVgrow(lvPedido, Priority.ALWAYS);

        //   se crea el Label del total 
        lblTotal = new Label("Total: 0,00 €");
        lblTotal.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: darkgreen;");

        //  se crean los botones 
        HBox hbBotones = new HBox(10);
        btnEliminar = new Button("Eliminar");
        btnEliminar.setTooltip(new Tooltip("Eliminar línea seleccionada"));
        btnEliminar.setDisable(true); 
        
        btnVaciar = new Button("Vaciar pedido");
        btnVaciar.setTooltip(new Tooltip("Vaciar todo el pedido"));
        
        hbBotones.getChildren().addAll(btnEliminar, btnVaciar);
        hbBotones.setAlignment(Pos.CENTER);

        //  se añaden al panel 
        panel.getChildren().addAll(lblTitulo, lvPedido, lblTotal, hbBotones);
        
        return panel;
    }
   
   private void configurarEventos(){
       //recarlcular el precio de la lista
       cmbBebida.setOnAction(e -> actualizarPrecioLinea());
        grupoTamano.selectedToggleProperty().addListener((obs, oldVal, newVal) -> actualizarPrecioLinea());
        chkAvena.setOnAction(e -> actualizarPrecioLinea());
        chkSirope.setOnAction(e -> actualizarPrecioLinea());
        chkNata.setOnAction(e -> actualizarPrecioLinea());
        spnCantidad.valueProperty().addListener((obs, oldVal, newVal) -> actualizarPrecioLinea());
        
        //boton anadir
        btnAnadir.setOnAction(e -> anadirLinea());
        
        //El nombre
        txtNombre.setOnAction(e -> anadirLinea());
        
        //habilitar o deshabilitar 
        txtNombre.textProperty().addListener((obs, oldVal, newVal) -> {
            boolean vacio = newVal.trim().isEmpty();
            btnAnadir.setDisable(vacio);
        });
        
        // seleccion del ListView para habilitar la eliminacion
        lvPedido.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            btnEliminar.setDisable(newVal == null);
        });
        
        //Boton eliminar
        btnEliminar.setOnAction(e -> eliminarLinea());
        
        //boton vaciar
          btnVaciar.setOnAction(e -> vaciarPedido());
          
        //Mouse en boton anadir
        btnAnadir.setOnMouseEntered(e -> {
            if (!btnAnadir.isDisabled() && cmbBebida.getValue() != null) {
                lblEstado.setText("Añadir: " + String.format("%.2f €", calcularPrecioUnitario()));
            } else {
                lblEstado.setText("Añadir: Selecciona bebida y nombre");
            }
        });
        btnAnadir.setOnMouseExited(e -> lblEstado.setText("Listo para añadir."));
        
        //Tecla suprimir en el listView
        lvPedido.setOnKeyPressed(event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.DELETE) {
                eliminarLinea();
            }
        });
   }
   
   private double calcularPrecioUnitario(){
        double base = 0.0;
        String bebida = cmbBebida.getValue();
        if (bebida != null) {
            switch (bebida) {
                case "Café": base = 1.20; break;
                case "Té": base = 1.00; break;
                case "Cola Cao": base = 1.30; break;
                case "Zumo de naranja": base = 1.80; break;
            }
        }

        // Tamaño
        if (rbMediano.isSelected()) base += 0.30;
        else if (rbGrande.isSelected()) base += 0.60;

        // Extras
        if (chkAvena.isSelected()) base += 0.20;
        if (chkSirope.isSelected()) base += 0.30;
        if (chkNata.isSelected()) base += 0.40;

        return base;
   }
   
   private void actualizarPrecioLinea() {
        double precioUnitario = calcularPrecioUnitario();
        int cantidad = spnCantidad.getValue();
        double totalLinea = precioUnitario * cantidad;
        lblPrecioLinea.setText(String.format("%.2f €", totalLinea));
    }
   private void anadirLinea(){
       String nombre = txtNombre.getText().trim();
        String bebida = cmbBebida.getValue();
        
        //Validar
        if (nombre.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Falta el nombre", "Por favor, introduce el nombre del cliente.");
            return;
        }
        if (bebida == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Falta la bebida", "Por favor, selecciona una bebida.");
            return;
        }
        
        //obtener los datos
         String tamano = rbPequeno.isSelected() ? "Pequeño" : (rbMediano.isSelected() ? "Mediano" : "Grande");
        StringBuilder extras = new StringBuilder();
        if (chkAvena.isSelected()) extras.append("Leche avena, ");
        if (chkSirope.isSelected()) extras.append("Sirope, ");
        if (chkNata.isSelected()) extras.append("Nata, ");
        String extrasStr = extras.length() > 0 ? extras.substring(0, extras.length() - 2) : "";
        
        int cantidad = spnCantidad.getValue();
        double precioUnitario = calcularPrecioUnitario();
        
        //Crear y anadir la lista
         LineaPedido linea = new LineaPedido(nombre, bebida, tamano, extrasStr, cantidad, precioUnitario);
        listaPedidos.add(linea);
        
        actualizarTotal();
        resetearFormulario();
        lblEstado.setText("Línea añadida correctamente.");
   }
   private void eliminarLinea(){
       
        LineaPedido seleccionada = lvPedido.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            listaPedidos.remove(seleccionada);
            actualizarTotal();
            lblEstado.setText("Línea eliminada.");
        }
   }
   
   private void vaciarPedido(){
    if (listaPedidos.isEmpty()) return;
    
    //Confirmacion 
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText("Vaciar pedido");
        alert.setContentText("¿Estás seguro de que quieres eliminar todas las líneas del pedido?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            listaPedidos.clear();
            actualizarTotal();
            lblEstado.setText("Pedido vaciado.");
        } else {
            lblEstado.setText("Acción cancelada.");
        }
  }
   
   private void actualizarTotal() {
        double total = listaPedidos.stream().mapToDouble(LineaPedido::getPrecio).sum();
        lblTotal.setText(String.format("Total: %.2f €", total));
    }
   
   private void resetearFormulario() {
        txtNombre.clear();
        cmbBebida.setValue(null);
        rbPequeno.setSelected(true);
        chkAvena.setSelected(false);
        chkSirope.setSelected(false);
        chkNata.setSelected(false);
        spnCantidad.getValueFactory().setValue(1);
        btnAnadir.setDisable(true); // Se deshabilita porque el nombre está vacío
        actualizarPrecioLinea();
    }
   private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
        lblEstado.setText("Error: " + mensaje);
    }
   
       public static void main(String[] args) {
        launch(args);
    }
}

