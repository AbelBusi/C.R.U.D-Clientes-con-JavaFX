package com.java.FxGui.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersona;

    private String nombre, telefono;

    public String toString(){
        return "ID: "+idPersona + " Nombre: "+nombre+ " Telefono: "+telefono;
    }

}
