package mdo;

import datarecord.BegainTimeOnBuff;
import datarecord.BufferNum;
import models.Buffer;
import models.Job;
import java.util.*;

/**
 * Created by leo on 17/1/16.
 */
public class BufferStage {

    private int bufferStageId;
    private int bufferNums;
    private List<Buffer> buffers = new LinkedList<>();
    private int[] lastJobOnThisStage;

    // 构造函数，供程序初始化时候使用
    public BufferStage(int bufferStageId, int bufferNums) {
        this.bufferNums = bufferNums;
        this.bufferStageId = bufferStageId;
        for (int i = 0; i < bufferNums; i++) {
            buffers.add(new Buffer(i, false));
        }
        lastJobOnThisStage = new int[bufferNums];
        Arrays.fill(lastJobOnThisStage, -1);
    }

    // 得到一个可供利用的缓冲区 ID
    public int getAvaliableBufferId() {
        Iterator iterator = buffers.iterator();
        while (iterator.hasNext()) {
            Buffer buffer = (Buffer) iterator.next();
            if (!buffer.isTaken()) {
                return buffer.getBufferId();
            }
        }
        return -1;
    }

    // 将一个工件放入到空闲的缓冲区中
    public void assignAjob(Job job, int bufferId, int begainTime) {
        Buffer buffer = buffers.get(bufferId);
        buffer.setTaken(true);
        BufferNum.setBufferNumbers(this.bufferStageId, job.getJobId(), buffer.getBufferId());
        BegainTimeOnBuff.setBegainTime(job.getJobId(), bufferStageId, begainTime);
        buffers.get(buffer.getBufferId()).setEnterTime(begainTime);
        buffers.get(buffer.getBufferId()).setJobInBuffer(job);
        Collections.sort(buffers);
        EventList.removeEventByJobId(job);

    }

    // 拿到下一个最适合走出缓冲区进入下一个阶段进行加工的工件 按进入缓冲区的时间顺序，越早进入的，越适合先出
    public Job getOneJobNeededToPro() {
        for (int i = 0; i < bufferNums; i++) {
            if (buffers.get(i).isTaken()) {
                buffers.get(i).setTaken(false);
                return buffers.get(i).getJobInBuffer();
            }
        }
        return null;
    }

    public int getBufferStageId() {
        return bufferStageId;
    }

    public void setBufferStageId(int bufferStageId) {
        this.bufferStageId = bufferStageId;
    }

    public int getBufferNums() {
        return bufferNums;
    }

    public void setBufferNums(int bufferNums) {
        this.bufferNums = bufferNums;
    }

    public List<Buffer> getBuffers() {
        return buffers;
    }

    public void setBuffers(List<Buffer> buffers) {
        this.buffers = buffers;
    }

    public int[] getLastJobOnThisStage() {
        return lastJobOnThisStage;
    }

    public void setLastJobOnThisStage(int[] lastJobOnThisStage) {
        this.lastJobOnThisStage = lastJobOnThisStage;
    }
}
