-- https://www.hackerrank.com/challenges/draw-the-triangle-1/problem?isFullScreen=true

with recursive
    cte(stars, n) as (
        select cast('*' as char(50)), 1
        union all
        select concat(stars, ' *'), n + 1
        from cte
        where n < 20
    )
select stars
from cte
order by n desc;
