package grupo4.FanTurWEB.test;

import static org.junit.Assert.*;

import org.junit.Test;

import grupo4.FanTurWEB.model.Ubicacion;
import grupo4.FanTurWEB.model.dao.UbicacionDao;

public class UbicacionDaoTest {

	private UbicacionDao ubicacionDao;
	private Ubicacion ubicacion;
	
	@Test
	public void insertionTest() {
		ubicacion = new Ubicacion("French", 440, "Resistencia", "Chaco", "Argentina");
		ubicacionDao = new UbicacionDao();
		ubicacionDao.create(ubicacion);
	}

}
