package epicode.EPICENERGYSERVICE.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import epicode.EPICENERGYSERVICE.entities.Fattura;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, UUID> {

}
