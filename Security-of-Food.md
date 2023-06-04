# 关于食品安全的数据库管理系统

- - -

## 这是一个基于Spring-boot以及Mybatis-plus的前后端不分离的B/S应用项目

## 主要有以下功能

1. 普通用户可以注册，对已注册用户完成登录，已注册用户的再次注册错误信息提示 √
2. 实现关于食品信息的查询功能，对缺失信息页面处理 √
3. 通过视图实现关于生产某一食品的生产商的查询，仅存在check option，完成对缺失信息页面的处理 √
create view ManufacturerView as select Manufacturer.factu_name factu,area,factu_principal principal,factu_contact contact,foodName food from Manufacturer inner join product on (Manufacturer.factu_name=product.factu_name and Manufacturer.factu_area=product.area) inner join food on id=food.foodID with check option;
4. 用户可以对食品信息进行反馈，要求用户对反馈信息类型分类正确，否则利用触发器，插入失败；插入成功时未注册用户反馈自动注册 √
delimiter //
create trigger accurateTypeForAdvice
before insert on note
for each row
if NEW.notes_type!='advice' and NEW.notes_type!='provide' then 
SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'Wrong Type!';
end if;//
delimiter ;
5. 用户可以对原有邮箱信息进行修改，更新时若新邮箱为非注册用户则更新成功，否则更新失败 √

delimiter //
create trigger updateForUser
after update on user
for each row
update note set note.notes_user=new.name,note.user_email=new.email where note.notes_user=old.name and note.user_email=old.email;//
delimiter ;

delimiter //
create procedure editEmail(in u_name char(40),in old_email char(100),in new_email char(100))
begin
if(select count(*) from user where name=u_name and email=new_email)!=0 then
signal sqlstate '45000'
set MESSAGE_TEXT = 'User Exists!';
end if;
update user set email=new_email where name=u_name and email=old_email;
end;//
delimiter ;
6. 用户可以选择注销，没有留言过的用户可以正常注销，但已有留言消息的用户注销提示错误，利用事务对RuntimeException捕捉并rollback √
7. 管理人员可以对查看反馈、可以对数据库信息进行数据增加、更新 √
8. 管理人员利用存储过程完成事务级存储厂商，以及其对应生产食品关系 √
delimiter //
create procedure storeManufacturer(in m_name char(40),in m_area char(50),in m_principal char(50),in p_food char(50))
begin
declare food_id int;
start transaction;
if(select count(*) from food where foodName=p_food)=0 then
insert into food(foodName) values(p_food);
end if;
select foodID into food_id from food where foodName=p_food limit 1;
insert into manufacturer(factu_name,factu_area,factu_principal) values(m_name,m_area,m_principal);
insert into product(factu_name,area,id) values(m_name,m_area,food_id);
commit;
end;//
delimiter ;
call storeManufacturer('津沽粮食工业有限公司','天津市','QS1200 0102 0547','米饭');
9. 管理人员利用存储过程插入生产信息，若插入食品不存在则同时插入该食品，插入生产信息时同时更新厂商注册生产个数 √
delimiter //
create procedure storeProduct(in factur char(40),in area char(50),in food char(50))
begin
declare food_id int;
start transaction;
if(select count(*) from food where foodName=food)=0 then
insert into food(foodName) values(food);
end if;
select foodID into food_id from food where foodName=food limit 1;
insert into product(factu_name,area,id) values(factur,area,food_id);
update manufacturer set factu_signup=factu_signup+1 where factu_name=factur and factu_area=area;
commit;
end;//
delimiter ;
