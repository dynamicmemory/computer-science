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

Example 5 
Return a list of all employees and the name of a department if they happen to 
manage a department 

employee_and_manager := employee LEFT OUTER JOIN ssn = mgr_ssn department 
final := employee_and_manager[fname, lname, ssn]

Example 6 
Return the name and address of all employees who work for the research department 

all_employees := employee JOIN dno = dnum department
research_employees := all_employees WHERE dname = 'reseach'
final := research_employees[fname, address]

Example 7
For every project located in 'Stafford', list the project number, the controlling
department number and hte department managers last name, birth date and address 

stafford_projects := project WHERE plocation = 'Stafford'[pnum, dno]
project_department := stafford_projects JOIN dno = dnum department[dnum, mgr_ssn]
department_employees := project_department JOIN mgr_ssn = ssn employees[pnum, lname, birth_date, address]

Example 8 
Find the names of employees who work on all the projects controlled by department 5 

projects_and_departments := projects JOIN pnumber = pno department
department_5_projects := projects_and_departments WHERE dnum = 5
all_employees := employee JOIN ssn = essn projects_and_departments
// This is wrong, use divided by 

Example 9 
Make a list of project numbers for projects that involve an employee whose last 
name is 'Smith', either as a worker or as a manager of the department that controls 
the project 

employee_named_smith := (employee WHERE lname = 'Smith')[ssn, dno]
smiths_department := employee_named_smith JOIN dno = dnum works_on 
smiths_projects := smiths_department JOIN pno = pnumber project 
result := smiths_projects[pnumber]

Example 10 
List the names of all employees with two or more dependents

count_dependents := (COUNT, dependent_names (dependent) GROUP BY essn) >= 2 
final := employee 
