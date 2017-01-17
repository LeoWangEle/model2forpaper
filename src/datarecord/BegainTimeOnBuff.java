package datarecord;

/**
 * Created by leo on 17/1/16.
 *
 * 用一个二维数组记录，对应 BufferNum 中的二维数组的对应元素位置上，即对应的阻塞的缓冲区上，工件的开始进入缓冲区时间
 *
 */
public class BegainTimeOnBuff {
    private int buffNums;
    private int stageNums;
    private static int[][] startTimes;

    public BegainTimeOnBuff(int buffNums, int stageNums){
        this.buffNums = buffNums;
        this.stageNums = stageNums;
        startTimes = new int[buffNums][stageNums];
    }

    public static void setBegainTime(int job, int stage, int begaintime) {
        startTimes[job][stage] = begaintime;
    }

    public static int[][] getStartTimes() {
        return startTimes;
    }
}
