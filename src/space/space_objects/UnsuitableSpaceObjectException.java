package space.space_objects;

public class UnsuitableSpaceObjectException extends RuntimeException {
    public SpaceObjectType expected;
    public SpaceObjectType received;

    UnsuitableSpaceObjectException() {
        super("Ожидался космический объект другого типа.");
    }

    UnsuitableSpaceObjectException(String message) {
        super(message);
    }

    UnsuitableSpaceObjectException(SpaceObjectType expected, SpaceObjectType received) {
        super("Неверный тип космического объекта: ожидалось " + expected + ", получено " + received + ".");
        this.expected = expected;
        this.expected = received;
    }

    public UnsuitableSpaceObjectException(String message, SpaceObjectType expected, SpaceObjectType received) {
        super(message + "\nОжидаемый тип объекта: " + expected + "\nПолученный: " + received);
        this.expected = expected;
        this.expected = received;
    }
}
