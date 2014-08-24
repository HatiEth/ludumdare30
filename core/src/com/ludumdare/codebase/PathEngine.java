package com.ludumdare.codebase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
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
    Array<PathNode> pathLeaves;
    GameObject gameObject;
    PathNode start;
    PathNode target;
    Vector3 direction;

    public PathEngine()
    {
        pathLeaves = new Array<PathNode>();

        gameObject = null;
        target = null;
        direction = new Vector3();
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

            PathNode targetedNode = getNodeAt(targetPosition);
            if (targetedNode != null)
            {
                target = targetedNode;
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
            gameObject.enterState(ObjectState.MOVE);

            direction.set(target.worldPosition, target.layer);
            direction.sub(start.worldPosition.x, start.worldPosition.y,
                    start.layer);

            direction.nor();
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
            if (dist2 < 100.0f)
            {
                if ((Math.abs(gameObject.getLayer() - target.layer) < 0.3f))
                {
                    target.execute(gameObject);
                    target = null;
                    start = null;

                    gameObject.enterState(ObjectState.IDLE);

                    return;
                }
            }

            Vector2 p = gameObject.getPosition();

            // p = p.lerp(target.worldPosition, Gdx.graphics.getDeltaTime());
            if (!GameData.isDevMode)
            {
                p.add(direction.x * 50.0f * 4.75f * 0.016f,
                        direction.y * 25.0f * 4.75f * 0.016f);
            }
            else
            {
                p.add(direction.x * 50.0f * 2 * 4.75f * 0.016f, direction.y
                        * 25.0f * 2 * 4.75f * 0.016f);
            }
            // p.add(direction.x * 50.0f * 1.75f * 0.016f,
            // direction.y * 25.0f * 1.75f * 0.016f);
            // p.add(direction.x * 50.0f * 1.25f * Gdx.graphics.getDeltaTime(),
            // direction.y * 25.0f * 1.25f * Gdx.graphics.getDeltaTime());
            // gameObject.setLayer(gameObject.getLayer()
            // + (target.layer - gameObject.getLayer())
            // * Gdx.graphics.getDeltaTime());

            direction.set(target.worldPosition, target.layer);
            direction.sub(gameObject.getPosition().x,
                    gameObject.getPosition().y, gameObject.getLayer());

            direction.nor();
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

        sr.begin(ShapeType.Filled);
        for (PathNode p : pathLeaves)
        {
            sr.setColor(new Color(0, 0, 1, 0.5f));
            if (p.clickableArea != null)
            {
                sr.rect(p.clickableArea.x, p.clickableArea.y,
                        p.clickableArea.width, p.clickableArea.height);
            }
        }
        sr.end();

        sr.begin(ShapeType.Line);
        for (PathNode p : pathLeaves)
        {
            sr.setColor(Color.YELLOW);
            sr.circle(p.worldPosition.x, p.worldPosition.y, 25.0f);
            PathNode a = p;

            sr.setColor(Color.CYAN);
            sr.line(p.worldPosition.x, p.worldPosition.y, p.clickableArea.x,
                    p.clickableArea.y);
            sr.rect(p.clickableArea.x, p.clickableArea.y,
                    p.clickableArea.width, p.clickableArea.height);

            PathNode b = a.prev;
            while (b != null)
            {
                sr.line(a.worldPosition, b.worldPosition);
                a = b;
                b = a.prev;
            }
        }
        sr.end();

        if (start != null && target != null)
        {
            sr.begin(ShapeType.Filled);
            sr.setColor(Color.NAVY);
            sr.circle(target.worldPosition.x, target.worldPosition.y, 25.0f);
            sr.circle(start.worldPosition.x, start.worldPosition.y, 25.0f);
            sr.end();

            sr.begin(ShapeType.Line);
            sr.setColor(Color.WHITE);
            sr.line(gameObject.getPosition(), target.worldPosition);
            sr.end();
        }
    }

    public void addLeaf(PathNode node)
    {
        this.pathLeaves.add(node);
    }

    private PathNode getNodeAt(Vector2 point)
    {
        for (PathNode p : pathLeaves)
        {
            PathNode a = p;
            if (a.isSelected(point))
            {
                return p;
            }
            while (a.prev != null) // traverse children if has any to get
                                   // possible points
            {
                a = a.prev;
                if (a.isSelected(point))
                {

                    return p;
                }
            }
        }
        return null;
    }
}
