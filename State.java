/**
 * abstract state class.
 */
public class State<T extends Descriptable<T>> implements Descriptable<State<T>> {
    private T data;
    private double cost;

    /**
     * constructor.
     *
     * @param data data type.
     */
    public State(T data) {
        this.data = data;
        this.cost = 0;
    }

    /**
     * get the inner data.
     *
     * @return data of type T.
     */
    public T getData() {
        return this.data;
    }

    /**
     * get the current cost from the start-state.
     *
     * @return double.
     */
    public double getCost() {
        return this.cost;
    }

    /**
     * add the given value to the current cost.
     *
     * @param value double.
     */
    public void updateCost(double value) {
        this.cost += value;
    }

    /**
     * get the instruction that describe how to move from the current state to the other state.
     *
     * @param other other state.
     * @return string.
     */
    @Override
    public String getInstructionTo(State<T> other) {
        return this.data.getInstructionTo(other.getData());
    }

    /**
     * check if this state is equal to the given object.
     *
     * @param other other object.
     * @return true if the object is state and its data is equal to this object's data, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return this.data.equals(other);
    }
}
