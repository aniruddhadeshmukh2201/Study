# Matplotlib and Seaborn Interview Questions & Answers

## Table of Contents
1. [Matplotlib Fundamentals](#matplotlib-fundamentals)
2. [Matplotlib Advanced](#matplotlib-advanced)
3. [Seaborn Fundamentals](#seaborn-fundamentals)
4. [Seaborn Advanced](#seaborn-advanced)
5. [Real-World Visualizations](#real-world-visualizations)
6. [Best Practices & Performance](#best-practices--performance)

---

## Matplotlib Fundamentals

### Q1: What is Matplotlib and why is it important?
**Answer:**
Matplotlib is a Python plotting library for creating static, animated, and interactive visualizations. It provides:

- **Line plots, scatter plots, bar charts, histograms**
- **2D and 3D visualizations**
- **Customizable styling and formatting**
- **Publication-quality plots**
- **Multiple output formats** (PNG, PDF, SVG, etc.)

**Why important:**
- Foundation for data visualization in Python
- Used by Pandas, Seaborn, and other libraries
- Highly customizable
- Works in notebooks, scripts, and applications

```python
import matplotlib.pyplot as plt

# Simple plot
plt.plot([1, 2, 3, 4], [1, 4, 2, 3])
plt.xlabel('X-axis')
plt.ylabel('Y-axis')
plt.title('My First Plot')
plt.show()
```

---

### Q2: What are Figures and Axes in Matplotlib?
**Answer:**

| Component | Definition | Purpose |
|-----------|-----------|---------|
| **Figure** | Container holding everything | The whole plot window |
| **Axes** | The actual plot area | Where data is drawn |
| **Subplot** | Multiple Axes in one Figure | Grid of plots |

```python
import matplotlib.pyplot as plt

# Method 1: Simple (creates figure & axes automatically)
plt.plot([1, 2, 3])
plt.show()

# Method 2: Explicit (recommended for control)
fig, ax = plt.subplots()
ax.plot([1, 2, 3])
plt.show()

# Method 3: Multiple subplots
fig, axes = plt.subplots(2, 2, figsize=(10, 8))
axes[0, 0].plot([1, 2, 3])
axes[0, 1].scatter([1, 2, 3], [1, 4, 2])
axes[1, 0].bar(['A', 'B', 'C'], [10, 20, 15])
axes[1, 1].hist([1, 2, 2, 3, 3, 3, 4, 4, 4, 4])
plt.show()

# Understanding the hierarchy:
# Figure → contains → Axes (one or more)
#          ↓              ↓
#     Whole Plot    Individual Plot Area
```

---

### Q3: What are the different types of plots in Matplotlib?
**Answer:**

```python
import matplotlib.pyplot as plt
import numpy as np

# 1. Line Plot
plt.figure(figsize=(12, 8))

plt.subplot(2, 3, 1)
plt.plot([1, 2, 3, 4], [1, 4, 2, 3], 'o-', label='Line')
plt.legend()
plt.title('Line Plot')

# 2. Scatter Plot
plt.subplot(2, 3, 2)
plt.scatter([1, 2, 3, 4], [1, 4, 2, 3], s=100, alpha=0.5)
plt.title('Scatter Plot')

# 3. Bar Chart
plt.subplot(2, 3, 3)
plt.bar(['A', 'B', 'C'], [10, 20, 15])
plt.title('Bar Chart')

# 4. Histogram
plt.subplot(2, 3, 4)
data = np.random.randn(1000)
plt.hist(data, bins=30, edgecolor='black')
plt.title('Histogram')

# 5. Pie Chart
plt.subplot(2, 3, 5)
plt.pie([30, 25, 20, 25], labels=['A', 'B', 'C', 'D'])
plt.title('Pie Chart')

# 6. Box Plot
plt.subplot(2, 3, 6)
plt.boxplot([np.random.randn(100) for _ in range(3)])
plt.title('Box Plot')

plt.tight_layout()
plt.show()
```

---

### Q4: Explain different marker styles and line styles in Matplotlib.
**Answer:**

```python
import matplotlib.pyplot as plt

# Line styles
plt.figure(figsize=(12, 4))

plt.subplot(1, 2, 1)
x = [1, 2, 3, 4, 5]
y = [1, 4, 2, 3, 5]

# Line styles: '-' solid, '--' dashed, '-.' dash-dot, ':' dotted
plt.plot(x, y, '-', label='solid')
plt.plot(x, [i+0.5 for i in y], '--', label='dashed')
plt.plot(x, [i+1 for i in y], '-.', label='dash-dot')
plt.plot(x, [i+1.5 for i in y], ':', label='dotted')
plt.legend()
plt.title('Line Styles')

# Markers
plt.subplot(1, 2, 2)
markers = ['o', 's', '^', 'v', 'D', '*', 'p', 'H']
for i, marker in enumerate(markers):
    plt.scatter(i, 1, s=200, marker=marker, label=marker)
plt.legend(loc='upper left')
plt.title('Markers')
plt.ylim(0.5, 1.5)

plt.tight_layout()
plt.show()

# Common markers: 'o' circle, 's' square, '^' triangle, '*' star, 'D' diamond, 'x' cross
# Common line styles: '-' solid, '--' dashed, '-.' dash-dot, ':' dotted
```

---

### Q5: How do you customize plots in Matplotlib?
**Answer:**

```python
import matplotlib.pyplot as plt
import numpy as np

x = np.linspace(0, 10, 100)
y = np.sin(x)

fig, ax = plt.subplots(figsize=(10, 6))

# Plot line
ax.plot(x, y, linewidth=2, color='blue', label='sin(x)', marker='o', markersize=4)

# Customize axes
ax.set_xlabel('X-axis', fontsize=12, fontweight='bold')
ax.set_ylabel('Y-axis', fontsize=12, fontweight='bold')
ax.set_title('Customized Plot', fontsize=14, fontweight='bold')

# Set limits
ax.set_xlim(0, 10)
ax.set_ylim(-1.5, 1.5)

# Grid
ax.grid(True, alpha=0.3, linestyle='--')

# Legend
ax.legend(fontsize=10, loc='upper right')

# Tick customization
ax.set_xticks(np.arange(0, 11, 2))
ax.set_yticks(np.arange(-1, 2, 0.5))
ax.tick_params(axis='both', labelsize=10)

# Add text annotation
ax.text(5, 0.5, 'Peak', fontsize=12, ha='center', 
        bbox=dict(boxstyle='round', facecolor='yellow', alpha=0.5))

# Spine customization (remove top and right spines)
ax.spines['top'].set_visible(False)
ax.spines['right'].set_visible(False)

plt.tight_layout()
plt.show()
```

---

## Matplotlib Advanced

### Q6: What is the difference between the state-based and object-oriented API?
**Answer:**

```python
import matplotlib.pyplot as plt

# STATE-BASED API (pyplot)
# Simpler but less control
plt.figure()
plt.plot([1, 2, 3, 4])
plt.xlabel('X')
plt.ylabel('Y')
plt.title('State-Based API')
plt.show()

# OBJECT-ORIENTED API (recommended for complex plots)
# More control and flexibility
fig, ax = plt.subplots()
ax.plot([1, 2, 3, 4])
ax.set_xlabel('X')
ax.set_ylabel('Y')
ax.set_title('Object-Oriented API')
plt.show()

# Comparison:
# ┌─────────────────────┬──────────────────────────┬─────────────────────────┐
# │ Aspect              │ State-Based              │ Object-Oriented         │
# ├─────────────────────┼──────────────────────────┼─────────────────────────┤
# │ Simplicity          │ Simple (few lines)       │ More verbose            │
# │ Control             │ Limited                  │ Full control            │
# │ Multiple subplots   │ Complicated              │ Easy                    │
# │ Recommended for     │ Quick plots              │ Production code         │
# │ Performance         │ Slower for many plots    │ Faster                  │
# └─────────────────────┴──────────────────────────┴─────────────────────────┘

# Best practice: Use object-oriented API for any serious work
```

---

### Q7: How do you create and customize subplots?
**Answer:**

```python
import matplotlib.pyplot as plt
import numpy as np

x = np.linspace(0, 10, 100)

# Method 1: Using subplots()
fig, axes = plt.subplots(2, 2, figsize=(10, 8))

axes[0, 0].plot(x, np.sin(x))
axes[0, 0].set_title('sin(x)')

axes[0, 1].plot(x, np.cos(x))
axes[0, 1].set_title('cos(x)')

axes[1, 0].plot(x, np.tan(x))
axes[1, 0].set_title('tan(x)')

axes[1, 1].plot(x, x**2)
axes[1, 1].set_title('x^2')

plt.tight_layout()
plt.show()

# Method 2: Using GridSpec (advanced - different sizes)
import matplotlib.gridspec as gridspec

fig = plt.figure(figsize=(10, 8))
gs = gridspec.GridSpec(3, 3, figure=fig)

ax1 = fig.add_subplot(gs[0, :])       # Top row, all columns
ax2 = fig.add_subplot(gs[1, :-1])     # Middle row, first 2 columns
ax3 = fig.add_subplot(gs[1:, -1])     # Right column, last 2 rows

ax1.plot(x, np.sin(x))
ax1.set_title('Full Width')

ax2.scatter(x, np.cos(x))
ax2.set_title('2x2 Area')

ax3.bar(range(5), [1, 4, 2, 3, 5])
ax3.set_title('Full Height')

plt.tight_layout()
plt.show()

# Method 3: Spacing control
fig, axes = plt.subplots(2, 2, figsize=(10, 8))
plt.subplots_adjust(left=0.1, right=0.9, top=0.9, bottom=0.1, 
                    wspace=0.3, hspace=0.4)
# wspace = width space between subplots
# hspace = height space between subplots
plt.show()
```

---

### Q8: How do you save plots in different formats?
**Answer:**

```python
import matplotlib.pyplot as plt

fig, ax = plt.subplots()
ax.plot([1, 2, 3, 4], [1, 4, 2, 3])
ax.set_title('Save Plot Example')

# Save in different formats
fig.savefig('plot.png', dpi=300, bbox_inches='tight')     # PNG
fig.savefig('plot.pdf', dpi=300, bbox_inches='tight')     # PDF
fig.savefig('plot.svg', dpi=300, bbox_inches='tight')     # SVG
fig.savefig('plot.jpg', dpi=300, bbox_inches='tight')     # JPEG

# Parameters:
# dpi - resolution (higher = better quality but larger file)
# bbox_inches='tight' - removes extra whitespace
# facecolor='white' - background color
# transparent=True - transparent background

# DPI guidelines:
# 72 dpi - web/screen
# 150 dpi - presentations
# 300 dpi - print
# 600 dpi - high-quality print

plt.show()
```

---

### Q9: Explain colors and colormaps in Matplotlib.
**Answer:**

```python
import matplotlib.pyplot as plt
import numpy as np

fig, axes = plt.subplots(2, 2, figsize=(12, 10))

# 1. Named colors
ax = axes[0, 0]
colors = ['red', 'blue', 'green', 'yellow', 'purple']
ax.bar(range(len(colors)), [1]*len(colors), color=colors)
ax.set_title('Named Colors')

# 2. RGB colors (0-1 range)
ax = axes[0, 1]
x = np.linspace(0, 10, 100)
ax.plot(x, np.sin(x), color=(1, 0, 0))  # Red
ax.plot(x, np.cos(x), color=(0, 0, 1))  # Blue
ax.set_title('RGB Colors')

# 3. Hex colors
ax = axes[1, 0]
colors_hex = ['#FF0000', '#00FF00', '#0000FF']
ax.scatter(range(3), [1, 2, 3], s=200, c=colors_hex)
ax.set_title('Hex Colors')

# 4. Colormaps (for scatter/heatmap)
ax = axes[1, 1]
x = np.random.rand(100)
y = np.random.rand(100)
c = np.random.rand(100)
scatter = ax.scatter(x, y, c=c, cmap='viridis', s=50)
plt.colorbar(scatter, ax=ax)
ax.set_title('Colormap (viridis)')

plt.tight_layout()
plt.show()

# Popular colormaps:
# Perceptually uniform: 'viridis', 'plasma', 'inferno', 'magma', 'cividis'
# Sequential: 'Blues', 'Greens', 'Reds', 'Purples', 'Greys'
# Diverging: 'RdBu', 'RdYlBu', 'coolwarm', 'Spectral'
# Cyclic: 'twilight', 'hsv'
```

---

### Q10: How do you add annotations and text to plots?
**Answer:**

```python
import matplotlib.pyplot as plt
import numpy as np

x = np.linspace(0, 10, 100)
y = np.sin(x)

fig, ax = plt.subplots(figsize=(10, 6))
ax.plot(x, y, linewidth=2)

# 1. Text annotation
ax.text(5, 0.5, 'Peak at x=π/2', fontsize=12, ha='center',
        bbox=dict(boxstyle='round', facecolor='yellow', alpha=0.7))

# 2. Arrow annotation
ax.annotate('Local Max', xy=(np.pi/2, 1), xytext=(4, 1.3),
            fontsize=10, ha='center',
            arrowprops=dict(arrowstyle='->', color='red', lw=2))

# 3. Horizontal/Vertical lines
ax.axhline(y=0, color='gray', linestyle='--', alpha=0.5)
ax.axvline(x=np.pi, color='gray', linestyle='--', alpha=0.5)

# 4. Add rectangle/patch
from matplotlib.patches import Rectangle
rect = Rectangle((2, -0.5), 2, 0.5, 
                 linewidth=2, edgecolor='blue', facecolor='none')
ax.add_patch(rect)

# 5. Add legend
ax.plot(x, np.cos(x), label='cos(x)', alpha=0.7)
ax.legend(fontsize=10)

# 6. Grid
ax.grid(True, alpha=0.3)

ax.set_xlim(0, 10)
ax.set_ylim(-1.5, 1.5)
ax.set_xlabel('X', fontsize=12)
ax.set_ylabel('Y', fontsize=12)
ax.set_title('Annotations Example', fontsize=14)

plt.tight_layout()
plt.show()

# Annotation properties:
# ha = 'left', 'center', 'right' (horizontal alignment)
# va = 'top', 'center', 'bottom' (vertical alignment)
# fontsize, fontweight, color, etc.
```

---

## Seaborn Fundamentals

### Q11: What is Seaborn and how does it differ from Matplotlib?
**Answer:**

| Aspect | Matplotlib | Seaborn |
|--------|-----------|---------|
| Purpose | Low-level plotting | High-level statistical plotting |
| Default Style | Plain | Beautiful |
| Statistical Plots | Manual | Built-in |
| Learning Curve | Steep | Easy |
| Customization | Highly customizable | Less customizable |
| Built on | Standalone | Matplotlib |
| Best for | Any plot | Statistical visualization |

```python
import matplotlib.pyplot as plt
import seaborn as sns
import pandas as pd
import numpy as np

# Sample data
df = pd.DataFrame({
    'x': np.random.randn(100),
    'y': np.random.randn(100),
    'group': np.random.choice(['A', 'B', 'C'], 100)
})

# Matplotlib (basic)
plt.figure(figsize=(5, 4))
plt.scatter(df['x'], df['y'])
plt.xlabel('X')
plt.ylabel('Y')
plt.show()

# Seaborn (statistical)
plt.figure(figsize=(5, 4))
sns.regplot(x='x', y='y', data=df)  # Includes regression line + confidence interval
plt.show()

# Seaborn styling
sns.set_theme(style='darkgrid')  # Beautiful default style
sns.set_palette('husl')  # Nice color palette

# Difference: Seaborn automates common statistical visualization tasks
```

---

### Q12: What are the main plot types in Seaborn?
**Answer:**

```python
import seaborn as sns
import pandas as pd
import matplotlib.pyplot as plt

# Load sample dataset
tips = sns.load_dataset('tips')

fig, axes = plt.subplots(2, 3, figsize=(15, 10))

# 1. Scatter plot with regression
sns.regplot(x='total_bill', y='tip', data=tips, ax=axes[0, 0])
axes[0, 0].set_title('Regression Plot')

# 2. Distribution plot
sns.histplot(data=tips, x='total_bill', kde=True, ax=axes[0, 1])
axes[0, 1].set_title('Distribution Plot')

# 3. Box plot
sns.boxplot(x='day', y='total_bill', data=tips, ax=axes[0, 2])
axes[0, 2].set_title('Box Plot')

# 4. Violin plot
sns.violinplot(x='day', y='total_bill', data=tips, ax=axes[1, 0])
axes[1, 0].set_title('Violin Plot')

# 5. Bar plot
sns.barplot(x='day', y='total_bill', data=tips, ax=axes[1, 1])
axes[1, 1].set_title('Bar Plot')

# 6. Count plot
sns.countplot(x='day', data=tips, ax=axes[1, 2])
axes[1, 2].set_title('Count Plot')

plt.tight_layout()
plt.show()

# Other important plots:
# - heatmap(): For correlation matrices
# - scatterplot(): Enhanced scatter plot
# - lineplot(): Statistical line plot
# - pairplot(): Pairwise relationships
# - clustermap(): Hierarchical clustering
```

---

### Q13: How do you work with categorical data in Seaborn?
**Answer:**

```python
import seaborn as sns
import pandas as pd
import matplotlib.pyplot as plt

tips = sns.load_dataset('tips')

fig, axes = plt.subplots(2, 2, figsize=(12, 10))

# 1. Bar plot by category
sns.barplot(x='day', y='total_bill', hue='sex', data=tips, ax=axes[0, 0])
axes[0, 0].set_title('Bar Plot with Hue')

# 2. Violin plot with multiple categories
sns.violinplot(x='day', y='total_bill', hue='sex', data=tips, 
               split=True, ax=axes[0, 1])
axes[0, 1].set_title('Violin Plot Split by Category')

# 3. Strip plot (scatter for categorical)
sns.stripplot(x='day', y='total_bill', hue='sex', data=tips, 
              size=8, alpha=0.6, ax=axes[1, 0])
axes[1, 0].set_title('Strip Plot')

# 4. Swarm plot (non-overlapping points)
sns.swarmplot(x='day', y='total_bill', hue='sex', data=tips, ax=axes[1, 1])
axes[1, 1].set_title('Swarm Plot')

plt.tight_layout()
plt.show()

# Key parameters:
# hue - color by group
# dodge - separate bars/plots by group
# split - split violin/box by group
# palette - color palette
# order - order of categories
```

---

### Q14: How do you create heatmaps and correlation plots?
**Answer:**

```python
import seaborn as sns
import pandas as pd
import matplotlib.pyplot as plt
import numpy as np

# Load sample data
iris = sns.load_dataset('iris')
df = iris.drop('species', axis=1)

fig, axes = plt.subplots(1, 2, figsize=(14, 5))

# 1. Correlation heatmap
corr = df.corr()
sns.heatmap(corr, annot=True, fmt='.2f', cmap='coolwarm', 
            center=0, square=True, ax=axes[0],
            cbar_kws={'label': 'Correlation'})
axes[0].set_title('Correlation Heatmap')

# 2. Clustermap (hierarchical clustering)
# This creates a new figure, so we use it separately
plt.figure(figsize=(8, 6))
g = sns.clustermap(corr, annot=True, fmt='.2f', cmap='coolwarm', center=0)
plt.suptitle('Clustermap with Hierarchical Clustering', y=0.98)
plt.show()

# Heatmap parameters:
# annot - show values
# fmt - format of annotations
# cmap - colormap
# vmin, vmax - value range
# cbar - show colorbar
# square - make cells square
```

---

### Q15: What are pairplots and relational plots?
**Answer:**

```python
import seaborn as sns
import pandas as pd
import matplotlib.pyplot as plt

iris = sns.load_dataset('iris')
tips = sns.load_dataset('tips')

# 1. Pairplot - all pairwise relationships
g = sns.pairplot(iris, hue='species', diag_kind='kde')
g.fig.suptitle('Pairplot: Iris Dataset', y=1.00)
plt.show()

# 2. Relational plot - flexible bivariate + multivariate
plt.figure(figsize=(10, 6))
sns.relplot(x='total_bill', y='tip', hue='sex', size='size',
            col='time', data=tips, height=5)
plt.show()

# 3. Joint plot - marginal distributions
g = sns.jointplot(x='total_bill', y='tip', data=tips, kind='hex')
g.fig.suptitle('Joint Plot with Hex Bins', y=0.98)
plt.show()

# Parameters:
# hue - color by group
# size - size by variable
# col - create separate plots by group (columns)
# row - create separate plots by group (rows)
# kind - 'scatter', 'kde', 'hex', 'reg', 'hist2d'
```

---

## Seaborn Advanced

### Q16: How do you customize Seaborn plots?
**Answer:**

```python
import seaborn as sns
import pandas as pd
import matplotlib.pyplot as plt

tips = sns.load_dataset('tips')

# 1. Set style and palette
sns.set_theme(style='whitegrid')  # style: whitegrid, darkgrid, white, dark, ticks
sns.set_palette('Set2')           # palette: deep, muted, pastel, colorblind, etc.

fig, axes = plt.subplots(2, 2, figsize=(12, 10))

# 2. Custom palette
custom_palette = ['#FF6B6B', '#4ECDC4', '#45B7D1']
sns.barplot(x='day', y='total_bill', data=tips, palette=custom_palette, ax=axes[0, 0])
axes[0, 0].set_title('Custom Palette')

# 3. Context (scale of plot elements)
sns.set_context('paper')    # paper, notebook, talk, poster (from small to large)
sns.scatterplot(x='total_bill', y='tip', data=tips, ax=axes[0, 1])
axes[0, 1].set_title('Context: Paper')

# 4. Despine (remove spines)
sns.despine(ax=axes[1, 0])  # Remove top and right spines
sns.barplot(x='day', y='total_bill', data=tips, ax=axes[1, 0])
axes[1, 0].set_title('Despined Plot')

# 5. Rotate labels
ax = axes[1, 1]
sns.countplot(x='day', data=tips, ax=ax)
plt.setp(ax.xaxis.get_majorticklabels(), rotation=45)
ax.set_title('Rotated Labels')

plt.tight_layout()
plt.show()

# Styles: whitegrid (default), darkgrid, white, dark, ticks
# Contexts: paper (small), notebook (medium), talk (large), poster (largest)
# Palettes: deep, muted, pastel, bright, dark, colorblind, Set1, Set2, etc.
```

---

### Q17: How do you create faceted plots in Seaborn?
**Answer:**

```python
import seaborn as sns
import pandas as pd
import matplotlib.pyplot as plt

tips = sns.load_dataset('tips')

# 1. Using catplot with col (columns)
g = sns.catplot(x='day', y='total_bill', col='sex', 
                data=tips, kind='box', height=5, aspect=0.8)
g.fig.suptitle('Faceted by Sex (Columns)', y=1.00)
plt.show()

# 2. Using catplot with row and col
g = sns.catplot(x='day', y='total_bill', col='sex', row='time',
                data=tips, kind='bar', height=4, aspect=0.8)
g.fig.suptitle('Faceted by Sex and Time', y=1.00)
plt.show()

# 3. Using relplot for continuous data
g = sns.relplot(x='total_bill', y='tip', col='day', row='sex',
                data=tips, height=4, aspect=0.9)
g.fig.suptitle('Relational Faceted Plot', y=1.00)
plt.show()

# 4. Control order of facets
g = sns.catplot(x='day', y='total_bill', col='sex',
                data=tips, col_order=['Male', 'Female'],
                height=5, aspect=0.8)
plt.show()

# Parameters:
# col - create subplots by column variable
# row - create subplots by row variable
# col_order, row_order - order of subplots
# height, aspect - size of each subplot
# kind - plot type (for catplot)
```

---

### Q18: How do you use FacetGrid for complex layouts?
**Answer:**

```python
import seaborn as sns
import pandas as pd
import matplotlib.pyplot as plt

tips = sns.load_dataset('tips')

# 1. Basic FacetGrid
g = sns.FacetGrid(tips, col='day', row='sex', height=4, aspect=0.9)
g.map(sns.scatterplot, 'total_bill', 'tip')
g.fig.suptitle('FacetGrid: Day and Sex', y=0.98)
plt.show()

# 2. Different plot types per facet
g = sns.FacetGrid(tips, col='time', height=5, aspect=0.8)
g.map(sns.histplot, 'total_bill', kde=True)
g.set_axis_labels('Total Bill', 'Count')
plt.show()

# 3. Hue within facets
g = sns.FacetGrid(tips, col='day', hue='sex', height=5, aspect=0.8)
g.map(sns.scatterplot, 'total_bill', 'tip')
g.add_legend()
g.fig.suptitle('FacetGrid with Hue', y=0.98)
plt.show()

# 4. Custom column order
g = sns.FacetGrid(tips, col='day', col_order=['Thurs', 'Fri', 'Sat', 'Sun'],
                  height=5, aspect=0.8)
g.map(sns.barplot, 'sex', 'total_bill')
plt.show()

# FacetGrid is most flexible for complex multi-plot layouts
```

---

### Q19: How do you create time series plots in Seaborn?
**Answer:**

```python
import seaborn as sns
import pandas as pd
import matplotlib.pyplot as plt
import numpy as np

# Create sample time series data
dates = pd.date_range('2023-01-01', periods=100)
df = pd.DataFrame({
    'date': dates,
    'value_A': np.cumsum(np.random.randn(100)),
    'value_B': np.cumsum(np.random.randn(100)),
    'value_C': np.cumsum(np.random.randn(100)),
    'category': np.random.choice(['X', 'Y'], 100)
})

fig, axes = plt.subplots(2, 2, figsize=(14, 10))

# 1. Line plot with hue
ax = axes[0, 0]
df_melted = df.melt(id_vars='date', 
                     value_vars=['value_A', 'value_B', 'value_C'],
                     var_name='Series', value_name='Value')
sns.lineplot(x='date', y='Value', hue='Series', data=df_melted, ax=ax)
ax.set_title('Line Plot with Multiple Series')

# 2. Area plot (stacked)
ax = axes[0, 1]
df.set_index('date')[['value_A', 'value_B', 'value_C']].plot(kind='area', ax=ax)
ax.set_title('Area Plot')

# 3. Rolling mean
ax = axes[1, 0]
df['rolling_mean'] = df['value_A'].rolling(window=7).mean()
sns.lineplot(x='date', y='value_A', data=df, label='Original', ax=ax)
sns.lineplot(x='date', y='rolling_mean', data=df, label='7-day MA', ax=ax)
ax.set_title('Line Plot with Rolling Mean')
ax.legend()

# 4. Categorical time series
ax = axes[1, 1]
sns.lineplot(x='date', y='value_A', hue='category', data=df, ax=ax)
ax.set_title('Time Series by Category')

plt.tight_layout()
plt.show()
```

---

### Q20: How do you perform statistical estimation in Seaborn plots?
**Answer:**

```python
import seaborn as sns
import pandas as pd
import matplotlib.pyplot as plt

tips = sns.load_dataset('tips')

fig, axes = plt.subplots(2, 2, figsize=(12, 10))

# 1. Regression plot (linear model)
sns.regplot(x='total_bill', y='tip', data=tips, ax=axes[0, 0],
            scatter_kws={'alpha': 0.5}, line_kws={'color': 'red'})
axes[0, 0].set_title('Regression Plot with Confidence Interval')

# 2. Low-LOESS fit (non-parametric)
sns.regplot(x='total_bill', y='tip', data=tips, ax=axes[0, 1],
            lowess=True, scatter_kws={'alpha': 0.5})
axes[0, 1].set_title('LOESS Smoothed Fit')

# 3. Robust regression
from scipy import stats
sns.regplot(x='total_bill', y='tip', data=tips, ax=axes[1, 0],
            robust=True, scatter_kws={'alpha': 0.5})
axes[1, 0].set_title('Robust Regression')

# 4. KDE (kernel density estimate)
sns.scatterplot(x='total_bill', y='tip', data=tips, ax=axes[1, 1], alpha=0.4)
sns.kdeplot(x='total_bill', y='tip', data=tips, ax=axes[1, 1], levels=5)
axes[1, 1].set_title('Scatter with KDE Contours')

plt.tight_layout()
plt.show()

# Statistical options:
# regplot() - regression with confidence interval
# lmplot() - regression on FacetGrid
# lowess - non-parametric smoother
# robust - resistant to outliers
# order - polynomial degree
```

---

## Real-World Visualizations

### Q21: How would you visualize sales data dashboard?
**Answer:**

```python
import seaborn as sns
import pandas as pd
import matplotlib.pyplot as plt
import numpy as np

# Create sample sales data
np.random.seed(42)
dates = pd.date_range('2023-01-01', periods=365)
sales_df = pd.DataFrame({
    'date': dates,
    'sales': np.cumsum(np.random.randint(100, 500, 365)),
    'region': np.random.choice(['East', 'West', 'North', 'South'], 365),
    'product': np.random.choice(['A', 'B', 'C'], 365),
    'quantity': np.random.randint(1, 100, 365)
})

# Create dashboard
fig = plt.figure(figsize=(16, 12))
gs = fig.add_gridspec(3, 3, hspace=0.3, wspace=0.3)

# 1. Sales trend over time (top, spanning 2 columns)
ax1 = fig.add_subplot(gs[0, :2])
sns.lineplot(x='date', y='sales', data=sales_df, ax=ax1, linewidth=2)
ax1.set_title('Sales Trend Over Time', fontsize=12, fontweight='bold')
ax1.set_ylabel('Cumulative Sales ($)')

# 2. Sales by region (top right)
ax2 = fig.add_subplot(gs[0, 2])
region_sales = sales_df.groupby('region')['sales'].max()
sns.barplot(x=region_sales.index, y=region_sales.values, ax=ax2, palette='Set2')
ax2.set_title('Sales by Region', fontsize=12, fontweight='bold')
ax2.set_ylabel('Total Sales ($)')

# 3. Product distribution (middle left)
ax3 = fig.add_subplot(gs[1, 0])
sns.countplot(x='product', data=sales_df, ax=ax3, palette='husl')
ax3.set_title('Product Distribution', fontsize=12, fontweight='bold')

# 4. Sales by product (middle center)
ax4 = fig.add_subplot(gs[1, 1])
product_sales = sales_df.groupby('product').size()
colors = sns.color_palette('husl', len(product_sales))
ax4.pie(product_sales, labels=product_sales.index, autopct='%1.1f%%',
        colors=colors, startangle=90)
ax4.set_title('Sales Share by Product', fontsize=12, fontweight='bold')

# 5. Quantity distribution (middle right)
ax5 = fig.add_subplot(gs[1, 2])
sns.histplot(data=sales_df, x='quantity', kde=True, ax=ax5, bins=20)
ax5.set_title('Quantity Distribution', fontsize=12, fontweight='bold')

# 6. Heatmap: Region vs Product (bottom, spanning 2 columns)
ax6 = fig.add_subplot(gs[2, :2])
pivot_data = sales_df.pivot_table(values='sales', index='region', 
                                   columns='product', aggfunc='sum')
sns.heatmap(pivot_data, annot=True, fmt='.0f', cmap='YlOrRd', ax=ax6, cbar_kws={'label': 'Sales ($)'})
ax6.set_title('Sales Heatmap: Region vs Product', fontsize=12, fontweight='bold')

# 7. Box plot: Sales by region (bottom right)
ax7 = fig.add_subplot(gs[2, 2])
sns.boxplot(x='region', y='sales', data=sales_df, ax=ax7, palette='Set2')
ax7.set_title('Sales Distribution by Region', fontsize=12, fontweight='bold')

plt.suptitle('Sales Dashboard', fontsize=16, fontweight='bold', y=0.995)
plt.show()
```

---

### Q22: How would you visualize model performance metrics?
**Answer:**

```python
import seaborn as sns
import pandas as pd
import matplotlib.pyplot as plt
import numpy as np

# Create sample model performance data
models = ['Model A', 'Model B', 'Model C', 'Model D']
metrics_data = pd.DataFrame({
    'Model': models * 4,
    'Metric': ['Accuracy'] * 4 + ['Precision'] * 4 + ['Recall'] * 4 + ['F1-Score'] * 4,
    'Score': [0.85, 0.88, 0.92, 0.87,
              0.83, 0.86, 0.91, 0.85,
              0.84, 0.89, 0.93, 0.88,
              0.83, 0.87, 0.92, 0.86]
})

# Confusion matrices
confusion_matrices = {
    'Model A': [[85, 15], [10, 90]],
    'Model B': [[88, 12], [8, 92]],
    'Model C': [[92, 8], [5, 95]],
}

fig = plt.figure(figsize=(16, 10))
gs = fig.add_gridspec(2, 2, hspace=0.3, wspace=0.3)

# 1. Model performance comparison (bar plot)
ax1 = fig.add_subplot(gs[0, 0])
sns.barplot(x='Model', y='Score', hue='Metric', data=metrics_data, ax=ax1)
ax1.set_title('Model Performance Comparison', fontsize=12, fontweight='bold')
ax1.set_ylim([0, 1])
ax1.legend(loc='lower right')

# 2. Performance across metrics (violin plot)
ax2 = fig.add_subplot(gs[0, 1])
sns.violinplot(x='Metric', y='Score', data=metrics_data, ax=ax2, palette='Set2')
ax2.set_title('Score Distribution by Metric', fontsize=12, fontweight='bold')
ax2.set_ylim([0.8, 1])

# 3. Model A Confusion Matrix
ax3 = fig.add_subplot(gs[1, 0])
cm = np.array(confusion_matrices['Model A'])
sns.heatmap(cm, annot=True, fmt='d', cmap='Blues', ax=ax3, cbar=False,
            xticklabels=['Negative', 'Positive'],
            yticklabels=['Negative', 'Positive'])
ax3.set_title('Model A Confusion Matrix', fontsize=12, fontweight='bold')
ax3.set_ylabel('True Label')
ax3.set_xlabel('Predicted Label')

# 4. Model C Confusion Matrix (best model)
ax4 = fig.add_subplot(gs[1, 1])
cm = np.array(confusion_matrices['Model C'])
sns.heatmap(cm, annot=True, fmt='d', cmap='Greens', ax=ax4, cbar=False,
            xticklabels=['Negative', 'Positive'],
            yticklabels=['Negative', 'Positive'])
ax4.set_title('Model C Confusion Matrix (Best)', fontsize=12, fontweight='bold')
ax4.set_ylabel('True Label')
ax4.set_xlabel('Predicted Label')

plt.suptitle('Model Performance Analysis', fontsize=16, fontweight='bold', y=0.995)
plt.show()
```

---

### Q23: How would you visualize distribution comparisons?
**Answer:**

```python
import seaborn as sns
import pandas as pd
import matplotlib.pyplot as plt
import numpy as np

# Create sample data
np.random.seed(42)
df = pd.DataFrame({
    'group_A': np.random.normal(100, 15, 1000),
    'group_B': np.random.normal(110, 20, 1000),
    'group_C': np.random.normal(95, 12, 1000)
})

# Reshape for seaborn
data_melted = pd.DataFrame({
    'value': np.concatenate([df['group_A'], df['group_B'], df['group_C']]),
    'group': ['A']*1000 + ['B']*1000 + ['C']*1000
})

fig, axes = plt.subplots(2, 2, figsize=(14, 10))

# 1. Histogram with KDE
ax = axes[0, 0]
for group in ['A', 'B', 'C']:
    sns.histplot(data=data_melted[data_melted['group']==group], x='value',
                kde=True, label=f'Group {group}', ax=ax, alpha=0.5)
ax.set_title('Distribution Comparison (Histogram)', fontsize=12, fontweight='bold')
ax.legend()

# 2. KDE plot
ax = axes[0, 1]
for group in ['A', 'B', 'C']:
    sns.kdeplot(data=data_melted[data_melted['group']==group], x='value',
               label=f'Group {group}', ax=ax, linewidth=2)
ax.set_title('Distribution Comparison (KDE)', fontsize=12, fontweight='bold')
ax.legend()

# 3. Box plot
ax = axes[1, 0]
sns.boxplot(x='group', y='value', data=data_melted, ax=ax, palette='Set2')
ax.set_title('Distribution Comparison (Box Plot)', fontsize=12, fontweight='bold')

# 4. Violin plot
ax = axes[1, 1]
sns.violinplot(x='group', y='value', data=data_melted, ax=ax, palette='Set2')
ax.set_title('Distribution Comparison (Violin Plot)', fontsize=12, fontweight='bold')

plt.tight_layout()
plt.show()
```

---

## Best Practices & Performance

### Q24: How do you optimize plot performance for large datasets?
**Answer:**

```python
import matplotlib.pyplot as plt
import pandas as pd
import numpy as np
from matplotlib.colors import LogNorm

# Sample large dataset
n = 1_000_000
df = pd.DataFrame({
    'x': np.random.randn(n),
    'y': np.random.randn(n),
    'z': np.random.randint(0, 100, n)
})

fig, axes = plt.subplots(2, 2, figsize=(12, 10))

# 1. Use scatter instead of plot for many points
ax = axes[0, 0]
ax.scatter(df['x'][:10000], df['y'][:10000], alpha=0.1, s=1)
ax.set_title('Scatter Plot (sampled data)')

# 2. Use hexbin for very large datasets
ax = axes[0, 1]
hex_plot = ax.hexbin(df['x'], df['y'], gridsize=30, cmap='viridis')
ax.set_title('Hexbin Plot (density)')
plt.colorbar(hex_plot, ax=ax)

# 3. Use rasterized for many lines
ax = axes[1, 0]
for i in range(100):
    ax.plot(df['x'][i*1000:(i+1)*1000], df['y'][i*1000:(i+1)*1000], 
            rasterized=True, linewidth=0.5, alpha=0.3)
ax.set_title('Rasterized Lines')

# 4. Use 2D histogram
ax = axes[1, 1]
h = ax.hist2d(df['x'], df['y'], bins=50, cmap='viridis')
ax.set_title('2D Histogram')
plt.colorbar(h[3], ax=ax)

plt.tight_layout()
plt.show()

# Performance tips:
# - Use hexbin for very large scatter plots (100k+ points)
# - Use hist2d for dense point clouds
# - Rasterize complex plots to reduce file size
# - Sample data for exploration, use all data for final output
# - Use appropriate marker sizes and transparency
```

---

### Q25: Best practices for publication-quality figures?
**Answer:**

```python
import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

# Set publication-quality defaults
plt.rcParams['figure.dpi'] = 100
plt.rcParams['savefig.dpi'] = 300
plt.rcParams['font.size'] = 11
plt.rcParams['font.family'] = 'sans-serif'
plt.rcParams['axes.labelsize'] = 12
plt.rcParams['axes.titlesize'] = 14
plt.rcParams['xtick.labelsize'] = 10
plt.rcParams['ytick.labelsize'] = 10
plt.rcParams['legend.fontsize'] = 10
plt.rcParams['lines.linewidth'] = 1.5
plt.rcParams['lines.markersize'] = 6

# Sample data
x = np.linspace(0, 10, 100)
y1 = np.sin(x)
y2 = np.cos(x)

fig, ax = plt.subplots(figsize=(8, 6))

# Plot with publication-quality formatting
ax.plot(x, y1, 'o-', label='sin(x)', linewidth=2, markersize=4)
ax.plot(x, y2, 's-', label='cos(x)', linewidth=2, markersize=4)

# Labels and title
ax.set_xlabel('Time (seconds)', fontsize=12, fontweight='bold')
ax.set_ylabel('Amplitude', fontsize=12, fontweight='bold')
ax.set_title('Publication-Quality Figure', fontsize=14, fontweight='bold', pad=20)

# Grid
ax.grid(True, alpha=0.3, linestyle='--', linewidth=0.5)

# Legend
ax.legend(loc='upper right', frameon=True, shadow=True, fontsize=10)

# Remove top and right spines
ax.spines['top'].set_visible(False)
ax.spines['right'].set_visible(False)

# Tight layout
plt.tight_layout()

# Save with high quality
plt.savefig('publication_figure.png', dpi=300, bbox_inches='tight', facecolor='white')
plt.savefig('publication_figure.pdf', dpi=300, bbox_inches='tight', facecolor='white')
plt.show()

# Publication checklist:
# ✓ Clear, descriptive labels
# ✓ Legend with clear notation
# ✓ Appropriate color palette (colorblind-friendly)
# ✓ High DPI (300+ for print)
# ✓ Readable font sizes
# ✓ No cluttered elements
# ✓ Remove unnecessary spines
# ✓ Save in multiple formats (PNG, PDF)
# ✓ Consistent styling across plots
# ✓ Appropriate figure size
```

---

## Comparison: Matplotlib vs Seaborn

| Task | Matplotlib | Seaborn |
|------|-----------|---------|
| Line plot | `ax.plot()` | `sns.lineplot()` |
| Scatter plot | `ax.scatter()` | `sns.scatterplot()` |
| Categorical | `ax.bar()` + manual hue | `sns.barplot()` + hue |
| Distribution | `ax.hist()` + manual | `sns.histplot()` with kde |
| Correlation | Manual + `ax.imshow()` | `sns.heatmap()` |
| Regression | `ax.plot()` + manual fit | `sns.regplot()` |
| Multiple plots | GridSpec (complex) | FacetGrid (simple) |
| Styling | Manual settings | `sns.set_theme()` |

---

## Summary

**Choose Matplotlib if:**
- You need full customization
- Creating publication-quality plots
- Building interactive applications
- Working with non-standard plot types

**Choose Seaborn if:**
- Doing statistical visualization
- Working with DataFrames
- Need beautiful defaults
- Creating exploratory plots
- Want less boilerplate code

**Best practice:** Use Seaborn for exploration, Matplotlib for production and customization!

---

## Additional Resources

- [Matplotlib Documentation](https://matplotlib.org/)
- [Seaborn Documentation](https://seaborn.pydata.org/)
- [Matplotlib Gallery](https://matplotlib.org/stable/gallery/index.html)
- [Seaborn Gallery](https://seaborn.pydata.org/examples.html)
- [Colorbrewer Palettes](https://colorbrewer2.org/)
