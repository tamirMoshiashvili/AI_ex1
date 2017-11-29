/**
 * object that can give a description (instruction) of how to get from one state to another.
 */
public interface Descriptable<T> {
    String getInstructionTo(T other);
}
