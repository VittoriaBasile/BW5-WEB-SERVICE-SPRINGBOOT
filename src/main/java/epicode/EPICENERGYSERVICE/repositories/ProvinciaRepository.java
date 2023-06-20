package epicode.EPICENERGYSERVICE.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import epicode.EPICENERGYSERVICE.entities.Provincia;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, UUID> {

	Optional<Provincia> findByNome(String nome);

}
