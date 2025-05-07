package pe.edu.upc.parkingzne.exceptions;

public class RequestBodyException extends RuntimeException {

    public RequestBodyException() {
        super("El cuerpo de la solicitud (RequestBody) es inválido o está incompleto.");
    }

    public RequestBodyException(String message) {
        super(message);
    }

    public RequestBodyException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestBodyException(Throwable cause) {
        super(cause);
    }
}
