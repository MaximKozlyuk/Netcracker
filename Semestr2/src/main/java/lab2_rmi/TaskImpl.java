package lab2_rmi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.function.BiFunction;

public final class TaskImpl implements Task ,Serializable {

    private static final long serialVersionUID = 1L;

    private MathAction action = MathAction.SUM;
    private HashMap<MathAction, BiFunction<Double,Double,Double>> operations;
    private double operand1 = 0;
    private double operand2 = 0;

    @Override
    public  double executeMAthAction (double o1, MathAction action, double o2) throws RemoteException {
        return operations.get(action).apply(o1, o2);
    }

    @Override
    public double executeMAthAction() throws RemoteException {
        return operations.get(action).apply(operand1,operand2);
    }

//    public static void main(String[] args) {
//        TaskImpl impl = new TaskImpl();
//        System.out.println(impl.executeMAthAction(1, MathAction.SUM, 1));
//    }

    private TaskImpl() {
        operations = new HashMap<>();
        operations.put(MathAction.SUM, (o1, o2) -> o1 + o2);
        operations.put(MathAction.SUB, (o1, o2) -> o1 - o2);
        operations.put(MathAction.MULT, (o1, o2) -> o1 * o2);
        operations.put(MathAction.DIV, (o1, o2) -> o1 / o2);
        operations.put(MathAction.MOD, (o1, o2) -> o1 % o2);
    }

    public TaskImpl (double o1, MathAction action, double o2) {
        this();
        this.operand1 = o1;
        this.operand2 = o2;
        this.action = action;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public MathAction getAction() {
        return action;
    }

    public void setAction(MathAction action) {
        this.action = action;
    }

    public double getOperand1() {
        return operand1;
    }

    @Override
    public void setOperand1(double operand1) {
        this.operand1 = operand1;
    }

    public double getOperand2() {
        return operand2;
    }

    @Override
    public void setOperand2(double operand2) {
        this.operand2 = operand2;
    }
}
