package mdo;

import models.Event;
import models.Job;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by leo on 17/1/8.
 */
public class EventList {

    private static List<Event> eventList = new LinkedList<>();
    private static Iterator iterator = eventList.iterator();

    public static boolean isEmpty(){
        return eventList.isEmpty();
    }

    public static void addEvent(Event event) {
        eventList.add(event);
        Collections.sort(eventList);
    }

    public static Event getNextEvent() {
        if(eventList.isEmpty()){
            return null;
        }
        Event event =  eventList.get(0);
        eventList.remove(0);
        return event;
    }

    public static boolean removeEventByJobId(int jobId) {
        while (iterator.hasNext()) {
            Event e = (Event) iterator.next();
            if (e.getJobIDOfThisEvent() == jobId) {
                iterator.remove();
                Collections.sort(eventList);
                return true; // 删除成功

            }
        }
        return false; // 删除失败
    }

    public static void updateEventList(int beginTime,Job thisJob, int stage) {
        Event event = new Event(beginTime + thisJob.getProcessTime(),stage,thisJob.getJobId());
        event.setJobIDOfThisEvent(thisJob.getJobId());
        event.setStageIDOfThisEvent(stage);
        addEvent(event);
        Collections.sort(eventList);
    }

    @Override
    public String toString() {
        return "EventList{" +
                "eventList=" + eventList +
                '}';
    }
}
