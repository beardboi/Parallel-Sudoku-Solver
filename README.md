# Parallel Sudoku

This project was developed for **High Performance Computing**. It tries to solved a sudoku board using parallelism and calculating using N threads.
This program has been tested on a AMD Ryzen 1300x (4 cores 3,5GHz - 3,7GHz).

### Calculating the Speedup

|  N threads | Execution Time (ns) | Speedup  | Efficiency  |
|:---:|:---:|:---:|:---:|
| 1  | 18981400  |  1 | 1  |   |
| 2  | 778500  | 24,4  | 12,19  |   
| 3  | 637900 |  29,76 | 9,91  |   
| 4  | 607400  | 31,25  | 7,81  |


### Graphics


![SpeedupVsThreads.png](https://i.ibb.co/R4rxTJh/Speedupvs-Threads.png)



![EffVsThreads](https://i.ibb.co/7KTY8Nr/Effvs-Threads.png)
