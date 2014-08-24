package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.Gdx;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.gameobjects.BackgroundGO;
import com.ludumdare.codebase.gameobjects.HaraldGO;

public class SleepingRoomScene extends Scene
{
    BackgroundGO background;
    BackgroundGO foreground;

    public SleepingRoomScene(GameData gameData)
    {
        super(gameData);

        background = new BackgroundGO("Schlafzimmer.png");
        foreground = new BackgroundGO("Schlafzimmer_Light.png");
        addObject(background);

        foreground.setLayer(10.0f);
        addObject(foreground);
    }

    @Override
    public void update()
    {
        super.update();

    }

    @Override
    public void onEnter(Scene from)
    {
        pathEngine.setGameObject(gameData.haraldGameObject);
        gameData.haraldGameObject.setPosition(0, 0);
        if (from == null) // starting
        {
            gameData.haraldGameObject.setPosition(-502, -296);
        }
    }
}
