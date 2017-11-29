/**
 * object that can search on some searchable.
 */
public interface ISearcher<T extends Descriptable<T>> {

    /**
     * search for a solution-path of the given searchable.
     *
     * @param searchable search-problem.
     * @return path object.
     */
    Path<T> searchPath(ISearchable<T> searchable);
}
