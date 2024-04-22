package com.java.FxGui;

import com.java.FxGui.control.PersonaRespository;
import com.java.FxGui.model.persona;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class controller implements Initializable{

    @Autowired
    private PersonaRespository personaRespository;

    private Integer id;

    private persona item;

    @FXML
    private AnchorPane menu;

    @FXML
    private ListView<persona> liistClientes;

    @FXML
    private TextField txtId, txtNombre, txtTelefono;

    @FXML
    private Button buttonGuardar, butonActualizar, butonEliminar,butonLimpiar;

    @FXML
    private  Label labelTotal;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //txtId.setDisable(true);
        liistClientes();
        liistClientes.getSelectionModel().selectedItemProperty().
                addListener((obs,old,newValue) -> {
            if(newValue!=null){
                id=newValue.getIdPersona();
                System.out.println(id);
                item=item();
                txtId.setText(String.valueOf(item.getIdPersona()));
                txtNombre.setText(item.getNombre());
                txtTelefono.setText(item.getTelefono());
                buttonGuardar.setVisible(false);
                butonLimpiar.setVisible(true);
                butonActualizar.setVisible(true);
                butonEliminar.setVisible(true);

            }
        });

    }

    public persona item(){
        Optional<persona> optionalPersona = personaRespository.findById(id);
        if (optionalPersona.isPresent()) {
            return optionalPersona.get();
        } else {
            // Manejar el caso en que no se encuentra ninguna persona con el ID proporcionado
            // Por ejemplo, podrías lanzar una excepción como IllegalStateException:
            throw new IllegalStateException("No se encontró ninguna persona con el ID proporcionado: " + id);
        }
    }

    public void liistClientes() {
        liistClientes.setItems(FXCollections.observableArrayList(personaRespository.findAll()));
        labelTotal.setText(String.valueOf(liistClientes.getItems().size()));
    }

    @FXML
    public void actualizar (){

        item.setNombre(txtNombre.getText());
        item.setTelefono(txtTelefono.getText());
        personaRespository.save(item);
        liistClientes();
        clear();
    }
    @FXML
    public void save(){
        if(!txtNombre.getText().trim().isEmpty()){
            persona persona = new persona();
            persona.setNombre(txtNombre.getText());
            persona.setTelefono(txtTelefono.getText());
            personaRespository.save(persona);
            liistClientes();
            clear();
        }
        else {
            //lnlWarning.setVisible(true);
        }
    }

    @FXML
    public void clear(){

        txtId.clear();
        txtNombre.clear();
        txtTelefono.clear();
        buttonGuardar.setVisible(true);
        butonLimpiar.setVisible(false);
        butonActualizar.setVisible(false);
        butonEliminar.setVisible(false);

    }

    public void eliminar(){
        personaRespository.delete(item);
        clear();
        liistClientes();
    }


}