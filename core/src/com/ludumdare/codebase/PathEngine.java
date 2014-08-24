package com.ludumdare.codebase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ludumdare.codebase.gameobjects.GameObject;

/**
 * Controller for GameObject and PathNodes
 * 
 * @author Hati Eth
 *
 */
public class PathEngine
{
    Array<PathNode> pathNodes;
    GameObject gameObject;
    PathNode start;
    PathNode target;
    Vector2 direction;

    public PathEngine()
    {
        pathNodes = new Array<PathNode>();

        gameObject = null;
        target = null;
    }

    public void setGameObject(GameObject o)
    {
        gameObject = o;
    }

    public void setTargetPosition(Vector2 targetPosition)
    {
        if (gameObject != null)
        {
            if (start != null)
            {
                this.start.layer = gameObject.getLayer();
                this.start.worldPosition = gameObject.getPosition();
            }
            else
            {
                this.start = new PathNode(gameObject.getPosition(),
                        gameObject.getLayer());
            }
            if (target != null)
            {
                this.target.layer = gameObject.getLayer();
                this.target.worldPosition = targetPosition;
            }
            else
            {
                this.target = new PathNode(targetPosition,
                        gameObject.getLayer());
            }

            if (target.worldPosition.x - gameObject.getPosition().x < 0)
            {
                gameObject.setDirection(GameObject.Direction.Left);
            }
            else
            {
                gameObject.setDirection(GameObject.Direction.Right);
            }
        }
        // this.targetPosition = targetPosition;
    }

    public void update()
    {
        if (gameObject != null && target != null)
        {
            float dist2 = Vector2.dst2(gameObject.getPosition().x,
                    gameObject.getPosition().y, target.worldPosition.x,
                    target.worldPosition.y);
            if (dist2 < 25.0f)
            {

                if ((Math.abs(gameObject.getLayer() - target.layer) < MathUtils.FLOAT_ROUNDING_ERROR))
                {
                    target.execute(gameObject);
                    target = null;

                    return;
                }
            }

            Vector2 p = gameObject.getPosition();

            p = p.lerp(target.worldPosition, Gdx.graphics.getDeltaTime());

            gameObject.setLayer(gameObject.getLayer()
                    + (target.layer - gameObject.getLayer())
                    * Gdx.graphics.getDeltaTime());
        }

    }

    public void reset()
    {
        target = null;
        gameObject = null;
    }

    public void debugDraw(Renderer renderer)
    {
        ShapeRenderer sr = renderer.getShapeRenderer();

        for (PathNode p : pathNodes)
        {
            sr.begin(ShapeType.Line);
            sr.setColor(Color.YELLOW);
            sr.circle(p.worldPosition.x, p.worldPosition.y, 50);
            PathNode a = p;
            PathNode b = a.prev;
            while (b != null)
            {
                sr.line(a.worldPosition, b.worldPosition);
                a = b;
                b = a.prev;
            }
            sr.end();
        }

        if (target != null)
        {
            sr.begin(ShapeType.Filled);
            sr.setColor(Color.NAVY);
            sr.circle(target.worldPosition.x, target.worldPosition.y, 50);
            sr.end();
        }
    }

    public PathNode createNode(float x, float y, float layer)
    {
        PathNode n = new PathNode(new Vector2(x, y), layer);
        pathNodes.add(n);
        return n;
    }

}
