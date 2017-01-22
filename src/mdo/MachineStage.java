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
    public Machine getAvaliableMachine() {
        Iterator iterator = machines.iterator();
        while (iterator.hasNext()) {
            Machine machine = (Machine) iterator.next();
            if (machine.isAvaliable()) {
                return machine;
            }
        }
        return null;
    }

    // 将一个工件安排到某台机器上生产
    public void assignAjob(Job job, int machineId, int begainTime, int processTime) {
        Iterator iterator = machines.iterator();
        Machine machine = null;
        /* 根据 machine 的 ID 值，找到该 machine */
        while (iterator.hasNext()) {
            machine = (Machine) iterator.next();
            if (machine.getMachineId() == machineId) {
                break;
            }
        }
        machine.setAvaliable(false);
        MachineNum.setProcessMachineNum(machStageId, job.getJobId(), machineId);
        BegainTimeOnMach.setBegainTime(job.getJobId(), machStageId, begainTime);
        machine.setEnterTime(begainTime);
        machine.setJobOnThisMach(job, processTime);
        EventList.updateEventList(begainTime,job,machStageId);
        Collections.sort(machines);
    }

    // 得到该阶段时最适合进入下一个阶段生产的工件
    public Job getOneJobNeededToPro() {
        for (int i = 0; i < machineNums; i++) {
            if (!machines.get(i).isAvaliable()) {
                machines.get(i).setAvaliable(true);
                return machines.get(i).getJobOnThisMach();
            }
        }
        return null;
    }

    // 将某台机器设置为阻塞状态
    public void setBlocked(Machine machine) {
        machine.setAvaliable(false);
        machine.setBlock(true);
        EventList.removeEventByJobId(machine.getJobOnThisMach());
    }

    /* 根据 mahcine 的 Id 得到该 machine */
    public Machine getMachineById(int machId) {
        Machine machine = null;
        Iterator iterator = machines.iterator();
        while (iterator.hasNext()) {
            machine = (Machine) iterator.next();
            if (machine.getMachineId() == machId) {
                return machine;
            }
        }
        return machine;
    }

    public int getMachStageId() {
        return machStageId;
    }

    public void setMachStageId(int machStageId) {
        this.machStageId = machStageId;
    }

    public int getMachineNums() {
        return machineNums;
    }

    public void setMachineNums(int machineNums) {
        this.machineNums = machineNums;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    public int[] getLastJobOnThisStage() {
        return lastJobOnThisStage;
    }

    public void setLastJobOnThisStage(int[] lastJobOnThisStage) {
        this.lastJobOnThisStage = lastJobOnThisStage;
    }
}
