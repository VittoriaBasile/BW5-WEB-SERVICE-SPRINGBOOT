package epicode.EPICENERGYSERVICE.repositories;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import epicode.EPICENERGYSERVICE.entities.Fattura;
import epicode.EPICENERGYSERVICE.entities.StatoFattura;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, UUID> {

	List<Fattura> findByStatoFattura(StatoFattura statoFattura);

	List<Fattura> findByData(Date data);

	List<Fattura> findByAnno(int anno);

	// @Query("SELECT * FROM Fattura f WHERE f.importo >= :importoMinimo AND
	// f.importo <= :importoMassimo")
	// List<Fattura> findByImportoRange(BigDecimal importoMinimo, BigDecimal
	// importoMassimo);
}
