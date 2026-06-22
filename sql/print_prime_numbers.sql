-- https://www.hackerrank.com/challenges/print-prime-numbers/problem?isFullScreen=true

with recursive
numbers as (
    select 2 as n
    union all
    select n + 1 from numbers where n < 1000
),
divisors as (
    select a.n as num
    from numbers a
    left join numbers b on b.n <= a.n / 2 and a.n % b.n = 0
    group by a.n
    having count(b.n) = 0   -- no divisors found = prime
)
select group_concat(num order by num separator '&') as primes
from divisors;
