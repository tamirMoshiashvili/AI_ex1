/**
 * can create Board-object from other types.
 */
public class BoardFactory {

    /**
     * create a board from a string.
     *
     * @param len      length (width = height).
     * @param boardStr string that represents the matrix.
     * @return board object.
     */
    public static Board toBoard(int len, String boardStr) {
        // convert the string to matrix
        char[][] matrix = new char[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int charIndex = i * len + j;
                matrix[i][j] = boardStr.charAt(charIndex);
            }
        }

        return new Board(len, matrix);
    }
}
