package datarecord;

/**
 * Created by leo on 17/1/16.
 *
 * 用一个二维数组记录，对应 BufferNum 中的二维数组的对应元素位置上，即对应的阻塞的缓冲区上，工件的开始进入缓冲区时间
 *
 *  该类需要和 BufferNum 配合使用
 *  BufferNum 中记录的是 工件 job 在 stage 阶段上 所暂存时占用的缓冲区编号
 *  BegainTimeOnBuff 记录的是在 BufferNum  中的对应元素位置上的 进入对应位置缓冲区时的开始时间，即记录进入缓冲区的早晚

 */
public class BegainTimeOnBuff {
    private int jobNums;
    private int stageNums;
    private static int[][] startTimes;

    /*构造函数*/
    public BegainTimeOnBuff(int jobNums, int stageNums){
        this.jobNums = jobNums;
        this.stageNums = stageNums;
        startTimes = new int[jobNums][stageNums];
    }
/*记录工件开始进入该缓冲区的时间*/
    public static void setBegainTime(int job, int stage, int begaintime) {
        startTimes[job][stage] = begaintime;
    }

    public static int[][] getStartTimes() {
        return startTimes;
    }
}
