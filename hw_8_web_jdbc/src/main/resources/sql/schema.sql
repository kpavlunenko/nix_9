DROP TABLE IF EXISTS company_business_direction;
DROP TABLE IF EXISTS agreements;
DROP TABLE IF EXISTS business_directions;
DROP TABLE IF EXISTS companies;
DROP TABLE IF EXISTS counterparties;

create table companies
(
    id            bigint auto_increment
        primary key,
    created       datetime(6)  null,
    updated       datetime(6)  null,
    deletion_mark bit          null,
    name          varchar(255) not null,
    company_type  varchar(255) not null
);

create table counterparties
(
    id                bigint auto_increment
        primary key,
    created           datetime(6)  null,
    updated           datetime(6)  null,
    deletion_mark     bit          null,
    name              varchar(255) not null,
    counterparty_type varchar(255) not null,
    inn               varchar(255) not null
);

create table agreements
(
    id              bigint auto_increment
        primary key,
    created         datetime(6)  null,
    updated         datetime(6)  null,
    deletion_mark   bit          null,
    name            varchar(255) not null,
    agreement_type  varchar(255) not null,
    company_id      bigint       not null,
    counterparty_id bigint       not null,
    foreign key (company_id) references companies (id),
    foreign key (counterparty_id) references counterparties (id)
);

create table business_directions
(
    id                bigint auto_increment
        primary key,
    created           datetime(6)  null,
    updated           datetime(6)  null,
    deletion_mark     bit          null,
    name              varchar(255) not null
);

create table company_business_direction
(
    company_id bigint not null,
    business_direction_id bigint not null,
    primary key (company_id, business_direction_id),
    foreign key (company_id) references companies (id),
    foreign key (business_direction_id) references business_directions (id)

);

