package gr.cinema.api.testing.dto;

public class ReqBodyDTO {

    private String thirdString;
    private int firstInt;
    private int secondInt;
    private boolean addMe;

    public String getThirdString() {
        return thirdString;
    }

    public void setThirdString(String thirdString) {
        this.thirdString = thirdString;
    }

    public int getFirstInt() {
        return firstInt;
    }

    public void setFirstInt(int firstInt) {
        this.firstInt = firstInt;
    }

    public int getSecondInt() {
        return secondInt;
    }

    public void setSecondInt(int secondInt) {
        this.secondInt = secondInt;
    }

    public boolean isAddMe() {
        return addMe;
    }

    public void setAddMe(boolean addMe) {
        this.addMe = addMe;
    }
}
