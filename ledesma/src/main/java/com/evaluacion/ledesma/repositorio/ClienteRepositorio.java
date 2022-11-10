package com.evaluacion.ledesma.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evaluacion.ledesma.modelo.Cliente;



@Repository
public interface ClienteRepositorio  extends JpaRepository<Cliente, Integer> {

}
