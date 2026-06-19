-- https://www.hackerrank.com/challenges/binary-search-tree-1/problem?isFullScreen=true

select N,
    case 
        when P is Null then "Root"
        when N not in (select distinct P from BST where P is not null) then "Leaf"
        else "Inner"
    end
as type
from BST order by N;
