--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: department; Type: TABLE; Schema: public; Owner: lenny
--

CREATE TABLE public.department (
    dname character varying(25) NOT NULL,
    dnumber integer NOT NULL,
    mgr_ssn character(9) NOT NULL,
    mgr_start_date date
);


ALTER TABLE public.department OWNER TO lenny;

--
-- Name: dependent; Type: TABLE; Schema: public; Owner: lenny
--

CREATE TABLE public.dependent (
    employee_ssn character(9) NOT NULL,
    dependent_name character varying(15) NOT NULL,
    sex character(1),
    birth_date date,
    relationship character varying(8),
    CONSTRAINT dependent_sex_check CHECK ((sex = ANY (ARRAY['M'::bpchar, 'F'::bpchar])))
);


ALTER TABLE public.dependent OWNER TO lenny;

--
-- Name: dept_locations; Type: TABLE; Schema: public; Owner: lenny
--

CREATE TABLE public.dept_locations (
    dnumber integer NOT NULL,
    dlocation character varying(15) NOT NULL
);


ALTER TABLE public.dept_locations OWNER TO lenny;

--
-- Name: employee; Type: TABLE; Schema: public; Owner: lenny
--

CREATE TABLE public.employee (
    fname character varying(15) NOT NULL,
    minit character varying(1),
    lname character varying(15) NOT NULL,
    ssn character(9) NOT NULL,
    birth_date date,
    address character varying(50),
    sex character(1),
    salary numeric(10,2),
    super_ssn character(9),
    dno integer
);


ALTER TABLE public.employee OWNER TO lenny;

--
-- Name: project; Type: TABLE; Schema: public; Owner: lenny
--

CREATE TABLE public.project (
    pname character varying(25) NOT NULL,
    pnumber integer NOT NULL,
    plocation character varying(15),
    dnum integer NOT NULL
);


ALTER TABLE public.project OWNER TO lenny;

--
-- Name: works_on; Type: TABLE; Schema: public; Owner: lenny
--

CREATE TABLE public.works_on (
    employee_ssn character(9) NOT NULL,
    project_number integer NOT NULL,
    hours numeric(4,1)
);


ALTER TABLE public.works_on OWNER TO lenny;

--
-- Data for Name: department; Type: TABLE DATA; Schema: public; Owner: lenny
--

COPY public.department (dname, dnumber, mgr_ssn, mgr_start_date) FROM stdin;
Research	5	333445555	1978-05-22
Administration	4	987654321	1985-01-01
Headquarters	1	888665555	1971-06-19
Software	6	111111100	1999-05-15
Hardware	7	444444400	1998-05-15
Sales	8	555555500	1997-01-01
\.


--
-- Data for Name: dependent; Type: TABLE DATA; Schema: public; Owner: lenny
--

COPY public.dependent (employee_ssn, dependent_name, sex, birth_date, relationship) FROM stdin;
333445555	Alice	F	1976-04-05	Daughter
333445555	Theodore	M	1973-10-25	Son
333445555	Joy	F	1948-05-03	Spouse
987654321	Abner	M	1932-02-29	Spouse
123456789	Michael	M	1978-01-01	Son
123456789	Alice	F	1978-12-31	Daughter
123456789	Elizabeth	F	1957-05-05	Spouse
444444400	Johnny	M	1997-04-04	Son
444444400	Tommy	M	1999-06-07	Son
444444401	Chris	M	1969-04-19	Spouse
444444402	Sam	M	1964-02-14	Spouse
\.


--
-- Data for Name: dept_locations; Type: TABLE DATA; Schema: public; Owner: lenny
--

COPY public.dept_locations (dnumber, dlocation) FROM stdin;
1	Houston
4	Stafford
5	Bellaire
5	Sugarland
5	Houston
6	Atlanta
6	Sacramento
7	Milwaukee
8	Chicago
8	Dallas
8	Philadephia
8	Seattle
8	Miami
\.


--
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: lenny
--

