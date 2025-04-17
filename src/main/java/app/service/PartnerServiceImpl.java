package app.service;

import app.dao.interfaces.PersonDao;
import app.dto.PartnerDto;
import app.service.interfaces.PartnerService;

public class PartnerServiceImpl implements PartnerService {

    private final PersonDao personDao;

    // Constructor para inyección de dependencia
    public PartnerServiceImpl(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public void createPartner(PartnerDto partnerDto) throws Exception {
        // Verifica si el socio ya existe antes de crearlo
        if (personDao.existsPartnerByDocument(partnerDto.getDocument())) {
            throw new Exception("El socio con documento " + partnerDto.getDocument() + " ya existe.");
        }
        personDao.createPartner(partnerDto);
    }

    @Override
    public PartnerDto findPartnerByUserId(PartnerDto partnerDto) throws Exception {
        return personDao.findPartnerByUserId(partnerDto);
    }

    @Override
    public void updatePartner(PartnerDto partnerDto) throws Exception {
        // Verifica si el socio existe antes de actualizarlo
        if (!personDao.existsPartnerByDocument(partnerDto.getDocument())) {
            throw new Exception("No se encontró un socio con documento " + partnerDto.getDocument());
        }
        personDao.updatePartner(partnerDto);
    }

    @Override
    public void deletePartner(PartnerDto partnerDto) throws Exception {
        // Verifica si el socio existe antes de eliminarlo
        if (!personDao.existsPartnerByDocument(partnerDto.getDocument())) {
            throw new Exception("No se encontró un socio con documento " + partnerDto.getDocument());
        }
        personDao.deletePartner(partnerDto);
    }

    @Override
    public boolean existsPartnerByDocument(long document) throws Exception {
        return personDao.existsPartnerByDocument(document);
    }
}

