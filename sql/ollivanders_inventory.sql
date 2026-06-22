-- https://www.hackerrank.com/challenges/harry-potter-and-wands/problem?isFullScreen=true

with min_golds as (select id, row_number() over (partition by code, power order by coins_needed) as rn from Wands)
select min_golds.id, wands_property.age, wands.coins_needed, wands.power
from min_golds
join Wands wands on min_golds.id = wands.id
join Wands_Property wands_property on wands.code = wands_property.code
where wands_property.is_evil = 0 and min_golds.rn = 1
order by wands.power desc, wands_property.age desc;