COPY public.employee (fname, minit, lname, ssn, birth_date, address, sex, salary, super_ssn, dno) FROM stdin;
Alex	D	Freed	444444400	1950-10-09	4333 Pillsbury, Milwaukee, WI	M	89000.00	\N	7
Bob	B	Bender	666666600	1968-04-17	8794 Garfield, Chicago, IL	M	96000.00	\N	8
Evan	E	Wallis	222222200	1958-01-16	134 Pelham, Milwaukee, WI	M	92000.00	\N	7
James	E	Borg	888665555	1927-11-10	450 Stone, Houston, TX	M	55000.00	\N	1
Jared	D	James	111111100	1966-10-10	123 Peachtree, Atlanta, GA	M	85000.00	\N	6
John	C	James	555555500	1975-06-30	7676 Bloomington, Sacramento, CA	M	81000.00	\N	6
Kim	C	Grace	333333300	1970-10-23	6677 Mills Ave, Sacramento, CA	F	79000.00	\N	6
Ahmad	V	Jabbar	987987987	1959-03-29	980 Dallas, Houston, TX	M	25000.00	987654321	4
Alicia	J	Zelaya	999887777	1958-07-19	3321 Castle, Spring, TX	F	25000.00	987654321	4
Franklin	T	Wong	333445555	1945-12-08	638 Voss, Houston, TX	M	40000.00	888665555	5
Jennifer	S	Wallace	987654321	1931-06-20	291 Berry, Bellaire, TX	F	43000.00	888665555	4
Red	A	Bacher	666666613	1980-05-21	196 Elm Street, Miami, FL	M	33500.00	666666612	8
Sammy	G	Hall	666666612	1970-01-11	433 Main Street, Miami, FL	M	37000.00	666666611	8
Carl	E	Reedy	666666611	1977-06-21	213 Ball St, Philadelphia, PA	M	32000.00	666666610	8
Naveen	B	Drew	666666610	1970-05-23	198 Elm St, Philadelphia, PA	M	34000.00	666666607	8
Ray	H	King	666666606	1949-08-16	213 Delk Road, Seattle, WA	M	44500.00	666666604	8
Billie	J	King	666666604	1960-01-01	556 Washington, Chicago, IL	F	38000.00	666666603	8
Jon	A	Kramer	666666605	1968-08-22	1988 Windy Creek, Seattle, WA	M	41500.00	666666603	8
Arnold	A	Head	666666608	1967-05-19	233 Spring St, Dallas, TX	M	33000.00	666666602	8
Gerald	D	Small	666666607	1962-05-15	122 Ball Street, Dallas, TX	M	29000.00	666666602	8
Helga	C	Pataki	666666609	1969-03-11	101 Holyoke St, Dallas, TX	F	32000.00	666666602	8
Lyle	G	Leslie	666666603	1963-01-09	417 Hancock Ave, Chicago, IL	M	41000.00	666666601	8
Jill	J	Jarvis	666666601	1966-01-14	6234 Lincoln, Chicago, IL	F	36000.00	666666600	8
Kate	W	King	666666602	1966-04-16	1976 Boone Trace, Chicago, IL	F	44000.00	666666600	8
Nandita	K	Ball	555555501	1969-04-16	222 Howard, Sacramento, CA	M	62000.00	555555500	6
Alec	C	Best	444444402	1966-01-18	233 Solid, Milwaukee, WI	M	60000.00	444444400	7
Bonnie	S	Bays	444444401	1956-06-19	111 Hollow, Milwaukee, WI	F	70000.00	444444400	7
Sam	S	Snedden	444444403	1977-07-31	987 Windy St, Milwaukee, WI	M	48000.00	444444400	7
John	B	Smith	123456789	1955-01-09	731 Fondren, Houston, TX	M	30000.00	333445555	5
Joyce	A	English	453453453	1962-07-31	5631 Rice, Houston, TX	F	25000.00	333445555	5
Ramesh	K	Narayan	666884444	1952-08-15	971 Fire Oak, Humble, TX	M	38000.00	333445555	5
Jeff	H	Chase	333333301	1970-01-07	145 Bradbury, Sacramento, CA	M	44000.00	333333300	6
Chris	A	Carter	222222205	1960-03-21	565 Jordan, Milwaukee, WI	F	43000.00	222222201	7
Jenny	F	Vos	222222204	1967-11-11	263 Mayberry, Milwaukee, WI	F	61000.00	222222201	7
Andy	C	Vile	222222202	1944-01-21	1967 Jordan, Milwaukee, WI	M	53000.00	222222200	7
Josh	U	Zell	222222201	1954-05-22	266 McGrady, Milwaukee, WI	M	56000.00	222222200	7
Tom	G	Brand	222222203	1966-12-16	112 Third St, Milwaukee, WI	M	62500.00	222222200	7
Brad	C	Knight	111111103	1968-02-13	176 Main St., Atlanta, GA	M	44000.00	111111100	6
Jon	C	Jones	111111101	1967-11-14	111 Allgood, Atlanta, GA	M	45000.00	111111100	6
Justin	\N	Mark	111111102	1966-01-12	2342 May, Atlanta, GA	M	40000.00	111111100	6
\.


--
-- Data for Name: project; Type: TABLE DATA; Schema: public; Owner: lenny
--

COPY public.project (pname, pnumber, plocation, dnum) FROM stdin;
ProductX	1	Bellaire	5
ProductY	2	Sugarland	5
ProductZ	3	Houston	5
Computerization	10	Stafford	4
Reorganization	20	Houston	1
Newbenefits	30	Stafford	4
OperatingSystems	61	Jacksonville	6
DatabaseSystems	62	Birmingham	6
Middleware	63	Jackson	6
InkjetPrinters	91	Phoenix	7
LaserPrinters	92	LasVegas	7
\.


--
-- Data for Name: works_on; Type: TABLE DATA; Schema: public; Owner: lenny
--

