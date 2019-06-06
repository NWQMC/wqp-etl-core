/* Drop and create the swap table so we are sure to have a clean verions (no indexes constraints, etc. */
drop table if exists wqp.project_data_swap_testsrc cascade;
select create_swap_table ('testsrc', 'wqp', 'project_data');

/* Setup a different partition to show we don't touch it. */
create table if not exists wqp.project_data_othersrc partition of wqp.project_data for values in (999);
create index if not exists project_data_othersrc_organization on wqp.project_data_othersrc(organization) with (fillfactor = 100);
create index if not exists project_data_othersrc_project_identifier on wqp.project_data_othersrc(project_identifier) with (fillfactor = 100);

/* We need to create the real partition and old tables here in order to populate them with dbunit. */
create table if not exists wqp.project_data_testsrc partition of wqp.project_data for values in (0);
create table if not exists wqp.project_data_testsrc_old (like wqp.project_data_testsrc);
