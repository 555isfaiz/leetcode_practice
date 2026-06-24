-- https://www.hackerrank.com/challenges/placements/problem?isFullScreen=true

with
    joined as (
        select s.ID, s.Name, p.Salary from Students s join Packages p on s.ID = p.ID
    ),
    joined_two as (
        select j1.ID, j1.Name, j1.Salary, max(j2.Salary) as Friend_salary
        from joined j1
        join
            (
                select f.ID, p.Salary
                from Friends f
                join Packages p on f.Friend_ID = p.ID
            ) j2
            on j1.ID = j2.ID
        group by j1.ID, j1.Name, j1.Salary
    )
select j2.name from joined_two j2 where j2.Friend_salary > j2.Salary order by Friend_salary;
