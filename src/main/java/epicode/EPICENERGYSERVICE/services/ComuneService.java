package epicode.EPICENERGYSERVICE.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import epicode.EPICENERGYSERVICE.entities.Comune;
import epicode.EPICENERGYSERVICE.entities.Provincia;
import epicode.EPICENERGYSERVICE.exceptions.NotFoundException;
import epicode.EPICENERGYSERVICE.repositories.ComuneRepository;
import epicode.EPICENERGYSERVICE.repositories.ProvinciaRepository;

@Service
public class ComuneService {

	@Autowired
	ComuneRepository comuneRepo;

	@Autowired
	ProvinciaRepository provinciaRepo;

	public List<Comune> readAll() {
		return comuneRepo.findAll();
	}

	public Comune findById(UUID comuneId) throws NotFoundException {
		return comuneRepo.findById(comuneId).orElseThrow(() -> new NotFoundException("Id non trovato"));
	}

	//	public Comune create(Comune c, Provincia p) {
	//
	//		String nomeProvincia = provinciaRepo.findByNome(p.getNome()).toString();
	//		Comune newComune = null;
	//		if (nomeProvincia.equals(c.getNomeProvincia())) {
	//			c.setProvincia(p);
	//			newComune = new Comune(c.getCodiceProvincia(), c.getProgressivoDelComune(), c.getDenominazioneInItaliano(),
	//					c.getNomeProvincia());
	//		}
	//		return comuneRepo.save(newComune);
	//	}
	public List<Comune> createComuniWithProvincia(List<Comune> comuni) {
		List<Provincia> province = provinciaRepo.findAll();

		for (Provincia provincia : province) {
			comuni = comuneRepo.findByProvincia(provincia.getNome().toString());

			for (Comune comune : comuni) {
				comune.setProvincia(provincia);
				comuneRepo.save(comune);

			}
		}
		return comuni;
	}

	//	public Comune createComuniWithProvincia(Comune c, Provincia p) {
	//		c.setProvincia(p);
	//		comuneRepo.save(c);
	//		return c;
	//	}

}
