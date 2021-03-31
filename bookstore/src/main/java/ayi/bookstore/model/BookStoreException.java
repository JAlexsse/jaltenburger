package ayi.bookstore.model;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookStoreException {

    private String message;
    private HttpStatus httpStatus;
    private ZonedDateTime timestamp;
    
}
