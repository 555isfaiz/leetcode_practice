-- https://www.hackerrank.com/challenges/the-report/problem?isFullScreen=true
select
    case when grades.grade >= 8 then students.name else null end,
    grades.grade,
    students.marks
from students students
join
    grades grades
    on students.marks >= grades.min_mark
    and students.marks <= grades.max_mark
order by
    grades.grade desc,
    case when grades.grade >= 8 then students.name else students.marks end;
