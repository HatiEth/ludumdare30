package com.ludumdare.codebase.interactions;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.InteractionEvent;
import com.ludumdare.codebase.gameobjects.GameObject;
import com.ludumdare.codebase.gameobjects.TextObject;
import com.ludumdare.codebase.scenes.Scene;

public class TextInteractionEvent extends InteractionEvent
{
    int current = 0;
    final TextObject[] textObject;
    final boolean noTextAfterSequence;

    static Array<TextObject> allTexts = new Array<TextObject>();

    public TextInteractionEvent(Scene scene, GameData gameData,
            boolean noTextAfterAllTexts, TextObject... textObject)
    {
        super(scene, gameData);
        this.textObject = textObject;

        noTextAfterSequence = noTextAfterAllTexts;

        for (int i = 0; i < textObject.length; ++i)
        {
            textObject[i].setNotifier(this);
        }
    }

    @Override
    public void fire(GameObject o)
    {
        if (current >= textObject.length) return;
        System.out.println("click");
        if (textObject[current].isAlive())
        {
            next();
            if (current == textObject.length) return;
        }
        if (!textObject[current].isAlive() && (current < textObject.length))
        {
            for (TextObject t : allTexts)
            {
                t.die();
            }
            allTexts.clear();
            scene.addObject(textObject[current].reset());
            allTexts.add(textObject[current]);
        }
    }

    public void handleTextExpiredSequence()
    {
        if (noTextAfterSequence)
        {
            current = MathUtils.clamp(current + 1, 0, textObject.length);
        }
        else
        {
            current = MathUtils.clamp(current + 1, 0, textObject.length - 1);
        }
    }

    public void next()
    {
        if (current >= 0 && current < textObject.length)
        {
            System.out.println("Skip dialog");
            textObject[current].die();
            textObject[current].update();
        }
    }
}
