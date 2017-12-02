/**
 * state class.
 */
public class State<T extends Descriptable<T>> implements Descriptable<State<T>> {
    private T data;
    private int cost;
    private State<T> cameFrom;
    private int depth;
    private int discoverTime;

    /**
     * constructor.
     *
     * @param data data type.
     */
    public State(T data) {
        this.data = data;
        this.cost = 0;
        this.cameFrom = null;
        this.depth = 0;
        this.discoverTime = 0;
    }

    /**
     * get the discover time of this state.
     *
     * @return int.
     */
    public int getDiscoverTime() {
        return discoverTime;
    }

    /**
     * set the discover time.
     *
     * @param discoverTime int.
     */
    public void setDiscoverTime(int discoverTime) {
        this.discoverTime = discoverTime;
    }

    /**
     * get the depth of this state.
     *
     * @return int.
     */
    public int getDepth() {
        return depth;
    }

    /**
     * set the depth to the given one.
     *
     * @param depth int.
     */
    public void setDepth(int depth) {
        this.depth = depth;
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
    public int getCost() {
        return this.cost;
    }

    /**
     * set the cost of the current state to the given value.
     *
     * @param value new cost.
     */
    public void setCost(int value) {
        this.cost = value;
    }

    /**
     * add the given value to the current cost.
     *
     * @param value double.
     */
    public void updateCost(int value) {
        this.cost += value;
    }

    /**
     * get the father state of this state.
     *
     * @return state, null if it is the start-state.
     */
    public State<T> getCameFrom() {
        return this.cameFrom;
    }

    /**
     * set the father state of this state.
     *
     * @param cameFrom state.
     */
    public void setCameFrom(State<T> cameFrom) {
        this.cameFrom = cameFrom;
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
        if (other instanceof State) {
            State<Point> otherState = (State<Point>) other;
            return this.data.equals(otherState.getData());
        }
        return false;
    }
}
