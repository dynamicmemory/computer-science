Example - get snn's for all employees that work in department 5 or any mananger
that supervisdes an employee from department 5.

emp_dept_five := employee WHERE dno = 5
result_one := emp_dept_five[ssn]
result_two := emp_dept_five[super_ssn]
final_results := result_one UNION result_two 

Example - list the names of each female employees dependants.

fem_employee := (employee WHERE sex='f')[ssn]
cross_product := fem_employee TIMES dependant
fem_employee_dep := cross_product WHERE ssn=essn
final_result := fem_employee_dep[dependant_name]

example - get the names of all department managers

dep_managers := R(ssn) :=department[mgr_ssn]
managers := employee * dep_managers
final_result := managers[ssn,fname,lname]

R = [1,5
1,6
2,7
2,8
3,5
3,6]

S = [5
6]

T = R DIVIDEDBY S 

T = [1
3]

Example - return the names of employees who work on all the projects john smith
works on 

em_projects := employee JOIN ssn=essn works_on
js_projects := (em_projects WHERE fname='John' AND lname='smith')[pno]
all_emp_projects := em_projects[ssn,pno]
works_on_js_projects := all_emp_projects DIVIDEDBY js_projects
final_results := (employee * works_on_js_projects)[snn,fname,lname]

