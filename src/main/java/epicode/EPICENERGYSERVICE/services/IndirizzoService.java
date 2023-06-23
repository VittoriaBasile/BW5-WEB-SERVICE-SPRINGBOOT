package epicode.EPICENERGYSERVICE.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import epicode.EPICENERGYSERVICE.entities.Comune;
import epicode.EPICENERGYSERVICE.entities.Indirizzo;
import epicode.EPICENERGYSERVICE.repositories.ComuneRepository;
import epicode.EPICENERGYSERVICE.repositories.IndirizzoRepository;

@Service
public class IndirizzoService {

	@Autowired
	IndirizzoRepository indirizzoRepo;

	@Autowired
	ComuneService comuneService;

	@Autowired
	ComuneRepository comuneRepo;

	// ***** CREATE INDIRIZZO *****
	public Indirizzo create(Indirizzo i) {

		//Comune comuneFound = comuneService.findByNome(i.getComune());

		Comune comuneFound = comuneService.findById(i.getComune().getId());

		System.out.println(comuneFound);

		Indirizzo indirizzo = new Indirizzo(i.getVia(), i.getCivico(), i.getLocalità(), i.getCap(), comuneFound);

		return indirizzoRepo.save(indirizzo);

		//		String nomeProvincia = provinciaRepo.findByNome(p.getNome()).toString();
		//		Comune newComune = null;
		//		if (nomeProvincia.equals(c.getNomeProvincia())) {
		//			c.setProvincia(p);
		//			newComune = new Comune(c.getCodiceProvincia(), c.getProgressivoDelComune(), c.getDenominazioneInItaliano(),
		//					c.getNomeProvincia());
		//		}
		//		return comuneRepo.save(newComune);
	}

}
