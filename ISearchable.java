import java.util.List;

/**
 * object that can be searched on.
 */
public interface ISearchable<T extends Descriptable<T>> {

    /**
     * get the start state of the searchable.
     *
     * @return state.
     */
    State<T> getStartState();

    /**
     * get the goal state of the searchable.
     *
     * @return state.
     */
    State<T> getGoalState();

    /**
     * get a list of successors of the given state.
     *
     * @param state state.
     * @return list of states.
     */
    List<State<T>> getSuccessors(State<T> state);

    /**
     * get the length of the problem.
     *
     * @return int.
     */
    int getLen();

    /**
     * get the cost to move between the given states.
     *
     * @param from source state.
     * @param to   end state.
     * @return cost.
     */
    double getCost(State<T> from, State<T> to);

    /**
     * get the order of the given operator.
     *
     * @param op operator.
     * @return int.
     */
    int getOperatorOrder(String op);
}
