package ayi.bookstore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InformationNotCorrectException extends RuntimeException{

    /**
    Excepcion personalizada para cuando se realiza una request con informacion equivocada o no suficiente.
    
    */
    private static final long serialVersionUID = 1L;

    public InformationNotCorrectException() {
        super();
    }

    public InformationNotCorrectException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InformationNotCorrectException(final String message) {
        super(message);
    }

    public InformationNotCorrectException(final Throwable cause) {
        super(cause);
    }
}
    
