package main;

import mdo.BufferStage;
import mdo.MachineStage;
import models.Job;

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

    public static void main(String[] args) {
        init();


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

        // 初始化每个机器阶段的及该阶段上的机器
        for (int i = 0; i < machines.length; i++) {
            MachineStage machineStage = new MachineStage(i, machines[i]);
            machineStages.add(machineStage);
        }

        // 初始化每个缓冲区阶段，及该缓冲区阶段中每个缓冲区状态
        for (int i = 0; i < buffers.length; i++) {
            BufferStage bufferStage = new BufferStage(i, buffers[i]);
            bufferStages.add(bufferStage);
        }

        // 初始化每个工件
        for (int i = 0; i < sequence.length; i++) {
            jobs.add(new Job(i,processTime[i]));
        }
    }
}
