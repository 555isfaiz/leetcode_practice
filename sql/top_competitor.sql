-- https://www.hackerrank.com/challenges/full-score/problem?isFullScreen=true

with max_scores as (select submissions.hacker_id, count(distinct submissions.challenge_id) as max_count
    from Submissions submissions 
    left join Challenges challenges on challenges.challenge_id = submissions.challenge_id
    left join Difficulty difficulty on challenges.difficulty_level = difficulty.difficulty_level
    where submissions.score = difficulty.score
    group by submissions.hacker_id)
select hackers.hacker_id, hackers.name from Hackers hackers
left join max_scores on hackers.hacker_id = max_scores.hacker_id
where max_scores.max_count > 1
order by max_scores.max_count desc, hackers.hacker_id asc;
