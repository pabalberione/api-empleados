package com.api_empleados.repository;

import com.api_empleados.model.Empleados;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadosRepository extends JpaRepository<Empleados, Integer> {
}
