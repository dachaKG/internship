insert into component (code,name,price,stock) values ('proba', 'naziv',12,2);
insert into component (code,name,price,stock) values ('m123', 'jetion',1200,10);
insert into component (code,name,price,stock) values ('mo12', 'dell',15000,3);
insert into component (code,name,price,stock) values ('t12', 'dell',1300,7);
insert into component (code,name,price,stock) values ('s01', 'fujitsu',20000,6);
insert into component (code,name,price,stock) values ('mp1', 'AMD A68H',20000,4);
insert into component (code,name,price,stock) values ('p01', 'fujitsu',20000,4);
insert into component (code,name,price,stock) values ('k01', 'acer',4000,20);

insert into typeofcomponent (toc_name, valueOfComponent) values ('mis','one');
insert into typeofcomponent (toc_name, valueOfComponent) values ('monitor','oneToMany');
insert into typeofcomponent (toc_name, valueOfComponent) values ('tastatura','one');
insert into typeofcomponent (toc_name, valueOfComponent) values ('stampac','zero');
insert into typeofcomponent (toc_name, valueOfComponent) values ('kuciste','one');
insert into typeofcomponent (toc_name, valueOfComponent) values ('maticna ploca','one');
insert into typeofcomponent (toc_name, valueOfComponent) values ('procesor','one');


insert into component_type (toc_id, component_id) values (1,1);
insert into component_type (toc_id, component_id) values (1,2);
insert into component_type (toc_id, component_id) values (2,3);
insert into component_type (toc_id, component_id) values (3,4);
insert into component_type (toc_id, component_id) values (4,5);
insert into component_type (toc_id, component_id) values (6,6);
insert into component_type (toc_id, component_id) values (7,7);
insert into component_type (toc_id, component_id) values (5,8);


