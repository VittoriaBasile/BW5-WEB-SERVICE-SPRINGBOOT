package epicode.EPICENERGYSERVICE.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import epicode.EPICENERGYSERVICE.entities.Fattura;
import epicode.EPICENERGYSERVICE.entities.StatoFattura;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, UUID> {

	@Query("SELECT stato FROM Fattura stato WHERE stato.statoFattura = :statoFattura")
	Optional<Fattura> findByState(@Param("statoFattura") StatoFattura statoFattura);

	@Query("SELECT d FROM Fattura d WHERE d.data = :data")
	Optional<Fattura> findByData(@Param("data") Date data);

	@Query("SELECT a FROM Fattura a WHERE a.anno = :anno")
	Optional<Fattura> findByAnno(@Param("anno") int anno);

	@Query("SELECT i FROM Fattura i WHERE i.importo = :importo")
	Optional<Fattura> findByImporto(@Param("importo") BigDecimal importo);
}
