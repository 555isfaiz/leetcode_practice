public class AverageSalaryExcludingtheMinimumandMaximumSalary {
    public double average(int[] salary) {
        int sum = 0, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (var s : salary) {
            sum += s;
            if (max < s)
                max = s;
            if (min > s)
                min = s;
        }
        return (double)(sum - max - min) / (salary.length - 2);
    }
}
