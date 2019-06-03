select build_organization_index ('testsrc', 'wqp', 'project_data');
select build_project_identifier_index ('testsrc', 'wqp', 'project_data');

create index if not exists project_data_testsrc_organization on wqp.project_data_testsrc(organization) with (fillfactor = 100);
create index if not exists project_data_testsrc_project_identifier on wqp.project_data_testsrc(project_identifier) with (fillfactor = 100);

create index if not exists project_data_testsrc_organization_old on wqp.project_data_testsrc_old(organization) with (fillfactor = 100);
create index if not exists project_data_testsrc_project_identifier_old on wqp.project_data_testsrc_old(project_identifier) with (fillfactor = 100);