COPY public.works_on (employee_ssn, project_number, hours) FROM stdin;
123456789	1	32.5
123456789	2	7.5
666884444	3	40.0
453453453	1	20.0
453453453	2	20.0
333445555	2	10.0
333445555	3	10.0
333445555	10	10.0
333445555	20	10.0
999887777	30	30.0
999887777	10	10.0
987987987	10	35.0
987987987	30	5.0
987654321	30	20.0
987654321	20	15.0
888665555	20	\N
111111100	61	40.0
111111101	61	40.0
111111102	61	40.0
111111103	61	40.0
222222200	62	40.0
222222201	62	48.0
222222202	62	40.0
222222203	62	40.0
222222204	62	40.0
222222205	62	40.0
333333300	63	40.0
333333301	63	46.0
444444400	91	40.0
444444401	91	40.0
444444402	91	40.0
444444403	91	40.0
555555500	92	40.0
555555501	92	44.0
666666601	91	40.0
666666603	91	40.0
666666604	91	40.0
666666605	92	40.0
666666606	91	40.0
666666607	61	40.0
666666608	62	40.0
666666609	63	40.0
666666610	61	40.0
666666611	61	40.0
666666612	61	40.0
666666613	61	30.0
666666613	62	10.0
666666613	63	10.0
\.


--
-- Name: department department_pkey; Type: CONSTRAINT; Schema: public; Owner: lenny
--

ALTER TABLE ONLY public.department
    ADD CONSTRAINT department_pkey PRIMARY KEY (dnumber);


--
-- Name: dependent dependent_pkey; Type: CONSTRAINT; Schema: public; Owner: lenny
--

ALTER TABLE ONLY public.dependent
    ADD CONSTRAINT dependent_pkey PRIMARY KEY (employee_ssn, dependent_name);


--
-- Name: dept_locations dept_locations_pkey; Type: CONSTRAINT; Schema: public; Owner: lenny
--

ALTER TABLE ONLY public.dept_locations
    ADD CONSTRAINT dept_locations_pkey PRIMARY KEY (dnumber, dlocation);


--
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: public; Owner: lenny
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (ssn);


--
-- Name: project project_pkey; Type: CONSTRAINT; Schema: public; Owner: lenny
--

ALTER TABLE ONLY public.project
    ADD CONSTRAINT project_pkey PRIMARY KEY (pnumber);


--
-- Name: project project_pname_key; Type: CONSTRAINT; Schema: public; Owner: lenny
--

ALTER TABLE ONLY public.project
    ADD CONSTRAINT project_pname_key UNIQUE (pname);


--
-- Name: works_on works_on_pkey; Type: CONSTRAINT; Schema: public; Owner: lenny
--

ALTER TABLE ONLY public.works_on
    ADD CONSTRAINT works_on_pkey PRIMARY KEY (employee_ssn, project_number);


--
-- Name: dependent dependent_employee_ssn_fkey; Type: FK CONSTRAINT; Schema: public; Owner: lenny
--

ALTER TABLE ONLY public.dependent
    ADD CONSTRAINT dependent_employee_ssn_fkey FOREIGN KEY (employee_ssn) REFERENCES public.employee(ssn);


--
-- Name: dept_locations dept_locations_dnumber_fkey; Type: FK CONSTRAINT; Schema: public; Owner: lenny
--

ALTER TABLE ONLY public.dept_locations
    ADD CONSTRAINT dept_locations_dnumber_fkey FOREIGN KEY (dnumber) REFERENCES public.department(dnumber);


--
-- Name: employee employee_dno_fkey; Type: FK CONSTRAINT; Schema: public; Owner: lenny
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_dno_fkey FOREIGN KEY (dno) REFERENCES public.department(dnumber) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: employee employee_super_ssn_fkey; Type: FK CONSTRAINT; Schema: public; Owner: lenny
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_super_ssn_fkey FOREIGN KEY (super_ssn) REFERENCES public.employee(ssn) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: project project_dnum_fkey; Type: FK CONSTRAINT; Schema: public; Owner: lenny
--

ALTER TABLE ONLY public.project
    ADD CONSTRAINT project_dnum_fkey FOREIGN KEY (dnum) REFERENCES public.department(dnumber);


--
-- Name: works_on works_on_employee_ssn_fkey; Type: FK CONSTRAINT; Schema: public; Owner: lenny
--

ALTER TABLE ONLY public.works_on
    ADD CONSTRAINT works_on_employee_ssn_fkey FOREIGN KEY (employee_ssn) REFERENCES public.employee(ssn);


--
-- Name: works_on works_on_project_number_fkey; Type: FK CONSTRAINT; Schema: public; Owner: lenny
--

ALTER TABLE ONLY public.works_on
    ADD CONSTRAINT works_on_project_number_fkey FOREIGN KEY (project_number) REFERENCES public.project(pnumber);


--
-- PostgreSQL database dump complete
--

