package app.service;

import app.dao.interfaces.MembresiaDao;
import app.dto.MembresiaDto;
import app.service.interfaces.MembresiaService;

public class MembresiaServiceImpl implements MembresiaService {
    private final MembresiaDao membresiaDao;

    public MembresiaServiceImpl(MembresiaDao membresiaDao) {
        this.membresiaDao = membresiaDao;
    }

    @Override
    public void createMembresia(MembresiaDto membresiaDto) throws Exception {
        if (membresiaDto == null) {
            throw new IllegalArgumentException("La membresía no puede ser nula");
        }
        membresiaDao.createMembresia(membresiaDto);
    }

    @Override
    public MembresiaDto findMembresiaById(long id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido para buscar membresía");
        }
        return membresiaDao.findMembresiaById(id);
    }

    @Override
    public void updateMembresia(MembresiaDto membresiaDto) throws Exception {
        if (membresiaDto == null || membresiaDto.getIdMembresia() <= 0) {
            throw new IllegalArgumentException("Membresía inválida para actualizar");
        }
        if (!membresiaDao.existsById(membresiaDto.getIdMembresia())) {
            throw new Exception("No se puede actualizar: la membresía no existe");
        }
        membresiaDao.updateMembresia(membresiaDto);
    }

    @Override
    public void deleteMembresia(MembresiaDto membresiaDto) throws Exception {
        if (membresiaDto == null || membresiaDto.getIdMembresia() <= 0) {
            throw new IllegalArgumentException("Membresía inválida para eliminar");
        }
        if (!membresiaDao.existsById(membresiaDto.getIdMembresia())) {
            throw new Exception("No se puede eliminar: la membresía no existe");
        }
        membresiaDao.deleteMembresia(membresiaDto);
    }

    @Override
public boolean existsById(long id) throws Exception {
    if (id <= 0) {
        throw new IllegalArgumentException("ID inválido para verificar membresía");
    }
    return membresiaDao.existsById(id);
}

}
