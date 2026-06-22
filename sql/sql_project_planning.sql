-- https://www.hackerrank.com/challenges/sql-projects/problem?isFullScreen=true
with
    left_join as (
        select
            p1.start_date as s1,
            p1.end_date as e1,
            p2.start_date as s2,
            p2.end_date as e2
        from projects p1
        left join projects p2 on p1.end_date = p2.start_date
        order by p1.start_date
    ),
    right_join as (
        select
            p1.start_date as s1,
            p1.end_date as e1,
            p2.start_date as s2,
            p2.end_date as e2
        from projects p1
        left join projects p2 on p1.start_date = p2.end_date
        order by p1.start_date
    ),
    union_projects as (
        select row_number() over (order by s1) as rn, u.s1, u.e1
        from
            (
                select right_join.s1, right_join.e1
                from right_join
                where right_join.s2 is null and right_join.e2 is null
                union all
                select left_join.s1, left_join.e1
                from left_join
                where left_join.s2 is null and left_join.e2 is null
            ) u
        order by u.s1
    ),
    with_days as (
        select
            u1.s1 as s1,
            u1.e1 as e1,
            u2.s1 as s2,
            u2.e1 as e2,
            u2.s1 - u1.s1 + 1 as days
        from union_projects u1, union_projects u2
        where u1.rn % 2 = 1 and u2.rn = u1.rn + 1
    )
select with_days.s1, with_days.e2
from with_days
order by with_days.days, with_days.s1;

-- AI version:
select s.start_date, min(e.end_date)
from
    (
        select start_date
        from projects
        where start_date not in (select end_date from projects)
    ) s
join
    (
        select end_date
        from projects
        where end_date not in (select start_date from projects)
    ) e
    on e.end_date > s.start_date
group by s.start_date
order by datediff(min(e.end_date), s.start_date), s.start_date;
