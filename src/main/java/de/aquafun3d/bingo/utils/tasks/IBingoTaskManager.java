package de.aquafun3d.bingo.utils.tasks;

import java.util.List;

public interface IBingoTaskManager {
    public List<IBingoTask> getTasks(int amount, BingoDifficulty diff);
}
