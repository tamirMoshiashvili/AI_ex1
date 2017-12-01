import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * searcher that work with the Iterative-Deepening algorithm.
 */
public class IDSearcher implements ISearcher<Point> {
    private ISearchable<Point> searchable;
    private List<State<Point>> openList;

    /**
     * constructor.
     */
    public IDSearcher() {
        this.searchable = null;
        this.openList = new ArrayList<State<Point>>();
    }

    /**
     * search for a solution in the given problem.
     *
     * @param searchable search-problem.
     * @return path, which is the solution.
     */
    @Override
    public Path<Point> searchPath(ISearchable<Point> searchable) {
        List<State<Point>> stateList = new ArrayList<State<Point>>();
        double cost = 0;

        this.searchable = searchable;
        int len = searchable.getLen();
        State<Point> root = searchable.getStartState();

        // run the algorithm
        State<Point> found = null;
        for (int depth = 0; depth < len; depth++) {
            // init
            this.openList.clear();
            this.openList.add(root);

            // find
            found = DLS(root, depth);
            if (found != null) {
                cost = found.getCost();
                break;
            }
        }

        // add the states of the solution to the list
        while (found != null) {
            stateList.add(found);
            found = found.getCameFrom();
        }
        Collections.reverse(stateList);

        return new Path<Point>("-", cost, stateList);
    }

    /**
     * recursive function to find a solution.
     *
     * @param state current state.
     * @param depth graph depth.
     * @return goal state if there is one that can be reached, null otherwise.
     */
    private State<Point> DLS(State<Point> state, int depth) {
        // base
        if (depth == 0 && state.equals(this.searchable.getGoalState())) {
            return state;
        }
        if (depth > 0) {
            // get successors
            List<State<Point>> successors = this.searchable.getSuccessors(state);
            // sort
//            successors.sort((first, second) -> {
//                double a = first.getCost(), b = second.getCost();
//                return a < b ? -1 : a == b ? 0 : 1;
//            });

            // run the algorithm
            for (State<Point> child : successors) {
                // try a child that is not in the open list
                if (this.openList.indexOf(child) == -1) {
                    this.openList.add(child);

                    State<Point> found = DLS(child, depth - 1);
                    if (found != null) {
                        return found;
                    }
                }
            }
        }
        return null;
    }
}
