package lab2_rmi;

import java.net.URISyntaxException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server  {

    private static TaskImpl task;

    private static final String EXECUTOR_NAME = "Task";
    static private String codebaseUrl;
    private static final int registryPort = 8080;
    private static final int executorPort = 1604;
    private static final String useCodebaseOnly = "yes";

    public static void main(String[] args) throws URISyntaxException {
        String codebaseUrl = "file://".concat(Server.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());

        System.setProperty("java.rmi.server.codebase", codebaseUrl);
        System.setProperty("java.rmi.server.useCodebaseOnly", "yes");
        System.setProperty("java.rmi.server.logCalls", "true");
        System.setProperty("java.security.policy", "client.policy");
        System.setProperty("java.rmi.server.hostname", "127.0.0.1");
        System.setSecurityManager(new SecurityManager());

        Registry registry;
        try {
            TaskImpl obj = new TaskImpl(1, MathAction.SUM, 1);
            registry = LocateRegistry.createRegistry(registryPort);
            Task stub = (Task) UnicastRemoteObject.exportObject(obj, 0);
            registry.bind("task", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }

    }

}
