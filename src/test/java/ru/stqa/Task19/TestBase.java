package ru.stqa.Task19;

import org.junit.Before;

public class TestBase {
    public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    //public Application app;
    Application app = new Application();
    @Before
    public void start() {
        if (tlApp.get() != null) {
            app = tlApp.get();
            return;
        }

        app = new Application();
        tlApp.set(app);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { app.stop(); app = null; }));
    }
}
