package ru.hse.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class EchoServer {
    private static Path accessLogFile;

    public static void main(String[] args) throws IOException {
        accessLogFile = args.length > 0 ? Paths.get(args[0]) : null;
        try (ServerSocket srv = new ServerSocket(48484)) {
            while (true) {
                new Thread(new RequestHandler(srv.accept())).start();
            }
        }
    }

    private static final class RequestHandler implements Runnable {
        private static final AtomicLong lastRequestId = new AtomicLong();

        private final long requestId = lastRequestId.incrementAndGet();

        private final Socket conn;
        private final PrintStream logStream;

        private RequestHandler(Socket conn) throws IOException {
            this.conn = Objects.requireNonNull(conn, "conn");
            this.logStream = accessLogFile == null
                    ? System.err
                    : new PrintStream(Files.newOutputStream(accessLogFile, StandardOpenOption.APPEND), true);
        }

        @Override
        public void run() {
            accessLog("Start handling request");
            try {
                handleRequest();
            } catch (Exception e) {
                accessLog("Got an error while handling request");
                e.printStackTrace(logStream);
            } finally {
                if (accessLogFile != null) {
                    // This can actually throw, too, but we don't log an error here because we are terminating
                    logStream.close();
                }
            }
        }

        private void handleRequest() throws IOException {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                 PrintWriter writer = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true)) {
                String line;
                while ((line = reader.readLine()) != null && !"END".equals(line)) {
                    accessLog(">> " + line);
                    writer.println(line);
                }
            } finally {
                accessLog("Request done");
            }
        }

        private void accessLog(String msg) {
            logStream.println(Instant.now() + " [" + Thread.currentThread().getName() + "] {" + requestId + "} - " + msg);
        }
    }
}
