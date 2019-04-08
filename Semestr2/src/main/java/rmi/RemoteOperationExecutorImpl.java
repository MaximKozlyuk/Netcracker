package rmi;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class RemoteOperationExecutorImpl implements RemoteOperationExecutor {

    @Override
    public double execute(Task task) throws RemoteException {
        return task.executeOperation();
    }
}
