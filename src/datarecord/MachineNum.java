package datarecord;

/**
 * Created by leo on 17/1/16.
 *
 * 用一个二维数组，记录在 某个 stage 上，  某个工件，是在哪台机器上加工的
 *
 * 数组的行为 工件 编号， 列 为  stage 编号
 * 该类中的二维向量记录的是 对应的 job 在 stage 上，是由哪台机器加工的，即机器的编号
 */
public class MachineNum {
    private int stageNumbers;
    private int jobNumbers;
    private static int[][] numbers;

    public MachineNum(int stageNumbers,int jobNumbers){
        this.jobNumbers = jobNumbers;
        this.stageNumbers = stageNumbers;
        numbers = new int[jobNumbers][stageNumbers];
    }

    public static void setProcessMachineNum(int stage, int job, int machineNum){
        numbers[job][stage] = machineNum;
    }

    public static int getMachNumProceThisJob(int jobId, int stageId) {
        return numbers[jobId][stageId];
    }

    public static int[][] getNumbers() {
        return numbers;
    }

}
