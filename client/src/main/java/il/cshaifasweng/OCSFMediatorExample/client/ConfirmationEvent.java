package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Confirmation;

public class ConfirmationEvent {
    private Confirmation confirmation;

    public Confirmation getConfirmation() {
        return confirmation;
    }

    public ConfirmationEvent(Confirmation confirmation) {
        this.confirmation = confirmation;
    }
}
