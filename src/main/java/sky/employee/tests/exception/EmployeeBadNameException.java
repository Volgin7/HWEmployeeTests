package sky.employee.tests.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeBadNameException extends RuntimeException {

    public EmployeeBadNameException() {
    }

    public EmployeeBadNameException(String message) {
        super(message);
    }

    public EmployeeBadNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeBadNameException(Throwable cause) {
        super(cause);
    }

    public EmployeeBadNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}