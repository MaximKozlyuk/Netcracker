begin;

--task 1
select min(sal), avg(sal), max(sal) from emp;

--task 2
select count(*) from emp where sal > 0;

--task 3
select deptno, sal from emp
where sal >= 1000 and sal in (
  select min(emp.sal) from emp group by deptno
);

--task 4
select avg(sal) as avg_sal from emp
  inner join dept on emp.deptno = dept.deptno group by dept.deptno;

--task 5
select empno, ename from emp where mgr =
(select mgr from emp where empno = 2221); --7698

--task 6
select empno, ename, sal from emp
where sal > (select avg(sal) from emp);

--task 7
select ename, deptno, sal from emp where deptno = any
                                        (select deptno from emp where comm > 0)
                                    and sal = any
                                        (select sal from emp where comm > 0);

--task 8
select ename, deptno, sal from emp where deptno = any
                                         (select deptno from emp where comm > 0)
                                     or sal = any
                                         (select sal from emp where comm > 0);

--task 9
select ename, empno from emp where sal >
(select max(sal) from emp where job = 'CLERK');

--task 10
select count(empno) from emp where deptno =
                            (select deptno from dept where loc = 'DALLAS');

--task 11
select count(empno) from emp where job = 'MANAGER';

--task 12
-- Сформировать запрос, выводящий фамилию сотрудника,
-- номер узла иерархии и имена всех его менеджеров через /.
with recursive r as (

  select 1 as i, empno, mgr, ename from emp
  where empno = 7369

  union

  select i + 1 as i, emp.empno, emp.mgr, emp.ename
    from emp
      join r on r.mgr = emp.empno
) select i, empno, ename from r;

--task 13
-- Сформировать запрос, выводящий фамилию сотрудника, номер и название подразделения, где он работает,
-- номер узла иерархии и имена всех его менеджеров через /.
with recursive r as (

  select 1 as i, empno, mgr, ename, deptno from emp
  where empno = 7369

  union

  select i + 1 as i, emp.empno, emp.mgr, emp.ename, emp.deptno
  from emp
         join r on r.mgr = emp.empno
) select i, empno, ename, r.deptno, dname from r join dept
    on r.deptno = dept.deptno;


--task 14
-- Сформировать запрос, выводящий фамилию сотрудника, номер и название подразделения, где он работает,
-- номер узла иерархии и имена всех его менеджеров через /.
-- Внутри одного уровня иерархии сотрудники должны быть отсортированы по названиям подразделения.

-- with main as
-- (with recursive r as (
--
--   select 1 as i, empno, mgr, ename, deptno from emp
--   where empno = 7369
--
--   union
--
--   select i + 1 as i, emp.empno, emp.mgr, emp.ename, emp.deptno
--   from emp
--          join r on r.mgr = emp.empno
-- ) select i, empno, ename, r.deptno, dname from r join dept
--                                                       on r.deptno = dept.deptno)
-- select *, array_to_string(
--   array (
--     select main.ename from main
--     ), './'
--   ) from main;

-- array_to_string(
--                  array (
--                  with recursive curr_nmgrs as (
--
--                  select 1 as cm
--                  union
--                  select i + 1 as cm from curr_nmgrs
--
--                  ) select curr_nmgrs.cm from curr_nmgrs
--                  ) , './')

with main as
       (with recursive r as (

         select 1 as i, empno, mgr, ename, deptno from emp
         where empno = 7369

         union

         select i + 1 as i, emp.empno, emp.mgr, emp.ename, emp.deptno
         from emp
                join r on r.mgr = emp.empno
         ) select i, empno, ename, r.deptno, dname from r join dept
                                                               on r.deptno = dept.deptno)
select *, (array(select ename from main))[2:] from main;

--                           join (
--   with recursive h as (
--     select 1 as j, array(select ename from main) as mngrs
--     union
--     select j + 1 as j, (array(select ename from main))[j:] from h
--     ) select * from h
-- ) as hierarhy on i = j;


--task 15
-- Сформировать запрос, выводящий фамилию сотрудника,
-- номер узла иерархии и имена всех его менеджеров через /, исключая фамилию самого сотрудника.


commit;