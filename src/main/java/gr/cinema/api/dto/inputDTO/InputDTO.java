package gr.cinema.api.dto.inputDTO;

import org.springframework.stereotype.Service;

@Service
public class InputDTO {
    private String firstString;
    private String secondString;
    private String storedInput ="";


    public String getFirstString() {
        return firstString;
    }

    public void setFirstString(String firstString) {
        this.firstString = firstString;
    }

    public String getSecondString() {
        return secondString;
    }

    public void setSecondString(String secondString) {
        this.secondString = secondString;
    }

    public String getStoredInput() {
        return storedInput;
    }

    public void setStoredInput(String storedInput) {
        this.storedInput = storedInput;
    }
}
