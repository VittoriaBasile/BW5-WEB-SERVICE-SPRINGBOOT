package epicode.EPICENERGYSERVICE.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import epicode.EPICENERGYSERVICE.entities.Comune;
import epicode.EPICENERGYSERVICE.entities.Provincia;
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

}
