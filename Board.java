import java.util.ArrayList;
import java.util.List;

/**
 * squared matrix that represents a map.
 */
public class Board implements ISearchable<Point> {
    private int len;
    private char[][] matrix;
    private State<Point> startState, goalState;

    /**
     * constructor.
     *
     * @param len    length of the matrix.
     * @param matrix matrix of chars.
     */
    public Board(int len, char[][] matrix) {
        this.len = len;
        this.matrix = matrix;

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

        Point right = new Point(x, y + 1);
        boolean r = isValid(right);
        addIfValid(successors, right, r, state);

        Point down = new Point(x + 1, y);
        boolean d = isValid(down);
        Point rd = new Point(x + 1, y + 1);
        addIfValid(successors, rd, this.isDiagonalValid(rd, r, d), state);
        addIfValid(successors, down, d, state);

        Point left = new Point(x, y - 1);
        boolean l = isValid(left);
        Point ld = new Point(x + 1, y - 1);
        addIfValid(successors, ld, this.isDiagonalValid(ld, l, d), state);
        addIfValid(successors, left, l, state);

        Point up = new Point(x - 1, y);
        boolean u = isValid(up);
        Point lu = new Point(x - 1, y - 1);
        addIfValid(successors, lu, this.isDiagonalValid(lu, l, u), state);
        addIfValid(successors, up, u, state);

        Point ru = new Point(x - 1, y + 1);
        addIfValid(successors, ru, this.isDiagonalValid(ru, r, u), state);

        return successors;
    }

    /**
     * add the state with the given point into the list if it is valid.
     *
     * @param list    list of states.
     * @param point   point of neighbor.
     * @param isValid bool.
     * @param parent  parent state.
     */
    private void addIfValid(List<State<Point>> list, Point point,
                                   boolean isValid, State<Point> parent) {
        if (isValid) {
            State<Point> state = new State<Point>(point);
            state.updateCost(parent.getCost() + this.getCost(state));
            state.setCameFrom(parent);
            list.add(state);
        }
    }

    /**
     * check if the given point is a valid diagonal neighbor.
     *
     * @param point point.
     * @param b1    is first straight neighbor is valid.
     * @param b2    is second straight neighbor is valid.
     * @return true if it valid, false otherwise.
     */
    private boolean isDiagonalValid(Point point, boolean b1, boolean b2) {
        return isValid(point) && b1 && b2;
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
        }
        return true;
    }

    @Override
    public int getLen() {
        return this.len * this.len;
    }

    @Override
    public double getCost(State<Point> dest) {
        // TODO check if can omit the 'from' object from this function
        double cost;
        Point toPoint = dest.getData();
        int x = toPoint.getX(), y = toPoint.getY();
        char square = this.matrix[x][y];
        switch (square) {
            case 'R':
                cost = 1;
                break;
            case 'D':
                cost = 3;
                break;
            case 'H':
                cost = 10;
                break;
            default:
                cost = 0;
        }
        return cost;
    }
}
