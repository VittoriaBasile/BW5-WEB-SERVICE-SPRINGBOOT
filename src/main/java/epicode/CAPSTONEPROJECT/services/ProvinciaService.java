package epicode.CAPSTONEPROJECT.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import epicode.CAPSTONEPROJECT.entities.Provincia;
import epicode.CAPSTONEPROJECT.repositories.ProvinciaRepository;

@Service
public class ProvinciaService {

	@Autowired
	ProvinciaRepository provinciaRepo;

	public Provincia create(Provincia p) {
		Provincia newProvincia = new Provincia(p.getSigla(), p.getNome(), p.getRegione(), p.getComuni());
		return provinciaRepo.save(newProvincia);
	}

}
