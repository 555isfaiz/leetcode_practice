-- https://www.hackerrank.com/challenges/contest-leaderboard/problem?isFullScreen=true

with
    max_scores as (
        select s.hacker_id as hacker_id, s.challenge_id, max(s.score) as score
        from submissions s
        group by s.hacker_id, s.challenge_id
    )
select hackers.hacker_id, hackers.name, ts.score
from hackers hackers
join
    (
        select ms.hacker_id as hacker_id, sum(ms.score) as score
        from max_scores ms
        group by ms.hacker_id
    ) ts
    on hackers.hacker_id = ts.hacker_id
where ts.score > 0
order by ts.score desc, hackers.hacker_id;
