package lab2_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Task extends Remote {

    double executeMAthAction (double o1, MathAction action, double o2) throws RemoteException;

    double executeMAthAction () throws RemoteException;

    void setOperand1 (double operand1) throws RemoteException;

    void setOperand2 (double operand2) throws RemoteException;

}
