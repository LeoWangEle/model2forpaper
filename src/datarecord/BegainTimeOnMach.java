package datarecord;

/**
 * Created by leo on 17/1/16.
 *
 * 用一个二维数组记录，对应 MachineNum类 中的二维数组的对应元素位置上，即对应的机器上，工件的开始加工时间
 *
 * 该类 和 MachineNum 配合使用，记录的是， MachineNum 类中二维数组对应位置上的工件的加工开始时间
 */
public class BegainTimeOnMach {
    private int jobNums;
    private int stageNums;
    private static int[][] startTimes;

    public BegainTimeOnMach(int jobNums, int stageNums){
        this.jobNums = jobNums;
        this.stageNums = stageNums;
        startTimes = new int[jobNums][stageNums];
    }

    public static void setBegainTime(int job, int stage, int begaintime) {
        startTimes[job][stage] = begaintime;
    }

    public int[][] getStartTimes() {
        return startTimes;
    }
}
