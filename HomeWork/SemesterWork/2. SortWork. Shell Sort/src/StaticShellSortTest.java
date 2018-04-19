import org.junit.Before;
import org.junit.Test;
import ru.itis.sorts.ShellSort.StaticShellSort;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * 2. SortWork
 * <p>
 * 23 03 2018
 *
 * @author Nita
 */
public class StaticShellSortTest {

    @Test
    public void toSortTest (){
        int [] actual = {1, 4, 8, 0, 2, 5, 3, 7, 6, 9};
        actual = StaticShellSort.toSort(actual);
        int [] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        boolean isEquals = true;
        for (int c = 0; c < expected.length && c < actual.length; c++){
            if (actual[c] != expected[c]){
                isEquals = false;
                break;
            }
        }
        assertTrue(isEquals);
    }



}



