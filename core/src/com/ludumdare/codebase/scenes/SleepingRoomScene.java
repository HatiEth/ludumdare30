package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.InteractionNode;
import com.ludumdare.codebase.ObjectState;
import com.ludumdare.codebase.PathNode;
import com.ludumdare.codebase.gameobjects.BackgroundGO;
import com.ludumdare.codebase.gameobjects.GameObject.Direction;
import com.ludumdare.codebase.gameobjects.TextObject;
import com.ludumdare.codebase.interactions.TextInteractionEvent;

public class SleepingRoomScene extends Scene
{
    BackgroundGO background;
    BackgroundGO foregroundLight;

    public SleepingRoomScene(GameData gameData)
    {
        super(gameData);

        background = new BackgroundGO("Schlafzimmer.png");
        foregroundLight = new BackgroundGO("Schlafzimmer_Light.png");
        addObject(background);

        foregroundLight.setLayer(10.0f);
        addObject(foregroundLight);

    }

    private void addRejectAreas()
    {
        InteractionNode interaction;
        PathNode rejectZone;

        // Computer
        interaction = new InteractionNode(ObjectState.IDLE, Direction.NoChange,
                new Vector2(-200, -308), 0, -174, -142, 80, 176,
                new TextInteractionEvent(this, gameData, false, new TextObject(
                        "texts/computer_interaction.png",
                        gameData.haraldGameObject, this, new Vector2(10, 350),
                        3.0f)));
        interaction.isRepeatable = true;
        pathEngine.addLeaf(interaction);

        PathNode bed = new PathNode(new Vector2(-738, -204), 0, -738, -204,
                142, 130, true);
        bed.isReject = true;

        // Bed
        interaction = new InteractionNode(ObjectState.IDLE, Direction.NoChange,
                new Vector2(-738, -204), 0, -738, -204, 142, 130,
                new TextInteractionEvent(this, gameData, false, new TextObject(
                        "texts/bed_interaction_0.png",
                        gameData.haraldGameObject, this, new Vector2(10, 350),
                        3.0f)));
        interaction.isRepeatable = true;
        interaction.isDynamic = true;

        bed.addPrev(interaction);

        pathEngine.addLeaf(bed);

        addWindowInteraction();

        // Kleiderschrank
        interaction = new InteractionNode(ObjectState.IDLE, Direction.NoChange,
                new Vector2(664, -350), 0, 664, -26, 164, 178);
        pathEngine.addLeaf(interaction);

        // Regal
        interaction = new InteractionNode(ObjectState.IDLE, Direction.NoChange,
                new Vector2(248, -314), 0, 252, 6, 140, 236);
        pathEngine.addLeaf(interaction);

        // Amy Pond
        interaction = new InteractionNode(ObjectState.IDLE, Direction.Right,
                new Vector2(-418, -268), 0.0f, -324, -56, 32, 32,
                new TextInteractionEvent(this, gameData, false, new TextObject(
                        "texts/amy_pond_0.png", gameData.haraldGameObject,
                        this, new Vector2(0, 350), 5.0f), new TextObject(
                        "texts/amy_pond_1.png", gameData.haraldGameObject,
                        this, new Vector2(0, 350), 5.0f)));
        interaction.isRepeatable = true;

        pathEngine.addLeaf(interaction);

        rejectZone = new PathNode(new Vector2(0, 150), 0, 0, 150, 960, 400,
                true);
        rejectZone.isReject = true;
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
        if (from instanceof KitchenScene)
        {
            gameData.haraldGameObject.setPosition(806, -362);
        }
    }

    private void addWindowInteraction()
    {
        InteractionNode windowLook = new InteractionNode(ObjectState.IDLE,
                Direction.NoChange, new Vector2(-405, -350), 0.0f, -415, 120,
                114, 134);
        windowLook.isRepeatable = true;

        PathNode window = new PathNode(new Vector2(-405, -350), 0.0f, -415,
                120, 114, 134, true);
        window.isReject = true;

        window.addPrev(windowLook);

        pathEngine.addLeaf(window);
    }

    @Override
    public void create()
    {

        // addObject(gameData.diebGameObject);
        // pathEngine.addLeaf(n);

        addWindowInteraction();

        addRejectAreas();
    }
}
