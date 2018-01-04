package com.sunday.game.engine.model;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.common.MovementState;
import com.sunday.game.engine.common.Outlook;
import com.sunday.game.engine.common.PhysicReflection;
import com.sunday.game.engine.control.EventProcessor;
import com.sunday.game.engine.databank.DataHolderPort;
import com.sunday.game.engine.databank.SynchronizeCondition;
import com.sunday.game.engine.databank.SynchronizeExecutor;

public abstract class AbstractModel implements Disposable {

    public Outlook outlook;
    public PhysicReflection physicReflection;
    public MovementState movementState;

    public AbstractModel() {
        movementState = new MovementState();
    }

    public void storeIntoDataStorage(DataHolderPort dataHolderPort) {
        dataHolderPort.addDataInstance(outlook);
        dataHolderPort.addDataInstance(physicReflection);
        dataHolderPort.addDataInstance(movementState);
        dataHolderPort.addDataSynchronize(outLookChangedCond, new SynchronizeExecutor(outlookWithPhysic));
        dataHolderPort.addDataSynchronize(bodyCreatedCond, new SynchronizeExecutor(movementStateWithReflection));
    }

    private SynchronizeCondition bodyCreatedCond = new SynchronizeCondition() {
        @Override
        public boolean isTriggered() {
            return physicReflection.bodyCreated;
        }
    };

    private SynchronizeCondition outLookChangedCond = new SynchronizeCondition() {
        @Override
        public boolean isTriggered() {
            return outlook.sizeChanged;
        }
    };

    private Runnable outlookWithPhysic = new Runnable() {
        @Override
        public void run() {
            physicReflection.bodyDef.position.set(movementState.position);
            switch (physicReflection.fixtureDef.shape.getType()) {
                case Chain:
                    break;
                case Polygon:
                    ((PolygonShape) physicReflection.fixtureDef.shape).setAsBox(outlook.dimension.x, outlook.dimension.y);
                    break;
                case Circle:
                    ((CircleShape) physicReflection.fixtureDef.shape).setRadius((outlook.dimension.x + outlook.dimension.y) / 2);
                    break;
                case Edge:
                    break;
            }
            physicReflection.reconfigure();
            outlook.sizeChanged = false;
        }
    };


    private Runnable movementStateWithReflection = new Runnable() {
        @Override
        public void run() {
            movementState.position.set(physicReflection.body.getPosition());
        }
    };


    protected abstract void generatePhysicDefinition();

    public abstract EventProcessor getEventProcessor();

}
