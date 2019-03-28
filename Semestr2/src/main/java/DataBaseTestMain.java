import domain.Employee;

import java.time.LocalDate;
import java.util.List;

public class DataBaseTestMain {

    static EmployeeDao dao = EmployeeDao.getInstance();

    public static void main(String[] args) {

        Employee testEmploy = new Employee();
        testEmploy.setId(100);
        testEmploy.setName("TheTest");
        testEmploy.setCommission(100);
        testEmploy.setJob("worker");
        testEmploy.setDepartmentNum(10);
        testEmploy.setHireDate(LocalDate.of(1,1,1));

        Employee employ = dao.findById(7900).get();
        System.out.println(employ + "\n");

        findAll();

        dao.deleteById(7900);
        System.out.println(dao.findById(7900) + "\n");

        dao.save(testEmploy);
        System.out.println("saved employee: " + dao.findById(100) + "\n");

        dao.closeConnection();
    }

    private static void findAll () {
        List<Employee> employees = dao.findAll();
        employees.forEach(System.out::println);
        System.out.println();
    }

}
