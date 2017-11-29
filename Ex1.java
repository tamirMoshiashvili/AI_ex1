import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * main class to run the program.
 */
public class Ex1 {

    /**
     * run a searcher on a search-problem.
     *
     * @param args
     */
    public static void main(String[] args) {
        String filename = "input.txt";
        try {
            // read input file
            BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
            String algoName = reader.readLine();
            int len = Integer.parseInt(reader.readLine());
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();

            // search-problem
            ISearchable<Point> board = BoardFactory.toBoard(len, sb.toString());

            // searchers
            Map<String, ISearcher<Point>> searcherFactory = new HashMap<String, ISearcher<Point>>();
            searcherFactory.put("IDS", new IDSearcher());
            searcherFactory.put("A*", new AStarSearcher());

            // search
            ISearcher<Point> searcher = searcherFactory.get(algoName);
            if (searcher == null){
                System.out.println("Error: Unknown searcher");
                return;
            }
            Path<Point> path = searcher.searchPath(board);
            path.writePathTo("output.txt");
        } catch (IOException e) {
            System.out.println("Error in input file");
            e.printStackTrace();
        }
    }
}
