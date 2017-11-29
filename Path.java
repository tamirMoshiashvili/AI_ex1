import java.io.*;
import java.util.List;

/**
 * path that hold a list of instructions and a cost.
 */
public class Path<T extends Descriptable<T>> {
    String separator;
    private double cost;
    private List<State<T>> stateList;

    /**
     * constructor.
     *
     * @param separator string that represent the separator between the instructions.
     * @param cost      number that represents the cost of the path.
     * @param stateList list of states, which is the path.
     */
    public Path(String separator, double cost, List<State<T>> stateList) {
        this.separator = separator;
        this.cost = cost;
        this.stateList = stateList;
    }

    /**
     * write the current path to the file.
     *
     * @param filename name of the file.
     */
    public void writePathTo(String filename) {
        StringBuilder stringBuilder = new StringBuilder();

        if (this.stateList.isEmpty()) {
            stringBuilder.append("no path");
        } else {
            int listSize = this.stateList.size();
            State<T> prev = this.stateList.get(0);

            // get the instructions
            for (int i = 1; i < listSize - 1; ++i) {
                State<T> current = this.stateList.get(i);
                stringBuilder.append(prev.getInstructionTo(current)).append(this.separator);

                prev = current;
            }
            // last instruction, without the '-' in the end
            State<T> current = this.stateList.get(listSize - 1);
            stringBuilder.append(prev.getInstructionTo(current));

            stringBuilder.append(" ").append(this.cost);
        }

        // write to file
        writePathTo(filename, stringBuilder);
    }

    /**
     * write the content of stringBuilder into the file.
     *
     * @param filename      name of the file.
     * @param stringBuilder holds the string to write.
     */
    private static void writePathTo(String filename, StringBuilder stringBuilder) {
        try (Writer writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filename), "utf-8"))) {
            writer.write(stringBuilder.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not open the output-file");
            e.printStackTrace();
        }
    }
}
