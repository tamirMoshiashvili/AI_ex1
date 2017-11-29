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

        // straight squares
        Point right = new Point(x, y + 1);
        boolean r = isValid(right);
        addStraightIfValid(successors, right, r);

        Point down = new Point(x + 1, y);
        boolean d = isValid(down);
        addStraightIfValid(successors, down, d);

        Point left = new Point(x, y - 1);
        boolean l = isValid(left);
        addStraightIfValid(successors, left, l);

        Point up = new Point(x - 1, y);
        boolean u = isValid(up);
        addStraightIfValid(successors, up, u);

        // diagonal squares
        Point rd = new Point(x + 1, y + 1);
        this.addDiagonalIfValid(successors, rd, r, d);

        Point ld = new Point(x + 1, y - 1);
        this.addDiagonalIfValid(successors, ld, l, d);

        Point lu = new Point(x - 1, y - 1);
        this.addDiagonalIfValid(successors, lu, l, u);

        Point ru = new Point(x - 1, y + 1);
        this.addDiagonalIfValid(successors, ru, r, u);

        return successors;
    }

    /**
     * add a state with the given point to the list if it is valid.
     * the point will be in straight direction from the source point,
     * i.e. only left, right, up or down point.
     *
     * @param list    list to add the object to.
     * @param point   point.
     * @param isValid bool.
     */
    private static void addStraightIfValid(List<State<Point>> list, Point point, boolean isValid) {
        if (isValid) {
            list.add(new State<Point>(point));
        }
    }

    /**
     * add a state with the given point to the list if it is valid.
     * the point will be in diagonal direction from the source point,
     * i.e. only left-up, left-down, right-up or right-down point.
     *
     * @param list  list to add the object to.
     * @param point point.
     * @param b1    boolean that say if the right or left direction is valid.
     * @param b2    boolean that say if the up or down direction is valid.
     */
    private void addDiagonalIfValid(List<State<Point>> list, Point point, boolean b1, boolean b2) {
        if (isValid(point) && b1 && b2) {
            list.add(new State<Point>(point));
        }
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
}
