-- https://www.hackerrank.com/challenges/challenges/problem?isFullScreen=true

with total_challenges as (select hacker_id, count(distinct challenge_id) as total_challenge_num from Challenges group by hacker_id),
counted as (select hacker_id, total_challenge_num, count(total_challenge_num) over (partition by total_challenge_num) as apperence from total_challenges)
select hackers.hacker_id, hackers.name, counted.total_challenge_num
from Hackers hackers
join counted on hackers.hacker_id = counted.hacker_id
where counted.apperence = 1 or counted.total_challenge_num = (select max(total_challenges.total_challenge_num) from total_challenges)
order by counted.total_challenge_num desc, hackers.hacker_id;
