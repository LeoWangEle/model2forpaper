package main;

import datarecord.BegainTimeOnBuff;
import datarecord.BegainTimeOnMach;
import datarecord.BufferNum;
import datarecord.MachineNum;
import mdo.BufferStage;
import mdo.EventList;
import mdo.MachineStage;
import models.Buffer;
import models.Event;
import models.Job;
import models.Machine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 17/1/19.
 */
public class MainClass {

    public static List<BufferStage> bufferStages = new ArrayList<>();
    public static List<MachineStage> machineStages = new ArrayList<>();
    public static List<Job> completedJobs = new ArrayList<>();
    public static List<Job> jobs = new ArrayList<>();

    public static int[][] processTime;
    public static int[] sequence;
    public static int[] machines;
    public static int[] buffers;

    private static int jobNums;
    private static int stageNums;
    private static int bufferNums;

    private static BufferNum bufferNum;
    private static MachineNum machineNum;
    private static BegainTimeOnBuff begainTimeOnBuff;
    private static BegainTimeOnMach begainTimeOnMach;
    private static EventList eventList;

    private static MachineStage machineStage;
    private static BufferStage bufferStage;
    private static Machine machine;
    private static Buffer buffer;

    public static void main(String[] args) {
        init();
        /* 工件先安排在 stage0 上的 前 m1 台 机器上进行加工 */
        assignTheFirstM1();
        Job job = machineStages.get(0).getOneJobNeededToPro();
        int machId = MachineNum.getMachNumProceThisJob(job.getJobId(), machineStages.get(0).getMachStageId());
        try {
            machine = machineStages.get(0).getMachineById(machId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* 如果 下一个阶段没有 机器可以利用 */
        if (null == machineStages.get(1).getAvaliableMachine()) {
            /* 如果下一个阶段没有可以利用的缓冲区 */
            if (null == bufferStages.get(0).getAvaliableBuffer()) {
                /*能进入到这个阶段的说明工件阻塞了机器，更新机器的状态  将当前机器设置为阻塞状态 */
                machineStages.get(0).setBlocked(machine);

                /* 拿到 enventList 中的下一个工件，继续加工*/
                while (!EventList.isEmpty()) {
                    Event event = EventList.getNextEvent();
                    int jobIDOfThisEvent = event.getJobIDOfThisEvent();
                    int stageIdOfThisEvent = event.getStageIDOfThisEvent();
                    if (stageNums == stageIdOfThisEvent) {
                    /*程序进入到这里说明是最后一个 stage 是该工件的最后一个加工阶段 */
                        //todo 将工件更新到已经加工完毕的工件列表中，继续进行下一个 eventList 中的工件的加工

                    } else {
                      /* 能进入到这里，说明 不是最后一个加工阶段 需要回溯*/
                        for (int i = stageIdOfThisEvent; i >= 0; i--) {
                            int machNumProceThisJob = MachineNum.getMachNumProceThisJob(event.getJobIDOfThisEvent(), event.getStageIDOfThisEvent());
                            Machine machine = machineStages.get(stageIdOfThisEvent).getMachineById(machNumProceThisJob);
                            if (null == machineStages.get(stageIdOfThisEvent + 1).getAvaliableMachine()) { /*下一个阶段没有机器可以利用*/
                                if (null == bufferStages.get(stageIdOfThisEvent).getAvaliableBuffer()) {/*下一个阶段没有缓冲区可以利用*/
                                    /*进入到这里的工件只能是阻塞*/
                                    machineStages.get(stageIdOfThisEvent).setBlocked(machine);
                                    break;
                                } else {// todo : 下一个阶段的缓冲区可以利用，安排工件进入缓冲区后，继续回溯

                                }
                            } else {// todo : 下一个阶段的机器可以利用，安排工件进入下一个阶段后，继续回溯

                            }
                        }
                    }
                }

            }/* 下一个阶段有空闲的缓冲区可以利用 */ else {
                buffer = bufferStages.get(0).getAvaliableBuffer();
                bufferStages.get(0).assignAjob(job, buffer.getBufferId(), machine.getCompleteTime());
                // todo : 有空闲的缓冲区可以利用，后则当前机器空闲，可以进行后续的 sequence 序列中的工件的加工

            }

            /* 下一个阶段有空闲的机器可以利用 */
        } else {
            //todo 下一个阶段有空闲的机器可以利用 ，将工件安排到下一个阶段进行生产， 可以进行后续的 sequence 序列中的工件继续加工
        }
    }

    /**
     * 安排工件 在前 m1 台机器机器进行加工
     */
    public static void assignTheFirstM1() {
        int minNum = 0;
        minNum = sequence.length > machines[0] ? machines[0] : sequence.length;
        for (int i = 0; i < minNum; i++) {
            Machine avaMachine = machineStages.get(0).getAvaliableMachine();
            Job job = jobs.get(sequence[i]);
            int MachineId = avaMachine.getMachineId();
            int processTime = job.getProcessTime(0);
            machineStages.get(0).assignAjob(job, MachineId, 0, processTime);
        }
    }

    /**
     * 初始化
     */
    public static void init() {
        processTime = new int[][]{
                {3, 4, 5},
                {5, 5, 9},
                {3, 1, 7},
                {3, 2, 5},
                {2, 3, 6},
                {4, 6, 7},
                {2, 3, 4},
                {3, 4, 5},
                {2, 6, 9},
                {1, 4, 7}
        };
        machines = new int[]{2, 2, 3};
        buffers = new int[]{2, 2};
        sequence = new int[]{2, 6, 9, 8, 7, 10, 3, 4, 1, 5};

        stageNums = machines.length;
        jobNums = sequence.length;
        bufferNums = buffers.length;

        //重新更改 加工工件的序号，从 0 开始
        for (int i = 0; i < jobNums; i++) {
            sequence[i] = sequence[i] - 1;
        }

        // 初始化每个机器阶段的及该阶段上的机器
        for (int i = 0; i < stageNums; i++) {
            MachineStage machineStage = new MachineStage(i, machines[i]);
            machineStages.add(machineStage);
        }

        // 初始化每个缓冲区阶段，及该缓冲区阶段中每个缓冲区状态
        for (int i = 0; i < bufferNums; i++) {
            BufferStage bufferStage = new BufferStage(i, buffers[i]);
            bufferStages.add(bufferStage);
        }

        // 初始化每个工件
        for (int i = 0; i < jobNums; i++) {
            jobs.add(new Job(i, processTime[i]));
        }

        // 初始化程序运行过程中负责记录数据的矩阵
        machineNum = new MachineNum(stageNums, jobNums);
        bufferNum = new BufferNum(stageNums, jobNums);
        begainTimeOnBuff = new BegainTimeOnBuff(jobNums, stageNums);
        begainTimeOnMach = new BegainTimeOnMach(jobNums, stageNums);
        eventList = new EventList();
    }

}
