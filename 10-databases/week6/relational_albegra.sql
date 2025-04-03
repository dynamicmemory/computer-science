Se1lect operation is similar to WHERE 
Table_name WHERE attribute = value 

Project operation is similar to SELECT 
Table_name[attribute, attribute2, attribute3]

Assigning and Renaming
TEMP := Table_name WHERE attribute = value 
R(att1,att2,att3) := TEMP[att1, att2, att3] 






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


Example - return a list of all employees and the name of a department if they 
happen to manage a department 

employee_and_man := employee LEFT OUTER JOIN ssn=mgr_ssn department 
final := employee_and_man[ssn,fname,lname,dname]


Query 1 - Retrueve the name and address of all employees who work for the research
department 

res_dept := department WHERE dname='Research' 
emp_res := employee JOIN dno=dnumber department
final := emp_res[ssn,fname,lname,address]


Query 2 - For every project located in dtaffod, list the project number, the controlling
department number and the department managers last name, birthdate and address 

staff_project := project WHERE plocation='Stafford'
staff_pro_dept := staff_project JOIN dno=dnumber department
staff_pro_dept_man := employee JOIN ssn=mgr_ssn staff_pro_dept 
final := staff_pro_dept_man[pnumber, dno, lname, dob, address]


Query 3 - Find the names of employees who work on all the projects controlled by 
department 

pro_dept_five(pno) := (projects WHERE dno=5)[pnumber]
emp_projects := (employee JOIN ssn=essn works_on)[snn,pno]
dept_five_emp := emp_projects DIVIDEDBY pro_dept_five
finale := (employee * dept_five_emp)[ssn,fname,lname]


Query 4 - Make a lis of project numbers for a projects that inclvce an empolyee whose 
last name is smith either as a worker or manager of department that controls project 

emp_smith := employee WHERE lname='Smith'
emp_proj_smith :=emp_smith JOIN ssn=essn works_on 
emp_proj_smith_pno := emp_proj_smith[pno]

emp_smith_man := emp_smith JOIN ssn=mgr_ssn department
emp_smith_man_proj(pno) := (emp_smith_man JOIN dnumber=dno project)[pnumber]

final := emp_proj_smith_pno UNION emp_smith_man_proj


Query 5 - List the names of all employees with two  or more dependants 

count_dep(ssn,count) := (COUNT, dependant_names (dependant) GROUP BY essn) >= 2
final := (employee * count_dep)[ssn,fname,lname]


Query 6 - get the names of employees who have no depentants

employee_with_dep(ssn) := dependant[essn]
employee_no_dep := employee[ssn] - employee_with_dep 
final := (employee_no_dep * employee)[ssn,fname,lname]


Query 7 - List the names of managers who have at least one dependent 

prelim := department[mgr_ssn](ssn) INTERSECTION dependent[essn](ssn)
final := (prelim * employee)[ssn,fname,lname]


Universal quantifier - Return a sole customer with genreal loan types and if another
customer with loan type general exists return no result. 

gen_loan := (customer_loan JOIN lno=lnum Loan) WHERE ltype='General'

count_of_gens(snn,count) := COUNT, essn (gen_loan) GROUP BY lno

gen_loan_customer := customer JOIN ssn=cssn gen_loan 

final := (gen_loan_customer WHERE count_of_gens[count] = 1)[ssn,fname,lname]




1. Display customers with a student account in armidale branch 

armidale_branch := bank_branch WHERE b_address = 'Armidal'
armidale_student_accounts := armidale_branch JOIN bno=bnum account 
student_accounts := armidale_student_accounts WHERE atype = 'Student'
customer_account_numbers := student_accounts JOIN anumber=ano customer_account 
final := (customer JOIN ssn=cssn customer_account_numbers)[ssn,name]


2. Lets display average, total and count of balances per branch 

bank_branch_accounts := bank_branch JOIN (bno,bco)=(bnum,bcum) account 
bank_branch_balances := bank_branch_accounts JOIN ano=anum customer_account 
final := (bank_branch_balance)[COUNT(balance), SUM(balance), AVG(balance)]


3. List the number of accounts and names of customers who have more than one 
   bank account 

count_accounts := (COUNT(anum) AS account_counts (customer_account) GROUP BY cssn) >= 2
final := (customer JOIN ssn=cssn count_accounts)[name, account_counts]


4.



