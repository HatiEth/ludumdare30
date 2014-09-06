package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.GameMode;
import com.ludumdare.codebase.InteractionNode;
import com.ludumdare.codebase.ObjectState;
import com.ludumdare.codebase.Renderer;
import com.ludumdare.codebase.gameobjects.BackgroundGO;
import com.ludumdare.codebase.gameobjects.GameObject.Direction;
import com.ludumdare.codebase.gameobjects.TextObject;
import com.ludumdare.codebase.interactions.DialogInteractionEvent;
import com.ludumdare.codebase.interactions.OnDialogStartEvent;
import com.ludumdare.codebase.interactions.TextInteractionEvent;

public class CreditsScene extends Scene
{

    BackgroundGO background;
    BackgroundGO knockknock;
    BackgroundGO credit_scene;
    BackgroundGO creditAnim0;
    BackgroundGO creditAnim1;
    BackgroundGO creditAnim2;
    BackgroundGO creditAnim3;
    BackgroundGO creditAnim4;

    boolean showCredits;
    float timer = 0.0f;

    public CreditsScene(GameData gameData)
    {
        super(gameData);
        background = new BackgroundGO("credits.png");

        knockknock = new BackgroundGO("sprechblasen/knockknock.png");
        credit_scene = new BackgroundGO("sprechblasen/credit_scene.png");

        showCredits = false;

        creditAnim0 = new BackgroundGO("credits/inyourface1.png");
        creditAnim1 = new BackgroundGO("credits/inyourface2.png");
        creditAnim2 = new BackgroundGO("credits/inyourface3.png");
        creditAnim3 = new BackgroundGO("credits/inyourface4.png");
        creditAnim4 = new BackgroundGO("credits/inyourface4.png");
    }

    @Override
    protected void updateInternal()
    {
        super.updateInternal();
        timer += 0.016f;
    }

    @Override
    public void render(Renderer renderer)
    {
        if (timer < 1.0f)
        {
            knockknock.render(renderer);
        }
        else
        {
            if (timer < 2.0f)
            {
                float d = 0.0f;
                if (timer < d + 1.18f)
                {
                    creditAnim0.render(renderer);
                }
                else if (timer < d + 1.22f)
                {
                    creditAnim1.render(renderer);
                }
                else if (timer < d + 1.75f)
                {
                    creditAnim2.render(renderer);
                }
                else if (timer < d + 2.0f)
                {
                    creditAnim3.render(renderer);
                }
                else
                {
                    creditAnim4.render(renderer);
                }

            }
            else
            {
                background.render(renderer);
                super.render(renderer);
            }
        }
        // background.render(renderer);

    }

    @Override
    public void onEnter(Scene from)
    {
        gameData.haraldGameObject.setPosition(Vector2.Zero);
        pathEngine.setTargetPosition(new Vector2(0, 0));
    }

    @Override
    public void create()
    {

    }

}
