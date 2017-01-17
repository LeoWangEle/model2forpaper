package models;

/**
 * Created by leo on 17/1/8.
 */
public class Event implements Comparable{

    private int completionTime;

    private int jobIDOfThisEvent;

    private int stageIDOfThisEvent;

    public int getJobIDOfThisEvent() {
        return jobIDOfThisEvent;
    }

    public void setJobIDOfThisEvent(int jobIDOfThisEvent) {
        this.jobIDOfThisEvent = jobIDOfThisEvent;
    }

    public int getStageIDOfThisEvent() {
        return stageIDOfThisEvent;
    }

    public void setStageIDOfThisEvent(int stageIDOfThisEvent) {
        this.stageIDOfThisEvent = stageIDOfThisEvent;
    }

    public Event(int completionTime, int stage, int jobID) {
        this.stageIDOfThisEvent = stage;
        this.completionTime = completionTime;
        this.jobIDOfThisEvent = jobID;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }



    @Override
    public int compareTo(Object o) {
        Event e = (Event) o;
        if(this.getCompletionTime()<e.getCompletionTime())
            return -1;
        else if(this.getCompletionTime()==e.getCompletionTime())
            return 0;
        else if(this.getCompletionTime()>e.getCompletionTime())
            return 1;
        else return 0;
    }

    @Override
    public String toString() {
        return "Event{" +
                "completionTime=" + completionTime +
                ", JobIDOfThisEvent=" + jobIDOfThisEvent +
                ", stageIDOfThisEvent=" + stageIDOfThisEvent +
                '}';
    }
}
