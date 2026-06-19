-- https://www.hackerrank.com/challenges/15-days-of-learning-sql/problem?isFullScreen=true

WITH
-- Count submissions per hacker per day
daily_submissions AS (
    SELECT submission_date, hacker_id, COUNT(*) AS sub_count
    FROM Submissions
    GROUP BY submission_date, hacker_id
),

-- For each hacker, count how many days they submitted starting from day 1
-- A hacker is "consistent" on day N if they submitted on ALL days from day 1 to day N
consistent_hackers AS (
    SELECT d1.submission_date, d1.hacker_id
    FROM daily_submissions d1
    WHERE (
        SELECT COUNT(DISTINCT d2.submission_date)
        FROM daily_submissions d2
        WHERE d2.hacker_id = d1.hacker_id
          AND d2.submission_date <= d1.submission_date
    ) = (
        SELECT COUNT(DISTINCT d3.submission_date)
        FROM daily_submissions d3
        WHERE d3.submission_date <= d1.submission_date
    )
),

-- Count unique consistent hackers per day
unique_consistent AS (
    SELECT submission_date, COUNT(DISTINCT hacker_id) AS unique_hackers
    FROM consistent_hackers
    GROUP BY submission_date
),

-- Find hacker with max submissions each day (lowest hacker_id on tie)
max_submitter AS (
    SELECT submission_date, hacker_id
    FROM daily_submissions d1
    WHERE sub_count = (
        SELECT MAX(sub_count)
        FROM daily_submissions d2
        WHERE d2.submission_date = d1.submission_date
    )
    AND hacker_id = (
        SELECT MIN(hacker_id)
        FROM daily_submissions d3
        WHERE d3.submission_date = d1.submission_date
          AND d3.sub_count = (
              SELECT MAX(sub_count)
              FROM daily_submissions d4
              WHERE d4.submission_date = d1.submission_date
          )
    )
)

-- Final output
SELECT uc.submission_date,
       uc.unique_hackers,
       ms.hacker_id,
       h.name
FROM unique_consistent uc
JOIN max_submitter ms ON uc.submission_date = ms.submission_date
JOIN Hackers h ON ms.hacker_id = h.hacker_id
ORDER BY uc.submission_date;
