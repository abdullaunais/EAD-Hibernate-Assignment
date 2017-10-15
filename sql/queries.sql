drop constraint fk2_employee_tasks;
drop constraint fk1_employee_tasks;
drop constraint fk1_task;
drop constraint fk1_employee;

drop table task;
drop table employee;
drop table role;



create table role (
    roleId int primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    title varchar(50)
);

create table employee (
    employeeId int primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    name varchar(100),
    role int not null,
    constraint fk1_employee foreign key (role) references role(roleId)
);

create table task (
    taskId int primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    description varchar(100),
    employee int,
    constraint fk1_task foreign key(employee) references employee(employeeId)
);

create table employee_tasks (
    employee int,
    task int,
    constraint fk1_employee_tasks foreign key(employee) references employee(employeeId),
    constraint fk2_employee_tasks foreign key(task) references task(taskId)
);

insert into role values (default, 'Software Engineer');
insert into role values (default, 'Senior Software Engineer');
insert into role values (default, 'Web Developer');
insert into role values (default, 'Database Administrator');
insert into role values (default, 'Tech Lead');

insert into employee values (default, 'Kamal', 1);
insert into employee values (default, 'Sunil', 1);
insert into employee values (default, 'Amal', 2);
insert into employee values (default, 'Jagath', 3);
insert into employee values (default, 'David', 3);
insert into employee values (default, 'John', 4);
insert into employee values (default, 'Larry', 5);

insert into task values (default, 'Delpoy App', null);
insert into task values (default, 'Compile Latest Build', null);
insert into task values (default, 'Assign Tasks', null);
insert into task values (default, 'Backup Database', null);
insert into task values (default, 'Contact with Client for Feedback', null);
insert into task values (default, 'Design Website', null);


