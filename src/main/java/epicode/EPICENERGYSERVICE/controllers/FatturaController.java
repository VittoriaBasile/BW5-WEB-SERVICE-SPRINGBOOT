package epicode.EPICENERGYSERVICE.controllers;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import epicode.EPICENERGYSERVICE.entities.Fattura;
import epicode.EPICENERGYSERVICE.entities.StatoFattura;
import epicode.EPICENERGYSERVICE.exceptions.NotFoundException;
import epicode.EPICENERGYSERVICE.services.FatturaService;

@RestController
@RequestMapping("/fatture")
public class FatturaController {
	@Autowired
	private FatturaService fatturaService;

	@GetMapping("")
	public Page<Fattura> getFatture(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return fatturaService.find(page, size, sortBy);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Fattura createFattura(@RequestBody @Validated Fattura f) {
		return fatturaService.create(f);
	}

	@GetMapping("/{fatturaId}")
	public Fattura getFattura(@PathVariable UUID fatturaId) throws Exception {
		return fatturaService.findById(fatturaId);
	}

	@GetMapping("/{statoFattura}")
	public List<Fattura> getFatturaByStato(@PathVariable StatoFattura stato) throws Exception {
		return fatturaService.findByStato(stato);
	}

	@GetMapping("/{dataFattura}")
	public List<Fattura> getFatturaByData(@PathVariable Date data) throws Exception {
		return fatturaService.findByData(data);
	}

	@GetMapping("/{annoFattura}")
	public List<Fattura> getFatturaByAnno(@PathVariable int anno) throws Exception {
		return fatturaService.findByAnno(anno);
	}

//	@GetMapping("/{importoMinimo}/{importoMassimo}")
//	public List<Fattura> getFatturaByImporto(@PathVariable BigDecimal importoMinimo,
//			@PathVariable BigDecimal importoMassimo) throws NotFoundException {
//		List<Fattura> fatture = fatturaService.findByImporti(importoMinimo, importoMassimo);
//		if (fatture.isEmpty()) {
//			throw new NotFoundException(
//					"Nessuna fattura trovata con importo compreso tra " + importoMinimo + " e " + importoMassimo);
//		}
//		return fatture;
//	}

	@PutMapping("/{fatturaId}")
	public Fattura updateUser(@PathVariable UUID fatturaId, @RequestBody Fattura f) throws Exception {
		return fatturaService.findByIdAndUpdate(fatturaId, f);
	}

	@DeleteMapping("/{fatturaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID fatturaId) throws NotFoundException {
		fatturaService.findByIdAndDelete(fatturaId);
	}

}
