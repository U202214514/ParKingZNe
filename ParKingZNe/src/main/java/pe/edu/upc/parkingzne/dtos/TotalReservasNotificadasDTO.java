package pe.edu.upc.parkingzne.dtos;

public class TotalReservasNotificadasDTO {
    private String TIPOS_RESERVA;
    private int CANTIDAD;

    public String getTIPOS_RESERVA() {
        return TIPOS_RESERVA;
    }

    public void setTIPOS_RESERVA(String TIPOS_RESERVA) {
        this.TIPOS_RESERVA = TIPOS_RESERVA;
    }

    public int getCANTIDAD() {
        return CANTIDAD;
    }

    public void setCANTIDAD(int CANTIDAD) {
        this.CANTIDAD = CANTIDAD;
    }
}
