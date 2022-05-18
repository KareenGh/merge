package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalTime;

public class Confirmation implements Serializable {
    @Serial
    private static final long serialVersionUID = -4990258759799276856L;

    private String message;
    private LocalTime time;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Confirmation(String message) {
        this.message = message;
        this.time = LocalTime.now();
    }

    public LocalTime getTime() {
        return time;
    }
}
