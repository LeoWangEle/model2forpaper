package datarecord;

/**
 * Created by leo on 17/1/16.
 *
 * 用一个二维数组，记录在 某个 stage 上，  某个工件，是在哪台机器上加工的
 *
 * 数组的行为 工件 编号， 列 为  stage 编号
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

    public static int[][] getNumbers() {
        return numbers;
    }

}
