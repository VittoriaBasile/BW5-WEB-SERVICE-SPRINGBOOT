package epicode.EPICENERGYSERVICE.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import epicode.EPICENERGYSERVICE.entities.Cliente;
import epicode.EPICENERGYSERVICE.exceptions.NotFoundException;
import epicode.EPICENERGYSERVICE.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepo;

	// ***** CREATE *****
	public Cliente create(Cliente c) {
		Cliente newCliente = new Cliente(c.getNome(), c.getPartitaIva(), c.getIndirizzoLegale(),
				c.getIndirizzoOperativo(), c.getEmail(), c.getTelefono(), c.getPec(), c.getEmailContatto(),
				c.getNomeContatto(), c.getCognomeContatto(), c.getTelefonoContatto(), LocalDate.now(), LocalDate.now(),
				c.getRagioneSociale(), 0.00, c.getFatture());

		return clienteRepo.save(newCliente);
	}

	// ***** READ *****
	public Page<Cliente> findAll(int page, int size, String sortBy) {
		if (size < 0)
			size = 0;
		if (size > 100)
			size = 100;

		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return clienteRepo.findAll(pageable);
	}

	// read by Id
	public Cliente findById(UUID clienteId) throws NotFoundException {
		return clienteRepo.findById(clienteId).orElseThrow(() -> new NotFoundException("Cliente non trovato"));
	}

	// read by nome
	public List<Cliente> findByNome(String nome) throws NotFoundException {
		List<Cliente> clienti = clienteRepo.findAll();
		List<Cliente> clientiPerNome = new ArrayList<>();
		boolean trovato = false;

		for (Cliente cliente : clienti) {
			if (cliente.getNome().contains(nome)) {
				clientiPerNome.add(cliente);
				trovato = true;
			}
		}

		if (!trovato) {
			throw new NotFoundException("Nessun cliente trovato con il nome: " + nome);
		}

		return clientiPerNome;
	}

	// read by FatturatoAnnuo
	public List<Cliente> findByFatturatoAnnuo(double fatturatoAnnuo) throws NotFoundException {
		List<Cliente> clienti = clienteRepo.findAll();
		List<Cliente> clientiPerFatturato = new ArrayList<>();
		boolean trovato = false;

		for (Cliente cliente : clienti) {
			int comparazione = Double.compare(cliente.getFatturatoAnnuo(), fatturatoAnnuo);
			if (comparazione == 0) {
				clientiPerFatturato.add(cliente);
				trovato = true;
			}
		}

		if (!trovato) {
			throw new NotFoundException("Nessun cliente trovato con il fatturato annuo: " + fatturatoAnnuo);
		}

		return clientiPerFatturato;
	}

// read by DataInserimento
	public List<Cliente> findByDataInserimento(LocalDate data) throws NotFoundException {
		List<Cliente> clienti = clienteRepo.findAll();
		List<Cliente> clientiPerDataInserimento = new ArrayList<>();
		boolean trovato = false;

		for (Cliente cliente : clienti) {
			if (cliente.getDataInserimento().isEqual(data)) {
				clientiPerDataInserimento.add(cliente);
				trovato = true;
			}
		}

		if (!trovato) {
			throw new NotFoundException("Nessun cliente trovato con la data di inserimento: " + data);
		}

		return clientiPerDataInserimento;
	}

// read by DataUltimoContatto
	public List<Cliente> findByDataUltimoContatto(LocalDate data) throws NotFoundException {
		List<Cliente> clienti = clienteRepo.findAll();
		List<Cliente> clientiPerDataUltimoContatto = new ArrayList<>();
		boolean trovato = false;

		for (Cliente cliente : clienti) {
			if (cliente.getDataUltimoContatto().isEqual(data)) {
				clientiPerDataUltimoContatto.add(cliente);
				trovato = true;
			}
		}

		if (!trovato) {
			throw new NotFoundException("Nessun cliente trovato con la data di ultimo contatto: " + data);
		}

		return clientiPerDataUltimoContatto;
	}

	// ***** UPDATE *****
	public Cliente update(UUID clienteId, Cliente c) throws NotFoundException {
		Cliente clienteFound = this.findById(clienteId);

		clienteFound.setId(clienteId);
		clienteFound.setNome(c.getNome());
		clienteFound.setPartitaIva(c.getPartitaIva());
		clienteFound.setIndirizzoLegale(c.getIndirizzoLegale());
		clienteFound.setIndirizzoOperativo(c.getIndirizzoOperativo());
		clienteFound.setEmail(c.getEmail());
		clienteFound.setTelefono(c.getTelefono());
		clienteFound.setPec(c.getPec());
		clienteFound.setEmailContatto(c.getEmailContatto());
		clienteFound.setNomeContatto(c.getNomeContatto());
		clienteFound.setCognomeContatto(c.getCognomeContatto());
		clienteFound.setTelefonoContatto(c.getTelefonoContatto());
		clienteFound.setDataUltimoContatto(c.getDataUltimoContatto());
		clienteFound.setRagioneSociale(c.getRagioneSociale());
		clienteFound.setFatturatoAnnuo(c.getFatturatoAnnuo());
		clienteFound.setFatture(c.getFatture());

		return clienteRepo.save(clienteFound);
	}

	// ***** DELETE *****
	public void delete(UUID clienteId) throws NotFoundException {
		Cliente clienteFound = this.findById(clienteId);

		clienteRepo.delete(clienteFound);
	}

}
