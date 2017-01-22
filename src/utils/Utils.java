package utils;

/**
 * Created by leo on 17/1/19.
 */
public class Utils {
    /*  打印一个二维数组 */
    public static void print2Dmatrix(int[][] tobePrinted) {
        if (tobePrinted == null) {
            return;
        }
        int row = tobePrinted.length;
        int column = tobePrinted[0].length;
        for (int i = 0; i <row ; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(tobePrinted[i][j]+"  ");
            }
            System.out.println();
        }
    }

    /* 根据机器的编号，在 list 列表中找到该机器并返回 */
}
