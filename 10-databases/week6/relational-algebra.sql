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

projects_department_5(pno) := (projects WHERE dno = 5)[pnumbers]
employee_projects := (employee JOIN ssn = essn works_on)[ssn, pno]
department_5_employees := employee_projects DIVIDEDBY projects_department_5 
final := (employee * department_5_employees)[ssn, fname, lname]

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

count_dependents(ssn, count) := (COUNT, dependent_names (dependent) GROUP BY essn) >= 2 
final := (employee * count_dependents)[ssn, fname, lname]

Example 11 
Get the names of employees who have no dependents 

employee_with_dependents(ssn) := dependent[essn]
employee_no_dep := employee[ssn] - employee_with_dependents
final := (employee_no_dep * employee)[ssn, fname, lname]

Example 12 
List the names of managers who have atleast one dependent 

preliminary := department[mgr_ssn](ssn) INTERSECTION dependent[essn](ssn)
final := (prelim * employee)[ssn, fname, lname]

Example 13
Return a sole customer with general loan ttypes and  if another customer with loan
type general exists return no result. 

general_loan := (customer_loan JOIN lno = lnum loan) WHERE ltype = 'General'
count_of_generals(ssn, count) := COUNT, essn (general_loan) GROUP BY lno
general_loan_customer := customer JOIN ssn = cssn general_loan
final := (general_loan_customer WHERE count_of_gens[count] = 1)[ssn, fname, lname]


Prac Exercises 

1 - Display customers with a student account in armidale branch 

armidale_branch := (bank_branch WHERE b_address = 'Armidale') JOIN bnum = bno customer_account
student_accounts := (account JOIN anum = ano armidale_branch) WHERE atype = 'Student'
customers := (customer JOIN ssn = cssn student_accounts)[ssn, name]

2 - Display the average, total an count of balances per branch 

branches := branch JOIN bnum = bno customer_account 
aggregate_balances := (COUNT, SUM, AVG) balance (branches)

3 - List the number of accounts and names of customers who have more than one bank account 

accounts := (customer JOIN ssn = cssn customer_account) JOIN ano = anum account 
number_of_accounts := COUNT ano (accounts) GROUP BY ssn
final := (accounts WHERE number_of_accounts >= 2)[number_of_accounts, fname]

4 - Use a UNION to display customer accounts and loans in one table. This should 
display customer name in the first column, balances and amount in the next column 
and account nuimber or loan number in the last column 

loans := (customer JOIN ssn = cssn customer_loan) JOIN lno = lnum loan
accounts := customer JOIN ssn = cssn customer_account) JOIN ano = anum account 
final := (loans UNION accounts)[fname, balance OR amount, anum OR lnum]

5 - Display customers with all accounts NOT in armiale branch 

armidale_branches := branch WHERE b_address = 'Armidale'
customers := (customer JOIN ssn = cssn customer_account) JOIN ano = anum account
all_customer_branches := customers JOIN bno = bnum branch 
armidale_customers[ssn] := customers JOIN bno = bnum armidale_branch
non_armidale_customers := customer[ssn] - armidale_customers
final := non_armidale_customers[ssn, fname]

6 - Get customers whos loan types are all general 

all_customers_loans := (loan JOIN lnum = lno customer_loan) JOIN cssn = ssn customer
general_loans := all_customers_loans WHERE ltype = 'General'
non_general_loans := all_customers_loans WHERE ltype != 'General'
final := (general_loans - non_general_loans)[ssn] 


