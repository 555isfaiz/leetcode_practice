-- https://www.hackerrank.com/challenges/weather-observation-station-5/problem?isFullScreen=true

select city, len
from
    (
        select
            row_number() over (order by length(city), city) as rn,
            city,
            length(city) as len
        from station
    ) processed
where rn = 1 or rn = (select count(*) from station);
