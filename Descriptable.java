/**
 * object that can give a description (instruction) of how to get from one state to another.
 */
public interface Descriptable<T> {

    /**
     * get the instruction to move to the other object.
     *
     * @param other other object.
     * @return string representation of the instruction.
     */
    String getInstructionTo(T other);
}
