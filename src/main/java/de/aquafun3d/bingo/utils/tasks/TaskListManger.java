package de.aquafun3d.bingo.utils.tasks;

import java.util.List;

public class TaskListManger implements ITaskListManager{

    private List<IBingoTask> _list;

    public void fillList(){

    }

    public final List<IBingoTask> getList(){
        return _list;
    }
}
