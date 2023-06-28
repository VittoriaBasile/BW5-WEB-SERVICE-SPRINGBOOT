package epicode.CAPSTONEPROJECT.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import epicode.CAPSTONEPROJECT.entities.Indirizzo;

public interface IndirizzoRepository extends JpaRepository<Indirizzo, UUID> {

}
