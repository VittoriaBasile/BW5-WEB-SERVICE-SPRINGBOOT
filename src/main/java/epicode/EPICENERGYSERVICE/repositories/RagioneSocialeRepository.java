package epicode.EPICENERGYSERVICE.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import epicode.EPICENERGYSERVICE.entities.RagioneSociale;

@Repository
public interface RagioneSocialeRepository extends JpaRepository<RagioneSociale, UUID> {

}
