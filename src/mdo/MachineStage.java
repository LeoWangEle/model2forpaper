package mdo;

import datarecord.BegainTimeOnMach;
import datarecord.MachineNum;
import models.Job;
import models.Machine;

import java.util.*;

/**
 * Created by leo on 17/1/16.
 */
public class MachineStage {
    private int machStageId;
    private int machineNums;
    private List<Machine> machines = new LinkedList<>();
    private int[] lastJobOnThisStage;

    // 构造函数
    public MachineStage(int machStageId, int machineNums) {
        this.machStageId = machStageId;
        this.machineNums = machineNums;
        for (int i = 0; i < machineNums; i++) {
            machines.add(new Machine(i));
        }
        lastJobOnThisStage = new int[machineNums];
        Arrays.fill(lastJobOnThisStage, -1);

    }

    // 得到一个空闲的机器ID
    public int getAvaliableMachineId() {
        Iterator iterator = machines.iterator();
        while (iterator.hasNext()) {
            Machine machine = (Machine) iterator.next();
            if (machine.isAvaliable()) {
                return machine.getMachineId();
            }
        }
        return -1;
    }

    // 将一个工件安排到某台机器上生产
    public void assignAjob(Job job, int machineId, int begainTime, int processTime) {
        Machine machine = machines.get(machineId);
        machine.setAvaliable(false);
        MachineNum.setProcessMachineNum(machStageId, job.getJobId(), machineId);
        BegainTimeOnMach.setBegainTime(job.getJobId(), machStageId, begainTime);
        machines.get(machineId).setEnterTime(begainTime);
        machines.get(machineId).setJobOnThisMach(job.getJobId(), processTime);
        EventList.updateEventList(begainTime,job,machStageId);
        Collections.sort(machines);
    }

    // 得到该阶段时最适合进入下一个阶段生产的工件
    public int getOneJobNeededToPro() {
        for (int i = 0; i < machineNums; i++) {
            if (!machines.get(i).isAvaliable()) {
                machines.get(i).setAvaliable(true);
                return machines.get(i).getMachineId();
            }
        }
        return -1;
    }

    // 将某台机器设置为阻塞状态
    public void setBlocked(Machine machine) {

    }
}
