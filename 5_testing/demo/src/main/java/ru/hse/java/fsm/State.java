package ru.hse.java.fsm;

public interface State {
    default State onUpdate() {
        throw new IllegalStateException();
    }

    default State onDelete() {
        throw new IllegalStateException();
    }

    default State onStop() {
        throw new IllegalStateException();
    }

    default State onDeployDone() {
        throw new IllegalStateException();
    }
}
