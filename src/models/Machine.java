package models;

/**
 * Created by leo on 17/1/16.
 */
public class Machine implements Comparable {
    private boolean isAvaliable = true;
    private boolean isBlock = false;
    private int machineId;
    private int avaliableTime;
    private int enterTime;
    private Job jobOnThisMach;
    private int completeTime;

    public Machine(int machineId) {
        this.machineId = machineId;
    }

    public int getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(int completeTime) {
        this.completeTime = completeTime;
    }

    public Job getJobOnThisMach() {
        return jobOnThisMach;
    }

    public void setJobOnThisMach(Job jobOnThisMach, int processTime) {
        this.jobOnThisMach = jobOnThisMach;
        this.completeTime = this.enterTime + processTime;
    }

    public int getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(int enterTime) {
        this.enterTime = enterTime;
    }


    public int getAvaliableTime() {

        return avaliableTime;
    }

    public void setAvaliableTime(int avaliableTime) {
        this.avaliableTime = avaliableTime;
    }

    public boolean isAvaliable() {
        return isAvaliable;
    }

    public void setAvaliable(boolean avaliable) {
        isAvaliable = avaliable;
        isBlock = !avaliable;
    }

    public boolean isBlock() {
        return isBlock;
    }

    public void setBlock(boolean block) {
        isBlock = block;
        isAvaliable = !block;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    @Override
    public int compareTo(Object o) {
        Machine e = (Machine) o;
        if (this.completeTime < e.getCompleteTime())
            return -1;
        else if (this.completeTime == e.getCompleteTime())
            return 0;
        else if (this.completeTime > e.getCompleteTime())
            return 1;
        else return 0;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "isAvaliable=" + isAvaliable +
                ", isBlock=" + isBlock +
                ", machineId=" + machineId +
                ", avaliableTime=" + avaliableTime +
                ", enterTime=" + enterTime +
                ", jobOnThisMach=" + jobOnThisMach +
                ", completeTime=" + completeTime +
                '}';
    }
}
