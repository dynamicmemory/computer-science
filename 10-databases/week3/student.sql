CREATE TABLE student(
  name            VARCHAR(20) NOT NULL,
  student_number  INTEGER PRIMARY KEY,
  class           INTEGER NOT NULL,
  major           VARCHAR(10)
);

CREATE TABLE course(
  course_name   VARCHAR(40) NOT NULL,
  course_number VARCHAR(20) PRIMARY KEY,
  credit_hours  INTEGER NOT NULL,
  department    VARCHAR(10) NOT NULL
);

CREATE TABLE section(
  section_identifier  INTEGER PRIMARY KEY,
  course_number       VARCHAR(20),
  semester            VARCHAR(10),
  year                VARCHAR(4),
  instructor          VARCHAR(20),
  FOREIGN KEY (course_number) REFERENCES course (course_number)
    ON DELETE CASCADE ON UPDATE CASCADE 
);

CREATE TABLE grade_report(
  student_number      INTEGER,
  section_identifier  INTEGER,
  grade               CHAR CHECK (grade IN ('A', 'B', 'C', 'F')),
  PRIMARY KEY(student_number, section_identifier),
  FOREIGN KEY (student_number) REFERENCES student (student_number)
    ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (section_identifier) REFERENCES section (section_identifier)
    ON DELETE CASCADE ON UPDATE CASCADE 
);

CREATE TABLE prerequisite(
  course_number        VARCHAR(20),
  prerequisite_number  VARCHAR(20),
  PRIMARY KEY (course_number, prerequisite_number), 
  FOREIGN KEY (course_number) REFERENCES course (course_number)
    ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO student(name, student_number, class, major)
  VALUES ('Smith', 17, 1, 'CS'),
         ('Brown',8, 2,'CS');

INSERT INTO course(course_name, course_number, credit_hours, department)
  VALUES ('Intro to computer science', 'CS1310', 4, 'CS'),
         ('Data Structures', 'CS3320', 4, 'CS'),
         ('Discrete Mathematics', 'MATH2410', 3, 'MATH'),
         ('Databases', 'CS3380', 4, 'CS');

INSERT INTO section(section_identifier, course_number, semester, year, instructor)
  VALUES (85, 'MATH2410', 'Fall', '07', 'King'),
         (92, 'CS1310', 'Fall', '07', 'Anderson'),
         (102, 'CS3320', 'Spring', '08', 'Knuth'),
         (112, 'MATH2410', 'Fall', '08', 'Chang'),
         (119, 'CS1310', 'Fall', '08', 'Anderson'),
         (135, 'CS3380', 'Fall', '08', 'Stone');

INSERT INTO grade_report(student_number, section_identifier, grade)
  VALUES (17, 112, 'B'),
         (17, 119, 'C'),
         (8, 85, 'A'),
         (8, 92, 'A'),
         (8, 102, 'B'),
         (8, 135, 'A');

INSERT INTO prerequisite(course_number, prerequisite_number)
  VALUES ('CS3380', 'CS3320'),
         ('CS3380', 'MATH2410'),
         ('CS3320', 'CS1310');











