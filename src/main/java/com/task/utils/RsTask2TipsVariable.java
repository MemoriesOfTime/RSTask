package com.task.utils;

import cn.nukkit.Player;
import com.task.utils.tasks.PlayerFile;
import com.task.utils.tasks.taskitems.PlayerTask;
import tip.utils.variables.BaseVariable;

import java.util.LinkedList;

/**
 * @author LT_Name
 */
public class RsTask2TipsVariable extends BaseVariable {
    public RsTask2TipsVariable(Player player) {
        super(player);
    }

    @Override
    public void strReplace() {
        PlayerFile file = PlayerFile.getPlayerFile(this.player.getName());
        LinkedList<PlayerTask> tasks = file.getInviteTasks();
        String taskName = "暂无任务";
        if (!tasks.isEmpty()) {
            PlayerTask task = tasks.get(0); //显示第一个任务
            if (task != null) {
                taskName = task.getTaskName();
            }
        }
        addStrReplaceString("{task-name}", taskName);
        addStrReplaceString("{task-count}", file.getCount() + "");
    }
}
