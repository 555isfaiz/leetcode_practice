-- https://www.hackerrank.com/challenges/the-report/problem?isFullScreen=true

select case
        when grades.grade >= 8 then students.name
        else null
    end, grades.grade, students.marks
from Students students
join Grades grades on students.marks >= grades.Min_Mark and students.marks <= grades.Max_Mark
order by grades.grade desc, case
        when grades.grade >= 8 then students.name
        else students.marks
    end;
