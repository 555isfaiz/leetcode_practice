-- https://www.hackerrank.com/challenges/the-company/problem?isFullScreen=true

with total_lead_managers as (select count(distinct lead_manager_code) as total_lead_manager, company_code from Lead_Manager group by company_code),
total_senior_managers as (select count(distinct senior_manager_code) as total_senior_manager, company_code from Senior_Manager group by company_code),
total_managers as (select count(distinct manager_code) as total_manager, company_code from Manager group by company_code),
total_employees as (select count(distinct employee_code) as total_employee, company_code from Employee group by company_code)
select 
    distinct company.company_code, 
    company.founder, 
    tlm.total_lead_manager,
    tsm.total_senior_manager,
    tm.total_manager,
    te.total_employee
from Company company 
join total_lead_managers tlm on tlm.company_code = company.company_code
join total_senior_managers tsm on tsm.company_code = company.company_code
join total_managers tm on tm.company_code = company.company_code
join total_employees te on te.company_code = company.company_code
order by company.company_code;
