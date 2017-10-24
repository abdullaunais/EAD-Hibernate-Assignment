drop constraint fk2_employee_tasks;
drop constraint fk1_employee_tasks;
drop constraint fk1_task;
drop constraint fk1_employee;

drop table task;
drop table employee;
drop table role;



create table role (
    roleId int primary key,
    title varchar(50)
);

create table employee (
    employeeId int primary key,
    name varchar(100),
    role int not null,
    constraint fk1_employee foreign key (role) references role(roleId)
);

create table task (
    taskId int primary key,
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

insert into role values (1, 'Software Engineer');
insert into role values (2, 'Senior Software Engineer');
insert into role values (3, 'Web Developer');
insert into role values (4, 'Database Administrator');
insert into role values (5, 'Tech Lead');

insert into employee values (1, 'Kamal', 1);
insert into employee values (2, 'Sunil', 1);
insert into employee values (3, 'Amal', 2);
insert into employee values (4, 'Jagath', 3);
insert into employee values (5, 'David', 3);
insert into employee values (6, 'John', 4);
insert into employee values (7, 'Larry', 5);

insert into task values (1, 'Delpoy App', null);
insert into task values (2, 'Compile Latest Build', null);
insert into task values (3, 'Assign Tasks', null);
insert into task values (4, 'Backup Database', null);
insert into task values (5, 'Contact with Client for Feedback', null);
insert into task values (6, 'Design Website', null);


