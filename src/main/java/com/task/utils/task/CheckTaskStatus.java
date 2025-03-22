package com.task.utils.task;


import cn.nukkit.Server;
import com.task.RsTask;
import com.task.events.TaskStopEvent;
import com.task.events.TaskTimeOutEvent;
import com.task.utils.tasks.PlayerFile;
import com.task.utils.tasks.taskitems.PlayerTask;

/**
 * 检查任务状态
 * @author SmallasWater
 */
public class CheckTaskStatus implements Runnable {

    private final RsTask owner;

    private RsTask getOwner() {
        return owner;
    }

    public CheckTaskStatus(RsTask owner) {
        this.owner = owner;
    }

    @Override
    public void run() {
        while (true) {
            for (PlayerFile file : RsTask.getTask().playerFiles.values()) {
                for (PlayerTask task : file.getPlayerTasks()) {
                    if (task.getTaskFile().getLoadDay() > 0) {
                        if (file.getTimeOutDay(task.getTaskName()) <= 0) {
                            if (task.getTaskClass().getOpen()) {
                                file.closeTask(task.getTaskName());
                                Server.getInstance().getPluginManager().callEvent(new TaskTimeOutEvent(file, task.getTaskFile()));
                            }
                        }
                    }
                }

            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Server.getInstance().getPluginManager().callEvent(new TaskStopEvent(getOwner(),this));
                return;
            }
        }
    }
}
