import psycopg2

db_map = {"fname": "Name", "lname": "Last Name", "birth_date": "Bday", "sex": "sex",
          "dname": "Department Name", "dlocation": "Address"}

conn = None 
while(True):
    try:
        conn = psycopg2.connect("dbname=prac_3 user=lenny password=")
        cursor = conn.cursor()
        
        inp: str = input("enter an sql command: ") 
        cursor.execute(inp)

        results = cursor.fetchall()

        colnames: list = [col[0] for col in cursor.description] 
        columns: str = ""
        for name in colnames:
            columns += db_map[name] + "\t"

        print(columns)
        for row in results:
            row_string: str = ""
            for var in row:
                row_string += var + "\t"
            print(row_string)
        print("Fin")

    except:
        print("Your command failed, try again")

