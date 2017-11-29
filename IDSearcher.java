import java.util.ArrayList;
import java.util.List;

/**
 * searcher that work with the Iterative-Deepening algorithm.
 */
public class IDSearcher implements ISearcher<Point> {
    private ISearchable<Point> searchable;
    private List<State<Point>> openList;

    public IDSearcher() {
        this.searchable = null;
        this.openList = new ArrayList<State<Point>>();
    }

    @Override
    public Path<Point> searchPath(ISearchable<Point> searchable) {
        this.searchable = searchable;
        return null;
    }

    private State<Point> DLS(State<Point> state, int depth) {
        if (depth == 0 && state.equals(this.searchable.getGoalState())) {
            return state;
        }
        if (depth > 0) {
            List<State<Point>> successors = this.searchable.getSuccessors(state);
            updateCostOfSuccessors(successors, state);
        }
        return null;
    }

    /**
     * update the cost of the successors according to their parent cost.
     *
     * @param successors list of states.
     * @param parent     state.
     */
    private void updateCostOfSuccessors(List<State<Point>> successors, State<Point> parent) {
        double parentCost = parent.getCost();
        for (State<Point> successor : successors) {
            successor.updateCost(parentCost);
        }
    }
}
