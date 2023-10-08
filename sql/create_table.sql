# mysql
# 员工信息
# 人员基础信息
CREATE TABLE IF NOT EXISTS `ods_base_employee_base` (
    `id` INTEGER NOT NULL auto_increment,
    `emp_id` VARCHAR(255) NOT NULL,
    `emp_name` VARCHAR(255) NOT NULL,
    `nickname` VARCHAR(255) NOT NULL,
    `gender` VARCHAR(255) NOT NULL,
    `idcard_type` VARCHAR(255) NOT NULL,
    `idcard_id` VARCHAR(255) NOT NULL,
    `employee_type` VARCHAR(255) NOT NULL,
    `state` VARCHAR(255) NOT NULL,
    `entry_date` VARCHAR(255) NOT NULL,
    `resign_date` VARCHAR(255) NOT NULL,
    `dept_id` VARCHAR(255) NOT NULL,
    `post_code` VARCHAR(255) NOT NULL,
    `p_level` VARCHAR(255) NOT NULL,
    `m_level` VARCHAR(255) NOT NULL,
    `p_title` VARCHAR(255) NOT NULL,
    `m_title` VARCHAR(255) NOT NULL,
    `leader_id` VARCHAR(255) NOT NULL,
    `leader_name` VARCHAR(255) NOT NULL,
    `area_name` VARCHAR(255) NOT NULL,
    `area_code` VARCHAR(255) NOT NULL,
    `company_name` VARCHAR(255) NOT NULL,
    `is_cadre` VARCHAR(255) NOT NULL,
    `is_leader` VARCHAR(255) NOT NULL,
    `regular_worker_date` VARCHAR(255) NOT NULL,
    `work_place` VARCHAR(255) NOT NULL,
    `work_place_name` VARCHAR(255) NOT NULL,
    `birth_date` VARCHAR(255) NOT NULL,
    `age` VARCHAR(255) NOT NULL,
    `ethnic_name` VARCHAR(255) NOT NULL,
    `nationality_name` VARCHAR(255) NOT NULL,
    `hometown_name` VARCHAR(255) NOT NULL,
    `dept_leader` VARCHAR(255) NOT NULL,
    `dept_leader_name` VARCHAR(255) NOT NULL,
    `bp_id` VARCHAR(255) NOT NULL,
    `bp_name` VARCHAR(255) NOT NULL,
    `start_work_date` VARCHAR(255) NOT NULL,
    `continuous_work_age` VARCHAR(255) NOT NULL,
    `cur_address` VARCHAR(255) NOT NULL,
    `phone_number` VARCHAR(255) NOT NULL,
    `mail` VARCHAR(255) NOT NULL,
    `company_mail` VARCHAR(255) NOT NULL,
    `marriage` VARCHAR(255) NOT NULL,
    `edu_type` VARCHAR(255) NOT NULL,
    `edu_start_date` VARCHAR(255) NOT NULL,
    `edu_end_date` VARCHAR(255) NOT NULL,
    `edu_major` VARCHAR(255) NOT NULL,
    `edu_univ_id` VARCHAR(255) NOT NULL,
    `edu_univ_name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB;

# 员工职位字典
CREATE TABLE IF NOT EXISTS `ods_base_post` (
    `id` INTEGER NOT NULL auto_increment,
    `post_id` VARCHAR(255) NOT NULL,
    `post1_id` VARCHAR(255) NOT NULL,
    `post2_id` VARCHAR(255) NOT NULL,
    `post3_id` VARCHAR(255) NOT NULL,
    `post1_name` VARCHAR(255) NOT NULL,
    `post2_name` VARCHAR(255) NOT NULL,
    `post3_name` VARCHAR(255) NOT NULL,
    `is_group_range` VARCHAR(255) NOT NULL,
    `create_date` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB;

# 员工部门字典表
CREATE TABLE IF NOT EXISTS `ods_base_department` (
    `id` INTEGER NOT NULL auto_increment,
    `post_id` VARCHAR(255) NOT NULL,
    `post1_id` VARCHAR(255) NOT NULL,
    `post2_id` VARCHAR(255) NOT NULL,
    `post3_id` VARCHAR(255) NOT NULL,
    `post1_name` VARCHAR(255) NOT NULL,
    `post2_name` VARCHAR(255) NOT NULL,
    `post3_name` VARCHAR(255) NOT NULL,
    `is_group_range` VARCHAR(255) NOT NULL,
    `create_date` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB;

# 入职系统
# 入职人员基础信息表
CREATE TABLE IF NOT EXISTS `ods_entrant_base_employee` (
    `id` VARCHAR(255) NOT NULL,
    `job_number` VARCHAR(255) NOT NULL,
    `employee_name` VARCHAR(255) NOT NULL,
    `nick_name` VARCHAR(255) NOT NULL,
    `employee_type` VARCHAR(255) NOT NULL,
    `post_id` VARCHAR(255) NOT NULL,
    `p_level` VARCHAR(255) NOT NULL,
    `m_level` VARCHAR(255) NOT NULL,
    `onboarding_status` VARCHAR(255) NOT NULL,
    `data_source` VARCHAR(255) NOT NULL,
    `freeze_status` VARCHAR(255) NOT NULL,
    `create_time` VARCHAR(255) NOT NULL,
    `update_time` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB;

# 入职人员流程信息表
CREATE TABLE IF NOT EXISTS `ods_entrant_employee_onboarding` (
 `id` INTEGER NOT NULL auto_increment,
 `employee_id` VARCHAR(255) NOT NULL,
 `entry_plan_date` VARCHAR(255) NOT NULL,
 `entry_actuality_date` VARCHAR(255) NOT NULL,
 `entry_cancel_date` VARCHAR(255) NOT NULL,
 `entry_cancel_reason` VARCHAR(255) NOT NULL,
 `entry_refuse_reason` VARCHAR(255) NOT NULL,
 `area_code` VARCHAR(255) NOT NULL,
 `area_name` VARCHAR(255) NOT NULL,
 `onboarding_invitation_notice` VARCHAR(255) NOT NULL,
 `accept_offer_date` VARCHAR(255) NOT NULL,
 `health_checkup_state ` VARCHAR(255) NOT NULL,
 `create_time` VARCHAR(255) NOT NULL,
 `update_time` VARCHAR(255) NOT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB;

# 入职人员合同信息表
CREATE TABLE IF NOT EXISTS `ods_entrant_employee_contract` (
 `id` VARCHAR(255) NOT NULL,
 `employee_id` VARCHAR(255) NOT NULL,
 `contract_start_date ` VARCHAR(255) NOT NULL,
 `contract_end_date ` VARCHAR(255) NOT NULL,
 `contract_period ` VARCHAR(255) NOT NULL,
 `seal_company_id ` VARCHAR(255) NOT NULL,
 `seal_company_name ` VARCHAR(255) NOT NULL,
 `probation_period ` VARCHAR(255) NOT NULL,
 `competition_agreement ` VARCHAR(255) NOT NULL,
 `competition_agreement_mon ` VARCHAR(255) NOT NULL,
 `competition_agreement_amt ` VARCHAR(255) NOT NULL,
 `create_time` VARCHAR(255) NOT NULL,
 `update_time` VARCHAR(255) NOT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB;

# 入职人员相关账户信息表
CREATE TABLE IF NOT EXISTS `ods_entrant_employee_account` (
 `id` VARCHAR(255) NOT NULL,
 `employee_id` VARCHAR(255) NOT NULL,
 `contract_start_date ` VARCHAR(255) NOT NULL,
 `contract_end_date ` VARCHAR(255) NOT NULL,
 `contract_period ` VARCHAR(255) NOT NULL,
 `seal_company_id ` VARCHAR(255) NOT NULL,
 `seal_company_name ` VARCHAR(255) NOT NULL,
 `probation_period ` VARCHAR(255) NOT NULL,
 `competition_agreement ` VARCHAR(255) NOT NULL,
 `competition_agreement_mon ` VARCHAR(255) NOT NULL,
 `competition_agreement_amt ` VARCHAR(255) NOT NULL,
 `create_time` VARCHAR(255) NOT NULL,
 `update_time` VARCHAR(255) NOT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB;

# 入职人员组织架构信息表
CREATE TABLE IF NOT EXISTS `ods_entrant_employee_org` (
 `id` VARCHAR(255) NOT NULL,
 `employee_id` VARCHAR(255) NOT NULL,
 `contract_start_date ` VARCHAR(255) NOT NULL,
 `contract_end_date ` VARCHAR(255) NOT NULL,
 `contract_period ` VARCHAR(255) NOT NULL,
 `seal_company_id ` VARCHAR(255) NOT NULL,
 `seal_company_name ` VARCHAR(255) NOT NULL,
 `probation_period ` VARCHAR(255) NOT NULL,
 `competition_agreement ` VARCHAR(255) NOT NULL,
 `competition_agreement_mon ` VARCHAR(255) NOT NULL,
 `competition_agreement_amt ` VARCHAR(255) NOT NULL,
 `create_time` VARCHAR(255) NOT NULL,
 `update_time` VARCHAR(255) NOT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB;

# 试用期系统
# 试用期员工信息表
  CREATE TABLE IF NOT EXISTS `ods_probation_base_employee` (
 `id` VARCHAR(255) NOT NULL,
 `user_id` VARCHAR(255) NOT NULL,
 `user_name` VARCHAR(255) NOT NULL,
 `dept_id` VARCHAR(255) NOT NULL,
 `entry_date` VARCHAR(255) NOT NULL,
 `regular_worker_date` VARCHAR(255) NOT NULL,
 `probation_period` VARCHAR(255) NOT NULL,
 `leader_id` VARCHAR(255) NOT NULL,
 `leader_name` VARCHAR(255) NOT NULL,
 `state` VARCHAR(255) NOT NULL,
 `create_time` VARCHAR(255) NOT NULL,
 `update_time` VARCHAR(255) NOT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB;

# 试用期阶段性反馈表
CREATE TABLE IF NOT EXISTS `ods_probation_stage_evaluate` (
 `id` VARCHAR(255) NOT NULL,
 `user_id` VARCHAR(255) NOT NULL,
 `user_name` VARCHAR(255) NOT NULL,
 `dept_id` VARCHAR(255) NOT NULL,
 `entry_date` VARCHAR(255) NOT NULL,
 `regular_worker_date` VARCHAR(255) NOT NULL,
 `probation_period` VARCHAR(255) NOT NULL,
 `leader_id` VARCHAR(255) NOT NULL,
 `leader_name` VARCHAR(255) NOT NULL,
 `state` VARCHAR(255) NOT NULL,
 `create_time` VARCHAR(255) NOT NULL,
 `update_time` VARCHAR(255) NOT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB;

# 试用期转正答辩反馈表
CREATE TABLE IF NOT EXISTS `ods_probation_defense_arrange` (
 `id` VARCHAR(255) NOT NULL,
 `probation_user_id` VARCHAR(255) NOT NULL,
 `evaluater_id` VARCHAR(255) NOT NULL,
 `evaluater_name` VARCHAR(255) NOT NULL,
 `evaluater_suggestion` VARCHAR(255) NOT NULL,
 `final_evaluate_time` VARCHAR(255) NOT NULL,
 `create_time` VARCHAR(255) NOT NULL,
 `update_time` VARCHAR(255) NOT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB;

# 员工考勤
# 员工oa信息表
CREATE TABLE IF NOT EXISTS `ods_attend_oa_attend` (
    `id` VARCHAR(255) NOT NULL,
    `oa_id` VARCHAR(255) NOT NULL,
    `emp_id` VARCHAR(255) NOT NULL,
    `current_node` VARCHAR(255) NOT NULL,
    `apply_type` VARCHAR(255) NOT NULL,
    `apply_start_time` VARCHAR(255) NOT NULL,
    `apply_end_time` VARCHAR(255) NOT NULL,
    `apply_total_days` VARCHAR(255) NOT NULL,
    `apply_total_hours` VARCHAR(255) NOT NULL,
    `need_referto` VARCHAR(255) NOT NULL,
    `work_arange_status` VARCHAR(255) NOT NULL,
    `related_doc` VARCHAR(255) NOT NULL,
    `opinion` VARCHAR(255) NOT NULL,
    `create_time` VARCHAR(255) NOT NULL,
    `update_time` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB;

# 员工考勤信息表
CREATE TABLE IF NOT EXISTS `ods_attend_emp_daily` (
    `id` VARCHAR(255) NOT NULL,
    `emp_no` VARCHAR(255) NOT NULL,
    `emp_name` VARCHAR(255) NOT NULL,
    `dept_id` VARCHAR(255) NOT NULL,
    `attend_date` VARCHAR(255) NOT NULL,
    `result` VARCHAR(255) NOT NULL,
    `stdard_hours` VARCHAR(255) NOT NULL,
    `actual_hours` VARCHAR(255) NOT NULL,
    `start_punch` VARCHAR(255) NOT NULL,
    `end_punch` VARCHAR(255) NOT NULL,
    `punch_logs` VARCHAR(255) NOT NULL,
    `late` VARCHAR(255) NOT NULL,
    `early_leave` VARCHAR(255) NOT NULL,
    `leave_oa_id` VARCHAR(255) NOT NULL,
    `overtime_duration` VARCHAR(255) NOT NULL,
    `overtime_start` VARCHAR(255) NOT NULL,
    `overtime_end` VARCHAR(255) NOT NULL,
    `punch_status` VARCHAR(255) NOT NULL,
    `create_time` VARCHAR(255) NOT NULL,
    `update_time` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB;

# 晋升
# 员工晋升信息表
CREATE TABLE IF NOT EXISTS `ods_promote_config_user` (
    `id` VARCHAR(255) NOT NULL,
    `activity_id` VARCHAR(255) NOT NULL,
    `user_id` VARCHAR(255) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `dept_id` VARCHAR(255) NOT NULL,
    `state` VARCHAR(255) NOT NULL,
    `type` VARCHAR(255) NOT NULL,
    `mail` VARCHAR(255) NOT NULL,
    `leader_code` VARCHAR(255) NOT NULL,
    `position_name` VARCHAR(255) NOT NULL,
    `position_name_now` VARCHAR(255) NOT NULL,
    `position_type_code` VARCHAR(255) NOT NULL,
    `position_type_code_now` VARCHAR(255) NOT NULL,
    `pro_level` VARCHAR(255) NOT NULL,
    `pro_level_now` VARCHAR(255) NOT NULL,
    `mng_level` VARCHAR(255) NOT NULL,
    `mng_level_now` VARCHAR(255) NOT NULL,
    `company_age` VARCHAR(255) NOT NULL,
    `talent_inventory` VARCHAR(255) NOT NULL,
    `last_promotion_time_p` VARCHAR(255) NOT NULL,
    `last_promotion_time_m` VARCHAR(255) NOT NULL,
    `last_kpi_period` VARCHAR(255) NOT NULL,
    `last_kpi_result` VARCHAR(255) NOT NULL,
    `recommend_score` VARCHAR(255) NOT NULL,
    `entry_date` VARCHAR(255) NOT NULL,
    `resign_date` VARCHAR(255) NOT NULL,
    `create_time` VARCHAR(255) NOT NULL,
    `update_time` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB;