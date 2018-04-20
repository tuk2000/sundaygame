package com.sunday.engine.physic;

import com.sunday.engine.common.signal.SpecificSignal;

public enum CollisionSignal implements SpecificSignal {
    None, Updated, PreSolve, PostSolve, BeginContact, EndContact, Collision
}
