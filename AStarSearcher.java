import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * searcher that work with the A* algorithm.
 */
public class AStarSearcher implements ISearcher<Point> {
    private List<State<Point>> openList;

    /**
     * constructor.
     */
    public AStarSearcher() {
        this.openList = new ArrayList<State<Point>>();
    }

    /**
     * sort the open list according to their cost and distance from goal position.
     *
     * @param goalPoint goal point.
     */
    private void sortOpenList(Point goalPoint) {
        // sort the list
        this.openList.sort((first, second) -> {
            double a = first.getCost() + first.getData().distanceTo(goalPoint);
            double b = second.getCost() + second.getData().distanceTo(goalPoint);
            return a < b ? -1 : a == b ? 0 : 1;
        });
    }

    @Override
    public Path<Point> searchPath(ISearchable<Point> searchable) {
        List<State<Point>> stateList = new ArrayList<State<Point>>();
        int cost = 0;

        // init
        State<Point> root = searchable.getStartState();
        State<Point> goal = searchable.getGoalState();
        Point goalPoint = goal.getData();
        this.openList.add(root);

        State<Point> found = null;
        while (!this.openList.isEmpty()) {
            // get the best state
            this.sortOpenList(goalPoint);
            State<Point> current = this.openList.get(0);
            if (current.equals(goal)) {
                // found a match
                found = current;
                break;
            }
            this.openList.remove(current);

            iterateOverNeighborsOf(current, searchable);
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
     * iterate over the successors of the curretn state in the given searchable.
     *
     * @param current    state.
     * @param searchable search-problem.
     */
    private void iterateOverNeighborsOf(State<Point> current, ISearchable<Point> searchable) {
        // get successors
        List<State<Point>> successors = searchable.getSuccessors(current);
        for (State<Point> neighbor : successors) {
            // duplicate pruning
            if (this.openList.indexOf(neighbor) == -1) {
                this.openList.add(neighbor);
            }

            // search for better cost
            int newCost = current.getCost() + searchable.getCost(neighbor);
            if (newCost >= neighbor.getCost()) {
                continue;
            }

            // this path is better
            neighbor.setCameFrom(current);
            neighbor.setCost(newCost);
        }
    }
}
