package com.ludumdare.codebase;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
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
    private ObjectState moveType = ObjectState.MOVE;

    Array<PathNode> pathLeaves;
    GameObject gameObject;
    PathNode start;
    PathNode target;
    Vector3 direction;
    PathNode lastTarget;

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

    private PathNode findTargetRecursive(PathNode p, float minDist)
    {
        float dstP = Vector2.dst2(gameObject.getPosition().x,
                gameObject.getPosition().y, p.worldPosition.x,
                p.worldPosition.y);
        PathNode returnValue = null;

        if (!p.isReject && dstP < minDist)
        {
            minDist = dstP;
            returnValue = p;
        }

        for (PathNode prev : p.prevs)
        {
            float dstPrev = Vector2.dst2(gameObject.getPosition().x,
                    gameObject.getPosition().y, prev.worldPosition.x,
                    prev.worldPosition.y);

            if (!prev.isReject && dstPrev < minDist * 0.75f)
            {
                returnValue = prev;
                minDist = dstPrev;
            }
        }
        return returnValue;
    }

    public void setTargetPosition(Vector2 targetPosition)
    {
        if (gameObject != null)
        {

            this.start = new PathNode(gameObject.getPosition(),
                    gameObject.getLayer());

            PathNode targetedNode = getNodeAt(targetPosition);
            if (((lastTarget != null && lastTarget.isRepeatable) || lastTarget != targetedNode)
                    && targetedNode != null)
            {
                target = targetedNode;
                PathNode tmpTarget = target;
                PathNode startingToLeaf = tmpTarget;
                float shortestDst = Float.POSITIVE_INFINITY;
                target = findTargetRecursive(target, shortestDst);
                lastTarget = target;
                if (target == null)
                {
                    target = new PathNode(gameObject.getPosition(),
                            gameObject.getLayer());

                    if (targetPosition.x - gameObject.getPosition().x < 0)
                    {
                        gameObject.setDirection(GameObject.Direction.Left);
                    }
                    else
                    {
                        gameObject.setDirection(GameObject.Direction.Right);
                    }

                    return;
                }
                if (target.isDynamic)
                {
                    target.execute(gameObject);

                    if (target.worldPosition.x - gameObject.getPosition().x < 0)
                    {
                        gameObject.setDirection(GameObject.Direction.Left);
                    }
                    else
                    {
                        gameObject.setDirection(GameObject.Direction.Right);
                    }

                    target = null;
                    start = null;
                    return;
                }

            }
            else
            {
                if (Vector2.dst(gameObject.getPosition().x,
                        gameObject.getPosition().y, targetPosition.x,
                        targetPosition.y) < 50.0f)
                {
                    return;
                }
                this.target = new PathNode(targetPosition,
                        gameObject.getLayer());

                lastTarget = target;

                if (target.worldPosition.x - gameObject.getPosition().x < 0)
                {
                    gameObject.setDirection(GameObject.Direction.Left);
                }
                else
                {
                    gameObject.setDirection(GameObject.Direction.Right);
                }
            }

            gameObject.enterState(moveType);

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

                    lastTarget = target;
                    if (target.next != null && !target.next.isReject)
                    {
                        start = target;
                        target = start.next;

                        gameObject.enterState(moveType);
                    }
                    else
                    {
                        start = null;
                        target = null;
                    }

                    return;
                }
            }

            if (target.worldPosition.x - gameObject.getPosition().x < 0)
            {
                gameObject.setDirection(GameObject.Direction.Left);
            }
            else
            {
                gameObject.setDirection(GameObject.Direction.Right);
            }

            // p = p.lerp(target.worldPosition, Gdx.graphics.getDeltaTime());
            if (!GameData.isDevMode)
            {
                gameObject.move(direction.x * 50.0f * 1.5f * 4.75f * 0.016f,
                        direction.y * 25.0f * 4.75f * 1.5f * 0.016f);
            }
            else
            {
                gameObject.move(direction.x * 50.0f * 5 * 4.75f * 0.016f,
                        direction.y * 25.0f * 5 * 4.75f * 0.016f);
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
                    gameObject.getPosition().y, 0);

            direction.nor();
        }

    }

    public void reset()
    {
        target = null;
        gameObject = null;
    }

    private void drawLineRecursive(ShapeRenderer sr, PathNode p)
    {
        for (PathNode prev : p.prevs)
        {
            sr.line(p.worldPosition, prev.worldPosition);

            drawLineRecursive(sr, prev);
        }
    }

    public void debugDraw(Renderer renderer)
    {
        if (GameData.isDevMode)
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

                sr.setColor(Color.CYAN);
                sr.line(p.worldPosition.x, p.worldPosition.y,
                        p.clickableArea.x, p.clickableArea.y);
                sr.rect(p.clickableArea.x, p.clickableArea.y,
                        p.clickableArea.width, p.clickableArea.height);

                drawLineRecursive(sr, p);
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
            if (a.isSelected(point) && !a.isDisabled)
            {
                return p;
            }
        }
        return null;
    }

    public void setMoveType(ObjectState moveType)
    {
        this.moveType = moveType;
    }
}
