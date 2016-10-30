/*
insert into t_employee (code,email,name,password) values(1001,'calvin.xiao@springside.io','Calvin','+2MunThvGcEfdYIFlT4NQQHt6z4=');
insert into t_employee (code,email,name,password) values(1002,'david.wang@springside.io','David','+2MunThvGcEfdYIFlT4NQQHt6z4=');
insert into t_employee (code,email,name,password) values(1003,'zzb205@163.com','Bob','QPfAH0GJUQAxrczZxgShKK2vmwA=');
insert into t_employee (code,email,name,password) values(1004,'ma.fei@163.com','Yema','QPfAH0GJUQAxrczZxgShKK2vmwA=');
INSERT INTO `t_roler` (`id`, `p_id`, `rl_name`) VALUES (-1, 0, '请选择角色');
INSERT INTO `t_roler` (`id`, `p_id`,`rl_name`, `rl_priv`, `remark`) VALUES
    (1, -1, '管理者', '1111', '测试数据');
insert into t_user_role(user_id,role_id) values(1,1);

insert into t_product(id,prod_code,prod_name,prod_gb_standard,prod_type,prod_num_alarm,prod_drawing_ID,remark) values(3,'bx101','双门冰箱','350L','家用',1000,1,'家用双门冰箱，要大，要省电');

insert into t_prod_instock_info(pis_code,prod_ID,gb_standard,pis_count,pis_em_id) values('pid_101',3,'350L',100,3);
*/

