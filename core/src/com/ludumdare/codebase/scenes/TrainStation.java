package com.ludumdare.codebase.scenes;

import com.ludumdare.codebase.gameobjects.SpriteObject;
import com.ludumdare.codebase.gameobjects.Train;
import com.ludumdare.codebase.gameobjects.TrainStationgHouse;

public class TrainStation extends Scene
{

    public TrainStation()
    {
        SpriteObject obj;
        addObject(new Train(-2000, 192));
        addObject(obj = new TrainStationgHouse(), -720, 150);
        obj.setLayer(-300);

    }
}
