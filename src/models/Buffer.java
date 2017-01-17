package models;

/**
 * Created by leo on 17/1/16.
 */
public class Buffer implements Comparable {
    private boolean isTaken = false;
    private int bufferId;
    private int enterTime;
    private Job jobInBuffer;


    public Buffer(int bufferId, boolean isTaken) {
        this.bufferId = bufferId;
        this.isTaken = isTaken;
    }

    public Job getJobInBuffer() {
        return jobInBuffer;
    }

    public void setJobInBuffer(Job jobInBuffer) {
        this.jobInBuffer = jobInBuffer;
    }

    public int getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(int enterTime) {
        this.enterTime = enterTime;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public int getBufferId() {
        return bufferId;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    @Override
    public int compareTo(Object o) {
        Buffer e = (Buffer) o;
        if (this.enterTime < e.getEnterTime())
            return -1;
        else if (this.enterTime == e.getEnterTime())
            return 0;
        else if (this.enterTime > e.getEnterTime())
            return 1;
        else return 0;
    }

    @Override
    public String toString() {
        return "Buffer{" +
                "isTaken=" + isTaken +
                ", bufferId=" + bufferId +
                ", enterTime=" + enterTime +
                ", jobInBuffer=" + jobInBuffer +
                '}';
    }
}
