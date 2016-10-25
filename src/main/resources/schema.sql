/*
drop table `t_barcode`;
--drop table `t_bcreader`;
drop table `t_customer`;
drop table `t_drawing`;
drop table `t_employee`;
drop table `t_ex_pm`;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='条码信息';

INSERT INTO `t_barcode` (`id`, `bar_code`, `bar_code_type`, `object_code`, `status`, `generate_date`, `enabled_date`, `remark`) VALUES
    (1, '', '', '1', 'material', '2016-10-12 23:01:46', '2016-10-12 23:01:46', NULL);


-- 导出  表 zm_db_data.t_customer 结构
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE IF NOT EXISTS `t_customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cust_name` varchar(256) COLLATE utf8_unicode_ci NOT NULL COMMENT '客户名称',
  `cust_addr` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '客户地址',
  `enabled_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '登记日期',
  `cust_contacter` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系人',
  `cust_contacter_phone` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `sales_person_id` bigint(20) DEFAULT NULL COMMENT '业务员',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `status` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '状态：有效、失效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='客户信息表';

-- 导出  表 zm_db_data.t_drawing 结构
DROP TABLE IF EXISTS `t_drawing`;
CREATE TABLE IF NOT EXISTS `t_drawing` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `drw_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '图纸名称',
  `drw_designer_id` bigint(20) DEFAULT NULL COMMENT '设计师',
  `drw_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '出图日期',
  `status` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '状态：有效、失效',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `drw_name` (`drw_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='图纸信息';

INSERT INTO `t_drawing` (`id`, `drw_name`, `drw_designer_id`, `drw_date`, `status`, `remark`) VALUES
    (1, '螺栓A', 1, '2010-01-01 00:00:00', '', '测试数据用');


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
  `status` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '状态：有效、失效',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='员工信息表';

INSERT INTO `t_employee` (`id`, `code`, `name`, `duty`, `roler_id`, `phone`, `email`, `password`, `family_addr`, `status`, `remark`) VALUES
    (1, '1001', 'Calvin', '总理', 1, '13399999999', 'calvin.xiao@springside.io', '+2MunThvGcEfdYIFlT4NQQHt6z4=', '阿斯顿发斯蒂芬', '', '阿斯顿发生大法师打发斯蒂芬'),
    (2, '1002', 'David', '总理', 1, '13809555523', 'david.wang@springside.io', '+2MunThvGcEfdYIFlT4NQQHt6z4=', '谁谁谁水水水水', '', '事实上事实上事实上'),
    (3, '1003', 'Bob', '员工', 1, '13888888888', 'zzb205@163.com', 'QPfAH0GJUQAxrczZxgShKK2vmwA=', '这地方被各种地方工作的风格', '', '上的规划是大法官 山东分公司的股份'),
    (4, '1004', 'Yema', '会计', 1, '13333333333', 'ma.fei@163.com', 'QPfAH0GJUQAxrczZxgShKK2vmwA=', '山东分公司的风格是大法官', '', '说过分手的风格是大法官');


-- 导出  表 zm_db_data.t_ex_pm 结构
DROP TABLE IF EXISTS `t_ex_pm`;
CREATE TABLE IF NOT EXISTS `t_ex_pm` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `prod_id` bigint(20) DEFAULT NULL COMMENT '产品ID',
  `mate_id` bigint(20) DEFAULT NULL COMMENT '原料ID',
  `mate_num` int(11) unsigned zerofill DEFAULT NULL COMMENT '数量',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='产品原料关系表';


-- 导出  表 zm_db_data.t_material 结构
DROP TABLE IF EXISTS `t_material`;
CREATE TABLE IF NOT EXISTS `t_material` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mate_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '原料名称',
  `mate_gb_standard` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '规格',
  `mate_num_stock` int(11) unsigned DEFAULT NULL COMMENT '库存量',
  `mate_num_alarm` int(11) unsigned DEFAULT NULL COMMENT '告警阈值',
  `status` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '状态：有效、失效',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='原料信息表';

INSERT INTO `t_material` (`id`, `mate_name`, `mate_gb_standard`, `mate_num_stock`, `mate_num_alarm`, `status`, `remark`) VALUES
    (1, '软管A型', 'GB-0101', 0, 5000, NULL, '测试数据test');

-- 导出  表 zm_db_data.t_mate_instock_info 结构
DROP TABLE IF EXISTS `t_mate_instock_info`;
CREATE TABLE IF NOT EXISTS `t_mate_instock_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mate_id` bigint(20) NOT NULL COMMENT '原料ID',
  `mate_barcode` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '原料条码',
  `gb_standard` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '规格',
  `purchase_datetime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '进货日期',
  `purchase_em_id` bigint(20) DEFAULT NULL COMMENT '进货人ID',
  `mis_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '入库时间',
  `mis_count` int(11) unsigned zerofill DEFAULT NULL COMMENT '入库数量',
  `mis_em_id` bigint(20) DEFAULT NULL COMMENT '入库人ID',
  `mis_receipt` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '入库单据号',
  `mate_price` double NULL COMMENT '原料价格（涉密字段）',
  `mate_supplier_id` bigint(20) DEFAULT NULL COMMENT '供应商ID',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='原料入库明细';

-- 导出  表 zm_db_data.t_mate_outstock_info 结构
DROP TABLE IF EXISTS `t_mate_outstock_info`;
CREATE TABLE IF NOT EXISTS `t_mate_outstock_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mate_id` bigint(20) NOT NULL COMMENT '原料ID',
  `mate_barcode` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '原料条码',
  `gb_standard` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '规格',
  `consumer_id` bigint(20) DEFAULT NULL COMMENT '领用人ID',
  `mos_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '出库时间',
  `mos_count` int(11) unsigned zerofill DEFAULT NULL COMMENT '出库数量',
  `mos_em_id` bigint(20) DEFAULT NULL COMMENT '出库人ID',
  `mos_receipt` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '出库单据号',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='原料出库明细';

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

-- 导出  表 zm_db_data.t_product 结构
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE IF NOT EXISTS `t_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `prod_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `prod_gb_standard` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '规格',
  `prod_type` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '类别',
  `prod_num_stock` int(11) unsigned zerofill DEFAULT NULL COMMENT '库存量',
  `prod_num_alarm` int(11) unsigned zerofill DEFAULT NULL COMMENT '库存告警阈值',
  `prod_drawing_id` bigint(20) DEFAULT NULL COMMENT '图纸编号',
  `status` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '产品状态：有效、失效',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='产品表';

INSERT INTO `t_product` (`id`, `prod_name`, `prod_gb_standard`, `prod_type`, `prod_num_stock`, `prod_num_alarm`, `prod_drawing_id`, `status`, `remark`) VALUES
    (1, '热水器管', 'GB-RSQ-01', '非标件', NULL, 00000000000000005000, 1, NULL, '测试数据');


-- 导出  表 zm_db_data.t_prod_instock_info 结构
DROP TABLE IF EXISTS `t_prod_instock_info`;
CREATE TABLE IF NOT EXISTS `t_prod_instock_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `prod_id` bigint(20) NOT NULL COMMENT '产品ID',
  `prod_barcode` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '产品条码',
  `gb_standard` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '规格',
  `pis_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '入库时间',
  `pis_count` int(11) unsigned zerofill DEFAULT NULL COMMENT '入库数量',
  `pis_em_id` bigint(20) DEFAULT NULL COMMENT '入库人ID',
  `pis_receipt` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '入库单据号',
  `prod_cost` double  COMMENT '产品成本（涉密字段）',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='产品入库明细';

-- 导出  表 zm_db_data.t_prod_outstock_info 结构
DROP TABLE IF EXISTS `t_prod_outstock_info`;
CREATE TABLE IF NOT EXISTS `t_prod_outstock_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `prod_id` bigint(20) NOT NULL COMMENT '产品ID',
  `prod_barcode` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '产品条码',
  `gb_standard` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '规格',
  `pos_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '出库时间',
  `pos_count` int(11) unsigned zerofill DEFAULT NULL COMMENT '出库数量',
  `pos_em_id` bigint(20) DEFAULT NULL COMMENT '出库人ID',
  `pos_receipt` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '出库单据号',
  `pos_salesperson_id` bigint(20) DEFAULT NULL COMMENT '业务员ID',
  `pos_customer_id` bigint(20) DEFAULT NULL COMMENT '客户ID',
  `prod_cost` bigint(20) unsigned zerofill DEFAULT NULL COMMENT '产品成本（涉密字段）',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='产品出库明细';

-- 导出  表 zm_db_data.t_roler 结构
DROP TABLE IF EXISTS `t_roler`;
CREATE TABLE IF NOT EXISTS `t_roler` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rl_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '角色名称',
  `rl_priv` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '权限',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色信息表';

INSERT INTO `t_roler` (`id`, `rl_name`, `rl_priv`, `remark`) VALUES
    (1, '管理者', '1111', '测试数据');


-- 导出  表 zm_db_data.t_supplier 结构
DROP TABLE IF EXISTS `t_supplier`;
CREATE TABLE IF NOT EXISTS `t_supplier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sp_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '供应商名称',
  `sp_addr` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '地址',
  `enabled_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '登记日期',
  `sp_contacter` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系人',
  `sp_contacter_phone` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `sales_person_id` bigint(20) DEFAULT NULL COMMENT '业务员',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `status` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '状态：有效、失效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='供应商信息表';

create table IF NOT EXISTS  message (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    receiver_id bigint null,
    message varchar(256),
    receive_date timestamp
);

*/
