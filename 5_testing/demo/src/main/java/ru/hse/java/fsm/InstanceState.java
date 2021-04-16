package ru.hse.java.fsm;

public enum InstanceState implements State {
    /**
     * Start state
     */
    STARTING {
        @Override
        public State onDelete() {
            // Program logic...
            return DELETING;
        }

        @Override
        public State onStop() {
            return STOPPING;
        }

        @Override
        public State onDeployDone() {
            return STARTED_ACTUAL;
        }
    },
    STARTED_ACTUAL {
        @Override
        public State onDelete() {
            return DELETING;
        }

        @Override
        public State onStop() {
            return STOPPING;
        }

        @Override
        public State onUpdate() {
            return STARTED_OUTDATED;
        }
    },
    STARTED_OUTDATED {
        @Override
        public State onDelete() {
            return DELETING;
        }

        @Override
        public State onStop() {
            return STOPPING;
        }

        @Override
        public State onDeployDone() {
            return STARTED_ACTUAL;
        }
    },
    STOPPING {
        @Override
        public State onDelete() {
            return DELETING;
        }

        @Override
        public State onUpdate() {
            return this;
        }

        @Override
        public State onDeployDone() {
            return STOPPED;
        }
    },
    STOPPED {
        @Override
        public State onDelete() {
            return DELETING;
        }

        @Override
        public State onUpdate() {
            return this;
        }

        @Override
        public State onDeployDone() {
            throw new IllegalStateException();
        }
    },
    DELETING {
        @Override
        public State onDeployDone() {
            return DELETED;
        }
    },
    /**
     * Final State. No actions are allowed
     */
    DELETED
}
