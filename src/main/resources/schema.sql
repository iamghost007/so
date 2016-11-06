/*
drop table `t_barcode`;
--drop table `t_bcreader`;
drop table `t_customer`;
drop table `t_drawing`;
drop table `t_employee`;
drop table `t_pm`;
drop table `t_material`;
drop table `t_mate_instock_info`;
drop table `t_mate_outstock_info`;
drop table `t_priv`;
drop table `t_product`;
drop table `t_prod_instock_info`;
drop table  `t_prod_outstock_info`;
drop table `t_roler`;
drop table `t_supplier`;
drop table `message`;
*/

/*

-- 导出 zm_db_data 的数据库结构
DROP DATABASE IF EXISTS `zm_db_data`;
CREATE DATABASE IF NOT EXISTS `zm_db_data` 
USE `zm_db_data`;

-- 导出  表 zm_db_data.t_barcode 结构
DROP TABLE IF EXISTS `t_barcode`;
CREATE TABLE IF NOT EXISTS `t_barcode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bar_code` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `bar_code_type` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '物料类别',
  `object_code` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '对应的产品或原料编号',
  `status` varchar(32) COLLATE utf8_unicode_ci DEFAULT '新增' COMMENT '状态：新增、入库、出库、无效',
  `generate_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '生成时间',
  `enabled_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '启用时间',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `bar_code` (`bar_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='条码信息';

-- 数据导出被取消选择。


-- 导出  表 zm_db_data.t_customer 结构
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE IF NOT EXISTS `t_customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cust_name` varchar(256) COLLATE utf8_unicode_ci NOT NULL COMMENT '客户名称',
  `cust_addr` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '客户地址',
  `enabled_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '登记日期',
  `cust_contacter` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '联系人',
  `cust_contacter_phone` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `sales_person_id` bigint(20) DEFAULT NULL COMMENT '业务员',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `status` varchar(64) COLLATE utf8_unicode_ci DEFAULT '有效' COMMENT '状态：有效、失效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='客户信息表';

-- 数据导出被取消选择。


-- 导出  表 zm_db_data.t_drawing 结构
DROP TABLE IF EXISTS `t_drawing`;
CREATE TABLE IF NOT EXISTS `t_drawing` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `drw_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '图纸名称',
  `drw_designer_id` bigint(20) DEFAULT NULL COMMENT '设计师',
  `drw_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '出图日期',
  `prod_id` bigint(20) DEFAULT NULL COMMENT '产品ID',
  `drw_img` blob,
  `status` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '有效' COMMENT '状态：有效、失效',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `drw_name` (`drw_name`),
  UNIQUE KEY `prod_id` (`prod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='图纸信息';

-- 数据导出被取消选择。


-- 导出  表 zm_db_data.t_employee 结构
DROP TABLE IF EXISTS `t_employee`;
CREATE TABLE IF NOT EXISTS `t_employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '员工编号',
  `name` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '姓名',
  `duty` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '职务',
  `roler_id` bigint(20) DEFAULT NULL COMMENT '角色',
  `phone` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号码',
  `email` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '邮箱',
  `password` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '密码',
  `family_addr` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '家庭住址',
  `status` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '有效' COMMENT '状态：有效、失效',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='员工信息表';

-- 数据导出被取消选择。


-- 导出  表 zm_db_data.t_material 结构
DROP TABLE IF EXISTS `t_material`;
CREATE TABLE IF NOT EXISTS `t_material` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `mate_name` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `mate_gb_standard` varchar(256) COLLATE utf8_unicode_ci NOT NULL COMMENT '规格',
  `gb_code` varchar(6) COLLATE utf8_unicode_ci NOT NULL,
  `mate_num_stock` int(10) unsigned DEFAULT '0' COMMENT '库存量',
  `mate_num_alarm` int(10) unsigned DEFAULT '9999' COMMENT '告警阈值',
  `status` varchar(64) COLLATE utf8_unicode_ci DEFAULT '有效' COMMENT '状态：有效、失效',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='原料信息表';

-- 数据导出被取消选择。


-- 导出  表 zm_db_data.t_mate_instock_info 结构
DROP TABLE IF EXISTS `t_mate_instock_info`;
CREATE TABLE IF NOT EXISTS `t_mate_instock_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mate_id` bigint(20) NOT NULL COMMENT '原料ID',
  `mate_barcode` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '原料条码',
  `gb_standard` varchar(256) COLLATE utf8_unicode_ci NOT NULL COMMENT '规格',
  `purchase_datetime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '进货日期',
  `purchase_em_id` bigint(20) NOT NULL COMMENT '进货人ID',
  `mis_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '入库时间',
  `mis_count` int(10) unsigned zerofill NOT NULL COMMENT '入库数量',
  `mis_em_id` bigint(20) NOT NULL COMMENT '入库人ID',
  `mis_receipt` varchar(64) COLLATE utf8_unicode_ci DEFAULT '无单据' COMMENT '入库单据号',
  `mate_price` double unsigned zerofill DEFAULT '0000000000000000000000' COMMENT '原料价格（涉密字段）',
  `mate_supplier_id` bigint(20) NOT NULL COMMENT '供应商ID',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='原料入库明细';

-- 数据导出被取消选择。


-- 导出  表 zm_db_data.t_mate_outstock_info 结构
DROP TABLE IF EXISTS `t_mate_outstock_info`;
CREATE TABLE IF NOT EXISTS `t_mate_outstock_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mate_id` bigint(20) NOT NULL COMMENT '原料ID',
  `mate_barcode` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '原料条码',
  `gb_standard` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '规格',
  `consumer_id` bigint(20) DEFAULT NULL COMMENT '领用人ID',
  `mos_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '出库时间',
  `mos_count` int(10) unsigned zerofill DEFAULT NULL COMMENT '出库数量',
  `mos_em_id` bigint(20) DEFAULT NULL COMMENT '出库人ID',
  `mos_receipt` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '出库单据号',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='原料出库明细';

-- 数据导出被取消选择。


-- 导出  表 zm_db_data.t_pm 结构
DROP TABLE IF EXISTS `t_pm`;
CREATE TABLE IF NOT EXISTS `t_pm` (
  `prod_id` bigint(20) NOT NULL COMMENT '产品ID',
  `mate_id` bigint(20) NOT NULL COMMENT '原料ID',
  `mate_num` int(10) unsigned zerofill DEFAULT '0000000000' COMMENT '数量',
  UNIQUE KEY `prod_id_mate_id` (`prod_id`,`mate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci COMMENT='产品与原料配置关系表';

-- 数据导出被取消选择。


-- 导出  表 zm_db_data.t_priv 结构
DROP TABLE IF EXISTS `t_priv`;
CREATE TABLE IF NOT EXISTS `t_priv` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pv_code` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '权限id',
  `pv_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `pv_desc` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '描述',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pv_code` (`pv_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='权限表';

-- 数据导出被取消选择。


-- 导出  表 zm_db_data.t_product 结构
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE IF NOT EXISTS `t_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `prod_name` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `prod_gb_standard` varchar(256) COLLATE utf8_unicode_ci NOT NULL COMMENT '规格',
  `prod_type` varchar(256) COLLATE utf8_unicode_ci DEFAULT '自有产品' COMMENT '类别',
  `prod_num_stock` int(10) unsigned zerofill DEFAULT '0000000000' COMMENT '库存量',
  `prod_num_alarm` int(10) unsigned zerofill DEFAULT '0000009999' COMMENT '库存告警阈值',
  `status` varchar(64) COLLATE utf8_unicode_ci DEFAULT '有效' COMMENT '产品状态：有效、失效',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='产品表';

-- 数据导出被取消选择。


-- 导出  表 zm_db_data.t_prod_instock_info 结构
DROP TABLE IF EXISTS `t_prod_instock_info`;
CREATE TABLE IF NOT EXISTS `t_prod_instock_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `prod_id` bigint(20) NOT NULL COMMENT '产品ID',
  `prod_barcode` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '产品条码',
  `gb_standard` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '规格',
  `pis_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '入库时间',
  `pis_count` int(10) unsigned zerofill DEFAULT NULL COMMENT '入库数量',
  `pis_em_id` bigint(20) DEFAULT NULL COMMENT '入库人ID',
  `pis_receipt` varchar(64) COLLATE utf8_unicode_ci DEFAULT '无单据' COMMENT '入库单据号',
  `prod_cost` double unsigned zerofill DEFAULT '0000000000000000000000' COMMENT '产品成本（涉密字段）',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='产品入库明细';

-- 数据导出被取消选择。


-- 导出  表 zm_db_data.t_prod_outstock_info 结构
DROP TABLE IF EXISTS `t_prod_outstock_info`;
CREATE TABLE IF NOT EXISTS `t_prod_outstock_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `prod_id` bigint(20) NOT NULL COMMENT '产品ID',
  `prod_barcode` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '产品条码',
  `gb_standard` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '规格',
  `pos_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '出库时间',
  `pos_count` int(10) unsigned NOT NULL COMMENT '出库数量',
  `pos_em_id` bigint(20) DEFAULT NULL COMMENT '出库人ID',
  `pos_receipt` varchar(64) COLLATE utf8_unicode_ci DEFAULT '无单据' COMMENT '出库单据号',
  `pos_salesperson_id` bigint(20) DEFAULT NULL COMMENT '业务员ID',
  `pos_customer_id` bigint(20) DEFAULT NULL COMMENT '客户ID',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='产品出库明细';

-- 数据导出被取消选择。

-- 数据导出被取消选择。


-- 导出  表 zm_db_data.t_supplier 结构
DROP TABLE IF EXISTS `t_supplier`;
CREATE TABLE IF NOT EXISTS `t_supplier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sp_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '供应商名称',
  `code` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `sp_addr` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '地址',
  `enabled_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '登记日期',
  `sp_contacter` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系人',
  `sp_contacter_phone` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `sales_person_id` bigint(20) DEFAULT NULL COMMENT '业务员',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `status` varchar(64) COLLATE utf8_unicode_ci DEFAULT '有效' COMMENT '状态：有效、失效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='供应商信息表';

-- 导出  表 zm_db_data.t_roler 结构
DROP TABLE IF EXISTS `t_roler`;
CREATE TABLE IF NOT EXISTS `t_roler` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `p_id` bigint(20),
  `rl_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '角色名称',
  `rl_priv` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '权限',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色信息表';

DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE IF NOT EXISTS `t_user_role` (
   user_id bigint(20) not null,
   role_id bigint(20) not null,
   primary key (user_id, role_id)
 );

DROP TABLE IF EXISTS `t_plan`;
CREATE TABLE IF NOT EXISTS `t_plan` (
   id bigint(20) not null AUTO_INCREMENT,
   product_id bigint(20),
   product_name varchar(255) default '',
   product_amount double,
   product_type varchar(255) ,
   customer varchar(255) ,
   order_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (id)
 );

-- 导出  表 zm_db_data.message 结构
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE IF NOT EXISTS `t_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from` bigint(20) DEFAULT NULL,
  `to` bigint(20) DEFAULT NULL,
  `message` varchar(256) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `link` varchar(888),
  `status` varchar(64) COLLATE utf8_unicode_ci DEFAULT '有效' COMMENT '状态：有效、失效',
  `receive_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

*/
