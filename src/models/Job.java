package models;

/**
 * Created by leo on 17/1/16.
 */
public class Job {
    private  int jobId;
    private int[] processTime;

    public Job(int jobId, int[] processTime) {
        this.jobId = jobId;
        this.processTime = processTime;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getProcessTime(int stage) {
        return processTime[stage];
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", processTime=" + processTime +
                '}';
    }
}
