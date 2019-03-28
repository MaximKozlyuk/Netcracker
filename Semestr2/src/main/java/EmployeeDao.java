import domain.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDao implements Dao<Employee>{

    private static EmployeeDao ourInstance = new EmployeeDao();

    public static EmployeeDao getInstance() {
        return ourInstance;
    }

    private Connection conn = DataBaseManager.getInstance().getConnection();

    private Employee mapRow (ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getLong("empno"));
        employee.setName(rs.getString("ename"));
        employee.setJob(rs.getString("job"));
        employee.setManagerId(rs.getLong("mgr"));
        employee.setHireDate(rs.getDate("hiredate").toLocalDate());
        employee.setSalary(rs.getDouble("sal"));
        employee.setCommission(rs.getDouble("comm"));
        employee.setDepartmentNum(rs.getInt("deptno"));
        return employee;
    }

    @Override
    public Optional<Employee> findById (long id) {
        try {
            PreparedStatement statement = conn.prepareStatement("select * from public.emp where empno =?;");
            statement.setLong(1, id);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            if (!rs.next()) {
                return Optional.empty();
            } else {
                return Optional.of(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Employee> findAll () {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from public.emp;");
            ps.execute();
            ResultSet rs = ps.getResultSet();

            if (!rs.next())
                return new ArrayList<>();
            do {
                employees.add(mapRow(rs));
            } while (rs.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public void deleteById(long id) {
        try {
            PreparedStatement ps = conn.prepareStatement("delete from public.emp where empno=?;");
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee save(Employee entity) {
        if (entity == null) throw new IllegalArgumentException("entity is null");
        try {
            PreparedStatement ps = conn.prepareStatement("insert into public.emp values (?,?,?,?,?,?,?,?);");
            ps.setLong(1, entity.getId());
            ps.setString(2, entity.getName());
            ps.setString(3, entity.getJob());
            ps.setLong(4, entity.getManagerId());
            ps.setDate(5, Date.valueOf(entity.getHireDate()));
            ps.setDouble(6,entity.getSalary());
            ps.setDouble(7,entity.getCommission());
            ps.setInt(8,entity.getDepartmentNum());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public void closeConnection () {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
