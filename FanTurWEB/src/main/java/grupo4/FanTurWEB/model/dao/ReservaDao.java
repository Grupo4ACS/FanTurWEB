package grupo4.FanTurWEB.model.dao;

import javax.ejb.Stateless;
import grupo4.FanTurWEB.model.Reserva;

@Stateless
public class ReservaDao extends AbstractDao<Reserva, Integer>implements  grupo4.FanTurWEB.model.dao.interfaces.ReservaDao {

	@Override
	protected Class<Reserva> getClazz() {
		return Reserva.class;
	}
	
	/* OJO!!!!!!!
	 * YO NO DEJARÍA MODIFICAR LA RESERVA
	 * SALVO PARA CAMBIAR LA FECHA DE PAGO
	 */
	@Override
	public void update(Integer id, Reserva nueva) {
		Reserva actual = this.findById(id);
		actual.setCliente(nueva.getCliente());
		actual.setFechaPago(nueva.getFechaPago());
		actual.setFechaReserva(nueva.getFechaReserva());
		actual.setPaquete(nueva.getPaquete());
		this.update(actual);
	}
	
}
