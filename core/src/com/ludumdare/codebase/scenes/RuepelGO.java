package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ludumdare.codebase.ObjectState;
import com.ludumdare.codebase.Renderer;
import com.ludumdare.codebase.gameobjects.AnimatedSpriteObject;
import com.ludumdare.codebase.gameobjects.BackgroundGO;
import com.ludumdare.codebase.gameobjects.BetterAnimation;
import com.ludumdare.codebase.gameobjects.HeadGO;
import com.ludumdare.codebase.interactions.TextInteractionEvent;

public class RuepelGO extends AnimatedSpriteObject
{
    HeadGO head;
    TextInteractionEvent text;

    Array<BackgroundGO> randomSpeech;

    float speechTimer;

    public RuepelGO()
    {
        BetterAnimation animation = BetterAnimation.createAnimation(
                "Charaktere/ruepel/sprite_sheet_sit down_ruepel_rechts.png", 5,
                1, 0.1f, false);
        addAnimation(ObjectState.TAKE_SEAT, Direction.Right, animation);

        head = new HeadGO(25, 120, 3, 6, 5, 3);

        randomSpeech = new Array<BackgroundGO>();
        randomSpeech.add(new BackgroundGO("sprechblasen/Bahn/1.01_Jerry.png"));
        randomSpeech.add(new BackgroundGO("sprechblasen/Bahn/2.01_Jerry.png"));
        randomSpeech.add(new BackgroundGO("sprechblasen/Bahn/4.01_jerry.png"));

        for (BackgroundGO bg : randomSpeech)
        {
            bg.setLayer(15.0f);
        }

        speechTimer = 11.0f + MathUtils.random() * 3.0f;
    }

    BackgroundGO choosen;

    @Override
    public void update()
    {
        head.setLayer(getLayer() + 0.0f);
        head.setPosition(getPosition());
        super.update();
        head.update();

        for (BackgroundGO bg : randomSpeech)
        {
            bg.setPosition(new Vector2(getPosition().x + 50,
                    getPosition().y + 160 + 148 / 2));
        }
        speechTimer = speechTimer - 0.016f;
        if (speechTimer < 10.0f)
        {
            choosen = randomSpeech.random();
            speechTimer = 11.0f + MathUtils.random() * 3.0f;
        }
        else
        {
        }
    }

    @Override
    public void render(Renderer renderer)
    {
        super.render(renderer);
        head.render(renderer);

        if (choosen != null) choosen.render(renderer);
    }
}
