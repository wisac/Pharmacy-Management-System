create table drugs
(
    drug_code   varchar(20)  not null
        primary key,
    name        varchar(100) null,
    description text         null,
    price       double       null,
    quantity    int          null
);

create table purchase_history
(
    id            int auto_increment
        primary key,
    drug_code     varchar(20)  null,
    purchase_date timestamp    null,
    buyer         varchar(100) null,
    quantity      int          null,
    total_amount  double       null,
    constraint purchase_history_ibfk_1
        foreign key (drug_code) references drugs (drug_code)
);

create index drug_code
    on purchase_history (drug_code);

create table sales
(
    id        int auto_increment
        primary key,
    drug_code varchar(20) null,
    sale_date timestamp   null,
    quantity  int         null,
    constraint sales_ibfk_1
        foreign key (drug_code) references drugs (drug_code)
);

create index drug_code
    on sales (drug_code);

create table suppliers
(
    supplier_id varchar(20)  not null
        primary key,
    name        varchar(100) null,
    location    varchar(100) null
);

create table drug_suppliers
(
    drug_code   varchar(20) not null,
    supplier_id varchar(20) not null,
    primary key (drug_code, supplier_id),
    constraint drug_suppliers_ibfk_1
        foreign key (drug_code) references drugs (drug_code),
    constraint drug_suppliers_ibfk_2
        foreign key (supplier_id) references suppliers (supplier_id)
);

create index supplier_id
    on drug_suppliers (supplier_id);


