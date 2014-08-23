package com.ludumdare.codebase;

import com.ludumdare.codebase.gameobjects.Train;

public class TrainStation extends Scene
{

    Train train;

    public TrainStation()
    {
        addObject(train = new Train());

    }

    @Override
    public void update()
    {
    }

}
