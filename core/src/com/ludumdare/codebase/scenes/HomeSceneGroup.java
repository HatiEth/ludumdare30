package com.ludumdare.codebase.scenes;

public class HomeSceneGroup extends SceneGroup
{
    protected SleepingRoomScene sleepRoom;
    protected LivingRoomScene livingRoom;
    protected KitchenScene kitchen;

    public HomeSceneGroup()
    {
        sleepRoom = new SleepingRoomScene();
        livingRoom = new LivingRoomScene();
        kitchen = new KitchenScene();

        setActiveScene(sleepRoom);
    }

}
