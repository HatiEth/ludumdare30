package com.ludumdare.codebase;

import com.ludumdare.codebase.gameobjects.SpriteObject;
import com.ludumdare.codebase.gameobjects.Train;

public class TrainStation extends Scene
{

    public TrainStation()
    {
        SpriteObject obj;
        addObject(new Train(-2000, 192));
        addObject(obj = new LivingHouse(), -720, 150);
        obj.setLayer(100);

    }
}
