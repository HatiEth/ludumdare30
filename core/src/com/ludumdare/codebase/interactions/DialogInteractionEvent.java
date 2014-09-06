package com.ludumdare.codebase.interactions;

import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.GameMode;
import com.ludumdare.codebase.gameobjects.GameObject;
import com.ludumdare.codebase.gameobjects.TextObject;
import com.ludumdare.codebase.scenes.Scene;

public class DialogInteractionEvent extends TextInteractionEvent
{
    OnDialogStartEvent startEvent;

    public DialogInteractionEvent(OnDialogStartEvent startEvent, Scene scene,
            GameData gameData, TextObject... textObject)
    {
        super(scene, gameData, true, textObject);
        this.startEvent = startEvent;
    }

    @Override
    public void fire(GameObject o)
    {
        if (current == 0)
        {
            startEvent.start();
            super.data.activeDialog = this;
            super.data.eventMode = GameMode.Talk;
        }

        super.fire(o);

    }

    @Override
    public void handleTextExpiredSequence()
    {
        super.handleTextExpiredSequence();
        if (current >= textObject.length)
        {
            data.eventMode = GameMode.Exploration;
            startEvent.end();
        }
    }

}
