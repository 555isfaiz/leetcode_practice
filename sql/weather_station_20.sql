-- https://www.hackerrank.com/challenges/weather-observation-station-20/problem?isFullScreen=true

with
    with_rn as (select row_number() over (order by lat_n) as rn, lat_n from station order by lat_n),
    total_count as (select count(*) from station)
select
    case
        when (select * from total_count) % 2 = 0
        then
            round(
                (
                    (
                        select lat_n
                        from with_rn
                        where rn = floor((select * from total_count) / 2)
                    ) + (
                        select lat_n
                        from with_rn
                        where rn = ceil((select * from total_count) / 2)
                    )
                )
                / 2,
                4
            )
        else round(lat_n, 4)
    end
from with_rn
where rn = ceil((select * from total_count) / 2);
