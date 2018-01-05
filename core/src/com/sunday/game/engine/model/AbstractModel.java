package com.sunday.game.engine.model;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.common.DataOperation;
import com.sunday.game.engine.common.MovementState;
import com.sunday.game.engine.common.Outlook;
import com.sunday.game.engine.common.PhysicReflection;
import com.sunday.game.engine.control.EventProcessor;
import com.sunday.game.engine.databank.port.HolderPort;
import com.sunday.game.engine.databank.synchronize.SynchronizeCondition;
import com.sunday.game.engine.databank.synchronize.SynchronizeEvent;
import com.sunday.game.engine.databank.synchronize.SynchronizeExecutor;

public abstract class AbstractModel implements Disposable {

    public Outlook outlook = new Outlook();
    public PhysicReflection physicReflection = new PhysicReflection();
    public MovementState movementState = new MovementState();

    public HolderPort holderPort;

    public void connectDataBank(HolderPort holderPort) {
        this.holderPort = holderPort;
        holderPort.addDataInstance(outlook);
        holderPort.addDataInstance(physicReflection);
        holderPort.addDataInstance(movementState);
        holderPort.addDataSynchronize(outLookChangedCond, outlookWithPhysic);
        holderPort.addDataSynchronize(bodyCreatedCond, movementStateWithReflection);
    }

    private SynchronizeCondition bodyCreatedCond = new SynchronizeCondition(physicReflection, DataOperation.Modification);

    private SynchronizeCondition outLookChangedCond = new SynchronizeCondition(outlook, DataOperation.Modification);

    private SynchronizeExecutor outlookWithPhysic = new SynchronizeExecutor<Outlook>() {
        @Override
        public void execute(SynchronizeEvent<Outlook> synchronizeEvent) {
            if (synchronizeEvent.dataOperation == DataOperation.Modification) {
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
            }
        }
    };


    private SynchronizeExecutor movementStateWithReflection = new SynchronizeExecutor<PhysicReflection>() {
        @Override
        public void execute(SynchronizeEvent<PhysicReflection> synchronizeEvent) {
            if (synchronizeEvent.dataOperation == DataOperation.Modification) {
                movementState.position.set(physicReflection.body.getPosition());
            }
        }
    };

    public abstract EventProcessor getEventProcessor();

}
