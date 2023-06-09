create table if not exists Projects
(
    id         varchar(36)                         not null primary key,
    name       text                                not null
);
create table if not exists Projects_Tasks
(
    task_id    varchar(36) not null,
    project_id varchar(36) not null,
    primary key (task_id, project_id)
);
create table if not exists Tags
(
    id          varchar(36) not null
        primary key,
    name        text        not null,
    description text        null
);
create table if not exists Tags_Tasks
(
    tag_id  varchar(36) not null,
    task_id varchar(36) not null,
    primary key (task_id, tag_id)
);
create table if not exists Tasks
(
    id          varchar(36)                         not null primary key,
    title       text                                not null,
    description text                                null,
    state       text                                null
);