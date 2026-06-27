# NumPy and Pandas Interview Questions & Answers

## Table of Contents
1. [NumPy Fundamentals](#numpy-fundamentals)
2. [NumPy Advanced](#numpy-advanced)
3. [Pandas Fundamentals](#pandas-fundamentals)
4. [Pandas Advanced](#pandas-advanced)
5. [Performance & Optimization](#performance--optimization)
6. [Real-World Scenarios](#real-world-scenarios)

---

## NumPy Fundamentals

### Q1: What is NumPy and why is it important?
**Answer:**
NumPy (Numerical Python) is a fundamental package for numerical computing in Python. It provides:
- **N-dimensional arrays** (ndarrays) - efficient homogeneous data structures
- **Mathematical functions** for array operations
- **Linear algebra operations**
- **Random number generation**
- **Fourier transforms**

**Why important:**
- Much faster than native Python lists (vectorized operations)
- More memory efficient
- Foundation for pandas, scipy, scikit-learn
- Implemented in C for performance

```python
import numpy as np
# NumPy operations are ~100x faster than Python loops for large arrays
arr = np.array([1, 2, 3, 4, 5])
result = arr * 2  # Vectorized operation (fast)
```

---

### Q2: Explain the difference between a Python list and a NumPy array.
**Answer:**

| Aspect | Python List | NumPy Array |
|--------|------------|------------|
| Type | Heterogeneous (mixed types) | Homogeneous (single type) |
| Speed | Slow | 10-100x faster |
| Memory | Higher usage | Lower usage |
| Functionality | Basic | Rich (math, linear algebra) |
| Dimensions | 1D primarily | N-dimensional |
| Broadcasting | Not supported | Supported |

```python
import numpy as np

# Python list
py_list = [1, 2, 3, 4, 5]
py_list[0] = "string"  # Can mix types

# NumPy array
np_arr = np.array([1, 2, 3, 4, 5])
np_arr.dtype  # dtype('int64')
np_arr[0] = 1.5  # Will convert to int: 1

# Speed comparison
import time
size = 1_000_000

# Python list
start = time.time()
result = [x*2 for x in range(size)]
print(f"Python list: {time.time() - start:.4f}s")  # ~0.1s

# NumPy
start = time.time()
arr = np.arange(size)
result = arr * 2
print(f"NumPy: {time.time() - start:.4f}s")  # ~0.001s
```

---

### Q3: What are different ways to create NumPy arrays?
**Answer:**

```python
import numpy as np

# 1. From Python list
arr1 = np.array([1, 2, 3, 4, 5])

# 2. Using creation functions
arr2 = np.zeros((3, 4))           # All zeros
arr3 = np.ones((2, 3))            # All ones
arr4 = np.full((2, 3), 5)         # Fill with value
arr5 = np.arange(0, 10, 2)        # Range: 0 to 10, step 2
arr6 = np.linspace(0, 10, 5)      # 5 equally spaced values

# 3. Random arrays
arr7 = np.random.rand(3, 4)       # Uniform [0, 1)
arr8 = np.random.randn(3, 4)      # Normal distribution
arr9 = np.random.randint(0, 10, (3, 4))  # Random integers

# 4. Special matrices
arr10 = np.eye(3)                 # Identity matrix
arr11 = np.diag([1, 2, 3])        # Diagonal matrix

# 5. From file
arr12 = np.loadtxt('file.txt')
arr13 = np.load('array.npy')
```

---

### Q4: What is broadcasting and give an example?
**Answer:**
Broadcasting allows NumPy to perform operations on arrays of different shapes. Dimensions are compatible if:
- They are equal, or
- One of them is 1

```python
import numpy as np

# Example 1: Scalar with array
arr = np.array([[1, 2, 3], [4, 5, 6]])  # Shape (2, 3)
result = arr + 10  # Scalar broadcasts to (2, 3)

# Example 2: 1D and 2D arrays
arr1 = np.array([[1, 2, 3], [4, 5, 6]])  # Shape (2, 3)
arr2 = np.array([10, 20, 30])             # Shape (3,)
result = arr1 + arr2  # arr2 broadcasts to (2, 3)
# Result:
# [[11 22 33]
#  [14 25 36]]

# Example 3: Column and row
arr_col = np.array([[1], [2], [3]])       # Shape (3, 1)
arr_row = np.array([[10, 20, 30]])        # Shape (1, 3)
result = arr_col + arr_row                # Broadcasts to (3, 3)
# Result:
# [[11 21 31]
#  [12 22 32]
#  [13 23 33]]

# Broadcasting rules:
# Starting from rightmost dimension, shapes must be compatible
# 1. (3,) broadcasts with (2, 3) ✓
# 2. (5, 4, 3) broadcasts with (4, 3) ✓
# 3. (5, 4, 3) does NOT broadcast with (5, 3) ✗
```

---

### Q5: Explain array indexing and slicing.
**Answer:**

```python
import numpy as np

arr = np.array([0, 1, 2, 3, 4, 5, 6, 7, 8, 9])

# Indexing
print(arr[0])      # 0
print(arr[-1])     # 9 (last element)
print(arr[-2])     # 8

# Slicing
print(arr[2:5])    # [2, 3, 4] (start:stop - not including stop)
print(arr[::2])    # [0, 2, 4, 6, 8] (step)
print(arr[::-1])   # [9, 8, 7, 6, 5, 4, 3, 2, 1, 0] (reverse)

# 2D indexing
arr2d = np.array([[1, 2, 3], [4, 5, 6], [7, 8, 9]])

print(arr2d[0])       # [1, 2, 3] (first row)
print(arr2d[0, 1])    # 2 (row 0, column 1)
print(arr2d[:, 1])    # [2, 5, 8] (all rows, column 1)
print(arr2d[1:, :2])  # [[4, 5], [7, 8]] (rows 1+, cols 0-1)

# Boolean indexing
arr = np.array([1, 2, 3, 4, 5, 6, 7, 8, 9, 10])
mask = arr > 5
print(arr[mask])  # [6, 7, 8, 9, 10]

# Fancy indexing
indices = np.array([0, 2, 4])
print(arr[indices])  # [1, 3, 5]

# Note: NumPy arrays do NOT return copies when slicing; they're views
arr = np.array([1, 2, 3, 4, 5])
slice_view = arr[1:3]
slice_view[0] = 999
print(arr)  # [1, 999, 3, 4, 5] - original modified!
```

---

## NumPy Advanced

### Q6: What is the difference between `.copy()` and `.view()` in NumPy?
**Answer:**

```python
import numpy as np

arr = np.array([1, 2, 3, 4, 5])

# View (shallow copy)
view = arr.view()
view[0] = 999
print(arr)  # [999, 2, 3, 4, 5] - original modified
print(view.base is arr)  # True - view has reference to original

# Copy (deep copy)
copy = arr.copy()
copy[0] = 999
print(arr)  # [1, 2, 3, 4, 5] - original unchanged
print(copy.base is arr)  # False - independent

# Use cases:
# - Use .view() when you want memory efficiency
# - Use .copy() when you need independence from original
```

---

### Q7: Explain reshaping, flattening, and transposing arrays.
**Answer:**

```python
import numpy as np

arr = np.arange(12)  # [0, 1, 2, ..., 11]

# Reshaping
reshaped = arr.reshape(3, 4)
# [[0, 1, 2, 3]
#  [4, 5, 6, 7]
#  [8, 9, 10, 11]]

reshaped = arr.reshape(2, 2, 3)  # 3D array

# Flattening (returns view, not copy)
flat = reshaped.flatten()  # [0, 1, 2, ..., 11]

# Ravel (similar but returns view)
raveled = reshaped.ravel()  # [0, 1, 2, ..., 11]

# Transposing
arr2d = np.array([[1, 2, 3], [4, 5, 6]])
transposed = arr2d.T
# [[1, 4]
#  [2, 5]
#  [3, 6]]

# Difference: flatten returns copy, ravel returns view
flat[0] = 999
print(reshaped)  # Original unchanged (flatten made a copy)

raveled[0] = 999
print(reshaped)  # Original changed (ravel is a view)
```

---

### Q8: What are universal functions (ufuncs) and give examples?
**Answer:**
Universal functions operate element-wise on arrays and support broadcasting. They're implemented in C for performance.

```python
import numpy as np

arr = np.array([1, 2, 3, 4, 5])

# Mathematical ufuncs
np.sqrt(arr)       # [1, √2, √3, √4, √5]
np.exp(arr)        # e^1, e^2, e^3, ...
np.log(arr)        # log(1), log(2), log(3), ...
np.sin(arr)        # sin(1), sin(2), ...

# Trigonometric
np.cos(arr)
np.tan(arr)

# Rounding
np.ceil(arr + 0.5)
np.floor(arr + 0.5)
np.round(arr + 0.5)

# Comparison ufuncs
np.greater(arr, 3)       # [False, False, True, True, True]
np.less_equal(arr, 3)    # [True, True, True, False, False]
np.equal(arr, 2)         # [False, True, False, False, False]

# Logical
arr1 = np.array([True, False, True])
arr2 = np.array([True, True, False])
np.logical_and(arr1, arr2)  # [True, False, False]
np.logical_or(arr1, arr2)   # [True, True, True]

# Arithmetic
np.add(arr, 10)
np.multiply(arr, 2)
np.power(arr, 2)

# Properties of ufuncs
add_ufunc = np.add
print(add_ufunc.nin)   # 2 (number of inputs)
print(add_ufunc.nout)  # 1 (number of outputs)
```

---

### Q9: How to aggregate data using NumPy (sum, mean, min, max)?
**Answer:**

```python
import numpy as np

arr = np.array([[1, 2, 3], [4, 5, 6], [7, 8, 9]])

# Aggregation functions
np.sum(arr)          # 45 (sum of all elements)
np.mean(arr)         # 5.0
np.std(arr)          # Standard deviation
np.var(arr)          # Variance
np.min(arr)          # 1
np.max(arr)          # 9
np.prod(arr)         # Product of all elements

# Aggregation along axis
np.sum(arr, axis=0)  # [12, 15, 18] (sum by column)
np.sum(arr, axis=1)  # [6, 15, 24] (sum by row)

# Cumulative operations
np.cumsum(arr)       # [1, 3, 6, 10, 15, 21, 28, 36, 45]
np.cumprod(arr)      # [1, 2, 6, 24, 120, 720, ...]

# Argmax/Argmin (indices of max/min)
np.argmax(arr)       # 8 (flat index)
np.argmin(arr)       # 0
np.argmax(arr, axis=0)  # [2, 2, 2] (column with max)
np.argmax(arr, axis=1)  # [2, 2, 2] (column index of max in each row)

# Percentiles
np.percentile(arr, 25)  # 25th percentile
np.percentile(arr, 75)  # 75th percentile

# Performance: axis aggregation is usually faster than Python loops
```

---

### Q10: Explain matrix operations and linear algebra in NumPy.
**Answer:**

```python
import numpy as np
from numpy.linalg import norm, inv, det, eig, solve

# Matrix multiplication
A = np.array([[1, 2], [3, 4]])
B = np.array([[5, 6], [7, 8]])

# Method 1: @ operator (recommended)
result = A @ B

# Method 2: dot product
result = np.dot(A, B)

# Method 3: matmul
result = np.matmul(A, B)

# Element-wise multiplication
result = A * B

# Transpose
A_T = A.T

# Determinant
det_A = np.linalg.det(A)

# Inverse
A_inv = np.linalg.inv(A)
print(A @ A_inv)  # Should be identity matrix

# Eigenvalues and eigenvectors
eigenvalues, eigenvectors = np.linalg.eig(A)

# Solving linear equations: Ax = b
A = np.array([[3, 1], [1, 2]])
b = np.array([9, 8])
x = np.linalg.solve(A, b)  # x = [2, 3]

# Norms
v = np.array([3, 4])
np.linalg.norm(v)  # 5.0 (L2 norm)
np.linalg.norm(v, ord=1)  # 7.0 (L1 norm)

# SVD (Singular Value Decomposition)
U, S, Vt = np.linalg.svd(A)

# Rank and trace
np.linalg.matrix_rank(A)
np.trace(A)  # Sum of diagonal elements
```

---

## Pandas Fundamentals

### Q11: What are the main data structures in Pandas?
**Answer:**

```python
import pandas as pd
import numpy as np

# 1. Series - 1D labeled array
s = pd.Series([1, 2, 3, 4, 5], index=['a', 'b', 'c', 'd', 'e'])
# a    1
# b    2
# c    3
# d    4
# e    5

# 2. DataFrame - 2D labeled table
df = pd.DataFrame({
    'name': ['Alice', 'Bob', 'Charlie'],
    'age': [25, 30, 35],
    'city': ['NYC', 'LA', 'Chicago']
})
#      name  age     city
# 0   Alice   25      NYC
# 1     Bob   30       LA
# 2 Charlie   35  Chicago

# 3. Index - row labels
print(df.index)  # RangeIndex(start=0, stop=3, step=1)

# 4. Columns - column names
print(df.columns)  # Index(['name', 'age', 'city'], dtype='object')

# Differences between Series and DataFrame:
# - Series: 1D, can be treated like dict or array
# - DataFrame: 2D, multiple dtypes per column, but uniform within column
```

---

### Q12: How do you access data in a DataFrame?
**Answer:**

```python
import pandas as pd

df = pd.DataFrame({
    'name': ['Alice', 'Bob', 'Charlie'],
    'age': [25, 30, 35],
    'salary': [50000, 60000, 75000]
})

# Column access
df['name']          # Series
df[['name', 'age']] # DataFrame

# Row access
df.iloc[0]          # First row by position
df.loc[0]           # First row by label
df.iloc[0:2]        # First 2 rows

# Element access
df.loc[0, 'name']   # 'Alice' - by label
df.iloc[0, 0]       # 'Alice' - by position
df.at[0, 'name']    # 'Alice' - fast scalar access
df.iat[0, 0]        # 'Alice' - fast scalar access by position

# Boolean indexing
df[df['age'] > 25]
df[df['salary'] >= 60000]

# Multiple conditions
df[(df['age'] > 25) & (df['salary'] > 55000)]
df[(df['age'] > 25) | (df['salary'] > 70000)]

# Query method
df.query('age > 25 and salary > 55000')

# Filter by index
df.filter(like='name')  # Columns containing 'name'
df.filter(regex='^n')   # Columns starting with 'n'

# Important differences:
# - .loc uses labels (index names)
# - .iloc uses integer positions
# - .at and .iat are faster for single scalar access
```

---

### Q13: Explain different join/merge operations in Pandas.
**Answer:**

```python
import pandas as pd

# Create sample DataFrames
df1 = pd.DataFrame({
    'key': ['A', 'B', 'C'],
    'val1': [1, 2, 3]
})

df2 = pd.DataFrame({
    'key': ['A', 'B', 'D'],
    'val2': [10, 20, 40]
})

# MERGE operations
# 1. Inner Join (only matching keys)
result = pd.merge(df1, df2, on='key', how='inner')
# Only 'A' and 'B' remain

# 2. Left Join (all from left, matching from right)
result = pd.merge(df1, df2, on='key', how='left')
# All rows from df1 ('A', 'B', 'C'), NaN for 'C' in val2

# 3. Right Join (all from right, matching from left)
result = pd.merge(df1, df2, on='key', how='right')
# All rows from df2 ('A', 'B', 'D'), NaN for 'D' in val1

# 4. Outer Join (all keys from both)
result = pd.merge(df1, df2, on='key', how='outer')
# 'A', 'B', 'C', 'D' with NaN where applicable

# Merge on different column names
df1_renamed = df1.rename(columns={'key': 'id'})
result = pd.merge(df1_renamed, df2, left_on='id', right_on='key')

# Merge on multiple columns
result = pd.merge(df1, df2, on=['key', 'another_col'])

# JOIN (Index-based merge)
df1_idx = df1.set_index('key')
df2_idx = df2.set_index('key')
result = df1_idx.join(df2_idx)  # Inner join by default

# CONCAT (Stack DataFrames)
result = pd.concat([df1, df2])  # Stack vertically
result = pd.concat([df1, df2], axis=1)  # Stack horizontally

# Important: Merge uses memory proportional to both DataFrames
# For large datasets, use joins or concat cautiously
```

---

### Q14: How do you handle missing values in Pandas?
**Answer:**

```python
import pandas as pd
import numpy as np

df = pd.DataFrame({
    'A': [1, 2, np.nan, 4],
    'B': [5, np.nan, np.nan, 8],
    'C': [9, 10, 11, 12]
})

# Detect missing values
df.isna()           # DataFrame of booleans
df.isnull()         # Alias for isna()
df.notna()          # Opposite of isna()
df.notnull()        # Alias for notna()

# Count missing values
df.isna().sum()     # Count per column
df.isna().sum().sum()  # Total missing

# Rows with missing values
df[df.isna().any(axis=1)]

# Drop missing values
df.dropna()         # Remove rows with ANY missing
df.dropna(how='all')  # Remove rows with ALL missing
df.dropna(subset=['A'])  # Remove based on specific column
df.dropna(thresh=2)  # Keep rows with at least 2 non-null values
df.dropna(axis=1)   # Drop columns with ANY missing

# Fill missing values
df.fillna(0)        # Fill with constant value
df.fillna(df.mean())  # Fill with column mean
df.fillna(method='ffill')  # Forward fill
df.fillna(method='bfill')  # Backward fill

# Interpolation
df.interpolate()    # Linear interpolation
df.interpolate(method='polynomial', order=2)

# Replace
df.replace(np.nan, 0)
df.replace([np.nan, None], 0)

# Performance tip: For large datasets, consider:
# 1. Using .notna().sum() instead of .isna().sum()
# 2. Using .dropna() early in pipeline (reduces memory)
# 3. Using fillna over interpolate when possible (faster)
```

---

### Q15: Explain groupby operations in Pandas.
**Answer:**

```python
import pandas as pd

df = pd.DataFrame({
    'department': ['Sales', 'Sales', 'IT', 'IT', 'HR'],
    'name': ['Alice', 'Bob', 'Charlie', 'David', 'Eve'],
    'salary': [50000, 60000, 70000, 65000, 55000]
})

# Basic groupby
grouped = df.groupby('department')

# Apply aggregation functions
grouped['salary'].sum()      # Total salary per department
grouped['salary'].mean()     # Average salary
grouped['salary'].count()    # Number of employees
grouped['salary'].agg(['sum', 'mean', 'count'])  # Multiple functions

# Custom aggregation
grouped['salary'].agg(
    total=('salary', 'sum'),
    average=('salary', 'mean'),
    count=('salary', 'count')
)

# Multiple columns
df.groupby(['department', 'name'])['salary'].sum()

# Apply custom function
def salary_range(x):
    return x.max() - x.min()

grouped['salary'].apply(salary_range)

# Transform (returns same shape as input)
df['salary_deviation'] = df.groupby('department')['salary'].transform(
    lambda x: x - x.mean()
)

# Filter (remove groups by condition)
df.groupby('department').filter(lambda x: x['salary'].mean() > 60000)

# Accessing group
for name, group in grouped:
    print(f"{name}:\n{group}")

# Get group
grouped.get_group('Sales')

# Multiple aggregations
grouped.agg({
    'salary': ['sum', 'mean', 'std']
})

# Performance tips:
# - Use .agg() over multiple .sum(), .mean() calls
# - Use categorical dtype for groupby column if repeating
# - Use named aggregation (my_col=('col', 'func')) for clarity
```

---

## Pandas Advanced

### Q16: What is MultiIndex in Pandas and how do you use it?
**Answer:**

```python
import pandas as pd
import numpy as np

# Creating MultiIndex
arrays = [
    ['bar', 'bar', 'baz', 'baz', 'foo', 'foo', 'qux', 'qux'],
    ['one', 'two', 'one', 'two', 'one', 'two', 'one', 'two']
]
index = pd.MultiIndex.from_arrays(arrays, names=['first', 'second'])

df = pd.DataFrame(
    np.random.randn(8, 2),
    index=index,
    columns=['A', 'B']
)

# Accessing MultiIndex data
df.loc['bar']           # All rows where first='bar'
df.loc['bar', 'one']    # Row where first='bar', second='one'
df.loc['bar':'foo']     # Partial slicing

# MultiIndex from tuples
tuples = [('a', 1), ('a', 2), ('b', 1), ('b', 2)]
index = pd.MultiIndex.from_tuples(tuples)

# MultiIndex from product (cartesian product)
index = pd.MultiIndex.from_product([['a', 'b'], [1, 2]])

# Stack and unstack
df_stacked = df.stack()     # Move column to index level
df_unstacked = df_stacked.unstack()  # Move index level to columns

# Swaplevel
df_swapped = df.swaplevel()

# Resetting MultiIndex
df_reset = df.reset_index()

# Setting MultiIndex
df.set_index(['col1', 'col2'])

# Use cases:
# - Time series with multiple indices (date, ticker, exchange)
# - Pivot tables with multiple dimensions
# - Hierarchical data organization
```

---

### Q17: What are pivot tables and how do you create them?
**Answer:**

```python
import pandas as pd
import numpy as np

# Sample data
df = pd.DataFrame({
    'date': pd.date_range('2023-01-01', periods=12),
    'product': ['A', 'B', 'A', 'B'] * 3,
    'region': ['East', 'West', 'East', 'West'] * 3,
    'sales': np.random.randint(100, 500, 12)
})

# Basic pivot table
pivot = df.pivot_table(
    values='sales',           # Column to aggregate
    index='product',          # Row labels
    columns='region',         # Column labels
    aggfunc='sum'             # How to aggregate (default: mean)
)

# Multiple aggregation functions
pivot = df.pivot_table(
    values='sales',
    index='product',
    columns='region',
    aggfunc=['sum', 'mean', 'count']
)

# Multiple value columns
pivot = df.pivot_table(
    values=['sales', 'quantity'],
    index='product',
    columns='region',
    aggfunc='sum'
)

# Multiple indices
pivot = df.pivot_table(
    values='sales',
    index=['product', 'date'],
    columns='region',
    aggfunc='sum'
)

# Margins (totals)
pivot = df.pivot_table(
    values='sales',
    index='product',
    columns='region',
    aggfunc='sum',
    margins=True  # Adds totals
)

# Fill missing values
pivot = df.pivot_table(
    values='sales',
    index='product',
    columns='region',
    aggfunc='sum',
    fill_value=0
)

# Performance: pivot_table is optimized for groupby + reshape
# For large datasets, consider:
# - Using groupby() + unstack() instead
# - Using sparse DataFrames
# - Aggregating before pivoting
```

---

### Q18: How do you work with time series data in Pandas?
**Answer:**

```python
import pandas as pd
import numpy as np

# Create time series
ts = pd.Series(
    np.random.randn(100),
    index=pd.date_range('2023-01-01', periods=100, freq='D')
)

# DateTime index properties
ts.index.year
ts.index.month
ts.index.day
ts.index.day_name()

# Slicing by date
ts['2023-01']           # All January data
ts['2023-01-01':'2023-01-10']  # Date range

# Resampling (frequency conversion)
ts.resample('W').mean()   # Weekly average
ts.resample('M').sum()    # Monthly sum
ts.resample('D').ffill()  # Fill missing daily values

# Common frequencies: D=daily, H=hourly, M=month, Q=quarter, Y=year

# Rolling windows
rolling_mean = ts.rolling(window=7).mean()  # 7-day moving average
rolling_std = ts.rolling(window=7).std()

# Expanding windows
expanding = ts.expanding().mean()  # Cumulative mean

# Shifting (lag/lead)
ts.shift(1)             # Lead by 1 period
ts.shift(-1)            # Lag by 1 period
ts.diff()               # Difference from previous

# Percentage change
ts.pct_change()

# Period operations
df = pd.DataFrame({
    'value': np.random.randn(100)
}, index=pd.date_range('2023-01-01', periods=100))

# Convert to period
df.index = df.index.to_period('M')

# Timezone handling
ts_utc = ts.tz_localize('UTC')
ts_est = ts_utc.tz_convert('US/Eastern')

# Performance optimization:
# - Use DatetimeIndex for fast time-based lookups
# - Resample early in pipeline if reducing data
# - Use .at[] instead of .loc[] for single values
```

---

### Q19: Explain apply, map, and applymap functions.
**Answer:**

```python
import pandas as pd
import numpy as np

df = pd.DataFrame({
    'A': [1, 2, 3],
    'B': [4, 5, 6],
    'C': ['x', 'y', 'z']
})

# APPLY - Apply function to Series or DataFrame
# Row-wise
df.apply(lambda row: row['A'] + row['B'], axis=1)

# Column-wise
df.apply(lambda col: col.sum(), axis=0)
df.apply(np.sum)  # Built-in functions work too

# APPLYMAP (elementwise) - now called map() in pandas >1.3
# Element-wise operation
df['A'].apply(lambda x: x * 2)
df['A'].map({1: 'one', 2: 'two', 3: 'three'})  # Mapping dict

# DataFrame element-wise (applymap in old versions)
df.applymap(lambda x: f"[{x}]")  # or df.map()

# MAP - for Series
series = pd.Series(['cat', 'dog', 'cat', 'bird'])
series.map({'cat': 'feline', 'dog': 'canine'})

# MAP with function
df['A'].map(lambda x: 'odd' if x % 2 else 'even')

# TRANSFORM - returns same shape as input
df['A'].transform(lambda x: (x - x.mean()) / x.std())  # Standardize

# Apply with multiple operations
def custom_func(x):
    return pd.Series({
        'sum': x.sum(),
        'mean': x.mean(),
        'std': x.std()
    })

df.apply(custom_func)

# Performance considerations:
# - apply() is slower than vectorized operations
# - Use .str accessor for string operations (faster)
# - Use numpy/pandas functions instead of apply when possible
# - For simple operations, list comprehensions can be faster than apply

# String operations (fast)
s = pd.Series(['apple', 'banana', 'cherry'])
s.str.upper()
s.str.len()
s.str.contains('a')
s.str.split(',')
```

---

### Q20: How do you perform data cleaning and preprocessing in Pandas?
**Answer:**

```python
import pandas as pd
import numpy as np

# Sample dirty data
df = pd.DataFrame({
    'name': ['Alice', 'bob', '  Charlie  ', 'Alice', None],
    'age': [25, 30, '35', 25, 28],
    'salary': [50000, 60000, np.nan, 55000, 65000],
    'date': ['2023-01-01', '2023/01/02', '2023-01-03', '2023-01-04', '2023-01-05']
})

# 1. Remove duplicates
df = df.drop_duplicates()
df = df.drop_duplicates(subset=['name'])
df = df.drop_duplicates(keep='first')  # Keep first occurrence

# 2. Handle missing values
df = df.dropna()
df['salary'] = df['salary'].fillna(df['salary'].mean())

# 3. Fix data types
df['age'] = pd.to_numeric(df['age'], errors='coerce')  # Convert to numeric
df['date'] = pd.to_datetime(df['date'], errors='coerce')  # Convert to datetime

# 4. String cleaning
df['name'] = df['name'].str.strip()  # Remove whitespace
df['name'] = df['name'].str.lower()  # Lowercase
df['name'] = df['name'].str.capitalize()  # Capitalize

# 5. Outlier detection
Q1 = df['salary'].quantile(0.25)
Q3 = df['salary'].quantile(0.75)
IQR = Q3 - Q1
outliers = df[(df['salary'] < Q1 - 1.5*IQR) | (df['salary'] > Q3 + 1.5*IQR)]

# 6. Normalization/Standardization
df['salary_normalized'] = (df['salary'] - df['salary'].min()) / (df['salary'].max() - df['salary'].min())
df['salary_standardized'] = (df['salary'] - df['salary'].mean()) / df['salary'].std()

# 7. Create derived features
df['senior'] = df['age'] > 30

# 8. Encoding categorical variables
df['department_encoded'] = pd.factorize(df['department'])[0]  # Label encoding
df_dummies = pd.get_dummies(df['department'])  # One-hot encoding

# 9. Validate data
assert df['age'].dtype in ['int64', 'float64']
assert df['age'].min() > 0
assert df['salary'].notna().all()

# 10. Data profiling
df.info()           # Data types and missing values
df.describe()       # Summary statistics
df.dtypes
df.shape
```

---

## Performance & Optimization

### Q21: How can you optimize Pandas performance?
**Answer:**

```python
import pandas as pd
import numpy as np

# 1. Use appropriate data types
df = pd.DataFrame({
    'category': pd.Categorical(['A', 'B', 'A', 'B']),  # Instead of object
    'value': np.int32([1, 2, 3, 4])  # int32 instead of int64 if possible
})

# 2. Use chunking for large files
for chunk in pd.read_csv('large_file.csv', chunksize=10000):
    # Process each chunk
    pass

# 3. Vectorization instead of loops
# Bad
result = []
for val in df['column']:
    result.append(val * 2)

# Good
result = df['column'] * 2

# 4. Use built-in functions instead of apply
# Bad
df['squared'] = df['value'].apply(lambda x: x**2)

# Good
df['squared'] = df['value'] ** 2

# 5. Query method for filtering
# Less efficient
result = df[(df['A'] > 5) & (df['B'] < 10)]

# More efficient
result = df.query('A > 5 and B < 10')

# 6. Use category dtype for repetitive data
df['category'] = df['category'].astype('category')

# 7. Use inplace=True cautiously (saves memory)
df.drop('column', axis=1, inplace=True)

# 8. Avoid chained indexing
# Bad
df[df['A'] > 5]['B'] = 10  # May trigger SettingWithCopyWarning

# Good
mask = df['A'] > 5
df.loc[mask, 'B'] = 10

# 9. Use .at[] or .iat[] for scalar access
# Slower
value = df.loc[100, 'column']

# Faster
value = df.at[100, 'column']

# 10. Read only needed columns
df = pd.read_csv('file.csv', usecols=['col1', 'col2', 'col3'])

# 11. Use memory_usage() to check memory consumption
df.memory_usage(deep=True)

# 12. Use sparse arrays for data with many zeros
from pandas.arrays import SparseArray
arr = SparseArray([1, 0, 0, 2, 0, 0, 3])
```

---

### Q22: Compare performance between Pandas, NumPy, and Polars for large datasets.
**Answer:**

```python
import pandas as pd
import numpy as np
import time

# Create large dataset
n = 10_000_000
data = {
    'A': np.random.randn(n),
    'B': np.random.randint(0, 100, n),
    'C': np.random.choice(['cat', 'dog', 'bird'], n)
}

# NumPy approach
print("NumPy operation:")
start = time.time()
arr_a = data['A']
arr_b = data['B']
result = arr_a[arr_b > 50].sum()
print(f"Time: {time.time() - start:.4f}s")

# Pandas approach
print("Pandas operation:")
start = time.time()
df = pd.DataFrame(data)
result = df[df['B'] > 50]['A'].sum()
print(f"Time: {time.time() - start:.4f}s")

# Polars approach (if installed: pip install polars)
try:
    import polars as pl
    print("Polars operation:")
    start = time.time()
    df_pl = pl.DataFrame(data)
    result = df_pl.filter(pl.col('B') > 50).select('A').sum()
    print(f"Time: {time.time() - start:.4f}s")
except ImportError:
    print("Polars not installed")

# General performance characteristics:
# NumPy: Fastest for pure numerical operations on homogeneous data
# Pandas: More flexible, handles mixed types, but slower
# Polars: Modern, optimized, often faster than Pandas for large data
# 
# When to use each:
# - NumPy: Scientific computing, linear algebra, large numerical arrays
# - Pandas: Data analysis, data cleaning, mixed data types
# - Polars: Big data processing, faster alternative to Pandas
```

---

## Real-World Scenarios

### Q23: How would you analyze sales data with multiple products and regions?
**Answer:**

```python
import pandas as pd
import numpy as np

# Create sample data
np.random.seed(42)
df = pd.DataFrame({
    'date': pd.date_range('2023-01-01', periods=365),
    'product': np.random.choice(['A', 'B', 'C'], 365),
    'region': np.random.choice(['East', 'West', 'South', 'North'], 365),
    'sales': np.random.randint(1000, 10000, 365),
    'quantity': np.random.randint(10, 100, 365)
})

# 1. Summary statistics by product
product_summary = df.groupby('product').agg({
    'sales': ['sum', 'mean', 'std'],
    'quantity': ['sum', 'mean']
})

# 2. Sales trend by region
regional_trend = df.groupby(['date', 'region'])['sales'].sum().unstack(fill_value=0)

# 3. Pivot table: Product vs Region
pivot = df.pivot_table(
    values='sales',
    index='product',
    columns='region',
    aggfunc='sum',
    margins=True
)

# 4. Top products
top_products = df.groupby('product')['sales'].sum().nlargest(3)

# 5. Month-over-month growth
df['month'] = df['date'].dt.to_period('M')
monthly_sales = df.groupby('month')['sales'].sum()
mom_growth = monthly_sales.pct_change()

# 6. Quarterly analysis
df['quarter'] = df['date'].dt.to_period('Q')
quarterly = df.groupby(['quarter', 'product'])['sales'].sum().unstack()

# 7. Average transaction value by region
df['transaction_value'] = df['sales'] / df['quantity']
avg_transaction = df.groupby('region')['transaction_value'].mean()

# 8. Identify anomalies
mean_sales = df['sales'].mean()
std_sales = df['sales'].std()
df['is_anomaly'] = (df['sales'] > mean_sales + 3*std_sales) | (df['sales'] < mean_sales - 3*std_sales)

print(f"Total sales: ${df['sales'].sum():,.2f}")
print(f"Average daily sales: ${df['sales'].mean():,.2f}")
print(f"Best product: {top_products.idxmax()}")
```

---

### Q24: How would you handle missing data in a time series and forecast?
**Answer:**

```python
import pandas as pd
import numpy as np

# Create time series with missing values
dates = pd.date_range('2023-01-01', periods=100, freq='D')
values = np.random.randn(100).cumsum()
# Introduce missing values
mask = np.random.random(100) < 0.1
values[mask] = np.nan

ts = pd.Series(values, index=dates)

# 1. Visualize missing data
print(f"Missing: {ts.isna().sum()} out of {len(ts)}")
print(f"Missing %: {ts.isna().sum() / len(ts) * 100:.2f}%")

# 2. Handle missing values
# Forward fill
ts_ffill = ts.fillna(method='ffill')

# Backward fill
ts_bfill = ts.fillna(method='bfill')

# Linear interpolation
ts_interp = ts.interpolate(method='linear')

# Spline interpolation
ts_spline = ts.interpolate(method='spline', order=2)

# 3. Simple moving average imputation
ts_ma = ts.fillna(ts.rolling(window=7, center=True).mean())

# 4. Check for seasonality
seasonal_pattern = ts.groupby(ts.index.dayofweek).mean()

# 5. Simple moving average forecast
# 10-day moving average
forecast_ma = ts_interp.rolling(window=10).mean().iloc[-1]

# 6. Exponential smoothing forecast
from pandas.tseries.seasonal import seasonal_decompose
decomposition = seasonal_decompose(ts_interp, model='additive', period=7)
trend = decomposition.trend
seasonal = decomposition.seasonal

# 7. Performance evaluation
def evaluate_imputation(original, imputed):
    rmse = np.sqrt((original - imputed)**2).mean()
    mae = (original - imputed).abs().mean()
    return {'RMSE': rmse, 'MAE': mae}

# 8. Best practice workflow
ts_clean = ts.interpolate(method='linear').fillna(method='bfill').fillna(method='ffill')
```

---

### Q25: How would you optimize a large data ETL pipeline in Pandas?
**Answer:**

```python
import pandas as pd
import numpy as np
from time import time

# ETL Pipeline Optimization

# 1. Read only necessary columns
def extract_data(filepath):
    return pd.read_csv(
        filepath,
        usecols=['date', 'product', 'sales', 'region'],  # Only needed columns
        dtype={'product': 'category', 'region': 'category'},  # Optimize dtypes
        parse_dates=['date']
    )

# 2. Transform with efficiency
def transform_data(df):
    # a) Filter early to reduce data
    df = df[df['sales'] > 0]
    
    # b) Use categorical for grouping columns
    df['product'] = df['product'].astype('category')
    df['region'] = df['region'].astype('category')
    
    # c) Vectorized operations
    df['revenue_adjusted'] = df['sales'] * 1.1  # 10% adjustment
    
    # d) Use column-specific operations
    df['month'] = df['date'].dt.to_period('M')
    
    # e) Drop unnecessary columns
    df = df.drop('unnecessary_col', axis=1)
    
    return df

# 3. Aggregate efficiently
def load_data(df):
    # Use groupby efficiently
    summary = df.groupby(['month', 'region']).agg({
        'sales': ['sum', 'mean', 'count'],
        'product': 'nunique'
    }, as_index=False)
    
    return summary

# 4. Full pipeline with chunking for large files
def etl_pipeline_chunked(filepath, chunksize=50000):
    results = []
    
    for chunk in pd.read_csv(
        filepath,
        chunksize=chunksize,
        usecols=['date', 'product', 'sales', 'region'],
        dtype={'product': 'category', 'region': 'category'},
        parse_dates=['date']
    ):
        # Transform each chunk
        chunk = transform_data(chunk)
        
        # Aggregate
        chunk_result = chunk.groupby(['month', 'region']).agg({
            'sales': ['sum', 'mean']
        })
        
        results.append(chunk_result)
    
    # Combine results
    final_result = pd.concat(results).groupby(level=[0, 1]).sum()
    return final_result

# 5. Performance monitoring
def measure_performance(func, *args):
    start = time()
    result = func(*args)
    elapsed = time() - start
    print(f"Execution time: {elapsed:.4f}s")
    return result

# 6. Memory optimization
def optimize_memory(df):
    for col in df.columns:
        col_type = df[col].dtype
        
        if col_type != 'object':
            c_min = df[col].min()
            c_max = df[col].max()
            
            if str(col_type)[:3] == 'int':
                if c_min > np.iinfo(np.int8).min and c_max < np.iinfo(np.int8).max:
                    df[col] = df[col].astype(np.int8)
                elif c_min > np.iinfo(np.int16).min and c_max < np.iinfo(np.int16).max:
                    df[col] = df[col].astype(np.int16)
                elif c_min > np.iinfo(np.int32).min and c_max < np.iinfo(np.int32).max:
                    df[col] = df[col].astype(np.int32)
            else:
                if c_min > np.finfo(np.float16).min and c_max < np.finfo(np.float16).max:
                    df[col] = df[col].astype(np.float16)
                elif c_min > np.finfo(np.float32).min and c_max < np.finfo(np.float32).max:
                    df[col] = df[col].astype(np.float32)
    
    return df

# 7. Best practices summary
"""
1. Read only needed columns
2. Optimize data types early
3. Filter data early in pipeline
4. Use categorical for repetitive string data
5. Vectorize operations (avoid apply when possible)
6. Use groupby + agg instead of loops
7. Drop unnecessary columns
8. Use chunking for large files
9. Monitor memory usage
10. Profile code to find bottlenecks
"""
```

---

## Summary Table

| Topic | Key Point | When to Use |
|-------|-----------|------------|
| NumPy Arrays | Homogeneous, fast, numerical | Scientific computing, linear algebra |
| Broadcasting | Operate on different shaped arrays | Element-wise operations |
| DataFrame | Heterogeneous, labeled 2D data | Data analysis, cleaning |
| Groupby | Aggregate by groups | Analysis by categories |
| Merge | Combine DataFrames | Data integration |
| Pivot Table | Reshape and summarize | Analysis, reporting |
| Missing Data | Handle nulls | Data cleaning |
| Time Series | DateTime index, resampling | Financial data, sensor data |
| Apply | Row/column functions | Custom operations |
| Optimization | Vectorize, chunk, type optimize | Large datasets, performance |

---

## Common Interview Follow-ups

**Q: What's the difference between .loc and .iloc?**
- `.loc`: Label-based indexing (uses index/column names)
- `.iloc`: Integer position-based indexing (0-based)

**Q: Explain categorical data in Pandas**
- Reduces memory by storing only unique values
- Useful for many repeated strings (e.g., 'M'/'F' repeated millions of times)
- Improves groupby performance

**Q: What is vectorization and why is it important?**
- Vectorization: Operating on entire arrays instead of element-by-element
- Important for: Speed (10-100x faster), readability, memory efficiency

**Q: How do you handle imbalanced classes in data?**
- Oversampling minority class
- Undersampling majority class
- SMOTE (Synthetic Minority Oversampling)
- Adjusting class weights in algorithms

---

## Additional Resources

- [NumPy Documentation](https://numpy.org/doc/)
- [Pandas Documentation](https://pandas.pydata.org/docs/)
- [100 NumPy Exercises](https://github.com/rougier/numpy-100)
- [Pandas Cheat Sheet](https://pandas.pydata.org/Pandas_Cheat_Sheet.pdf)
