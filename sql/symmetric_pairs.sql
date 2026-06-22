-- https://www.hackerrank.com/challenges/symmetric-pairs/problem?isFullScreen=true
with rn_assigned as (select row_number() over (order by x) as rn, x, y from functions)
select distinct r1.x, r1.y
from rn_assigned r1
where
    r1.y in (select r2.x from rn_assigned r2 where r2.y = r1.x and r1.rn != r2.rn)
    and r1.x <= r1.y
order by r1.x;
