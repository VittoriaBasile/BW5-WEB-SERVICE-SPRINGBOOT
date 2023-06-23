package epicode.EPICENERGYSERVICE.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import epicode.EPICENERGYSERVICE.entities.Indirizzo;

@Repository
public interface IndirizzoRepository extends JpaRepository<Indirizzo, UUID> {

}
