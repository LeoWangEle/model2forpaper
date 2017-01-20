package test;

import org.junit.Test;
import utils.Utils;

/**
 * Created by leo on 17/1/19.
 */
public class UtilsTest {

    @Test
    public void printTest() {
        int[][] a = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Utils.print2Dmatrix(a);
    }
}
