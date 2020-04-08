CREATE TABLE demo.employees (
	id integer NOT NULL GENERATED ALWAYS AS IDENTITY,
	first_name varchar NOT NULL,
	last_name varchar NULL,
	reports_to varchar NULL,
	team_name varchar NULL,
	"role" integer NULL,
	hired_on date NOT NULL,
	CONSTRAINT employees_pk PRIMARY KEY (id)
);
