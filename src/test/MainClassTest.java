package test;

import main.MainClass;
import org.junit.Assert;
import org.junit.Test;

import static main.MainClass.jobs;

/**
 * Created by leo on 17/1/19.
 */
public class MainClassTest {

    @Test
    public void initTest() {
        MainClass.init();
        Assert.assertEquals(3, MainClass.machineStages.size());
        System.out.println("--------------共有以下几个 machine stage ,每个 stage 的情况见打印------------------");
        MainClass.machineStages.forEach(machineStage -> System.out.println(machineStage.getMachineNums()));
        MainClass.machineStages.forEach(machineStage -> System.out.println(machineStage.getMachines()));
        System.out.println("--------------------共有以下几个 buffer stage 每个 stage 的情况见打印-----------------");
        MainClass.bufferStages.forEach(bufferStage -> System.out.println(bufferStage.getBufferNums()));
        MainClass.bufferStages.forEach(bufferStage -> System.out.println(bufferStage.getBuffers()));
        System.out.println("---------------------------Job 的情况如下-----------------------------");
        jobs.forEach(job -> System.out.println(job));
        for (int i=0;i<10;i++) {
            for (int j = 0; j < 3; j++) {

                System.out.print(jobs.get(i).getProcessTime(j)+"  ");
            }
            System.out.println();
        }
    }


}
