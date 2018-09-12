package paradiseTravels.service.primitiveResponse;

public class PrimitiveBooleanResponse {
    public boolean value;

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public PrimitiveBooleanResponse(boolean value) {
        this.value = value;
    }

    public PrimitiveBooleanResponse() {
    }
}
