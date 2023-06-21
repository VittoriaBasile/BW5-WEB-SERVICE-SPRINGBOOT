package epicode.EPICENERGYSERVICE.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import epicode.EPICENERGYSERVICE.entities.Fattura;
import epicode.EPICENERGYSERVICE.entities.StatoFattura;
import epicode.EPICENERGYSERVICE.exceptions.NotFoundException;
import epicode.EPICENERGYSERVICE.repositories.ClienteRepository;
import epicode.EPICENERGYSERVICE.repositories.FatturaRepository;

@Service
public class FatturaService {
	@Autowired
	FatturaRepository fatturaRepo;

	// ClienteService clienteService;

	@Autowired
	ClienteRepository clienteRepo;

	public Fattura create(Fattura f) {
		Fattura newFattura = new Fattura(f.getImporto(), f.getNumeroFattura(), f.getData(), f.getAnno(),
				f.getStatoFattura(), f.getCliente());
		// clienteService.update(f.getCliente().getFatture());
		return fatturaRepo.save(newFattura);
	}

	public Page<Fattura> find(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return fatturaRepo.findAll(pageable);
	}

	public Fattura findById(UUID id) throws NotFoundException {
		return fatturaRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("Cliente con Id:" + id + "non trovato!!"));
	}

//	public Cliente findByIdCliente(UUID id) throws NotFoundException {
//		return clienteRepo.findById(id)
//				.orElseThrow(() -> new NotFoundException("Cliente con Id:" + id + "non trovato!!"));
//	}

	public Fattura findByState(StatoFattura statoFattura) throws NotFoundException {
		return fatturaRepo.findByState(statoFattura)
				.orElseThrow(() -> new NotFoundException("Stato fattura:" + statoFattura + "non esistente!!"));

	}

	public Fattura findByData(Date dataFattura) throws NotFoundException {
		return fatturaRepo.findByData(dataFattura)
				.orElseThrow(() -> new NotFoundException("Data fattura:" + dataFattura + "non trovata!!"));

	}

	public Fattura findByAnno(int annoFattura) throws NotFoundException {
		return fatturaRepo.findByAnno(annoFattura)
				.orElseThrow(() -> new NotFoundException("Anno fattura:" + annoFattura + "non trovato!!"));

	}

	public Fattura findByImporto(BigDecimal importoFattura) throws NotFoundException {
		return fatturaRepo.findByImporto(importoFattura)
				.orElseThrow(() -> new NotFoundException("Importo fattura:" + importoFattura + "non trovato!!"));

	}

	public Fattura findByIdAndUpdate(UUID id, Fattura f) throws NotFoundException {
		Fattura found = this.findById(id);

		found.setId(id);
		found.setImporto(f.getImporto());
		found.setNumeroFattura(f.getNumeroFattura());
		found.setData(f.getData());
		found.setAnno(f.getAnno());
		found.setStatoFattura(f.getStatoFattura());
		found.setCliente(f.getCliente());

		return fatturaRepo.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Fattura found = this.findById(id);
		fatturaRepo.delete(found);
	}

}
