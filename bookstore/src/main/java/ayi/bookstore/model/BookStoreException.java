package ayi.bookstore.model;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class BookStoreException {

    private String message;
    private HttpStatus httpStatus;
    private ZonedDateTime timestamp;
    
}
