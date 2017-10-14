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