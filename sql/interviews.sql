-- https://www.hackerrank.com/challenges/interviews/problem?isFullScreen=true

SELECT contests.contest_id, contests.hacker_id, contests.name, sum(ts) as sts, sum(tas) as stas, sum(tv) as stv, sum(tuv) as stuv
FROM
Contests contests join Colleges colleges on contests.contest_id = colleges.contest_id
join Challenges challenges on colleges.college_id = challenges.college_id
left join
(
    SELECT challenges.challenge_id, 
           SUM(view_stats.total_views) AS tv, 
           SUM(view_stats.total_unique_views) AS tuv 
    FROM Challenges challenges 
    JOIN View_Stats view_stats ON view_stats.challenge_id = challenges.challenge_id
    GROUP BY challenges.challenge_id
) a on a.challenge_id = challenges.challenge_id
left JOIN (
    SELECT challenges.challenge_id, 
           SUM(submission_stats.total_submissions) AS ts, 
           SUM(submission_stats.total_accepted_submissions) AS tas 
    FROM Challenges challenges 
    JOIN Submission_Stats submission_stats ON submission_stats.challenge_id = challenges.challenge_id
    GROUP BY challenges.challenge_id
) b ON b.challenge_id = challenges.challenge_id
group by contests.contest_id, contests.hacker_id, contests.name
HAVING sts > 0 AND stas > 0 AND stv > 0 AND stuv > 0
ORDER BY contests.contest_id;
