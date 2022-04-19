package mk.ukim.finki.emt.lab2.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CountryNotFoundException extends RuntimeException{

    public CountryNotFoundException(Long id) {
        super(String.format("Country with id: %d was not found", id));
    }
}

