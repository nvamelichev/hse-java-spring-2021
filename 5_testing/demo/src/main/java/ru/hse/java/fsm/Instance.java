package ru.hse.java.fsm;

import com.google.common.annotations.VisibleForTesting;

import java.util.Objects;

public class Instance {
    private final State state;

    private Instance(State state) {
        this.state = Objects.requireNonNull(state, "state");
    }

    @VisibleForTesting
    State getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Instance{" + state + "}";
    }

    public static Instance create(String... args) {
        return new Instance(InstanceState.STARTING);
    }

    public Instance update(String... args) {
        return new Instance(state.onUpdate());
    }

    public Instance delete(String... args) {
        return new Instance(state.onDelete());
    }

    public Instance stop(String... args) {
        return new Instance(state.onStop());
    }

    // Called from a listener/callback:
    public Instance deployDone() {
        return new Instance(state.onDeployDone());
    }
}
