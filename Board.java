import java.util.ArrayList;
import java.util.List;

/**
 * squared matrix that represents a map.
 */
public class Board implements ISearchable<Point> {
    private int len;
    private char[][] matrix;
    State<Point> startState, goalState;
    private List<Point> openList;

    /**
     * constructor.
     *
     * @param len    length of the matrix.
     * @param matrix matrix of chars.
     */
    public Board(int len, char[][] matrix) {
        this.len = len;
        this.matrix = matrix;
        this.openList = new ArrayList<Point>();

        // find start and goal states
        boolean startFound = false, goalFound = false;
        for (int i = 0; i < this.len && (!goalFound && !startFound); i++) {
            for (int j = 0; j < this.len && (!goalFound && !startFound); j++) {
                if (matrix[i][j] == 'S') {
                    this.startState = new State<Point>(new Point(i, j));
                    startFound = true;
                } else if (matrix[i][j] == 'G') {
                    this.goalState = new State<Point>(new Point(i, j));
                    goalFound = true;
                }
            }
        }
    }

    @Override
    public State<Point> getStartState() {
        return this.startState;
    }

    @Override
    public State<Point> getGoalState() {
        return this.goalState;
    }

    @Override
    public List<State<Point>> getSuccessors(State<Point> state) {
        List<State<Point>> successors = new ArrayList<State<Point>>();

        Point statePoint = state.getData();
        int x = statePoint.getX(), y = statePoint.getY();

        for (int j = y - 1; j < y + 1; j++) {
            for (int i = x - 1; i < x + 1; i++) {
                Point current = new Point(i, j);
                if (this.isValid(current)) {
                    this.openList.add(current);
                    successors.add(new State<Point>(current));
                }
            }
        }
        return successors;
    }

    /**
     * check if the given point (i, j) is a valid point in the matrix,
     *
     * @param point point.
     * @return true if it is valid, false otherwise.
     */
    private boolean isValid(Point point) {
        int i = point.getX(), j = point.getY();
        if (i < 0 || i >= this.len) { // check x coordinate
            return false;
        } else if (j < 0 || j >= this.len) { // check y coordinate
            return false;
        } else if (this.matrix[i][j] == 'W') { // check for water
            return false;
        } else if (this.openList.indexOf(new Point(i, j)) != -1) { // duplicate pruning
            return false;
        }
        return true;
    }

}
