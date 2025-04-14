Relational algebra

Example 1
Get ssns fro all employees that work in department 5 or any manager that supervises
an employee from department 5. 

department_5_employees := employee where dno = 5 
employee_ssns := department_5_employees[ssn]
employee_supervisors := department_5_employees[super_ssn]
final := employee_ssns UNION employee_supervisors


Example 2 
List the names of all female employees dependents

female_employees := (employee WHERE sex = 'f')[ssn]
cross_produce_dependents := female_employees TIMES dependent 
female_employee_dependents := cross_produce_dependents WHERE ssn = essn 
final := female_employees_dependents[dependent_name]

Example 3 
Get the names of all department managers 

department_managers := department[mgr_ssn]
managers := employee JOIN ssn = mgr_ssn department_managers
final := managers[snn, fname, lname]

Example 4
Return the names of employees who work on all the projects that John Smith works on 

employee_projects := employee JOIN ssn=essn works_on
john_smith_projects := (employee_projects WHERE fname = 'John' AND lname = 'Smith')[pno]
all_employee_projects := employee_projects[ssn, pno]
works_on_js_projects := all_employee_projects DIVIDEDBY john_smith_projects
final := (employee * works_on_js_projects)[ssn, fname, lname]


