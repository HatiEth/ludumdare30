package com.ludumdare.codebase.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.ludumdare.codebase.Ludumdare30Main;

public class HtmlLauncher extends GwtApplication
{

    @Override
    public GwtApplicationConfiguration getConfig()
    {
        GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(1440,
                810);

        cfg.antialiasing = true;
        cfg.preserveDrawingBuffer = true;
        cfg.useDebugGL = false;

        return cfg;
    }

    @Override
    public ApplicationListener getApplicationListener()
    {
        return new Ludumdare30Main();
    }
}