ALTER TABLE `t_plan` ADD COLUMN `product_codes` varchar(500) NULL;
update t_plan p set product_codes=(select code from t_product prod where prod.id=p.product_id);

CREATE TABLE IF NOT EXISTS `t_plan_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(110) null,
  `plan_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `product_price` double NOT NULL DEFAULT '0',
  `product_amount` double NOT NULL DEFAULT '0',
  `product_length` double NOT NULL DEFAULT '0',
  `real_amount` double NOT NULL DEFAULT '0',
  `product_remark` varchar(100) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
); 

insert into t_plan_product(plan_id,product_id,product_amount,product_length,real_amount) select id, product_id,product_amount,product_length,real_amount from t_plan order by id asc; 
update t_plan_product pp set name=(select concat(name,'/1') from t_plan p where p.id=pp.plan_id);

ALTER TABLE t_plan DROP COLUMN product_id;
ALTER TABLE t_plan DROP COLUMN product_amount;
ALTER TABLE t_plan DROP COLUMN product_length;
ALTER TABLE t_plan DROP COLUMN real_amount;