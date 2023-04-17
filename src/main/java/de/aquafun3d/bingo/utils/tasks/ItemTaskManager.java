package de.aquafun3d.bingo.utils.tasks;

import java.util.List;

public class ItemTaskManager implements IBingoTaskManager{

    private List<IBingoTask> _list;
    private List<IBingoTask> _listReturn;

    public final List<IBingoTask> getTasks(int amount, BingoDifficulty diff){
        switch (diff){
            case ONE -> getOverworldEasy();
            default -> {
                //
            }
        }
        //RANDOM aus list in returnlist
        return _list;
    }

    public void getOverworldEasy(){

    }

}
