/*
drop table `t_barcode`;
drop table `t_bcreader`;
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


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 zm_db_data 的数据库结构
CREATE DATABASE IF NOT EXISTS `zm_db_data` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_520_ci */;
USE `zm_db_data`;


CREATE TABLE IF NOT EXISTS `t_barcode` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bar_code` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '条码编号',
  `bar_code_type` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '物料类别',
  `object_code` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '对应的产品或原料编号',
  `status` varchar(32) COLLATE utf8_unicode_ci DEFAULT '新增' COMMENT '状态：新增、入库、出库、无效',
  `generate_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '生成时间',
  `enabled_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '启用时间',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `bar_code` (`bar_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='条码信息';

CREATE TABLE IF NOT EXISTS `t_bcreader` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bcr_code` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '扫描枪编号',
  `bcr_location` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '扫描枪位置',
  `read_times` int(11) unsigned zerofill DEFAULT NULL COMMENT '扫描次数',
  `enabled_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '启用时间',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `bcr_code` (`bcr_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='扫描枪信息';

CREATE TABLE IF NOT EXISTS `t_customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cust_code` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '客户编号',
  `cust_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '客户名称',
  `cust_addr` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '客户地址',
  `enabled_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '登记日期',
  `cust_contacter` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系人',
  `cust_contacter_phone` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `sales_person` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '业务员',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `cust_code` (`cust_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='客户信息表';

CREATE TABLE IF NOT EXISTS `t_drawing` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `drw_code` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '图纸编号',
  `drw_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '图纸名称',
  `drw_designer` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '设计师',
  `drw_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '出图日期',
  `drw_prod_ID` bigint COMMENT '产品ID',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `drw_code` (`drw_code`),
  UNIQUE KEY `drw_name` (`drw_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='图纸信息';

CREATE TABLE IF NOT EXISTS `t_employee` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '员工编号',
  `name` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '姓名',
  `duty` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '职务',
  `roler` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色',
  `phone` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号码',
  `email` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '邮箱',
  `password` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '密码',
  `family_addr` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '家庭住址',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='员工信息表';

CREATE TABLE IF NOT EXISTS `t_ex_pm` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `prod_ID` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '产品ID',
  `mate_ID` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '原料ID',
  `mate_num` int(11) unsigned zerofill DEFAULT NULL COMMENT '数量',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='产品原料关系表';

CREATE TABLE IF NOT EXISTS `t_material` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `mate_code` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '原料编号',
  `mate_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '原料名称',
  `mate_gb_standard` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '规格',
  `mate_num_stock` int(11) unsigned DEFAULT NULL COMMENT '库存量',
  `mate_num_alarm` int(11) unsigned DEFAULT NULL COMMENT '告警阈值',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mate_code` (`mate_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='原料信息表';

CREATE TABLE IF NOT EXISTS `t_mate_instock_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `mis_code` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '入库序号',
  `mate_ID` bigint NOT NULL COMMENT '原料ID',
  `mate_name` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '原料名称(预留)',
  `mate_barcode` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '原料条码',
  `gb_standard` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '规格',
  `purchase_datetime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '进货日期',
  `purchase_em_ID` bigint COMMENT '进货人ID',
  `mis_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '入库时间',
  `mis_count` int(11) unsigned zerofill DEFAULT NULL COMMENT '入库数量',
  `mis_em_ID` bigint COMMENT '入库人ID',
  `mis_receipt` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '入库单据号',
  `mate_price` int(11) unsigned zerofill DEFAULT NULL COMMENT '原料价格（涉密字段）',
  `mate_supplier_ID` bigint COMMENT '供应商ID',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mis_code` (`mis_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='原料入库明细';

CREATE TABLE IF NOT EXISTS `t_mate_outstock_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `mos_code` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '出库序号',
  `mate_ID` bigint NOT NULL COMMENT '原料ID',
  `mate_name` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '原料名称',
  `mate_barcode` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '原料条码',
  `gb_standard` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '规格',
  `consumer_ID` bigint COMMENT '领用人ID',
  `mos_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '出库时间',
  `mos_count` int(11) unsigned zerofill DEFAULT NULL COMMENT '出库数量',
  `mos_em_ID` bigint COMMENT '出库人ID',
  `mos_receipt` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '出库单据号',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mos_code` (`mos_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='原料出库明细';

CREATE TABLE IF NOT EXISTS `t_priv` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pv_code` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '权限id',
  `pv_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `pv_desc` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '描述',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pv_code` (`pv_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='权限表';

CREATE TABLE IF NOT EXISTS `t_product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `prod_code` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '编号',
  `prod_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `prod_gb_standard` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '规格',
  `prod_type` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '类别',
  `prod_num_stock` int(11) unsigned zerofill DEFAULT NULL COMMENT '库存量',
  `prod_num_alarm` int(11) unsigned zerofill DEFAULT NULL COMMENT '库存告警阈值',
  `prod_drawing_ID` bigint COMMENT '图纸编号',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `prod_code` (`prod_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='产品表';


CREATE TABLE IF NOT EXISTS `t_prod_instock_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pis_code` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '入库序号',
  `prod_ID` bigint NOT NULL COMMENT '产品ID',
  `prod_name` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '产品名称(预留)',
  `prod_barcode` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '产品条码',
  `mate_barcode` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '原料条码列表（一个产品会有多个原料）',
  `gb_standard` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '规格',
  `pis_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '入库时间',
  `pis_count` int(11) unsigned zerofill DEFAULT NULL COMMENT '入库数量',
  `pis_em_ID` bigint COMMENT '入库人ID',
  `pis_receipt` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '入库单据号',
  `prod_cost` int(11) unsigned zerofill DEFAULT NULL COMMENT '产品成本（涉密字段）',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pis_code` (`pis_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='产品入库明细';


-- 导出  表 zm_db_data.t_prod_outstock_info 结构
CREATE TABLE IF NOT EXISTS `t_prod_outstock_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pos_code` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '出库序号',
  `prod_ID` bigint NOT NULL COMMENT '产品ID',
  `prod_name` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '产品名称',
  `prod_barcode` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '产品条码',
  `mate_barcode` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '原料条码列表（一个产品会有多个原料）',
  `gb_standard` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '规格',
  `pos_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '出库时间',
  `pos_count` int(11) unsigned zerofill DEFAULT NULL COMMENT '出库数量',
  `pos_em_ID` bigint COMMENT '出库人ID',
  `pos_receipt` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '出库单据号',
  `pos_salesperson_ID` bigint COMMENT '业务员ID',
  `pos_customer_ID` bigint COMMENT '客户ID',
  `prod_cost` int(11) unsigned zerofill DEFAULT NULL COMMENT '产品成本（涉密字段）',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pos_code` (`pos_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='产品出库明细';


-- 导出  表 zm_db_data.t_roler 结构
CREATE TABLE IF NOT EXISTS `t_roler` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `rl_code` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '角色编号',
  `rl_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '角色名称',
  `rl_priv` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '权限',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `rl_code` (`rl_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色信息表';


-- 导出  表 zm_db_data.t_supplier 结构
CREATE TABLE IF NOT EXISTS `t_supplier` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `sp_code` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '供应商编号',
  `sp_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '供应商名称',
  `sp_addr` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '地址',
  `enabled_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '登记日期',
  `sp_contacter` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系人',
  `sp_contacter_phone` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `sales_person` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '业务员',
  `remark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sp_code` (`sp_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='供应商信息表';

create table IF NOT EXISTS  message (
	id bigint PRIMARY KEY AUTO_INCREMENT,
	receiver_id bigint null,
	message varchar(256),
	receive_date timestamp
);

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
