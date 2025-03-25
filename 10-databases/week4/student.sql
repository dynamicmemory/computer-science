CREATE TABLE student (
    name VARCHAR(20) NOT NULL,
    snum INTEGER PRIMARY KEY,
    class INTEGER NOT NULL CHECK (class > 0),
    major VARCHAR(10) NOT NULL
);

CREATE TABLE course (
    cname VARCHAR(50) NOT NULL, 
    cnum VARCHAR(10) PRIMARY KEY,
    chours INTEGER NOT NULL CHECK (chours > 0),
    department VARCHAR(10)
);

CREATE TABLE section (
    sid INTEGER PRIMARY KEY,
    cnum VARCHAR(10),
    sem VARCHAR(6) NOT NULL,
    year INTEGER NOT NULL,
    instructor VARCHAR(20),
    FOREIGN KEY (cnum) REFERENCES course (cnum) 
      ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE grade_report (
    snum INTEGER,
    sid INTEGER,
    grade VARCHAR(2) NOT NULL,
    PRIMARY KEY (snum, sid),
    FOREIGN KEY (snum) REFERENCES student (snum)
      ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (sid) REFERENCES section (sid)
      ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE prerequisite (
    cnum VARCHAR(10),
    pnum VARCHAR(10),
    PRIMARY KEY (cnum, pnum),
    FOREIGN KEY (cnum) REFERENCES course (cnum)
      ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO student (name,snum,class,major) VALUES
    ('Smith',17,1,'CS'),
    ('BROWN',8,2,'CS');

INSERT INTO course (cname,cnum,chours,department) VALUES 
    ('Intro to Computer Science','CS1310',4,'CS'),
    ('Data Structures','CS3320',4,'CS'),
    ('Discrete Mathematics','MATH2410',3,'MATH'),
    ('Database','CS3380',3,'CS');

INSERT INTO section (sid,cnum,sem,year,instructor) VALUES 
    (85,'MATH2410','Fall',07,'King'),
    (92,'CS1310','Fall`',07,'Anderson'),
    (102,'CS3320','Spring', 08,'Knuth'),
    (112,'MATH2410','Fall',08,'Chang'),
    (119,'CS1310','Fall',08,'Anderson'),
    (135,'CS3380','Fall',08,'Stone');

INSERT INTO grade_report (snum,sid,grade) VALUES 
    (17,112,'B'),
    (17,119,'C'),
    (8,85,'A'),
    (8,92,'A'),
    (8,102,'B'),
    (8,135,'A');

INSERT INTO prerequisite (cnum,pnum) VALUES
    ('CS3380','CS3320'),
    ('CS3380','MATH2410'),
    ('CS3320','CS1310');


-- QUESTION 2 

-- a) Retrieve the names and major departments of all straight-A students 
SELECT DISTINCT name, major FROM student AS s, grade_report AS g WHERE grade = 'A' AND 
    s.snum = g.snum;

-- b) Retrieve the names and major departments of all students who do not have any 
--    grade of A in any of their courses. 

SELECT DISTINCT name, major FROM student AS s
    WHERE NOT EXISTS
    (SELECT 1 FROM grade_report AS g WHERE s.snum = g.snum AND g.grade = 'A');
