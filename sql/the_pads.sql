-- https://www.hackerrank.com/challenges/the-pads/problem?isFullScreen=true

select concat(Name, "(", substring(Occupation, 1, 1), ")") as whatever from OCCUPATIONS order by whatever;

with grouped as (select count(*) as c, lower(Occupation) as Occupation from OCCUPATIONS group by Occupation order by c, Occupation)
select concat("There are a total of ", c, " ", Occupation, "s.") from grouped;
