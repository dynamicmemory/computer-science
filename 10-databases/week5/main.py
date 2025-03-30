import psycopg2

db_map = {"fname": "Name", "lname": "Last Name", "birth_date": "Bday", "sex": "sex",
          "dname": "Department Name", "dlocation": "Address"}

conn = None 
while(True):

    # This doesnt work so gpt a solution as uni so shit it cant even teach me basics
    sql = """INSERT INTO employee(fname,minit,lname,ssn,bith_date, address,sex,
            salary,super_ssn,dno) VALUES%s;"""
    insert_employee_row = ("new_fname", "C", "new_lname", 112233446,'1966-1-12',
                           "2342 May, Atlanta, GA",'M',40000.00, 111111100 ,6)

    conn = None 
    try:
        # Connects to db
        conn = psycopg2.connect("dbname=prac_3 user=lenny password=")
        cursor = conn.cursor()
        
        # Execute insert command 
        cursor.execute(sql,(insert_employee_row))
        conn.commit()

        # Gets user query
        inp: str = input("enter an sql command: ") 
        cursor.execute(inp)

        results = cursor.fetchall()

        # Gets col names from query
        colnames: list = [col[0] for col in cursor.description] 
        columns: str = ""
        for name in colnames:
            columns += db_map[name] + "\t"

        # displays query
        print(columns)
        for row in results:
            row_string: str = ""
            for var in row:
                row_string += var + "\t"
            print(row_string)
        print("Fin")

    except (Exception, psycopg2.DatabaseError) as error:
        print("Error connecting to database", error)

    finally:
        if conn:
            conn.close()
            print("Connection closed")
