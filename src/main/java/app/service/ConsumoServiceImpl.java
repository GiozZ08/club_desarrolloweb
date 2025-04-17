package app.service;

import app.dao.interfaces.ConsumoDao;
import app.dto.ConsumoDto;
import app.service.interfaces.ConsumoService;

/**
 * Implementación de {@link ConsumoService} para la gestión de consumos.
 */
public class ConsumoServiceImpl implements ConsumoService {

    private final ConsumoDao consumoDao;

    /**
     * Constructor que inicializa el servicio con un DAO de consumo.
     *
     * @param consumoDao DAO de consumo para la interacción con la base de datos.
     */
    public ConsumoServiceImpl(ConsumoDao consumoDao) {
        this.consumoDao = consumoDao;
    }

    @Override
    public void createConsumo(ConsumoDto consumoDto) throws Exception {
        consumoDao.createConsumo(consumoDto);
    }

    @Override
    public ConsumoDto findConsumoById(long id) throws Exception {
        return consumoDao.findConsumoById(id);
    }

    @Override
    public void updateConsumo(ConsumoDto consumoDto) throws Exception {
        consumoDao.updateConsumo(consumoDto);
    }

    @Override
    public void deleteConsumo(ConsumoDto consumoDto) throws Exception {
        consumoDao.deleteConsumo(consumoDto);
    }

    @Override
    public boolean existsById(long id) throws Exception {
        return consumoDao.existsById(id);
    }
}
