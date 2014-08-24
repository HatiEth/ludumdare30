package com.ludumdare.codebase.scenes;

import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.gameobjects.SpriteObject;
import com.ludumdare.codebase.gameobjects.Train;
import com.ludumdare.codebase.gameobjects.TrainStationgHouse;

public class TrainStationScene extends Scene
{

    public TrainStationScene(GameData gameData)
    {
        super(gameData);
        SpriteObject obj;
        addObject(new Train(-2000, 192));
        addObject(obj = new TrainStationgHouse(), -720, 150);
        obj.setLayer(-300);

    }
}
