package io.github.ingmargoudt.referee.game;

public enum Phase {
    BEGINNING_PHASE{
        public void run(Game game){

        }
    },
    PRECOMBAT_MAINPHASE {
        @Override
        void run(Game game) {

        }
    },
    COMBAT_PHASE {
        @Override
        void run(Game game) {

        }
    },
    POSTCOMBAT_MAINPHASE {
        @Override
        void run(Game game) {

        }
    },
    END_PHASE {
        @Override
        void run(Game game) {

        }
    };

    abstract void run(Game game);

}
