package ru.hse.java.logging;

interface Printer {
    Printer SYSTEM_OUT = new Printer() {
        int printed;

        @Override
        public void println(String message) {
            System.out.println(message);
            printed++;
        }

        @Override
        public int printedCount() {
            return printed;
        }
    };

    // Null Object
    Printer BIT_BUCKET = new Printer() {
        @Override
        public void println(String message) {
            // NOOP
        }

        @Override
        public int printedCount() {
            return 0;
        }
    };

    void println(String message);

    int printedCount();
}
