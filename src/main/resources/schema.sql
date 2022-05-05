drop table if exists part;
create table part (
    part_id bigint auto_increment primary key,
    name varchar(50) not null,
    price bigint not null,
    created_at datetime default now(),
    category varchar(50)
);

drop table if exists orders;
create table orders(
    order_id bigint auto_increment primary key,
    order_status varchar(50) not null,
    email varchar(50) not null,
    address varchar(200) not null,
    zipcode varchar(50) not null,
    created_at datetime default now()
);

drop table if exists orders_part;
create table orders_part (
    order_part_id bigint auto_increment primary key ,
    part_id bigint not null,
    order_id bigint not null,
    price bigint not null,
    quantity int not null,
    created_at datetime default now(),
    constraint FK_part_orders_part foreign key (part_id) references part(part_id),
    constraint FK_orders_orders_part foreign key (order_id) references orders(order_id)
);


insert into part(name, price, category) VALUES ('인텔 코어i9-12세대 12900KS (엘더레이크) (정품)', 1016830, 'CPU');
insert into part(name, price, category) VALUES ('AMD 라이젠5-4세대 5600X (버미어) (멀티팩(프리즘팩))', 283000, 'CPU');
insert into part(name, price, category) VALUES ('AMD 라이젠5-4세대 5600X (버미어) (멀티팩(정품))', 228520, 'CPU');
insert into part(name, price, category) VALUES ('인텔 코어i5-12세대 12400F (엘더레이크) (정품)', 219990, 'CPU');
insert into part(name, price, category) VALUES ('AMD 라이젠5 PRO 4650G (르누아르) (멀티팩(정품))', 168000, 'CPU');

insert into part(name, price, category) VALUES ('GAINWARD 지포스 GT1030 D4 2GB 디앤디컴', 119000, 'GPU');
insert into part(name, price, category) VALUES ('이엠텍 지포스 RTX 3050 STORM X Dual OC D6 8GB', 450000, 'GPU');
insert into part(name, price, category) VALUES ('ASRock 라데온 RX 6600 CHALLENGER D D6 8GB 에즈윈', 390000, 'GPU');

insert into part(name, price, category) VALUES ('삼성전자 DDR4-3200 (8GB)', 36000, 'RAM');
insert into part(name, price, category) VALUES ('삼성전자 DDR4-3200 (16GB)', 80000, 'RAM');
insert into part(name, price, category) VALUES ('삼성전자 DDR4-3200 (32GB)', 160000, 'RAM');

insert into part(name, price, category) VALUES ('MSI PRO H610M-G DDR4', 120000, 'MAIN_BOARD');
insert into part(name, price, category) VALUES ('ASRock B660M Pro RS D4', 120000, 'MAIN_BOARD');
insert into part(name, price, category) VALUES ('GIGABYTE B660M DS3H D4', 150000, 'MAIN_BOARD');
insert into part(name, price, category) VALUES ('GIGABYTE B660M DS3H D4', 150000, 'MAIN_BOARD');

insert into part(name, price, category) VALUES ('삼성전자 870 EVO (500GB)', 70000, 'SSD');
insert into part(name, price, category) VALUES ('삼성전자 980 M.2 NVMe (1TB)', 150000, 'SSD');
insert into part(name, price, category) VALUES ('SK하이닉스 Gold P31 M.2 NVMe (1TB)', 160000, 'SSD');







