package test;

import mdo.BufferStage;
import models.Job;
import org.junit.Test;

/**
 * Created by leo on 17/1/19.
 */
public class BufferStageTest {
    @Test
    public void assignAjobTest() {
        BufferStage bufferStage = new BufferStage(1, 2);
        int[] processTime = new int[]{1, 2, 3};
        Job job = new Job(1, processTime);
        bufferStage.assignAjob(job, 0, 2017);
    }
}
