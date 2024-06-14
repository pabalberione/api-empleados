package com.api_empleados.controller;

import com.api_empleados.model.Empleados;
import com.api_empleados.repository.EmpleadosRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/empleados/")
public class EmpleadosController {

    @Autowired
    EmpleadosRepository empleadosRepository;

    //Crear empleado
    @PostMapping
    public Empleados createEmpleado(@RequestBody Empleados empleados){
       return empleadosRepository.save(empleados);
    }

    //Obtener todos los empleados
    @GetMapping
    public List<Empleados> getAllEmpleados(){
        return empleadosRepository.findAll();
    }

    //Obtener un empleado por Id
    @GetMapping("{id}")
    public ResponseEntity<Empleados> getEmpleadoById(@PathVariable Integer id){
        Empleados empleado = empleadosRepository.findById(id).orElse(null);
        if(empleado == null){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empleado);
    }

    //Actualizar un empleado
    @PutMapping("{id}")
    public ResponseEntity<Empleados>updateEmpleado(@PathVariable Integer id, @RequestBody Empleados empleadosDetails){
        Empleados empleado = empleadosRepository.findById(id).orElse(null);
        if(empleado == null){
            ResponseEntity.notFound().build();
        }
        empleado.setFirstName(empleadosDetails.getFirstName());
        empleado.setLastName(empleadosDetails.getLastName());
        empleado.setPosition(empleadosDetails.getPosition());
        empleado.setSalary(empleadosDetails.getSalary());
        empleado.setEmail(empleadosDetails.getEmail());
        Empleados newEmpleado = empleadosRepository.save(empleado);
        return ResponseEntity.ok(newEmpleado);
    }

    //Eliminar un empleado
    @DeleteMapping("{id}")
    public ResponseEntity<Void>deleteEmpleado(@PathVariable Integer id){
        Empleados empleado = empleadosRepository.findById(id).orElse(null);
        if(empleado == null){
            ResponseEntity.notFound().build();
        }
        empleadosRepository.delete(empleado);
        return ResponseEntity.noContent().build();
    }
}
