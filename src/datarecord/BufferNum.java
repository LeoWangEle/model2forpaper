package datarecord;

/**
 * Created by leo on 17/1/16.
 *
 * 用一个二维数组，记录在 某个 stage 上，  某个工件，是在哪个缓冲区阻塞的
 *
 * 数组的行为 工件 编号， 列 为  stage 编号
 */
public class BufferNum {

    private int stageNumbers;
    private int jobNumbers;
    private static int[][] numbers;

    public BufferNum(int stageNumbers,int jobNumbers){
        this.jobNumbers = jobNumbers;
        this.stageNumbers = stageNumbers;
        numbers = new int[jobNumbers][stageNumbers];
    }

    /* 记录工件在此 stage 中是在哪个缓冲区暂存的 */
    public static void setBufferNumbers(int stage, int job, int bufferNum){
        numbers[job][stage] = bufferNum;
    }

    public static int[][] getNumbers() {
        return numbers;
    }

}
