package weg.arquiteturasoftware.chaodefabricaapplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import weg.arquiteturasoftware.chaodefabricaapplication.Entity.Maquina;

public interface MaquinaRepository extends JpaRepository<Maquina, Integer> {
}
